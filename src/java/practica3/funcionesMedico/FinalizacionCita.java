package practica3.funcionesMedico;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;

/**
 *
 * @author luisGonzalez
 */
public class FinalizacionCita {
    
    private static Connection cn;
    private static Conexion login;
    private static final String FINALIZACION = "UPDATE Consulta SET estado = ? WHERE id = ?";
    private static final String ESTADO = "CONCLUIDA";
    private static final String PAGO_CONSULTA = "INSERT INTO Factura (id, nombres, apellidos, ciudad, fecha_factura, estado, tipo, total, nit, id_empleado_medico, id_empleado_venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void finalizarCita(int id_consulta) throws SQLException{
        obtenerConexion();
        PreparedStatement decFinalizacion = cn.prepareStatement(FINALIZACION);
        decFinalizacion.setString(1, ESTADO);
        decFinalizacion.setInt(2, id_consulta);
        decFinalizacion.executeUpdate();
        login.Desconectar();
    }
    
    

}
