package practica3.Controladores;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.funcionesMedico.CirugiaPaciente;
import practica3.funcionesRecursosHumanos.NuevoTipoCirugia;
import practica3.objetos.Cirugias;

/**
 *
 * @author luisGonzalez
 */
public class ControladorCirugias extends HttpServlet {

    private static final String DATO_TARIFA = "SELECT * FROM Tarifas_cirugias WHERE descripcion = ?";
    private static final String DATO_COSTOS = "SELECT * FROM Costos_cirugias WHERE descripcion = ?";
    private final CirugiaPaciente cirugia = new CirugiaPaciente();
    private final NuevoTipoCirugia tipo = new NuevoTipoCirugia();
    private Cirugias nuevo;
   
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorCirugias</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCirugias at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        int numFilas, idCirugia, idAsignacion;
        try {

            switch (accion) {
                case "Registrar operacion en proceso":
                    int idHistorial = (int) session.getAttribute("idHMedico");
                    String operacion = request.getParameter("descripcion");
                    Date fechaCirugia = Date.valueOf(request.getParameter("fecha"));
                    cirugia.crearNuevaOperacion(idHistorial, operacion, fechaCirugia);
                    request.getRequestDispatcher("asignacion-medicos-cirugia.jsp").forward(request, response);
                    break;
                case "Asignar medicos":
                    numFilas = (int) session.getAttribute("filas");
                    cirugia.asignarMedicos(numFilas, request, session);
                    request.getRequestDispatcher("pacientes-medico.jsp").forward(request, response);
                    break;
                case "Marcar como finalizada":
                    nuevo = new Cirugias();
                    idCirugia = Integer.parseInt(request.getParameter("id"));
                    nuevo.setId_historial_medico((int) session.getAttribute("idHMedico"));
                    nuevo.setFecha_cirugia(cirugia.obtenerFechaCirugia(idCirugia));
                    nuevo.setTipo_operacion(cirugia.obtenerNombreCirugia(idCirugia));
                    nuevo.setPrecio_cirugia(cirugia.calcularTotales(nuevo.getTipo_operacion(), DATO_TARIFA));  
                    nuevo.setCosto_cirugia(cirugia.calcularTotales(nuevo.getTipo_operacion(), DATO_COSTOS));
                    cirugia.finalizarCirugia(idCirugia);
                    cirugia.crearEventoCirugia(idCirugia, nuevo);
                    cirugia.crearCostoCirugia(nuevo);
                    cirugia.asignarPagoMedicos(idCirugia, nuevo);
                    request.getRequestDispatcher("cirugias-activas.jsp").forward(request, response);
                    break;
                case "Asignaciones":
                    idCirugia = Integer.parseInt(request.getParameter("id"));
                    session.setAttribute("idCirugia", idCirugia);
                    request.getRequestDispatcher("medicos-asignados-cirugia.jsp").forward(request, response);
                    break;
                case "Reemplazar":
                    idAsignacion = Integer.parseInt(request.getParameter("id"));
                    session.setAttribute("idAsignacion", idAsignacion);
                    request.getRequestDispatcher("reemplazo-medico-cirugia.jsp").forward(request, response);
                    break;
                case "Guardar reemplazo":
                    numFilas = (int) session.getAttribute("filas");
                    idAsignacion = (int) session.getAttribute("idAsignacion");
                    cirugia.reemplazarMedico(numFilas, request, session, idAsignacion);
                    break;
                case "Registrar operacion":
                    nuevo = new Cirugias();
                    nuevo.setTipo_operacion(request.getParameter("descripcion"));
                    nuevo.setCosto_cirugia(Float.parseFloat(request.getParameter("costo")));
                    nuevo.setPrecio_cirugia(Float.parseFloat(request.getParameter("precio")));
                    tipo.crearCosto(nuevo);
                    tipo.crearPrecio(nuevo);
                    request.getRequestDispatcher("perfil-recursos-humanos.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCirugias.class.getName()).log(Level.SEVERE, null, ex);
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
