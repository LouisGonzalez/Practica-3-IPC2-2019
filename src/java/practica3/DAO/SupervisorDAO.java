package practica3.DAO;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Supervisor;

/**
 *
 * @author luisGonzalez
 */
public class SupervisorDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_SUPERVISORES = "SELECT * FROM Supervisor WHERE area_trabajo = ?";
    private static final String NOMBRE_SUPERVISOR = "SELECT * FROM Empleados WHERE id = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Supervisor> listarSupervisores(String area) throws SQLException{
        ArrayList<Supervisor> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionSupervisor = cn.prepareStatement(LISTADO_SUPERVISORES);
        declaracionSupervisor.setString(1, area);
        ResultSet result = declaracionSupervisor.executeQuery();
        while(result.next()){
            Supervisor supervisor = new Supervisor();
            supervisor.setId(result.getInt("id"));
            supervisor.setId_empleado(result.getInt("id_empleado"));
            supervisor.setPersonas_a_cargo(result.getInt("empleados_a_cargo"));
            supervisor.setLimite_personas(result.getInt("limite_empleados"));
            PreparedStatement declaracionEmpleado = cn.prepareStatement(NOMBRE_SUPERVISOR);
            declaracionEmpleado.setInt(1, supervisor.getId_empleado());
            ResultSet result2 = declaracionEmpleado.executeQuery();
            while(result2.next()){
                supervisor.setNombres(result2.getString("nombres"));
                supervisor.setApellidos(result2.getString("apellidos"));
            }
            list.add(supervisor);
        }
        login.Desconectar();
        return list;
    }
    
}
