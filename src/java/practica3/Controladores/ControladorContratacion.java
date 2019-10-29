package practica3.Controladores;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.funcionesGenerales.ContratacionPersonal;
import practica3.funcionesGenerales.VerificarContratador;
import practica3.funcionesSupervisor.FechasVacacionales;
import practica3.objetos.Empleados;
import practica3.objetos.SesionEmpleados;
import practica3.objetos.Supervisor;

/**
 *
 * @author luisGonzalez
 */
public class ControladorContratacion extends HttpServlet {

    private Empleados empleado;
    private Supervisor supervisor;
    private final VerificarContratador verificar = new VerificarContratador();
    private final ContratacionPersonal contratacion = new ContratacionPersonal();
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
        String area = request.getParameter("areas");
        HttpSession session = request.getSession();
        SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
        String usuarioCuenta = sesion.getUsername();
        int cuiEmpleado = Integer.parseInt(request.getParameter("cui"));
        //int idSupervisor = Integer.parseInt(request.getParameter("idSupervisor"));
        try {
        
            switch(accion){
                case "confirmar":
                    empleado = new Empleados();
                    empleado.setNombres(request.getParameter("nombre"));
                    empleado.setApellidos(request.getParameter("apellido"));
                    empleado.setCUI(Integer.parseInt(request.getParameter("cui")));
                    empleado.setEdad(Integer.parseInt(request.getParameter("edad")));
                    empleado.setTipo_contratacion(request.getParameter("tipoContratacion"));
                    empleado.setArea_trabajo(area);
                    empleado.setFecha_contratacion(Date.valueOf(request.getParameter("fecha")));
                    contratacion.crearEmpleado(empleado, request, response);
                    session.setAttribute("nuevoEmpleado", empleado);
                    if(area.equals("Medicos")){
                        request.getRequestDispatcher("tipo-medico.jsp").forward(request, response);
                    } else if(area.equals("Supervision")){
                        contratacion.crearHistorial(empleado);
                        vacaciones.creacionFechasAleatorias(cuiEmpleado);                                     
                        request.getRequestDispatcher("area-supervisor.jsp").forward(request, response);
                    } else if(area.equals("Recursos Humanos")){
                        contratacion.crearHistorial(empleado);
                        vacaciones.creacionFechasAleatorias(cuiEmpleado);                    
                    
                        verificar.verificacion(usuarioCuenta, request, response);
                    } else {
                        contratacion.crearHistorial(empleado);
                        vacaciones.creacionFechasAleatorias(cuiEmpleado);                    
                        request.getRequestDispatcher("supervisor-inmediato.jsp").forward(request, response);
                    }
                    break;
                case "Confirmar Area":
                    supervisor = new Supervisor();
                    supervisor.setArea_trabajo(request.getParameter("areaSupervisor"));
                    contratacion.crearSupervisor(supervisor, cuiEmpleado);                    
                    break;
                case "Asignar supervisor":
                    Empleados empleado1 = (Empleados) session.getAttribute("nuevoEmpleado");    
                    contratacion.asignarSupervisor(empleado1, cuiEmpleado);
                    contratacion.sumarPersonasMando(cuiEmpleado);
                    break;
                case "Guardar Medico":
                    Empleados empleado2 = (Empleados) session.getAttribute("nuevoEmpleado");
                    String tipo = request.getParameter("tipoDoctor");
                    String especialidad = request.getParameter("especialidad");
                    contratacion.crearMedico(empleado, especialidad, tipo);
                    if(tipo.equals("Medico")){
                        contratacion.crearHistorial(empleado2);
                        vacaciones.creacionFechasAleatorias(cuiEmpleado);                    
                    
                    }
                    request.getRequestDispatcher("supervisor-inmediato.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
                Logger.getLogger(ControladorContratacion.class.getName()).log(Level.SEVERE, null, ex);
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
