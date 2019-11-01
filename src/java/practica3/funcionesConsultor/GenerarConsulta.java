package practica3.funcionesConsultor;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Consultas;

/**
 *
 * @author luisGonzalez
 */
public class GenerarConsulta {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CONSULTA = "INSERT INTO Consulta (id, cui, nombres, apellidos, estado, fecha_consulta, id_empleado_medico) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearConsulta(Consultas consulta) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionConsulta = cn.prepareStatement(CONSULTA);
        declaracionConsulta.setInt(1, 0);
        declaracionConsulta.setInt(2, consulta.getCui());
        declaracionConsulta.setString(3, consulta.getNombres());
        declaracionConsulta.setString(4, consulta.getApellidos());
        declaracionConsulta.setString(5, consulta.getEstado());
        declaracionConsulta.setDate(6, consulta.getFecha_consulta());
        declaracionConsulta.setInt(7, consulta.getId_empleado_medico());
        declaracionConsulta.executeUpdate();
        login.Desconectar();
    }
    
    
}
