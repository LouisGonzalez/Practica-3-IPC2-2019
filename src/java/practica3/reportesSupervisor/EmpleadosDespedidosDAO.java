package practica3.reportesSupervisor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import practica3.conexion.Conexion;
import practica3.objetos.HistorialLaboral;

/**
 *
 * @author luisGonzalez
 */
public class EmpleadosDespedidosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_EMPLEADOS = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado WHERE fecha_historial_laboral >= ? AND fecha_historial_laboral <= ? AND n.estado = ?";
    private static final String LISTADO_SIN_FECHA = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado WHERE n.estado = ?";
    private static final String LISTADO_CON_AREA = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado WHERE fecha_historial_laboral >= ? AND fecha_historial_laboral <= ? AND area_trabajo = ? AND n.estado = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimir(){
        File objetoFile = new File("ReporteEmpleadosDespedidos.pdf");
        try {
            Desktop.getDesktop().open(objetoFile);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void imprimirReporteEmpleados(Date fechaInicial, Date fechaFinal, HttpServletRequest request, String area, HttpSession session){
        JasperPrint jasperPrint2;
        try {
            jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesSupervisor/ReporteEmpleadosDespedidos.jasper"), null, new JRBeanCollectionDataSource(listarEmpleados(fechaInicial, fechaFinal, request, area, session)));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteEmpleadosDespedidos.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<HistorialLaboral> listarEmpleados(Date fechaInicial, Date fechaFinal, HttpServletRequest request, String area, HttpSession session){
        ArrayList<HistorialLaboral> list = new ArrayList<>();
        obtenerConexion();
        try {
            if (request.getParameter("verificador") != null) {
                listarTipoEmpleadoArea(fechaInicial, fechaFinal, "RENUNCIA", list, area);
                listarTipoEmpleadoArea(fechaInicial, fechaFinal, "DESPEDIDO", list, area);
            } else {
                listarTipoEmpleado(fechaInicial, fechaFinal, "RENUNCIA", list);
                listarTipoEmpleado(fechaInicial, fechaFinal, "DESPEDIDO", list);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    public ArrayList<HistorialLaboral> listarEmpleado2() throws SQLException{
        obtenerConexion();
        ArrayList<HistorialLaboral> list = new ArrayList<>();
        listarEmpleadoSinFecha("RENUNCIA", list);
        listarEmpleadoSinFecha("DESPEDIDO", list);
        login.Desconectar();
        return list;
    }
    
    
    public ArrayList<HistorialLaboral> listarTipoEmpleado(Date fechaInicial, Date fechaFinal, String estado, ArrayList<HistorialLaboral> list) throws SQLException{
        PreparedStatement declaracionEmpleado = cn.prepareStatement(LISTADO_EMPLEADOS);
        declaracionEmpleado.setDate(1, fechaInicial);
        declaracionEmpleado.setDate(2, fechaFinal);
        declaracionEmpleado.setString(3, estado);
        ResultSet result = declaracionEmpleado.executeQuery();
        while(result.next()){
            HistorialLaboral empleado = new HistorialLaboral();
            empleado.setId_empleado(result.getInt("e.id"));
            empleado.setId(result.getInt("n.id"));
            empleado.setNombres(result.getString("e.nombres"));
            empleado.setApellidos(result.getString("e.apellidos"));
            empleado.setArea_trabajo(result.getString("e.area_trabajo"));
            empleado.setTipo_contratacion(result.getString("e.tipo_contratacion"));
            empleado.setNo_periodo_laboral(result.getInt("n.no_periodo_laboral"));
            empleado.setSalario_base(result.getFloat("n.salario_base"));
            empleado.setSalario_descuento(result.getFloat("n.salario_descuento"));
            empleado.setEstado(result.getString("n.estado"));   
            empleado.setYear(result.getInt("n.años_totales"));
            list.add(empleado);         
        } 
        return list;     
    }
    
    public ArrayList<HistorialLaboral> listarEmpleadoSinFecha(String estado, ArrayList<HistorialLaboral> list) throws SQLException{
        PreparedStatement declaracionEmpleado = cn.prepareStatement(LISTADO_SIN_FECHA);
        declaracionEmpleado.setString(1, estado);
        ResultSet result = declaracionEmpleado.executeQuery();
        while(result.next()){
            HistorialLaboral empleado = new HistorialLaboral();
            empleado.setId_empleado(result.getInt("e.id"));
            empleado.setId(result.getInt("n.id"));
            empleado.setNombres(result.getString("e.nombres"));
            empleado.setApellidos(result.getString("e.apellidos"));
            empleado.setArea_trabajo(result.getString("e.area_trabajo"));
            empleado.setTipo_contratacion(result.getString("e.tipo_contratacion"));
            empleado.setNo_periodo_laboral(result.getInt("n.no_periodo_laboral"));
            empleado.setSalario_base(result.getFloat("n.salario_base"));
            empleado.setSalario_descuento(result.getFloat("n.salario_descuento"));
            empleado.setEstado(result.getString("n.estado"));   
            empleado.setYear(result.getInt("n.años_totales"));
            list.add(empleado);         
        }
        return list;
    }
    
    private ArrayList<HistorialLaboral> listarTipoEmpleadoArea(Date fechaInicial, Date fechaFinal, String estado, ArrayList<HistorialLaboral> list, String area) throws SQLException{
        PreparedStatement declaracionEmpleado = cn.prepareStatement(LISTADO_CON_AREA);
        declaracionEmpleado.setDate(1, fechaInicial);
        declaracionEmpleado.setDate(2, fechaFinal);
        declaracionEmpleado.setString(3, area);
        declaracionEmpleado.setString(4, estado);
        ResultSet result = declaracionEmpleado.executeQuery();
        while(result.next()){
            HistorialLaboral empleado = new HistorialLaboral();
            empleado.setId_empleado(result.getInt("e.id"));
            empleado.setId(result.getInt("n.id"));
            empleado.setNombres(result.getString("e.nombres"));
            empleado.setApellidos(result.getString("e.apellidos"));
            empleado.setArea_trabajo(result.getString("e.area_trabajo"));
            empleado.setTipo_contratacion(result.getString("e.tipo_contratacion"));
            empleado.setNo_periodo_laboral(result.getInt("n.no_periodo_laboral"));
            empleado.setSalario_base(result.getFloat("n.salario_base"));
            empleado.setSalario_descuento(result.getFloat("n.salario_descuento"));
            empleado.setEstado(result.getString("n.estado"));   
            empleado.setYear(result.getInt("n.años_totales"));
            list.add(empleado);         
        } 
        return list;     
    }

}
