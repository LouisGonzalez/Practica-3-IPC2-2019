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
import practica3.funcionesConsultor.GenerarConsulta;
import practica3.funcionesMedico.FinalizacionCita;
import practica3.objetos.Consultas;

/**
 *
 * @author luisGonzalez
 */
public class ControladorConsultas extends HttpServlet {

    private Consultas consulta;
    private final GenerarConsulta nuevaConsulta = new GenerarConsulta();
    private final FinalizacionCita finalizacion = new FinalizacionCita();
    
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
            out.println("<title>Servlet ControladorConsultas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorConsultas at " + request.getContextPath() + "</h1>");
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
        try {
            switch (accion) {
                case "Agendar una cita":
                    int id_medico = Integer.parseInt(request.getParameter("id"));
                    System.out.println(id_medico);
                    session.setAttribute("idEmpleado", id_medico);
                    request.getRequestDispatcher("formulario-nueva-consulta.jsp").forward(request, response);
                    break;
                case "Confirmar cita":
                    int idMedico = (int) session.getAttribute("idEmpleado");
                    consulta = new Consultas();
                    consulta.setNombres(request.getParameter("nombres"));
                    consulta.setApellidos(request.getParameter("apellidos"));
                    consulta.setCui(Integer.parseInt(request.getParameter("cui")));
                    consulta.setFecha_consulta(Date.valueOf(request.getParameter("fechaConsulta")));
                    consulta.setId_empleado_medico(idMedico);
                    consulta.setEstado("PENDIENTE");
                    nuevaConsulta.crearConsulta(consulta);
                    request.getRequestDispatcher("listado-medicos-consulta.jsp").forward(request, response);
                    break;
                case "Concluir cita":
                    int idCita = Integer.parseInt(request.getParameter("id"));
                    finalizacion.finalizarCita(idCita);
                    request.getRequestDispatcher("consultas-pendientes.jsp").forward(request, response);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorConsultas.class.getName()).log(Level.SEVERE, null, ex);
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
