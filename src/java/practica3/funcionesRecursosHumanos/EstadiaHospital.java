package practica3.funcionesRecursosHumanos;

import java.sql.*;
import java.sql.SQLException;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class EstadiaHospital {
    
    private static Connection cn;
    private static Conexion login;
    private static final String TARIFA_ESTADIA = "UPDATE Tarifas SET total = ? WHERE id = ?";
    
    private static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void cambioTarifas(Tarifas tarifa) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionTarifa = cn.prepareStatement(TARIFA_ESTADIA);
        declaracionTarifa.setFloat(1, tarifa.getNuevoTotal());
        declaracionTarifa.setInt(2, tarifa.getId());
        declaracionTarifa.executeUpdate();
        login.Desconectar();
    }
    
    
    
    
}
