package practica3.funcionesRecursosHumanos;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class DiasVacacionalesDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String DIAS_VACACIONALES = "SELECT * FROM Tarifas WHERE id = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Tarifas> listarDiasVacacionales() throws SQLException{
        ArrayList<Tarifas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionVacaciones = cn.prepareStatement(DIAS_VACACIONALES);
        declaracionVacaciones.setInt(1, 4);
        ResultSet result = declaracionVacaciones.executeQuery();
        while(result.next()){
            Tarifas tarifa = new Tarifas();
            tarifa.setId(result.getInt("id"));
            tarifa.setDescripcion(result.getString("descripcion"));
            tarifa.setDias(result.getInt("total"));
            list.add(tarifa);
        }
        login.Desconectar();
        return list;
    }
}
