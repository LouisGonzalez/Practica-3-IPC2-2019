package practica3.funcionesConsultor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Camas;

/**
 *
 * @author luisGonzalez
 */
public class CamasHabitacionesDAO {
    
    private static Connection cn;
    private static Conexion login;
    private Camas cama;
    private static final String LISTADO_CAMAS = "SELECT * FROM Camas WHERE id_habitacion = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Camas> listarCamas(int idHabitacion) throws SQLException{
        ArrayList<Camas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionCamas = cn.prepareStatement(LISTADO_CAMAS);
        declaracionCamas.setInt(1, idHabitacion);
        ResultSet result = declaracionCamas.executeQuery();
        while(result.next()){
            cama = new Camas();
            cama.setId(result.getInt("id"));
            cama.setId_habitacion(result.getInt("id_habitacion"));
            cama.setEstado(result.getString("estado"));
            list.add(cama);
        }
        login.Desconectar();
        return list;
    }
}
