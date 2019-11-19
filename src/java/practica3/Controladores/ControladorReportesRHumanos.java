package practica3.Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import practica3.reportesSupervisor.EmpleadosContratadosDAO;
import practica3.reportesSupervisor.EmpleadosDespedidosDAO;
import practica3.reportesSupervisor.MedicosDAO;

/**
 *
 * @author luisGonzalez
 */
public class ControladorReportesRHumanos extends HttpServlet {

    EmpleadosContratadosDAO reporte1 = new EmpleadosContratadosDAO();
    EmpleadosDespedidosDAO reporte2 = new EmpleadosDespedidosDAO();
    MedicosDAO reporte3 = new MedicosDAO();
    
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
            out.println("<title>Servlet ControladorReportesRHumanos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorReportesRHumanos at " + request.getContextPath() + "</h1>");
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
        Date fechaInicial, fechaFinal;
        String areaTrabajo;
        switch(accion){
            case "reporte empleados contratados":
                fechaInicial = Date.valueOf(request.getParameter("fechaInicial"));
                fechaFinal = Date.valueOf(request.getParameter("fechaFinal"));
                areaTrabajo = request.getParameter("areaTrabajo");
                reporte1.imprimirReporteMedicamentos(fechaInicial, fechaFinal, request, areaTrabajo);
                reporte1.imprimir();
                request.getRequestDispatcher("reporte-empleados-contratados.jsp").forward(request, response);
                break;
            case "reporte empleados fuera":
                fechaInicial = Date.valueOf(request.getParameter("fechaInicial"));
                fechaFinal = Date.valueOf(request.getParameter("fechaFinal"));
                areaTrabajo = request.getParameter("areaTrabajo");
                reporte2.imprimirReporteEmpleados(fechaInicial, fechaFinal, request, areaTrabajo);
                reporte2.imprimir();
                request.getRequestDispatcher("reporte-empleados-fuera.jsp").forward(request, response);
                break;
            case "reporte medicos": 
                reporte3.imprimirReporte(request);
                reporte3.imprimir();
                request.getRequestDispatcher("reporte-medicos.jsp").forward(request, response);
                break;
            
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
