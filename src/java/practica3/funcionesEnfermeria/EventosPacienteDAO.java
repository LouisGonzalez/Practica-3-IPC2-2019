package practica3.funcionesEnfermeria;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.EventoHMedico;

/**
 *
 * @author luisGonzalez
 */
public class EventosPacienteDAO {
    
    private static Connection cn;
    private static Conexion login;
    private EventoHMedico evento;
    private static final String LISTADO_EVENTOS = "SELECT * FROM Historial_medico WHERE id_historial_medico = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<EventoHMedico> listarEventos(int idHistorial) throws SQLException{
        ArrayList<EventoHMedico> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEvento = cn.prepareStatement(LISTADO_EVENTOS);
        declaracionEvento.setInt(1, idHistorial);
        ResultSet result = declaracionEvento.executeQuery();
        while(result.next()){
            evento = new EventoHMedico();
            evento.setId(result.getInt("id"));
            evento.setEvento(result.getString("evento"));
            evento.setCobro(result.getFloat("cobro"));
            evento.setFecha_evento(result.getDate("fecha_evento"));
            evento.setId_medicamento(result.getInt("id_medicamento"));
            list.add(evento);
        }
        login.Desconectar();
        return list;
    }
    
}
