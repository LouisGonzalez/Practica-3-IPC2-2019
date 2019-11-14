package practica3.funcionesRecursosHumanos;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class SalariosHospitalDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_SALARIOS = "SELECT * FROM Tarifas WHERE id > ? AND id < ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Tarifas> listarSalarios() throws SQLException{
        ArrayList<Tarifas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionSalarios = cn.prepareStatement(LISTADO_SALARIOS);
        declaracionSalarios.setInt(1, 4);
        declaracionSalarios.setInt(2, 15);
        ResultSet result = declaracionSalarios.executeQuery();
        while(result.next()){
            Tarifas tarifa = new Tarifas();
            tarifa.setId(result.getInt("id"));
            tarifa.setDescripcion(result.getString("descripcion"));
            tarifa.setTotal(result.getFloat("total"));
            list.add(tarifa);
        }
        login.Desconectar();
        return list;
    }
}
