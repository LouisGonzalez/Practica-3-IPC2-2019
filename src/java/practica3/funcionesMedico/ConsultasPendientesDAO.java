package practica3.funcionesMedico;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Consultas;

/**
 *
 * @author luisGonzalez
 */
public class ConsultasPendientesDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_CONSULTAS = "SELECT * FROM Consulta WHERE id_empleado_medico = ? AND estado = ?";
    private static final String ESTADO = "PENDIENTE";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Consultas> listarConsultas(int id_empleado) throws SQLException{
        ArrayList<Consultas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionConsulta = cn.prepareStatement(LISTADO_CONSULTAS);
        declaracionConsulta.setInt(1, id_empleado);
        declaracionConsulta.setString(2, ESTADO);
        ResultSet result = declaracionConsulta.executeQuery();
        while(result.next()){
            Consultas consulta = new Consultas();
            consulta.setId(result.getInt("id"));
            consulta.setNombres(result.getString("nombres"));
            consulta.setApellidos(result.getString("apellidos"));
            consulta.setCui(result.getInt("cui"));
            list.add(consulta);
        }
        login.Desconectar();
        return list;
    }
}
