package practica3.Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.funcionesConsultor.PagoConsulta;
import practica3.funcionesConsultor.PagoHospitalizacion;
import practica3.funcionesFarmaceutico.VentaMedicamentos;
import practica3.funcionesMedico.GeneracionFactura;
import practica3.objetos.Facturas;
import practica3.objetos.SesionEmpleados;
import practica3.objetos.VentasFactura;

/**
 *
 * @author luisGonzalez
 */
public class ControladorFacturas extends HttpServlet {

    private Facturas factura;
    private final PagoConsulta pago = new PagoConsulta();
    private final GeneracionFactura generacion = new GeneracionFactura();
    private final VentaMedicamentos venta = new VentaMedicamentos();
    private final PagoHospitalizacion pago2 = new PagoHospitalizacion();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        boolean verificador = false;
        int numFilas = 0;
        //agrega el total de filas iniciales dentro de la factura
        session.setAttribute("numFilas", numFilas);
        //interruptor para calcular el total unitario por medicamento
        session.setAttribute("verificador", verificador);
        request.getRequestDispatcher("orden-compra-consulta.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
        int id = sesion.getId();        
        int filas, idFactura;
        boolean verificacion, checkbox, iniciador;
        try {
            switch (accion) {
                case "Pagar consulta":
                    int idEmpleadoMedico = (int) session.getAttribute("idMedico");
                    float totalCancelar = pago.calculoTotalConsulta();
                    int idCita = (int) session.getAttribute("idConsulta");
                    factura = new Facturas();
                    factura.setNombres(request.getParameter("nombres"));
                    factura.setApellidos(request.getParameter("apellidos"));
                    factura.setCiudad(request.getParameter("ciudad"));
                    factura.setFecha_factura(Date.valueOf(request.getParameter("fechaPago")));
                    factura.setEstado("CANCELADA");
                    factura.setTipo("CONSULTA");
                    factura.setNit(Integer.parseInt(request.getParameter("nit")));
                    factura.setId_empleado_medico(idEmpleadoMedico);
                    factura.setTotal(totalCancelar);
                    pago.pagoCita(factura, id);
                    pago.cancelarConsulta(idCita);
                    request.getRequestDispatcher("consultas-por-pagar.jsp").forward(request, response);
                    break;
                case "Numero de filas":
                    int numFilas = Integer.parseInt(request.getParameter("numeroFilas"));
                    int cambio = numFilas;
                    //cambia el numero de filas por las ingresadas por el usuario
                    session.setAttribute("numFilas", cambio);
                    request.getRequestDispatcher("orden-compra-consulta.jsp").forward(request, response);
                    break;
                case "Calcular totales":
                    filas = (int) session.getAttribute("numFilas");
                    //cambio del interruptor para el total unitario por medicamento
                    boolean verificador = true;
                    session.setAttribute("verificador", verificador);
                    float cant = 0;
                    generacion.totalFactura(cant, request, response, filas, session);
                    request.getRequestDispatcher("orden-compra-consulta.jsp").forward(request, response);
                    break;
                case "Crear factura":
                    filas = (int) session.getAttribute("numFilas");
                    int idMedico = Integer.parseInt(request.getParameter("idMedico"));
                    factura = (Facturas) session.getAttribute("datosPersonales");
                    //creacion de factura
                    generacion.crearFactura(factura, idMedico, id);
                    //creacion de metodos por factura
                    generacion.crearEventosFactura(filas, session);
                    request.getRequestDispatcher("perfil-medicos.jsp").forward(request, response);
                    break;
                case "Ver compras":
                    idFactura = Integer.parseInt(request.getParameter("id"));
                    session.setAttribute("idFactura", idFactura);
                    request.getRequestDispatcher("ventas-desglosadas.jsp").forward(request, response);
                    break;
                case "Ver compra":
                    idFactura = Integer.parseInt(request.getParameter("id"));
                    verificacion = false;
                    iniciador = false;
                    session.setAttribute("iniciador", iniciador);
                    session.setAttribute("verificador", verificacion);
                    session.setAttribute("idFactura", idFactura);                    
                    request.getRequestDispatcher("confirmacion-venta.jsp").forward(request, response);
                    break;
                case "Total final":
                    filas = (int) session.getAttribute("filas");
                    verificacion = true;
                    iniciador = true;
                    session.setAttribute("iniciador", iniciador);
                    session.setAttribute("verificador", verificacion);
                    VentasFactura ventas = (VentasFactura) session.getAttribute("ventas");
                    venta.calculoTotal(filas, request, response, ventas, session);
                    request.getRequestDispatcher("confirmacion-venta.jsp").forward(request, response);
                    break;
                case "Efectuar compra":
                    float totalFinal = Float.valueOf(request.getParameter("totalFinal"));
                    filas = (int) session.getAttribute("filas");
                    idFactura = (int) session.getAttribute("idFactura");
                    venta.confirmarCompra(idFactura, totalFinal, filas, request, session, id);
                    request.getRequestDispatcher("ventas-prestablecidas.jsp").forward(request, response);
                    break;
                case "Cancelar cuenta":
                    idFactura = Integer.parseInt(request.getParameter("id"));
                    pago2.actualizarEstado(idFactura);
                    pago2.actualizarEmpleado(idFactura, id);
                    request.getRequestDispatcher("hospitalizaciones-por-pagar.jsp").forward(request, response);            
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorFacturas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
