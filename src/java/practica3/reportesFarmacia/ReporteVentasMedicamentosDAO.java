package practica3.reportesFarmacia;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;
import practica3.objetos.MedicamentosDTO;
import practica3.reportesSupervisor.EmpleadosDespedidosDAO;

/**
 *
 * @author luisGonzalez
 */
public class ReporteVentasMedicamentosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_MEDICAMENTOS = "SELECT * FROM Medicamentos ORDER BY id";
    private static final String LISTADO_GANANCIAS = "SELECT SUM(IF(m.id = ?, v.total, 0)) total_medicamento FROM Medicamentos m JOIN Ventas_factura v ON m.id = v.id_medicamento WHERE estado = ?";
    private static final String LISTADO_DESGLOSADO = "SELECT * FROM Factura f JOIN Ventas_factura v ON f.id = v.id_factura WHERE v.id_medicamento = ? AND f.estado = ? AND f.fecha_factura >= ? AND f.fecha_factura <= ?";
    private static final String ESTADO = "CANCELADA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimir(){
        File objetoFile = new File("ReporteMedicamentosGanancias.pdf");
        try {                     
            Desktop.getDesktop().open(objetoFile);
        } catch (IOException ex) {
            Logger.getLogger(ReporteVentasMedicamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerListarMedicamentos(Date fecha1, Date fecha2){
        JasperPrint jasperPrint2;
        try {
            jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesFarmacia/ReporteMedicamentosGanancias.jasper"), null, new JRBeanCollectionDataSource(listarMedicamentos(fecha1, fecha2)));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteMedicamentosGanancias.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReporteVentasMedicamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<MedicamentosDTO> listarMedicamentos(Date fecha1, Date fecha2) throws SQLException{
        ArrayList<MedicamentosDTO> list = new ArrayList<>();
        obtenerConexion();  
        PreparedStatement decMedicamento = cn.prepareStatement(LISTADO_MEDICAMENTOS);
        ResultSet result = decMedicamento.executeQuery();
        while(result.next()){
            MedicamentosDTO medicamento = new MedicamentosDTO(listarGanancias(result.getInt("id"), fecha1, fecha2));
            medicamento.setId(result.getInt("id"));
            medicamento.setNombre(result.getString("nombre"));
            medicamento.setCosto_unitario(result.getFloat("costo_unitario"));
            medicamento.setPrecio_venta(result.getFloat("precio_venta"));
            PreparedStatement declaracionGanancia = cn.prepareStatement(LISTADO_GANANCIAS);
            declaracionGanancia.setInt(1, medicamento.getId());
            declaracionGanancia.setString(2, ESTADO);
            ResultSet result2 = declaracionGanancia.executeQuery();
            while(result2.next()){
                medicamento.setGanancia(result2.getFloat("total_medicamento"));
            }
            list.add(medicamento);
        }
        login.Desconectar();
        
        return list;
    }
    
    private ArrayList<Facturas> listarGanancias(int idMedicamento, Date fecha1, Date fecha2) throws SQLException{
        ArrayList<Facturas> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionGanancia = cn.prepareStatement(LISTADO_DESGLOSADO);
        declaracionGanancia.setInt(1, idMedicamento);
        declaracionGanancia.setString(2, ESTADO);
        declaracionGanancia.setDate(3, fecha1);
        declaracionGanancia.setDate(4, fecha2);
        ResultSet result = declaracionGanancia.executeQuery();
        while(result.next()){
            Facturas medicamento = new Facturas();
            medicamento.setId(result.getInt("id"));
            medicamento.setNombres(result.getString("f.nombres"));
            medicamento.setApellidos(result.getString("f.apellidos"));
            medicamento.setFecha_factura(result.getDate("f.fecha_factura"));
            medicamento.setTotal(result.getFloat("v.total"));
            medicamento.setCant_producto(result.getInt("v.cant_producto"));     
            list.add(medicamento);
        }
        login.Desconectar();
        return list;
    }
    
    
    
    
    
    
}
