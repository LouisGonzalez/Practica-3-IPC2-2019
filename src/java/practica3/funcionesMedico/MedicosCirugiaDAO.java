   package practica3.funcionesMedico;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Medicos;

/**
 *
 * @author luisGonzalez
 */
public class MedicosCirugiaDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_MEDICOS = "SELECT * FROM Empleados e JOIN Medicos m ON e.id = m.id_empleado";
    private static final String MEDICOS_ASIGNADOS = "SELECT * FROM Empleados e JOIN Trabajadores_paciente_cirugia t ON e.id = t.id_empleado JOIN Medicos m ON e.id = m.id_empleado WHERE id_cirugia = ?";
    private static final String LISTADO_REEMPLAZO = "SELECT * FROM Empleados e JOIN Medicos m ON e.id = m.id_empleado WHERE e.id <> ?";
    private static final String ID_EMPLEADO = "SELECT * FROM Trabajadores_paciente_cirugia WHERE id = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Medicos> listarMedicos() throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionListado = cn.prepareStatement(LISTADO_MEDICOS);
        ResultSet result = declaracionListado.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId_empleado(result.getInt("e.id"));
            medico.setNombres(result.getString("nombres"));
            medico.setApellidos(result.getString("apellidos"));
            medico.setEspecialidad(result.getString("especialidad"));
            list.add(medico);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Medicos> listarMedicosAsignados(int idCirugia) throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement decAsignados = cn.prepareStatement(MEDICOS_ASIGNADOS);
        decAsignados.setInt(1, idCirugia);
        ResultSet result = decAsignados.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId(result.getInt("t.id"));
            medico.setId_empleado(result.getInt("e.id"));
            medico.setNombres(result.getString("nombres"));
            medico.setApellidos(result.getString("apellidos"));
            medico.setTipo(result.getString("tipo"));
            medico.setEspecialidad(result.getString("especialidad"));
            list.add(medico);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Medicos> listarReemplazo(int idAsignacion) throws SQLException{
        ArrayList<Medicos> list =  new ArrayList<>();
        obtenerConexion();
        int idEmpleado = obtenerIdEmpleado(idAsignacion);
        PreparedStatement decReemplazo = cn.prepareStatement(LISTADO_REEMPLAZO);
        decReemplazo.setInt(1, idEmpleado);
        ResultSet result = decReemplazo.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId(result.getInt("e.id"));
            medico.setNombres(result.getString("nombres"));
            medico.setApellidos(result.getString("apellidos"));
            medico.setTipo(result.getString("tipo"));
            medico.setEspecialidad(result.getString("especialidad"));
            list.add(medico);
        }
        login.Desconectar();
        return list;
    }
    
    
    private int obtenerIdEmpleado(int idAsignacion) throws SQLException{
        int idEmpleado = 0;
        PreparedStatement declaracionId = cn.prepareStatement(ID_EMPLEADO);
        declaracionId.setInt(1, idAsignacion);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            idEmpleado = result.getInt("id_empleado");
        }
        return idEmpleado;
    }
    
}
