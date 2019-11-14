package practica3.funcionesRecursosHumanos;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class CambioTarifasCirugias {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CAMBIO_PAGO_MEDICO = "UPDATE Tarifas SET total = ? WHERE id = ?";
    private static final String CAMBIO_PRECIO_CIRUGIA = "UPDATE Tarifas_cirugias SET total = ? WHERE id = ?";
    private static final String CAMBIO_COSTO_CIRUGIA = "UPDATE Costos_cirugias SET total = ? WHERE id = ?";

    private static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void cambiarPagoMedicoCirugia(Tarifas tarifa) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionPago = cn.prepareStatement(CAMBIO_PAGO_MEDICO);
        declaracionPago.setFloat(1, tarifa.getNuevoTotal());
        declaracionPago.setInt(2, tarifa.getId());
        declaracionPago.executeUpdate();
        login.Desconectar();
    }
    
    public void cambiarPreciosCirugia(Tarifas tarifa) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionPrecio = cn.prepareStatement(CAMBIO_PRECIO_CIRUGIA);
        declaracionPrecio.setFloat(1, tarifa.getNuevoTotal());
        declaracionPrecio.setInt(2, tarifa.getId());
        declaracionPrecio.executeUpdate();
        login.Desconectar();
    }
    
    public void cambiarCostosCirugias(Tarifas tarifa) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionCosto = cn.prepareStatement(CAMBIO_COSTO_CIRUGIA);
        declaracionCosto.setFloat(1, tarifa.getNuevoTotal());
        declaracionCosto.setInt(2, tarifa.getId());
        declaracionCosto.executeUpdate();
        login.Desconectar();
    }
    
}

