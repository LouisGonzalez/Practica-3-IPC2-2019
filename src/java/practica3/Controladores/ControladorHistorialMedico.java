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
import practica3.funcionesEnfermeria.MedicamentoPaciente;
import practica3.funcionesMedico.AltaMedica;
import practica3.objetos.Facturas;
import practica3.objetos.SesionEmpleados;

/**
 *
 * @author luisGonzalez
 */
public class ControladorHistorialMedico extends HttpServlet {

    private final MedicamentoPaciente medicamento = new MedicamentoPaciente();
    private final AltaMedica alta = new AltaMedica();
    private Facturas factura;
    
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
            out.println("<title>Servlet ControladorHistorialMedico</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorHistorialMedico at " + request.getContextPath() + "</h1>");
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
        SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
        
        int idHistorial, idMedicina;
        try {
            switch (accion) {
                case "Opciones historial":
                    idHistorial = Integer.parseInt(request.getParameter("id"));
                    session.setAttribute("idHMedico", idHistorial);
                    request.getRequestDispatcher("opciones-historial-medico.jsp").forward(request, response);
                    break;
                case "Registrar un medicamento":
                    request.getRequestDispatcher("control-medicamentos-paciente.jsp").forward(request, response);
                    break;
                case "Registrar consumo":
                    idHistorial = (int) session.getAttribute("idHMedico");
                    idMedicina = Integer.parseInt(request.getParameter("medicinas"));
                    int cantMedicamentos = Integer.parseInt(request.getParameter("cantidad"));
                    Date fecha = Date.valueOf(request.getParameter("fechaEvento"));
                    medicamento.crearEventoHistorial(idHistorial, idMedicina, cantMedicamentos, fecha);
                    request.getRequestDispatcher("pacientes-medico.jsp").forward(request, response);
                    break;
                case "Ver historial":
                    idHistorial = Integer.parseInt(request.getParameter("id"));
                    session.setAttribute("idHMedico", idHistorial);
                    request.getRequestDispatcher("eventos-historial-medico.jsp").forward(request, response);
                    break;
                case "Registrar una operacion":
                    request.getRequestDispatcher("formulario-nueva-cirugia.jsp").forward(request, response);
                    break;
                case "Ver cirugias":
                    request.getRequestDispatcher("cirugias-activas.jsp").forward(request, response);
                    break;
                case "Alta medica":
                    request.getRequestDispatcher("alta-medica.jsp").forward(request, response);
                    break;
                case "Dar de alta medica":
                    idHistorial = (int) session.getAttribute("idHMedico");
                    Date fechaFinal = Date.valueOf(request.getParameter("fechaSalida"));
                    int idMedico = sesion.getId();
                    factura = new Facturas();
                    factura.setNombres(request.getParameter("nombres"));
                    factura.setApellidos(request.getParameter("apellidos"));
                    factura.setNit(Integer.parseInt(request.getParameter("nit")));
                    factura.setCiudad(request.getParameter("ciudad"));
                    alta.crearUltimoEvento(idHistorial, fechaFinal, factura, idMedico);
                    request.getRequestDispatcher("pacientes-medico.jsp").forward(request, response);
                    break;
                    
                    
                    
                    
                    
                    
                    
                    
                    //AL VOLVER AGREGAR EL EVENTO A LOS PAGOS PENDIENTES DE LAS CONSULTAS
                    
                    
                    
                    
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorHistorialMedico.class.getName()).log(Level.SEVERE, null, ex);
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
