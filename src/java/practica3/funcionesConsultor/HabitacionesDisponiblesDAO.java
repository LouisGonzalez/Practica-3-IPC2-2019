package practica3.funcionesConsultor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Habitaciones;
/**
 *
 * @author luisGonzalez
 */
public class HabitacionesDisponiblesDAO {
    
    private static Connection cn;
    private static Conexion login;
    private Habitaciones habitacion;
    private static final String HABITACIONES_DISPONIBLES = "SELECT * FROM Habitaciones WHERE estado = ?";
    private static final String ESTADO = "HABILITADA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Habitaciones> listarHabitaciones() throws SQLException{
        ArrayList<Habitaciones> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionLista = cn.prepareStatement(HABITACIONES_DISPONIBLES);
        declaracionLista.setString(1, ESTADO);
        ResultSet result = declaracionLista.executeQuery();
        while(result.next()){
            habitacion = new Habitaciones();
            habitacion.setId(result.getInt("id"));
            habitacion.setNo_camas(result.getInt("no_camas"));
            habitacion.setEstado(result.getString("estado"));
            list.add(habitacion);
        }
        login.Desconectar();
        return list;
    }
}

