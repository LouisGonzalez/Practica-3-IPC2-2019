package practica3.funcionesSupervisor;

import java.sql.*;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class DespidoEmpleados {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ID_HISTORIAL = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    private static final String RENUNCIA = "UPDATE No_historial_laboral SET estado = ? WHERE id_empleado = ?";
    private static final String DESPIDO = "UPDATE No_historial_laboral SET estado = ? WHERE id_empleado = ?";
    private static final String EVENTO = "INSERT INTO historial_laboral (id, id_historial_laboral, evento, monto, fecha_evento) VALUES (?, ?, ?, ?, ?)";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void renunciaEmpleado(int id_empleado) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionRenuncia = cn.prepareStatement(RENUNCIA);
        declaracionRenuncia.setString(1, "RENUNCIA");
        declaracionRenuncia.setInt(2, id_empleado);
        declaracionRenuncia.executeUpdate();
        login.Desconectar();
    }
    
    public void despidoEmpleado(int id_empleado) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionDespido = cn.prepareStatement(DESPIDO);
        declaracionDespido.setString(1, "DESPEDIDO");
        declaracionDespido.setInt(2, id_empleado);
        declaracionDespido.executeUpdate();
        login.Desconectar();
    }
    
    public int idHistorial(int id_empleado) throws SQLException{
        obtenerConexion();
        int idHistorial = 0;
        PreparedStatement declaracionHistorial = cn.prepareStatement(ID_HISTORIAL);
        declaracionHistorial.setInt(1, id_empleado);
        ResultSet result = declaracionHistorial.executeQuery();
        while(result.next()){
            idHistorial = result.getInt("id");
        }
        login.Desconectar();
        return idHistorial;
    }
    
    public void subirEventoHistorial(int id_empleado, Date fecha_evento, String evento) throws SQLException{
        obtenerConexion();
        int idHistorial = idHistorial(id_empleado);
        PreparedStatement declaracionEvento = cn.prepareStatement(EVENTO);
        declaracionEvento.setInt(1, 0);
        declaracionEvento.setInt(2, idHistorial);
        declaracionEvento.setString(3, evento);
        declaracionEvento.setFloat(4, 0);
        declaracionEvento.setDate(5, fecha_evento);
        declaracionEvento.executeUpdate();
        login.Desconectar();
    }
    
}
