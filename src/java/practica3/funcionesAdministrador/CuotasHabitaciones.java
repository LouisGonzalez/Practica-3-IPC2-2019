package practica3.funcionesAdministrador;

import java.sql.*;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class CuotasHabitaciones {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CAMBIO_CUOTA = "UPDATE Habitaciones SET costo_diario = ? WHERE id = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void modificarCuota(int idHabitacion, float nuevoTotal) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionCuota = cn.prepareStatement(CAMBIO_CUOTA);
        declaracionCuota.setFloat(1, nuevoTotal);
        declaracionCuota.setInt(2, idHabitacion);
        declaracionCuota.executeUpdate();
        login.Desconectar();
    }
    
}
