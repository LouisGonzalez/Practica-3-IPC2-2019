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
import practica3.funcionesAdministrador.ControlHabitaciones;
import practica3.funcionesAdministrador.CuotasHabitaciones;
import practica3.objetos.Habitaciones;

/**
 *
 * @author luisGonzalez
 */
public class ControladorHabitaciones extends HttpServlet {

    private Habitaciones habitacion;
    private static final String ESTADO_HABITACION = "HABILITADA";
    private final ControlHabitaciones control = new ControlHabitaciones();
    private final CuotasHabitaciones cuota = new CuotasHabitaciones();
    
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
            out.println("<title>Servlet ControladorHabitaciones</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorHabitaciones at " + request.getContextPath() + "</h1>");
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
        int idHabitacion;
        try {

            switch (accion) {
                case "Crear habitaciones":
                    int numHabitaciones = Integer.parseInt(request.getParameter("nuevaHabitacion"));
                    habitacion = new Habitaciones();
                    habitacion.setNo_camas(2);
                    habitacion.setEstado(ESTADO_HABITACION);
                    control.crearHabitacion(numHabitaciones, habitacion);
                    request.getRequestDispatcher("perfil-administrador.jsp").forward(request, response);
                    break;
                case "Cambiar cuota":
                    idHabitacion = Integer.parseInt(request.getParameter("id"));
                    float nuevoTotal = Float.parseFloat(request.getParameter("nuevoTotal"));
                    cuota.modificarCuota(idHabitacion, nuevoTotal);
                    request.getRequestDispatcher("cuota-diaria-habitacion.jsp").forward(request, response);
                    break;
                case "Ver camas":
                    idHabitacion = Integer.parseInt(request.getParameter("id"));
                    session.setAttribute("idHabitacion", idHabitacion);
                    request.getRequestDispatcher("camas-hospitalizacion.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorHabitaciones.class.getName()).log(Level.SEVERE, null, ex);
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
