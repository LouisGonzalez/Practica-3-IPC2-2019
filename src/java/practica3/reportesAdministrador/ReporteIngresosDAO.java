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
import practica3.objetos.BeanReporteAdmin;
import practica3.objetos.IngresosDTO;
import practica3.reportesFarmacia.ReporteVentasMedicamentosDAO;
import practica3.reportesSupervisor.EmpleadosDespedidosDAO;

/**
 *
 * @author luisGonzalez
 */
public class ReporteIngresosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_VENTAS = "SELECT m.id FROM Factura f JOIN Ventas_factura v ON f.id = v.id_factura JOIN Medicamentos m ON v.id_medicamento = m.id WHERE f.estado = ? AND f.tipo = ? AND f.fecha_factura >= ? AND f.fecha_factura <= ? GROUP BY m.id";
    private static final String DATOS_MEDICAMENTO = "SELECT * FROM Medicamentos WHERE id = ?";
    private static final String SUMA_MEDICAMENTOS = "SELECT SUM(IF(v.id_medicamento = ?, v.total, 0)) total_medicamento FROM Factura f JOIN Ventas_factura v ON f.id = v.id_factura";
    private static final String LISTADO_GENERAL = "SELECT * FROM Factura WHERE estado = ? AND tipo = ? AND fecha_factura >= ? AND fecha_factura <= ?";
    private static final String ESTADO = "CANCELADA";
    private static final String TIPO1 = "MEDICINA";
    private static final String TIPO2 = "CONSULTA";
    private static final String TIPO3 = "HOSPITALIZACION";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimir(){
        File objetoFile = new File("ReporteIngresos.pdf");
        try {
            Desktop.getDesktop().open(objetoFile);
        } catch (IOException ex) {
            Logger.getLogger(ReporteIngresosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerListadoIngresos(Date fecha1, Date fecha2){
        JasperPrint jasperPrint2;
        try {
            jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesAdministrador/ReporteIngresos.jasper"), null, new JRBeanCollectionDataSource(listarIngresos(fecha1, fecha2)));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteIngresos.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReporteVentasMedicamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private ArrayList<IngresosDTO> listarIngresos(Date fecha1, Date fecha2) throws SQLException{
        ArrayList<IngresosDTO> list = new ArrayList<>();
        obtenerConexion();
        IngresosDTO general = new IngresosDTO(listarFarmacia(fecha1, fecha2), listarOpciones(fecha1, fecha2, TIPO2), listarOpciones(fecha1, fecha2, TIPO3));
        
        list.add(general);
        login.Desconectar();
        return list;    
    }
    
    private ArrayList<BeanReporteAdmin> listarFarmacia(Date fecha1, Date fecha2) throws SQLException{
        ArrayList<BeanReporteAdmin> list = new ArrayList<>();
        PreparedStatement declaracionIngresos = cn.prepareStatement(LISTADO_VENTAS);
        declaracionIngresos.setString(1, ESTADO);
        declaracionIngresos.setString(2, TIPO1);
        declaracionIngresos.setDate(3, fecha1);
        declaracionIngresos.setDate(4, fecha2);
        ResultSet result = declaracionIngresos.executeQuery();
        while(result.next()){
            BeanReporteAdmin bean = new BeanReporteAdmin();
            bean.setId_medicamento(result.getInt("id"));
            PreparedStatement declaracionDatos = cn.prepareStatement(DATOS_MEDICAMENTO);
            declaracionDatos.setInt(1, bean.getId_medicamento());
            ResultSet result3 = declaracionDatos.executeQuery();
            while(result3.next()){
                bean.setNombre_medicamento(result3.getString("nombre"));
            }
            PreparedStatement declaracionSuma = cn.prepareStatement(SUMA_MEDICAMENTOS);
            declaracionSuma.setInt(1, bean.getId_medicamento());
            ResultSet result2 = declaracionSuma.executeQuery();
            while(result2.next()){
                bean.setTotal_farmacia(result2.getFloat("total_medicamento"));
                bean.setAcumulado_farmacia(bean.getTotal_farmacia());
            }
            list.add(bean);
        }
        return list;
    }
    
    private ArrayList<BeanReporteAdmin> listarOpciones(Date fecha1, Date fecha2, String tipo) throws SQLException{
        ArrayList<BeanReporteAdmin> list = new ArrayList<>();
        PreparedStatement declaracionConsulta = cn.prepareStatement(LISTADO_GENERAL);
        declaracionConsulta.setString(1, ESTADO);
        declaracionConsulta.setString(2, tipo);
        declaracionConsulta.setDate(3, fecha1);
        declaracionConsulta.setDate(4, fecha2);
        ResultSet result = declaracionConsulta.executeQuery();
        while(result.next()){
            BeanReporteAdmin bean = new BeanReporteAdmin();            
            if(tipo.equals(TIPO2)){
                bean.setNombre_consulta(result.getString("nombres"));
                bean.setApellido_consulta(result.getString("apellidos"));
                bean.setFecha_consulta(result.getDate("fecha_factura"));
                bean.setTotal_consulta(result.getFloat("total"));
                bean.setAcumulado_consulta(bean.getTotal_consulta());
            } else if(tipo.equals(TIPO3)){
                bean.setNombre_hospital(result.getString("nombres"));
                bean.setApellido_hospital(result.getString("apellidos"));
                bean.setFecha_hospital(result.getDate("fecha_factura"));
                bean.setTotal_hospital(result.getFloat("total"));
                bean.setAcumulado_hospital(bean.getTotal_hospital());
            }
            list.add(bean);
        }
        return list;
    }
    
   
}
