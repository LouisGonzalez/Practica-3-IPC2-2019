package practica3.reportesAdministrador;

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
import practica3.objetos.BeanReporteAdmin2;
import practica3.objetos.PerdidasDTO;
import practica3.reportesFarmacia.ReporteVentasMedicamentosDAO;
import practica3.reportesSupervisor.EmpleadosDespedidosDAO;

/**
 *
 * @author luisGonzalez
 */
public class ReportePerdidasDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_PERDIDAS = "SELECT * FROM Historial_medico h JOIN Costos_historial_medico c ON h.id = c.id_evento_historial JOIN No_historial_medico n ON c.id_historial_medico = n.id WHERE c.evento = ? AND h.fecha_evento >= ? AND h.fecha_evento <= ?";
    private static final String LISTADO_PAGOS_EMPLEADOS = "SELECT * FROM Empleados e JOIN Pagos_empleados p ON e.id = p.id_empleado WHERE fecha_pago >= ? AND p.fecha_pago <= ?";
    private static final String EVENTO = "CIRUGIA"; 
    private static final String EVENTO2 = "ALTA MEDICA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimir(){
        File objetoFile = new File("ReportePerdidas.pdf");
        try {
            Desktop.getDesktop().open(objetoFile);
        } catch (IOException ex) {
            Logger.getLogger(ReportePerdidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerListadoPerdidas(Date fecha1, Date fecha2){
        JasperPrint jasperPrint2;
        try {
            jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesAdministrador/ReportePerdidas.jasper"), null, new JRBeanCollectionDataSource(listarPerdidas(fecha1, fecha2)));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReportePerdidas.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReporteVentasMedicamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private ArrayList<PerdidasDTO> listarPerdidas(Date fecha1, Date fecha2) throws SQLException{
        ArrayList<PerdidasDTO> list = new ArrayList<>();
        obtenerConexion();
        PerdidasDTO general = new PerdidasDTO(listarPagoEmpleados(fecha1, fecha2), listarEventosHospitalizacion(fecha1, fecha2, EVENTO), listarEventosHospitalizacion(fecha1, fecha2, EVENTO2));
        list.add(general);
        login.Desconectar();
        return list;
    }   
    
    private ArrayList<BeanReporteAdmin2> listarEventosHospitalizacion(Date fecha1, Date fecha2, String tipo) throws SQLException{
        ArrayList<BeanReporteAdmin2> list = new ArrayList<>();
        PreparedStatement declaracionCirugia = cn.prepareStatement(LISTADO_PERDIDAS);
        declaracionCirugia.setString(1, tipo);
        declaracionCirugia.setDate(2, fecha1);
        declaracionCirugia.setDate(3, fecha2);
        ResultSet result = declaracionCirugia.executeQuery();
        while(result.next()){
            BeanReporteAdmin2 bean = new BeanReporteAdmin2();
            if(tipo.equals(EVENTO)){
                bean.setNombres_cirugia(result.getString("n.nombres"));
                bean.setApellidos_cirugia(result.getString("n.apellidos"));
                bean.setFecha_cirugia(result.getDate("h.fecha_evento"));
                bean.setTotal_cirugia(result.getFloat("c.total"));
                bean.setAcumulado_cirugia(bean.getTotal_cirugia());
            }
            else if(tipo.equals(EVENTO2)){
                bean.setNombres_alta_medica(result.getString("n.nombres"));
                bean.setApellidos_alta_medica(result.getString("n.apellidos"));
                bean.setFecha_alta_medica(result.getDate("h.fecha_evento"));
                bean.setTotal_alta_medica(result.getFloat("c.total"));
                bean.setAcumulado_alta_medica(bean.getTotal_alta_medica());
            }
            list.add(bean);
           
        }
        return list;
    }
    
    private ArrayList<BeanReporteAdmin2> listarPagoEmpleados(Date fecha1, Date fecha2) throws SQLException{
        ArrayList<BeanReporteAdmin2> list = new ArrayList<>();
        PreparedStatement declaracionPagos = cn.prepareStatement(LISTADO_PAGOS_EMPLEADOS);
        declaracionPagos.setDate(1, fecha1);
        declaracionPagos.setDate(2, fecha2);
        ResultSet result = declaracionPagos.executeQuery();
        while(result.next()){
            BeanReporteAdmin2 bean = new BeanReporteAdmin2();
            bean.setNombres_empleado(result.getString("e.nombres"));
            bean.setApellidos_empleado(result.getString("e.apellidos"));
            bean.setFecha_empleado(result.getDate("p.fecha_pago"));
            bean.setTotal_empleado(result.getFloat("p.total"));
            bean.setAcumulado_empleado(bean.getTotal_empleado());
            list.add(bean);
        }
        return list;
    }
    
    
   
    
}
