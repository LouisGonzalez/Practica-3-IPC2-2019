package practica3.funcionesGenerales;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import practica3.conexion.Conexion;
import practica3.funcionesAutomaticas.PagoEmpleados;

/**
 *
 * @author luisGonzalez
 */
public class Sesion {

    private static Connection cn;
    private static Conexion login;
    private final static String AREA_EMPLEADO = "SELECT * FROM Sesion_empleados WHERE username = ?";
    private static final String VERIFICACION = "SELECT * FROM Sesion_empleados WHERE username = ? AND password = ?";
    private static final String COMPROBACION_CUENTA_ACTIVA = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    private int idEmpleado;
    private String estado;
    private final PagoEmpleados pago = new PagoEmpleados();
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public String tipoSesion(String username) throws SQLException{
        obtenerConexion();
        String area = "";
        PreparedStatement declaracionArea = cn.prepareStatement(AREA_EMPLEADO);
        declaracionArea.setString(1, username);
        ResultSet result = declaracionArea.executeQuery();
        while(result.next()){
            area = result.getString("tipo_cuenta");
        }
        login.Desconectar();
        return area;
    }
    
    public int idSesion(String username) throws SQLException {
        obtenerConexion();
        int id = 0;
        PreparedStatement declaracionId = cn.prepareStatement(AREA_EMPLEADO);
        declaracionId.setString(1, username);
        ResultSet result = declaracionId.executeQuery();
        while (result.next()) {
            id = result.getInt("id_empleado");
        }
        login.Desconectar();
        return id;        
    }
    
    public void verificarCuenta(String username, String password, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        obtenerConexion();
        String area = "";
        PreparedStatement declaracionArea = cn.prepareStatement(VERIFICACION);
        declaracionArea.setString(1, username);
        declaracionArea.setString(2, password);
        ResultSet result = declaracionArea.executeQuery();
        if (result.next()) {
            area = result.getString("tipo_cuenta");
            idEmpleado = result.getInt("id_empleado");
            estado = comprobarCuentaActiva(idEmpleado);
            System.out.println(idEmpleado);
            System.out.println(estado);
            if (estado.equals("ACTIVO")) {         
                switch (area) {
                    case "Administracion":
                        request.getRequestDispatcher("perfil-administrador.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);            
                        break;
                    case "Consultoria":
                        request.getRequestDispatcher("perfil-consultoria.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);             
                        break;
                    case "Enfermeria":
                        request.getRequestDispatcher("perfil-enfermeria.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);               
                        break;
                    case "Farmacia":
                        request.getRequestDispatcher("perfil-farmacia.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);               
                        break;
                    case "Medicos":
                        request.getRequestDispatcher("perfil-medicos.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);           
                        break;
                    case "Recursos Humanos":
                        request.getRequestDispatcher("perfil-recursos-humanos.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);                
                        break;
                    case "Supervision":
                        request.getRequestDispatcher("perfil-supervisor.jsp").forward(request, response);
                        pago.calcularDiasEmpleado(idEmpleado);                    
                        break;
                    default:                        
                        request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
                        
                        break;
                }
            } else {
                request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
        }
        login.Desconectar();
    }
    
    private String comprobarCuentaActiva(int idEmpleado) throws SQLException{
        String estadoCuenta = "";
        PreparedStatement declaracionHistorial = cn.prepareStatement(COMPROBACION_CUENTA_ACTIVA);
        declaracionHistorial.setInt(1, idEmpleado);
        ResultSet result = declaracionHistorial.executeQuery();
        while(result.next()){
            estadoCuenta = result.getString("estado");
        }
        return estadoCuenta;
    }
    
}
