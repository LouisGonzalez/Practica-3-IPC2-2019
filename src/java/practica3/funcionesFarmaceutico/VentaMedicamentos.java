package practica3.funcionesFarmaceutico;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.conexion.Conexion;
import practica3.objetos.Medicamentos;
import practica3.objetos.VentasFactura;

/**
 *
 * @author luisGonzalez
 */
public class VentaMedicamentos {
    
    private static Connection cn;
    private VentasFactura ventas;
    private static Conexion login;
    private static final String VENTAS_DESGLOSADAS = "SELECT * FROM Ventas_factura WHERE id_factura = ?";
    private static final String CANT_EXISTENCIA = "SELECT * FROM Medicamentos WHERE id = ?";
    
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    
    
    public float revisionExistencias(int idFactura, int idMedicamento, VentasFactura ventas) throws SQLException {
        obtenerConexion();
        float totalAgregado = 0;
        Medicamentos medicamento = new Medicamentos();
        PreparedStatement decExistencia = cn.prepareStatement(CANT_EXISTENCIA);
        decExistencia.setInt(1, idMedicamento);
        ResultSet result = decExistencia.executeQuery();
        while (result.next()) {
            medicamento.setCant_existencia(result.getInt("cant_existencia"));
            medicamento.setPrecio_venta(result.getFloat("precio_venta"));
            if (ventas.getCant_producto() > medicamento.getCant_existencia()) {

                totalAgregado = medicamento.getCant_existencia() * medicamento.getPrecio_venta();

            } else {
                totalAgregado = ventas.getTotal();
            }

        }
        login.Desconectar();
        return totalAgregado;
    }
    
    public int totalEnExistencia(int idFactura, int idMedicamento, VentasFactura ventas) throws SQLException{
        obtenerConexion();
        int existencias = 0;
        Medicamentos medicamento = new Medicamentos();
        PreparedStatement decExistencia = cn.prepareStatement(CANT_EXISTENCIA);
        decExistencia.setInt(1, idMedicamento);
        ResultSet result = decExistencia.executeQuery();
        while (result.next()) {
            medicamento.setCant_existencia(result.getInt("cant_existencia"));
            medicamento.setPrecio_venta(result.getFloat("precio_venta"));
            if (ventas.getCant_producto() > medicamento.getCant_existencia()) {
                existencias = medicamento.getCant_existencia();
            } else {
                existencias = ventas.getCant_producto();
            }

        }
        login.Desconectar();
        return existencias;
        
    }
    
    public void calculoTotal(int numFilas, HttpServletRequest request, HttpServletResponse response, VentasFactura venta, HttpSession session){
        obtenerConexion();
        float totalFinal = 0;
        for(int i = 1; i <= numFilas; i++){
            float total = Float.parseFloat(request.getParameter("total"+i));
            if(request.getParameter("confirmacion"+i) != null){
                totalFinal += total;
                
            } 
        }
        session.setAttribute("totalFinal", totalFinal);
        System.out.println(totalFinal);
    }
    
    
}
