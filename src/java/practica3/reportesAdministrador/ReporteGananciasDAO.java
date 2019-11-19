package practica3.reportesAdministrador;

import java.sql.Connection;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class ReporteGananciasDAO {
    
    private static Connection cn;
    private static Conexion login;

    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    
    
}
