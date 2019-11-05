package practica3.funcionesFarmaceutico;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;
import practica3.objetos.VentasFactura;

/**
 *
 * @author luisGonzalez
 */
public class ListadoPreVentasDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_PRE_VENTAS = "SELECT * FROM Factura WHERE estado = ?";
    private static final String LISTADO_DESGLOSADO = "SELECT * FROM Ventas_factura WHERE id_factura = ?";
    private static final String ESTADO = "PENDIENTE";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Facturas> listarPreVentas() throws SQLException{
        ArrayList<Facturas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionVenta = cn.prepareStatement(LISTADO_PRE_VENTAS);
        declaracionVenta.setString(1, ESTADO);
        ResultSet result = declaracionVenta.executeQuery();
        while(result.next()){
            Facturas factura = new Facturas();
            factura.setId(result.getInt("id"));
            factura.setNombres(result.getString("nombres"));
            factura.setApellidos(result.getString("apellidos"));
            factura.setCiudad(result.getString("ciudad"));
            factura.setFecha_factura(result.getDate("fecha_factura"));
            factura.setTotal(result.getFloat("total"));
            factura.setNit(result.getInt("nit"));
            factura.setId_empleado_medico(result.getInt("id_empleado_medico"));
            list.add(factura);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<VentasFactura> listaDesglosada(int idFactura) throws SQLException{
        ArrayList<VentasFactura> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionVenta = cn.prepareStatement(LISTADO_DESGLOSADO);
        declaracionVenta.setInt(1, idFactura);
        ResultSet result = declaracionVenta.executeQuery();
        while(result.next()){
            VentasFactura ventas = new VentasFactura();
            ventas.setId(result.getInt("id"));
            ventas.setId_factura(result.getInt("id_factura"));
            ventas.setId_medicamento(result.getInt("id_medicamento"));
            ventas.setCant_producto(result.getInt("cant_producto"));
            ventas.setTotal(result.getFloat("total"));
            ventas.setEstado(result.getString("estado"));
            list.add(ventas);        
        }
        login.Desconectar();
        return list;
    }
}
