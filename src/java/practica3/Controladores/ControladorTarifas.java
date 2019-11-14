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
import practica3.funcionesRecursosHumanos.CambioTarifasCirugias;
import practica3.funcionesRecursosHumanos.EstadiaHospital;
import practica3.funcionesRecursosHumanos.ModificacionCuotasEmpleado;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class ControladorTarifas extends HttpServlet {

    private Tarifas tarifa;
    private final CambioTarifasCirugias tarifaCirugia = new CambioTarifasCirugias(); 
    private final EstadiaHospital estadia = new EstadiaHospital();
    private final ModificacionCuotasEmpleado empleado = new ModificacionCuotasEmpleado();
    
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
            out.println("<title>Servlet ControladorTarifas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorTarifas at " + request.getContextPath() + "</h1>");
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
                case "Actualizar tarifa medicos":
                    tarifa = new Tarifas();
                    tarifa.setId(Integer.parseInt(request.getParameter("id")));
                    tarifa.setNuevoTotal(Float.parseFloat(request.getParameter("nuevaTarifa")));
                    tarifaCirugia.cambiarPagoMedicoCirugia(tarifa);
                    request.getRequestDispatcher("tarifa-pagos-cirugia.jsp").forward(request, response);
                    break;
                case "Actualizar precios por operacion":
                    tarifa = new Tarifas();
                    tarifa.setId(Integer.parseInt(request.getParameter("id")));
                    tarifa.setNuevoTotal(Float.parseFloat(request.getParameter("nuevaTarifa")));
                    tarifaCirugia.cambiarPreciosCirugia(tarifa);
                    request.getRequestDispatcher("tarifa-cobro-cirugia.jsp").forward(request, response);
                    break;
                case "Actualizar costos por operacion":
                    tarifa = new Tarifas();
                    tarifa.setId(Integer.parseInt(request.getParameter("id")));
                    tarifa.setNuevoTotal(Float.parseFloat(request.getParameter("nuevaTarifa")));
                    tarifaCirugia.cambiarCostosCirugias(tarifa);
                    request.getRequestDispatcher("tarifa-costo-cirugia.jsp").forward(request, response);
                case "Actualizar costos por estadia":
                    tarifa = new Tarifas();
                    tarifa.setId(Integer.parseInt(request.getParameter("id")));
                    tarifa.setNuevoTotal(Float.parseFloat(request.getParameter("nuevaTarifa")));
                    estadia.cambioTarifas(tarifa);
                    request.getRequestDispatcher("cuotas-estadia-hospital.jsp").forward(request, response);
                    break;
                case "Modificar rango":
                    tarifa = new Tarifas();
                    tarifa.setId(Integer.parseInt(request.getParameter("id")));
                    tarifa.setDias(Integer.parseInt(request.getParameter("vacaciones")));
                    empleado.modificarVacaciones(tarifa);
                    request.getRequestDispatcher("dias-vacacionales.jsp").forward(request, response);
                    break;
                case "Modificar salarios":
                    tarifa = new Tarifas();
                    tarifa.setId(Integer.parseInt(request.getParameter("id")));
                    tarifa.setNuevoTotal(Float.parseFloat(request.getParameter("salario")));
                    empleado.modificarSalario(tarifa);
                    request.getRequestDispatcher("salarios-hospital.jsp").forward(request, response);
                    break;
                    
                    
                    
                    
                    
                    
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTarifas.class.getName()).log(Level.SEVERE, null, ex);
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
