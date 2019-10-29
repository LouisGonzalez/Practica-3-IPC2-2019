package practica3.funcionesSupervisor;
import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.EventoHLaboral;

/**
 *
 * @author luisGonzalez
 */
public class EventosHistorialesDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String EVENTOS = "SELECT * FROM historial_laboral WHERE id_historial_laboral = ?";
    private static final String ID_HISTORIAL = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
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
    
    public ArrayList<EventoHLaboral> listarEventos(int id_empleado) throws SQLException{
        ArrayList<EventoHLaboral> list = new ArrayList<>();
        obtenerConexion();
        int idHistorial = idHistorial(id_empleado);
        PreparedStatement declaracionHistorial = cn.prepareStatement(EVENTOS);
        declaracionHistorial.setInt(1, idHistorial);
        ResultSet result = declaracionHistorial.executeQuery();
        while(result.next()){
            EventoHLaboral evento = new EventoHLaboral();
            evento.setId_historial_laboral(result.getInt("id_historial_laboral"));
            evento.setMonto(result.getFloat("monto"));
            evento.setEvento(result.getString("evento"));
            evento.setFecha_evento(result.getDate("fecha_evento"));
            list.add(evento);
        }
        login.Desconectar();
        return list;
    }    
}

