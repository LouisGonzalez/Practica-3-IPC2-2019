package practica3.funcionesConsultor;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class AsignacionesHospitalizacion {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ID_HISTORIAL_MEDICO = "SELECT * FROM No_historial_medico ORDER BY id DESC LIMIT 1";
    private static final String ASIGNACION = "INSERT INTO Trabajadores_paciente_hospitalizado (id, id_empleado, id_historial_medico, area_trabajador) VALUES (?, ?, ?, ?)";
    private static final String AREA_ENFERMERA = "Enfermeria";
    private static final String AREA_MEDICOS = "Medicos";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void asignarEnfermeras(int numFilas, HttpServletRequest request, HttpSession session) throws SQLException{
        obtenerConexion();
        int idHistorial = idHistorialMedico();
        for(int x = 0; x<numFilas; x++){
            if(request.getParameter("enfermera"+x) != null){
                int idEnfermera = (int) session.getAttribute("idEnfermera"+x);
                PreparedStatement declaracionEnfermera = cn.prepareStatement(ASIGNACION);
                declaracionEnfermera.setInt(1, 0);
                declaracionEnfermera.setInt(2, idEnfermera);
                declaracionEnfermera.setInt(3, idHistorial);
                declaracionEnfermera.setString(4, AREA_ENFERMERA);
                declaracionEnfermera.executeUpdate();           
            }
        }
        login.Desconectar();
    }
        
    public void asignarMedicos(int numFilas, HttpServletRequest request, HttpSession session) throws SQLException{
        obtenerConexion();
        int idHistorial = idHistorialMedico();
        for(int x = 0; x<numFilas; x++){
            if(request.getParameter("medico"+x) != null){
                int idMedico = (int) session.getAttribute("idMedico"+x);
                PreparedStatement declaracionMedico = cn.prepareStatement(ASIGNACION);
                declaracionMedico.setInt(1, 0);
                declaracionMedico.setInt(2, idMedico);
                declaracionMedico.setInt(3, idHistorial);
                declaracionMedico.setString(4, AREA_MEDICOS);
                declaracionMedico.executeUpdate();
            }
        }
        login.Desconectar();
    }
    
    private int idHistorialMedico() throws SQLException {
        int idHistorial = 0;
        PreparedStatement declaracionHistorial = cn.prepareStatement(ID_HISTORIAL_MEDICO);
        ResultSet result = declaracionHistorial.executeQuery();
        while(result.next()){
            idHistorial = result.getInt("id");
        }
        return idHistorial;
    }
}
