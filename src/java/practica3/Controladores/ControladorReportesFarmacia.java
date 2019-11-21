package practica3.Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.reportesFarmacia.ReporteMedicamentos;
import practica3.reportesFarmacia.ReporteVentasEmpleadoDAO;
import practica3.reportesFarmacia.ReporteVentasMedicamentosDAO;

/**
 *
 * @author luisGonzalez
 */
public class ControladorReportesFarmacia extends HttpServlet {

    ReporteMedicamentos reporte1 = new ReporteMedicamentos(); 
    ReporteVentasMedicamentosDAO reporte2 = new ReporteVentasMedicamentosDAO();
    ReporteVentasEmpleadoDAO reporte3 = new ReporteVentasEmpleadoDAO();
    
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
            out.println("<title>Servlet ControladorReportesFarmacia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorReportesFarmacia at " + request.getContextPath() + "</h1>");
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
        Date fecha1, fecha2;
            switch (accion) {
                case "reporte de medicamentos":
                    reporte1.imprimirReporteMedicamentos();
                    reporte1.imprimir();
                    request.getRequestDispatcher("reporte-medicamentos-farmacia.jsp").forward(request, response);
                    break;
                case "reporte ganancias medicamento":
                    fecha1 = Date.valueOf(request.getParameter("fecha1"));
                    fecha2 = Date.valueOf(request.getParameter("fecha2"));
                    reporte2.obtenerListarMedicamentos(fecha1, fecha2);
                    reporte2.imprimir();
                    request.getRequestDispatcher("reporte-medicamento-ganancias.jsp").forward(request, response);
                    break;
                case "reporte ventas empleado":
                    reporte3.imprimirReporte(request);
                    reporte3.imprimir();
                    request.getRequestDispatcher("reporte-ventas-empleado.jsp").forward(request, response);
                    break;
                case "reporte ganancias medicamento html":
                    fecha1 = Date.valueOf(request.getParameter("fecha1"));
                    fecha2 = Date.valueOf(request.getParameter("fecha2"));
                    boolean verificador = false;
                    session.setAttribute("verificador", verificador);
                    session.setAttribute("fecha1", fecha1);
                    session.setAttribute("fecha2", fecha2);
                    request.getRequestDispatcher("reporte-medicamento-ganancias.jsp").forward(request, response);
                case "reporte ventas html":
                    String[] parametro = request.getParameterValues("filtro");
                    for(String parametro1 : parametro){
                        switch(parametro1){
                            case "opcion1":
                                session.setAttribute("opciones", 1);
                                break;
                            case "opcion2":
                                session.setAttribute("opciones", 2);
                                break;
                            case "opcion3":
                                session.setAttribute("opciones", 3);
                                break;
                        }
                    }
                    request.getRequestDispatcher("reporte-ventas-empleado.jsp").forward(request, response);
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
