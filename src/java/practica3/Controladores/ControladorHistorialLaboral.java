package practica3.Controladores;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.funcionesSupervisor.FechasVacacionales;
import practica3.funcionesSupervisor.InformacionUsuariosDAO;
import practica3.objetos.HistorialLaboral;

/**
 *
 * @author luisGonzalez
 */
public class ControladorHistorialLaboral extends HttpServlet {

    private final InformacionUsuariosDAO informacionUsuarios = new InformacionUsuariosDAO();
    private final FechasVacacionales vacaciones = new FechasVacacionales();
    
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
            out.println("<title>Servlet ControladorHistorialLaboral</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorHistorialLaboral at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        int id_empleado = Integer.parseInt(request.getParameter("id"));
        HistorialLaboral informacion = new HistorialLaboral();
        session.setAttribute("info", informacion);
        try {
            informacionUsuarios.seteoInformacion(informacion, id_empleado);
            request.getRequestDispatcher("info-empleados-supervisor.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorHistorialLaboral.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        //try {

            switch (accion) {
                case "Aumento":
                    session.setAttribute("idEmpleado", id);
                    request.getRequestDispatcher("nuevo-salario.jsp").forward(request, response);
                    break;
                case "Renuncia":
                    session.setAttribute("idEmpleado", id);
                    request.getRequestDispatcher("confirmacion-renuncia.jsp").forward(request, response);
                    break;
                case "Despido":
                    session.setAttribute("idEmpleado", id);
                    request.getRequestDispatcher("confirmacion-despido.jsp").forward(request, response);
                    break;
                case "Historial":
                    session.setAttribute("idEmpleado", id);
                    request.getRequestDispatcher("eventos-historial-laboral.jsp").forward(request, response);
                    break;
                case "Vacaciones":
                    session.setAttribute("idEmpleado", id);
                    request.getRequestDispatcher("fechas-vacacionales.jsp").forward(request, response);
                    break;
            }
       /* } catch (SQLException ex) {
            Logger.getLogger(ControladorHistorialLaboral.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     
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
