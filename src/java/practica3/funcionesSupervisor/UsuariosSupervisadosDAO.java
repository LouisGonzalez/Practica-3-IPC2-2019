 package practica3.funcionesSupervisor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.HistorialLaboral;

/**
 *
 * @author luisGonzalez
 */
public class UsuariosSupervisadosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ID_SUPERVISOR = "SELECT * FROM Supervisor WHERE id_empleado = ?";
    private static final String EMPLEADOS_SUPERVISOR = "SELECT * FROM No_historial_laboral WHERE supervisor = ? AND estado = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<HistorialLaboral> listarHistorial(int id_empleado) throws SQLException{
        ArrayList<HistorialLaboral> list = new ArrayList<>();
        int id_supervisor = 0;
        obtenerConexion();
        PreparedStatement declaracionSupervisor = cn.prepareStatement(ID_SUPERVISOR);
        declaracionSupervisor.setInt(1, id_empleado);
        ResultSet result = declaracionSupervisor.executeQuery();
        while(result.next()){
            id_supervisor = result.getInt("id");
            PreparedStatement declaracionEmpleados = cn.prepareStatement(EMPLEADOS_SUPERVISOR);
            declaracionEmpleados.setInt(1, id_supervisor);
            declaracionEmpleados.setString(2, "ACTIVO");
            ResultSet result2 = declaracionEmpleados.executeQuery();
            while(result2.next()){
                HistorialLaboral historial = new HistorialLaboral();
                seteoHistorial(result2, historial);
                list.add(historial);                
            }
        }
        login.Desconectar();
        return list;
    }
    
    public HistorialLaboral seteoHistorial(ResultSet result, HistorialLaboral historial) throws SQLException{
        historial.setId(result.getInt("id"));
        historial.setId_empleado(result.getInt("id_empleado"));
        historial.setNombres(result.getString("nombres"));
        historial.setApellidos(result.getString("apellidos"));
        historial.setNo_periodo_laboral(result.getInt("no_periodo_laboral"));
        historial.setSalario_base(result.getFloat("salario_base"));
        historial.setSalario_descuento(result.getFloat("salario_descuento"));
        historial.setFecha_historial_laboral(result.getDate("fecha_historial_laboral"));
        historial.setEstado(result.getString("estado"));
        return historial;
    }
    
    
   
}
