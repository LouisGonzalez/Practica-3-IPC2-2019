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
    private static Conexion login;
    private static final String CANT_EXISTENCIA = "SELECT * FROM Medicamentos WHERE id = ?";
    private static final String ID_VENTAS = "SELECT * FROM Ventas_factura WHERE id = ?";
    private static final String NUEVO_TOTAL = "UPDATE Factura SET total = ? WHERE id = ?";
    private static final String VENTA_CANCELADA = "UPDATE Factura SET estado = ? WHERE id = ?";
    private static final String ESTADO = "CANCELADA";
    private static final String ELEMENTOS_INUTILIZADOS = "DELETE FROM Ventas_factura WHERE id = ?";
    private static final String CONFIRMACION_EVENTO = "UPDATE Ventas_factura SET estado = ? WHERE id = ?";
    private static final String CAMBIO_TOTAL_EVENTO = "UPDATE Ventas_factura SET total = ? WHERE id = ?";
    
    private static final String ELEMENTOS_VENTAS_FACTURA = "SELECT * FROM Ventas_factura WHERE id = ?";
    private static final String RESTA_MEDICAMENTOS = "UPDATE Medicamentos SET cant_existencia = ? WHERE id = ?";
    
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
        float totalFinal = 0;
        boolean checkbox;      
        for(int i = 1; i <= numFilas; i++){
            float total = Float.parseFloat(request.getParameter("total"+i));
            if(request.getParameter("confirmacion"+i) != null){
                totalFinal += total;
                checkbox = true;
                session.setAttribute("verificadorCasilla"+i, checkbox);                
            } else {
                checkbox = false;
                session.setAttribute("verificadorCasilla"+i, checkbox);
            } 
        }
        session.setAttribute("totalFinal", totalFinal);
    }
    
    //confirma la venta dentro del sistema
    public void confirmarCompra(int idFactura, float totalFinal, int numFilas, HttpServletRequest request, HttpSession session) throws SQLException{
        cambiarEstadoFactura(idFactura);
        cambiarTotal(totalFinal, idFactura);
        comprarMedicamentos(numFilas, request, session);
        
        
    }
    
    private void cambiarEstadoFactura(int idFactura) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionEstado = cn.prepareStatement(VENTA_CANCELADA);
        declaracionEstado.setString(1, ESTADO);
        declaracionEstado.setInt(2, idFactura);
        declaracionEstado.executeUpdate();
        login.Desconectar();
    }
    
    private void cambiarTotal(float totalFinal, int idFactura) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionTotal = cn.prepareStatement(NUEVO_TOTAL);
        declaracionTotal.setFloat(1, totalFinal);
        declaracionTotal.setInt(2, idFactura);
        declaracionTotal.executeUpdate();
        login.Desconectar();
    }
    
    private void comprarMedicamentos(int numFilas, HttpServletRequest request, HttpSession session) throws SQLException{
        int idVenta;
        for(int i = 1; i <= numFilas; i++){
            idVenta = (int) session.getAttribute("id"+i);
            if(request.getParameter("confirmacion"+i) != null){
                efectuarCompraFila(idVenta);
                cambiarTotalEvento(session, i, idVenta);
                cambioMedicamentos(idVenta, i, session);
            } else {
                eliminarFila(idVenta);
            }
        }       
    }
     
    //elimina las filas en las que el usuario no ejercera compra alguna
    private void eliminarFila(int idVenta) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionFila = cn.prepareStatement(ELEMENTOS_INUTILIZADOS);
        declaracionFila.setInt(1, idVenta);
        declaracionFila.executeUpdate();
        login.Desconectar();
    }
      
    private void cambiarTotalEvento(HttpSession session, int i, int idVenta) throws SQLException{
        obtenerConexion();
        float totalFinal = (float) session.getAttribute("totalFinal"+i);
        PreparedStatement declaracionTotal = cn.prepareStatement(CAMBIO_TOTAL_EVENTO);
        declaracionTotal.setFloat(1, totalFinal);
        declaracionTotal.setInt(2, idVenta);
        declaracionTotal.executeUpdate();
        login.Desconectar();
    }
    
    
    private void efectuarCompraFila(int idVenta) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionFila = cn.prepareStatement(CONFIRMACION_EVENTO);
        declaracionFila.setString(1, ESTADO);
        declaracionFila.setInt(2, idVenta);
        declaracionFila.executeUpdate();
        login.Desconectar();
    }  
    
    private void cambioMedicamentos(int idVenta, int i, HttpSession session) throws SQLException{
        obtenerConexion();
        int idMedicamento;
        int cantVenta = (int) session.getAttribute("cantFinal"+i);
        PreparedStatement declaracionElementos = cn.prepareStatement(ELEMENTOS_VENTAS_FACTURA);
        declaracionElementos.setInt(1, idVenta);
        ResultSet result = declaracionElementos.executeQuery();
        while(result.next()){
            idMedicamento = result.getInt("id_medicamento");
            restaMedicamentos(cantVenta, idMedicamento);
        }
        login.Desconectar();
    }
    
    private void restaMedicamentos(int cantVenta, int idMedicamento) throws SQLException{
        int cantTotal, nuevaCantidad;
        PreparedStatement declaracionCantidad = cn.prepareStatement(CANT_EXISTENCIA);
        declaracionCantidad.setInt(1, idMedicamento);
        ResultSet result = declaracionCantidad.executeQuery();
        while(result.next()){
            cantTotal = result.getInt("cant_existencia");
            nuevaCantidad = cantTotal - cantVenta;
            modificarCantidad(nuevaCantidad, idMedicamento);
        }
    }
    
    private void modificarCantidad(int nuevaCantidad, int idMedicamento) throws SQLException{
        PreparedStatement declaracionCantidad = cn.prepareStatement(RESTA_MEDICAMENTOS);
        declaracionCantidad.setInt(1, nuevaCantidad);
        declaracionCantidad.setInt(2, idMedicamento);
        declaracionCantidad.executeUpdate();
    }
    
}
