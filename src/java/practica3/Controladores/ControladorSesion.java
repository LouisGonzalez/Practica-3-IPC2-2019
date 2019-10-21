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
import practica3.funcionesGenerales.Sesion;
import practica3.objetos.SesionEmpleados;

/**
 *
 * @author luisGonzalez
 */
public class ControladorSesion extends HttpServlet {

    private SesionEmpleados objetoSesion;
    private final Sesion cuenta = new Sesion();
    
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
            out.println("<title>Servlet ControladorSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorSesion at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("nombre");
        String password = request.getParameter("pass");
        try {
            String area = cuenta.tipoSesion(username);
            switch(accion){
                case "Entrar":
                    objetoSesion = new SesionEmpleados();
                    objetoSesion.setUsername(request.getParameter("nombre"));
                    objetoSesion.setPassword(request.getParameter("pass"));
                    objetoSesion.setTipo_cuenta(area);
                    session.setAttribute("usuario", objetoSesion);
                    cuenta.verificarCuenta(username, request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSesion.class.getName()).log(Level.SEVERE, null, ex);
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
