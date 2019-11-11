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
    
    
}
