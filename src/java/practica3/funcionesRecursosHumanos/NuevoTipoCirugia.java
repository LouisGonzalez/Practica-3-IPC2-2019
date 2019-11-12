package practica3.funcionesRecursosHumanos;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Cirugias;

/**
 *
 * @author luisGonzalez
 */
public class NuevoTipoCirugia {
    
    private static Connection cn;
    private static Conexion login;
    private static final String PRECIO_OPERACION = "INSERT INTO Tarifas_cirugias (id, descripcion, total) VALUES (?, ?, ?)";
    private static final String COSTO_OPERACION = "INSERT INTO Costos_cirugias (id, descripcion, total) VALUES (?, ?, ?)";
    
    private static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearPrecio(Cirugias cirugia) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionPrecio = cn.prepareStatement(PRECIO_OPERACION);
        declaracionPrecio.setInt(1, 0);
        declaracionPrecio.setString(2, cirugia.getTipo_operacion());
        declaracionPrecio.setFloat(3, cirugia.getPrecio_cirugia());
        declaracionPrecio.executeUpdate();
        login.Desconectar();
    }
    
    public void crearCosto(Cirugias cirugia) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionCosto = cn.prepareStatement(COSTO_OPERACION);
        declaracionCosto.setInt(1, 0);
        declaracionCosto.setString(2, cirugia.getTipo_operacion());
        declaracionCosto.setFloat(3, cirugia.getCosto_cirugia());
        declaracionCosto.executeUpdate();
        login.Desconectar();
    }
    
}
