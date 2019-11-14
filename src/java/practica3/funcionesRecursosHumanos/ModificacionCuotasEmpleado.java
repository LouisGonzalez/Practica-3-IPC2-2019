package practica3.funcionesRecursosHumanos;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class ModificacionCuotasEmpleado {
    
    private static Connection cn;
    private static Conexion login;
    private static final String MODIFICACIONES = "UPDATE Tarifas SET total = ? WHERE id = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void modificarVacaciones(Tarifas tarifa) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionVacaciones = cn.prepareStatement(MODIFICACIONES);
        declaracionVacaciones.setInt(1, tarifa.getDias());
        declaracionVacaciones.setInt(2, tarifa.getId());
        declaracionVacaciones.executeUpdate();
        login.Desconectar();
    }
    
    public void modificarSalario(Tarifas tarifa) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionSalario = cn.prepareStatement(MODIFICACIONES);
        declaracionSalario.setFloat(1, tarifa.getNuevoTotal());
        declaracionSalario.setInt(2, tarifa.getId());
        declaracionSalario.executeUpdate();
        login.Desconectar();
    }

}
