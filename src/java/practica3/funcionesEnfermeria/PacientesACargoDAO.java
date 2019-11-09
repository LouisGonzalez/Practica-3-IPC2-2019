package practica3.funcionesEnfermeria;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.HistorialMedico;

/**
 *
 * @author luisGonzalez
 */
public class PacientesACargoDAO {
    
    private static Connection cn;
    private static Conexion login;
    private HistorialMedico historial;
    private static final String LISTADO_PACIENTES = "SELECT * FROM No_historial_medico WHERE id_enfermera_mando = ? AND estado = ?";
    private static final String ESTADO = "ACTIVO";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<HistorialMedico> listarPacientes(int idEnfermera) throws SQLException{
        ArrayList<HistorialMedico> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionLista = cn.prepareStatement(LISTADO_PACIENTES);
        declaracionLista.setInt(1, idEnfermera);
        declaracionLista.setString(2, ESTADO);
        ResultSet result = declaracionLista.executeQuery();
        while(result.next()){
            historial = new HistorialMedico();
            historial.setId(result.getInt("id"));
            historial.setCui_paciente(result.getInt("cui_paciente"));
            historial.setNombres(result.getString("nombres"));
            historial.setApellidos(result.getString("apellidos"));
            historial.setDias_hospitalizado(result.getInt("dias_hospitalizado"));
            historial.setPadecimiento(result.getString("padecimiento"));
            historial.setNo_cama(result.getInt("no_cama"));
            historial.setNo_habitacion(result.getInt("no_habitacion"));
            historial.setFecha_historial_medico(result.getDate("fecha_historial_medico"));
            historial.setTotal(result.getFloat("total"));
            list.add(historial);
        }
        login.Desconectar();
        return list;
    }
}
