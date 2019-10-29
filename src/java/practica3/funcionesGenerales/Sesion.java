package practica3.funcionesGenerales;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class Sesion {

    private static Connection cn;
    private static Conexion login;
    private final static String AREA_EMPLEADO = "SELECT * FROM Sesion_empleados WHERE username = ?";
    private static final String VERIFICACION = "SELECT * FROM Sesion_empleados WHERE username = ? AND password = ?";
    
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
    
    public int idSesion(String username) throws SQLException{
        obtenerConexion();
        int id = 0;
        PreparedStatement declaracionId = cn.prepareStatement(AREA_EMPLEADO);
        declaracionId.setString(1, username);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            id = result.getInt("id_empleado");
        }
        login.Desconectar();
        return id;  
    }
    
    public void verificarCuenta(String username, String password, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        obtenerConexion();
        String area = "";
        PreparedStatement declaracionArea = cn.prepareStatement(VERIFICACION);
        declaracionArea.setString(1, username);
        declaracionArea.setString(2, password);
        ResultSet result = declaracionArea.executeQuery();
        while(result.next()){
            area = result.getString("tipo_cuenta");
            switch (area){
                case "Administracion":
                    request.getRequestDispatcher("perfil-administrador.jsp").forward(request, response);
                    break;
                case "Consultoria":
                    request.getRequestDispatcher("perfil-consultoria.jsp").forward(request, response);
                    break;
                case "Enfermeria":
                    request.getRequestDispatcher("perfil-enfermeria.jsp").forward(request, response);
                    break;
                case "Farmacia":
                    request.getRequestDispatcher("perfil-farmacia.jsp").forward(request, response);
                    break;
                case "Medicos":
                    request.getRequestDispatcher("perfil-medicos.jsp").forward(request, response);
                    break;
                case "Recursos Humanos":
                    request.getRequestDispatcher("perfil-recursos-humanos.jsp").forward(request, response);
                    break;
                case "Supervision":
                    request.getRequestDispatcher("perfil-supervisor.jsp").forward(request, response);
                    break;
            }
        }
        login.Desconectar();
    }
    
}
