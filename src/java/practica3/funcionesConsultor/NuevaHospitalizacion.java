package practica3.funcionesConsultor;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.HistorialMedico;

/**
 *
 * @author luisGonzalez
 */
public class NuevaHospitalizacion {
    
    private static Connection cn;
    private static Conexion login;
    private static final String NUEVO_HISTORIAL_MEDICO = "INSERT INTO No_historial_medico (id, cui_paciente, nombres, apellidos, dias_hospitalizado, padecimiento, estado, no_cama, no_habitacion, fecha_historial_medico, id_enfermera_mando, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String EVENTO_HISTORIAL_MEDICO = "INSERT INTO Historial_medico (id, id_historial_medico, evento, cobro, fecha_evento, id_medicamento) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ID_HISTORIAL_MEDICO = "SELECT * FROM No_historial_medico ORDER BY id DESC LIMIT 1";
    private static final String CAMA = "UPDATE Camas SET estado = ? WHERE id = ?";
    private static final String ESTADO_CAMA = "OCUPADA";
    private static final String EVENTO = "INGRESO";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearHistorialMedico(HistorialMedico historial) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionHistorial = cn.prepareStatement(NUEVO_HISTORIAL_MEDICO);
        declaracionHistorial.setInt(1, 0);
        declaracionHistorial.setInt(2, historial.getCui_paciente());
        declaracionHistorial.setString(3, historial.getNombres());
        declaracionHistorial.setString(4, historial.getApellidos());
        declaracionHistorial.setInt(5, historial.getDias_hospitalizado());
        declaracionHistorial.setString(6, historial.getPadecimiento());
        declaracionHistorial.setString(7, historial.getEstado());
        declaracionHistorial.setInt(8, historial.getNo_cama());
        declaracionHistorial.setInt(9, historial.getNo_habitacion());
        declaracionHistorial.setDate(10, historial.getFecha_historial_medico());
        declaracionHistorial.setInt(11, historial.getId_enfermera_mando());
        declaracionHistorial.setFloat(12, historial.getTotal());
        declaracionHistorial.executeUpdate();
        crearPrimerEvento(historial.getFecha_historial_medico());
        ocuparCama(historial.getNo_cama());
        login.Desconectar();
    }
    
    public void ocuparCama(int idCama) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionCama = cn.prepareStatement(CAMA);
        declaracionCama.setString(1, ESTADO_CAMA);
        declaracionCama.setInt(2, idCama);
        declaracionCama.executeUpdate();
        login.Desconectar();
    }
    
    private int verIdHistorialMedico() throws SQLException{
        int idHistorial = 0;
        PreparedStatement declaracionId = cn.prepareStatement(ID_HISTORIAL_MEDICO);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            idHistorial = result.getInt("id");
        }
        return idHistorial;
    }
    
    public void crearPrimerEvento(Date fechaEvento) throws SQLException{
        int idHistorial = verIdHistorialMedico();
        PreparedStatement declaracionEvento = cn.prepareStatement(EVENTO_HISTORIAL_MEDICO);
        declaracionEvento.setInt(1, 0);
        declaracionEvento.setInt(2, idHistorial);
        declaracionEvento.setString(3, EVENTO);
        declaracionEvento.setFloat(4, 0);
        declaracionEvento.setDate(5, fechaEvento);
        declaracionEvento.setInt(6, 0);
        declaracionEvento.executeUpdate();
    }
    
    
    
}

