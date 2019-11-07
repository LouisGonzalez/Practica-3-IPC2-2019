package practica3.funcionesAdministrador;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Habitaciones;

/**
 *
 * @author luisGonzalez
 */
public class ListadoHabitacionesDAO {
    
    private static Connection cn;
    private static Conexion login;
    private Habitaciones habitacion;
    private static final String LISTADO_ADMIN = "SELECT * FROM Habitaciones";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Habitaciones> listarHabitacionesAdmin() throws SQLException{
        ArrayList<Habitaciones> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionLista = cn.prepareStatement(LISTADO_ADMIN);
        ResultSet result = declaracionLista.executeQuery();
        while(result.next()){
            habitacion = new Habitaciones();
            habitacion.setId(result.getInt("id"));
            habitacion.setNo_camas(result.getInt("no_camas"));
            habitacion.setEstado(result.getString("estado"));
            habitacion.setCosto_diario(result.getFloat("costo_diario"));
            list.add(habitacion);
        }
        login.Desconectar();
        return list;
    }
}
