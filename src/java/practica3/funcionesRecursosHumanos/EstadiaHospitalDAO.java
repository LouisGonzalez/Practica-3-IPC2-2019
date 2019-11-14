package practica3.funcionesRecursosHumanos;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class EstadiaHospitalDAO {
    
    private static Connection cn;
    private static Conexion login;
    private Tarifas tarifa;
    private static final String ESTADIA = "SELECT * FROM Tarifas WHERE id > ?";
    
    private static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Tarifas> listarTarifas() throws SQLException{
        ArrayList<Tarifas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEstadia = cn.prepareStatement(ESTADIA);
        declaracionEstadia.setInt(1, 17);
        ResultSet result = declaracionEstadia.executeQuery();
        while(result.next()){
            tarifa = new Tarifas();
            tarifa.setId(result.getInt("id"));
            tarifa.setDescripcion(result.getString("descripcion"));
            tarifa.setTotal(result.getFloat("total"));
            list.add(tarifa);
        }
        login.Desconectar();
        return list;
    }
}
