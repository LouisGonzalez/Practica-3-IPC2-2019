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
import practica3.funcionesSupervisor.AumentoSalarios;
import practica3.funcionesSupervisor.CambioFechasVacacionales;
import practica3.funcionesSupervisor.DespidoEmpleados;
import practica3.funcionesSupervisor.FechasVacacionales;

/**
 *
 * @author luisGonzalez
*/
public class ControladorOperacionesHistorial extends HttpServlet {

    private final AumentoSalarios aumento = new AumentoSalarios();
    private final DespidoEmpleados despido = new DespidoEmpleados();
    private final FechasVacacionales vacaciones = new FechasVacacionales();
    private final CambioFechasVacacionales cambio = new CambioFechasVacacionales();
    
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
            out.println("<title>Servlet ControladorOperacionesHistorial</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorOperacionesHistorial at " + request.getContextPath() + "</h1>");
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
        int idEmpleado = Integer.parseInt(request.getParameter("id"));
        try {
            switch (accion) {
                case "Confirmar aumento":
                    float salario = Integer.parseInt(request.getParameter("salario"));
                    Date fecha = Date.valueOf(request.getParameter("fecha"));
                    aumento.confirmarAumento(idEmpleado, salario, fecha);
                    request.getRequestDispatcher("nuevo-salario.jsp").forward(request, response);
                    break;
                case "Confirmar renuncia":
                    Date fechaRenuncia = Date.valueOf(request.getParameter("fechaRenuncia"));
                    despido.renunciaEmpleado(idEmpleado);
                    despido.subirEventoHistorial(idEmpleado, fechaRenuncia, "RENUNCIA");
                    request.getRequestDispatcher("historial-laboral.jsp").forward(request, response);
                    break;
                case "Confirmar despido":
                    Date fechaDespido = Date.valueOf(request.getParameter("fechaDespido"));
                    despido.despidoEmpleado(idEmpleado);
                    despido.subirEventoHistorial(idEmpleado, fechaDespido, "DESPEDIDO");
                    request.getRequestDispatcher("historial-laboral.jsp").forward(request, response);
                    break;
                case "Confirmar vacaciones":
                    //vacaciones.comparacionFechas(idEmpleado);
                    Date fechaInicial = Date.valueOf(request.getParameter("fechaVacaciones"));
                    cambio.actualizarFechas(idEmpleado, fechaInicial);
                    request.getRequestDispatcher("fechas-vacacionales.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorOperacionesHistorial.class.getName()).log(Level.SEVERE, null, ex);
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
