package practica3.funcionesAutomaticas;

import java.sql.*;
import java.util.Calendar;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class PagoEmpleados {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CONSULTAS_EMPLEADO = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado WHERE e.id = ? AND estado = ?";
    private static final String CREACION_PAGO = "INSERT INTO Pagos_empleados (id, id_empleado, no_mes, total, fecha_pago) VALUES (?, ?, ?, ?, ?)";
    private static final String EXISTENCIAS = "SELECT * FROM Pagos_empleados WHERE id_empleado = ?";
    private static final String MESES_PAGO = "SELECT * FROM Pagos_empleados WHERE id_empleado = ? ORDER BY id DESC LIMIT 1";
    private static final String PAGO_EMPLEADO = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    private static final String ESTADO = "ACTIVO";
    private int dias, meses, ultimoMes;
    private Calendar fecha;
    private boolean existencia;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    
    public void calcularDiasEmpleado(int idEmpleado) throws SQLException{
        obtenerConexion();
        Date fechaContrato = null;
        Date fechaActual = null;
        java.util.Date fechaHoy = new java.util.Date();
        fecha = Calendar.getInstance();
        fecha.setTime(fechaHoy);
        PreparedStatement declaracionId = cn.prepareStatement(CONSULTAS_EMPLEADO);
        declaracionId.setInt(1, idEmpleado);
        declaracionId.setString(2, ESTADO);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            fechaContrato = result.getDate("fecha_historial_laboral");
        }
        fechaActual = new Date(fecha.getTime().getTime());
        dias = verDiasTotales(fechaContrato, fechaActual);
        meses = dias / 30;
        existencia = verExistenciaPagos(idEmpleado);    
        if(existencia == true){
            ultimoMes = verUltimoMes(idEmpleado);
            if(meses > ultimoMes){
                crearPago(idEmpleado, meses, fechaActual);
            }
        } else {
            crearPago(idEmpleado, meses, fechaActual);
        }   
        login.Desconectar();
    }
    
    private int verDiasTotales(Date fechaContrato, Date fechaActual){
        int diasTotales = 0;
        diasTotales = (int) ((fechaActual.getTime() - fechaContrato.getTime())/86400000);
        return diasTotales;
    }
    
    private boolean verExistenciaPagos(int idEmpleado) throws SQLException{
        PreparedStatement decExistencia = cn.prepareStatement(EXISTENCIAS);
        decExistencia.setInt(1, idEmpleado);
        ResultSet result = decExistencia.executeQuery();
        login.Desconectar();
        return result.next();
    }
    
    private int verUltimoMes(int idEmpleado) throws SQLException{
        int noMes = 0;
        PreparedStatement declaracionMes = cn.prepareStatement(MESES_PAGO);
        declaracionMes.setInt(1, idEmpleado);
        ResultSet result = declaracionMes.executeQuery();
        while(result.next()){
            noMes = result.getInt("no_mes");
        }
        return noMes;
    }
      
    private void crearPago(int idEmpleado, int mes, Date fechaPago) throws SQLException{
        float pagoEmpleado = verPago(idEmpleado);
        PreparedStatement declaracionPago = cn.prepareStatement(CREACION_PAGO);
        declaracionPago.setInt(1, 0);
        declaracionPago.setInt(2, idEmpleado);
        declaracionPago.setInt(3, mes);
        declaracionPago.setFloat(4, pagoEmpleado);
        declaracionPago.setDate(5, fechaPago);
        declaracionPago.executeUpdate();     
    }
    
    private float verPago(int idEmpleado) throws SQLException{
        float pagoEmpleado = 0;
        PreparedStatement declaracionPago = cn.prepareStatement(PAGO_EMPLEADO);
        declaracionPago.setInt(1, idEmpleado);
        ResultSet result = declaracionPago.executeQuery();
        while(result.next()){
            pagoEmpleado = result.getFloat("salario_descuento");
        }
        return pagoEmpleado;
    }
    
    
    
    
    
}
