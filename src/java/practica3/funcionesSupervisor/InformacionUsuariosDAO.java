package practica3.funcionesSupervisor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.HistorialLaboral;

/**
 *
 * @author luisGonzalez
 */
public class InformacionUsuariosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String INFORMACION_EMPLEADO = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    /*public ArrayList<HistorialLaboral> listarInformacion(int id_empleado) throws SQLException{
        ArrayList<HistorialLaboral> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionInfo = cn.prepareStatement(INFORMACION_EMPLEADO);
        declaracionInfo.setInt(1, id_empleado);
        ResultSet result = declaracionInfo.executeQuery();
        while(result.next()){
            HistorialLaboral historial = new HistorialLaboral();
            seteoInformacion(result, historial);
            list.add(historial);
        }
        login.Desconectar();
        return list;
    }
    
    public HistorialLaboral seteoInformacion(ResultSet result, HistorialLaboral historial) throws SQLException{
        historial.setNombres(result.getString("nombres"));
        historial.setApellidos(result.getString("apellidos"));
        historial.setNo_periodo_laboral(result.getInt("no_periodo_laboral"));
        historial.setSalario_base(result.getFloat("salario_base"));
        historial.setSalario_descuento(result.getFloat("salario_descuento"));
        historial.setFecha_historial_laboral(result.getDate("fecha_historial_laboral"));
        return historial;
    }*/
    
    public HistorialLaboral seteoInformacion(HistorialLaboral historial, int id_empleado) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionInfo = cn.prepareStatement(INFORMACION_EMPLEADO);
        declaracionInfo.setInt(1, id_empleado);
        ResultSet result = declaracionInfo.executeQuery();
        while(result.next()){
            historial.setNombres(result.getString("nombres"));
            historial.setApellidos(result.getString("apellidos"));
            historial.setNo_periodo_laboral(result.getInt("no_periodo_laboral"));
            historial.setSalario_base(result.getFloat("salario_base"));
            historial.setSalario_descuento(result.getFloat("salario_descuento"));
            historial.setFecha_historial_laboral(result.getDate("fecha_historial_laboral"));            
        }
        return historial;
    }
}
