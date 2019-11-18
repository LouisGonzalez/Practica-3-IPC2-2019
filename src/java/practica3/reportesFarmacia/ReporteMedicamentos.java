package practica3.reportesFarmacia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import practica3.conexion.Conexion;
import practica3.objetos.Medicamentos;



/**
 *
 * @author luisGonzalez
 */
public class ReporteMedicamentos {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_MEDICAMENTOS = "SELECT * FROM Medicamentos ORDER BY id";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimirReporteMedicamentos(){
        try {            
            JasperPrint jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesFarmacia/Reporte1.jasper"), null, new JRBeanCollectionDataSource(getReporteMedicamentos()));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteMedicamentos.pdf"));
            JasperViewer view = new JasperViewer(jasperPrint2);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
            
            
            
            
            
        } catch (SQLException | JRException ex) {
            Logger.getLogger(ReporteMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Medicamentos> getReporteMedicamentos() throws SQLException {
        obtenerConexion();
        List<Medicamentos> list = new ArrayList();
        PreparedStatement decMedicamento = cn.prepareStatement(LISTADO_MEDICAMENTOS);
        ResultSet result = decMedicamento.executeQuery();
        while(result.next()){
            Medicamentos nuevo = new Medicamentos();
            nuevo.setId(result.getInt("id"));
            nuevo.setNombre(result.getString("nombre"));
            nuevo.setCosto_unitario(result.getFloat("costo_unitario"));
            nuevo.setPrecio_venta(result.getFloat("precio_venta"));
            nuevo.setCant_existencia(result.getInt("cant_existencia"));
            nuevo.setLimite_existencia(result.getInt("limite_existencia"));
            list.add(nuevo);
        }
        login.Desconectar();
        return list;
    }
    
    
        
        

    
    
}
