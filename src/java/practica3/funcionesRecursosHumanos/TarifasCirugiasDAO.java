package practica3.funcionesRecursosHumanos;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Tarifas;

/**
 *
 * @author luisGonzalez
 */
public class TarifasCirugiasDAO {
    
    private static Connection cn;
    private static Conexion login;
    private Tarifas tarifa;
    private static final String LISTADO_PAGO = "SELECT * FROM Tarifas WHERE id > ? AND id < ?";
    private static final String LISTADO_COBRO = "SELECT * FROM Tarifas_cirugias ORDER BY id";
    private static final String LISTADO_COSTOS = "SELECT * FROM Costos_cirugias ORDER BY id";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
     public ArrayList<Tarifas> listarPagoMedico() throws SQLException{
        ArrayList<Tarifas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionPago = cn.prepareStatement(LISTADO_PAGO);
        declaracionPago.setInt(1, 15);
        declaracionPago.setInt(2, 18);
        ResultSet result = declaracionPago.executeQuery();
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
     
    public ArrayList<Tarifas> listarCobroCirugia() throws SQLException{
        ArrayList<Tarifas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionCobro = cn.prepareStatement(LISTADO_COBRO);
        ResultSet result = declaracionCobro.executeQuery();
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
    
    public ArrayList<Tarifas> listarCostosCirugia() throws SQLException{
        ArrayList<Tarifas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionCosto = cn.prepareStatement(LISTADO_COSTOS);
        ResultSet result = declaracionCosto.executeQuery();
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
