package practica3.funcionesConsultor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Consultas;

/**
 *
 * @author luisGonzalez
 */
public class ConsultasFinalizadasDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CONSULTAS_FINALIZADAS = "SELECT * FROM Consulta WHERE estado = ?";
    private static final String ESTADO = "CONCLUIDA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Consultas> listarConsultasFinalizadas() throws SQLException{
        ArrayList<Consultas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionConsulta = cn.prepareStatement(CONSULTAS_FINALIZADAS);
        declaracionConsulta.setString(1, ESTADO);
        ResultSet result = declaracionConsulta.executeQuery();
        while(result.next()){
            Consultas consulta = new Consultas();
            consulta.setId(result.getInt("id"));
            consulta.setNombres(result.getString("nombres"));
            consulta.setApellidos(result.getString("apellidos"));
            consulta.setFecha_consulta(result.getDate("fecha_consulta"));
            consulta.setId_empleado_medico(result.getInt("id_empleado_medico"));
            list.add(consulta);
        }
        login.Desconectar();    
        return list;
    }
}
