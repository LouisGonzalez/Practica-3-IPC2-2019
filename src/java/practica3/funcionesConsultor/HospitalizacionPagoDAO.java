package practica3.funcionesConsultor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;

/**
 *
 * @author luisGonzalez
 */
public class HospitalizacionPagoDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO = "SELECT * FROM Factura WHERE estado = ? AND tipo = ?";
    private static final String ESTADO = "PENDIENTE";
    private static final String TIPO = "HOSPITALIZACION";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Facturas> listarHospitalizaciones() throws SQLException{
        ArrayList<Facturas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionListado = cn.prepareStatement(LISTADO);
        declaracionListado.setString(1, ESTADO);
        declaracionListado.setString(2, TIPO);
        ResultSet result = declaracionListado.executeQuery();
        while(result.next()){
            Facturas factura = new Facturas();
            factura.setId(result.getInt("id"));
            factura.setNombres(result.getString("nombres"));
            factura.setApellidos(result.getString("apellidos"));
            factura.setTotal(result.getFloat("total"));
            list.add(factura);
        }
        login.Desconectar();
        return list;
    }
}
