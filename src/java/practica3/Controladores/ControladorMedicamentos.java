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
import practica3.funcionesFarmaceutico.CreacionMedicamentos;
import practica3.objetos.Medicamentos;

/**
 *
 * @author luisGonzalez
 */
public class ControladorMedicamentos extends HttpServlet {

    private Medicamentos medicamento;
    private final CreacionMedicamentos creacion = new CreacionMedicamentos();
    
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
            out.println("<title>Servlet ControladorMedicamentos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorMedicamentos at " + request.getContextPath() + "</h1>");
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
        try {

            switch (accion) {
                case "Guardar medicamento":
                    medicamento = new Medicamentos();
                    medicamento.setNombre(request.getParameter("nombre"));
                    medicamento.setDescripcion(request.getParameter("descripcion"));
                    medicamento.setCosto_unitario(Float.parseFloat(request.getParameter("costoUnitario")));
                    medicamento.setPrecio_venta(Float.parseFloat(request.getParameter("precioVenta")));
                    medicamento.setCant_existencia(Integer.parseInt(request.getParameter("existencia")));
                    medicamento.setLimite_existencia(Integer.parseInt(request.getParameter("limiteExistencia")));
                    creacion.crearMedicamento(medicamento);
                    request.getRequestDispatcher("perfil-farmacia.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
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
