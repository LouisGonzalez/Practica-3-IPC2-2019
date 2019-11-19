package practica3.funcionesMedico;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;

/**
 *
 * @author luisGonzalez
 */
public class AltaMedica {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ACTUALIZACION_HISTORIAL = "UPDATE No_historial_medico SET estado = ? WHERE id = ?";   
    private static final String ACTUALIZACION_TOTAL = "UPDATE No_historial_medico SET total = ? WHERE id = ?";
    private static final String ACTUALIZACION_DIAS = "UPDATE No_historial_medico SET dias_hospitalizado = ? WHERE id = ?";
    private static final String NUEVO_EVENTO_HISTORIAL = "INSERT INTO Historial_medico (id, id_historial_medico, evento, cobro, fecha_evento, id_medicamento, id_empleado_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String NUEVO_EVENTO_COSTO = "INSERT INTO Costos_historial_medico (id, id_historial_medico, evento, total, id_evento_historial) VALUES (?, ?, ?, ?, ?)";
    private static final String DATO_HISTORIAL = "SELECT * FROM No_historial_medico WHERE id = ?";
    private static final String DATO_TARIFA = "SELECT * FROM Tarifas WHERE id = ?";
    private static final String NUEVA_FACTURA = "INSERT INTO Factura (id, nombres, apellidos, ciudad, fecha_factura, estado, tipo, total, nit, id_empleado_medico, id_empleado_venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ULTIMO_EVENTO = "SELECT * FROM Historial_medico ORDER BY id DESC LIMIT 1";
    private static final String EVENTO = "ALTA MEDICA";
    private static final String ESTADO = "CONCLUIDO";
    private static final String ESTADO_FACTURA = "PENDIENTE";
    private static final String TIPO = "HOSPITALIZACION";
    private float totalEstadia, tarifaDiaria, costoDiario, totalCosto;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearUltimoEvento(int idHistorial, Date fechaFinal, Facturas factura, int idMedico, int id) throws SQLException{
        obtenerConexion();
        tarifaDiaria = verTarifasEstadia(18);
        costoDiario = verTarifasEstadia(19);
        int diasTotales = calcularTotalDias(fechaFinal, idHistorial);
        totalEstadia = tarifaDiaria * diasTotales;
        totalCosto = costoDiario * diasTotales;
        actualizarHistorial(idHistorial);
        actualizarDias(idHistorial, diasTotales);
        eventoPagoEstadia(idHistorial, totalEstadia, fechaFinal, factura, idMedico, id);
        eventoCostoEstadia(idHistorial, totalCosto);
        login.Desconectar();        
    }
  
    private void actualizarHistorial(int idHistorial) throws SQLException{
        PreparedStatement declaracionHistorial = cn.prepareStatement(ACTUALIZACION_HISTORIAL);
        declaracionHistorial.setString(1, ESTADO);
        declaracionHistorial.setInt(2, idHistorial);
        declaracionHistorial.executeUpdate();
    }
    
    private void actualizarDias(int idHistorial, int dias) throws SQLException{
        PreparedStatement declaracionHistorial = cn.prepareStatement(ACTUALIZACION_DIAS);
        declaracionHistorial.setInt(1, dias);
        declaracionHistorial.setInt(2, idHistorial);
        declaracionHistorial.executeUpdate();
    } 
    
    
    private void eventoPagoEstadia(int idHistorial, float totalEstadia, Date fechaFinal, Facturas factura, int idMedico, int id) throws SQLException{
        PreparedStatement declaracionEvento = cn.prepareStatement(NUEVO_EVENTO_HISTORIAL);
        declaracionEvento.setInt(1, 0);
        declaracionEvento.setInt(2, idHistorial);
        declaracionEvento.setString(3, EVENTO);
        declaracionEvento.setFloat(4, totalEstadia);
        declaracionEvento.setDate(5, fechaFinal);
        declaracionEvento.setInt(6, 0);
        declaracionEvento.setInt(7, 0);
        declaracionEvento.executeUpdate();
        float nuevoTotal = calcularTotalAcumulado(idHistorial, totalEstadia);
        agregarNuevoTotal(nuevoTotal, idHistorial);
        crearFactura(factura, fechaFinal, nuevoTotal, idMedico, id);
    }
    
    private int obtenerIdEvento() throws SQLException{
        int idEvento = 0;
        PreparedStatement declaracionEvento =cn.prepareStatement(ULTIMO_EVENTO);
        ResultSet result = declaracionEvento.executeQuery();
        while(result.next()){
            idEvento = result.getInt("id");
        }
        return idEvento;
    }
    
    private void eventoCostoEstadia(int idHistorial, float costoTotal) throws SQLException{
        int idEvento = obtenerIdEvento();
        PreparedStatement declaracionCosto = cn.prepareStatement(NUEVO_EVENTO_COSTO);
        declaracionCosto.setInt(1, 0);
        declaracionCosto.setInt(2, idHistorial);
        declaracionCosto.setString(3, EVENTO);
        declaracionCosto.setFloat(4, costoTotal);
        declaracionCosto.setInt(5, idEvento);
        declaracionCosto.executeUpdate();
    }
    
    private void crearFactura(Facturas factura, Date fecha, float totalFinal, int idMedico, int id) throws SQLException{
        PreparedStatement declaracionFactura = cn.prepareStatement(NUEVA_FACTURA);
        declaracionFactura.setInt(1, 0);
        declaracionFactura.setString(2, factura.getNombres());
        declaracionFactura.setString(3, factura.getApellidos());
        declaracionFactura.setString(4, factura.getCiudad());
        declaracionFactura.setDate(5, fecha);
        declaracionFactura.setString(6, ESTADO_FACTURA);
        declaracionFactura.setString(7, TIPO);
        declaracionFactura.setFloat(8, totalFinal);
        declaracionFactura.setInt(9, factura.getNit());
        declaracionFactura.setInt(10, idMedico);
        declaracionFactura.setInt(11, id);
        declaracionFactura.executeUpdate();     
    }
    
    private int calcularTotalDias(Date fechaFinal, int idHistorial) throws SQLException{
        Date fechaInicial = verFechaInicial(idHistorial);
        int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime())/86400000);
        return dias;
    }
    
    private Date verFechaInicial(int idHistorial) throws SQLException{
        Date fechaInicial = null;
        PreparedStatement declaracionFecha = cn.prepareStatement(DATO_HISTORIAL);
        declaracionFecha.setInt(1, idHistorial);
        ResultSet result = declaracionFecha.executeQuery();
        while(result.next()){
            fechaInicial = result.getDate("fecha_historial_medico");
        }
        return fechaInicial;
    }
    
    private float verTarifasEstadia(int id) throws SQLException{
        float valor = 0;
        PreparedStatement declaracionTarifa = cn.prepareStatement(DATO_TARIFA);
        declaracionTarifa.setInt(1, id);
        ResultSet result = declaracionTarifa.executeQuery();
        while(result.next()){
            valor = result.getFloat("total");
        }
        return valor;
    }
    
    private float calcularTotalAcumulado(int idHistorial, float totalAgregado) throws SQLException{
        float totalInicial = 0;
        float totalFinal = 0;
        PreparedStatement declaracionTotal = cn.prepareStatement(DATO_HISTORIAL);
        declaracionTotal.setInt(1, idHistorial);
        ResultSet result = declaracionTotal.executeQuery();
        while(result.next()){
            totalInicial = result.getFloat("total");
        }
        totalFinal = totalInicial + totalAgregado;
        return totalFinal;  
    }
    
    private void agregarNuevoTotal(float nuevoTotal, int idHistorial) throws SQLException{
        PreparedStatement declaracionTotal = cn.prepareStatement(ACTUALIZACION_TOTAL);
        declaracionTotal.setFloat(1, nuevoTotal);
        declaracionTotal.setInt(2, idHistorial);
        declaracionTotal.executeUpdate();
    }
}
