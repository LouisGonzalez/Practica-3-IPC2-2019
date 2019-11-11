package practica3.funcionesConsultor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Empleados;
import practica3.objetos.Medicos;

/**
 *
 * @author luisGonzalez
 */
public class TrabajadoresAsignacionDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_ENFERMERAS = "SELECT * FROM Empleados WHERE area_trabajo = ?";
    private static final String LISTADO_MEDICOS = "SELECT * FROM Empleados e JOIN Medicos m ON e.id = m.id_empleado WHERE area_trabajo = ? AND tipo = ? AND e.id <> ?";
    private static final String AREA_ENFERMERIA = "Enfermeria";
    private static final String AREA_MEDICINA = "Medicos";
    private static final String TIPO_MEDICO = "Medico";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Empleados> listarEnfermeras() throws SQLException{
        ArrayList<Empleados> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEnfermera = cn.prepareStatement(LISTADO_ENFERMERAS);
        declaracionEnfermera.setString(1, AREA_ENFERMERIA);
        ResultSet result = declaracionEnfermera.executeQuery();
        while(result.next()){
            Empleados asignacion = new Empleados();
            asignacion.setId(result.getInt("id"));
            asignacion.setNombres(result.getString("nombres"));
            asignacion.setApellidos(result.getString("apellidos"));
            list.add(asignacion);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Medicos> listarMedicos(int idMedico) throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionMedico = cn.prepareStatement(LISTADO_MEDICOS);
        declaracionMedico.setString(1, AREA_MEDICINA);      
        declaracionMedico.setString(2, TIPO_MEDICO);
        declaracionMedico.setInt(3, idMedico);
        ResultSet result = declaracionMedico.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId_empleado(result.getInt("e.id"));
            medico.setId(result.getInt("m.id"));
            medico.setNombres(result.getString("nombres"));
            medico.setApellidos(result.getString("apellidos"));
            medico.setEspecialidad(result.getString("especialidad"));
            list.add(medico);
        }
        login.Desconectar();
        return list;
    }
    
    
}
