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
public class VerificarContratador {
    
    private static Connection cn;
    private static Conexion login;
    private static final String VERIFICACION_USUARIO = "SELECT * FROM Sesion_empleados WHERE username = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void verificacion(String username, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        obtenerConexion();
        String area = "";
        PreparedStatement declaracionVerificador = cn.prepareStatement(VERIFICACION_USUARIO);
        declaracionVerificador.setString(1, username);
        ResultSet result = declaracionVerificador.executeQuery();
        while(result.next()){
            area = result.getString("tipo_cuenta");
            switch(area){
                case "Administracion":
                    request.getRequestDispatcher("perfil-administrador.jsp").forward(request, response);
                    break;
                case "Recursos Humanos":
                    request.getRequestDispatcher("perfil-recursos-humanos.jsp").forward(request, response);
                    break;           
            }
        }
        login.Desconectar();
    }
}
