package practica3.funcionesConsultor;

import java.sql.*;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class PagoHospitalizacion {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ACTUALIZACION_ESTADO = "UPDATE Factura SET estado = ? WHERE id = ?";
    private static final String ACTUALIZACION_EMPLEADO = "UPDATE Factura SET id_empleado_venta = ? WHERE id = ?";
    private static final String ESTADO = "CANCELADA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void actualizarEstado(int idFactura) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionEstado = cn.prepareStatement(ACTUALIZACION_ESTADO);
        declaracionEstado.setString(1, ESTADO);
        declaracionEstado.setInt(2, idFactura);
        declaracionEstado.executeUpdate();
        login.Desconectar();
    }
    
    public void actualizarEmpleado(int idFactura, int id) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionEmpleado = cn.prepareStatement(ACTUALIZACION_EMPLEADO);
        declaracionEmpleado.setInt(1, id);
        declaracionEmpleado.setInt(2, idFactura);
        declaracionEmpleado.executeUpdate();
        login.Desconectar();
    }
    
}
