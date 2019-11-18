package practica3.reportesSupervisor;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import practica3.conexion.Conexion;
import practica3.objetos.HistorialLaboral;
import practica3.reportesFarmacia.ReporteMedicamentos;

/**
 *
 * @author luisGonzalez
 */
public class EmpleadosContratadosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_EMPLEADOS = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado WHERE fecha_historial_laboral >= ? AND fecha_historial_laboral <= ? AND n.estado = ?";
    private static final String LISTADO_CON_AREA = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado WHERE fecha_historial_laboral >= ? AND fecha_historial_laboral <= ? AND area_trabajo = ? AND n.estado = ?";
    private static final String ESTADO = "ACTIVO";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimirReporteMedicamentos(Date fechaInicial, Date fechaFinal, HttpServletRequest request, String area){
        try {
            if(request.getParameter("verificador") != null){
                parametrosImpresion(listarEmpleadosArea(fechaInicial, fechaFinal, area));
            } else {
                parametrosImpresion(listarEmpleados(fechaInicial, fechaFinal));
            }
        } catch (SQLException | JRException ex) {
            Logger.getLogger(ReporteMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void parametrosImpresion(ArrayList<HistorialLaboral> empleados) throws JRException, SQLException{
        JasperPrint jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesSupervisor/ReporteEmpleados.jasper"), null, new JRBeanCollectionDataSource(empleados));
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteEmpleados.pdf"));
        JasperViewer view = new JasperViewer(jasperPrint2);
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        view.setVisible(true);
                
    }
    
    public ArrayList<HistorialLaboral> listarEmpleados(Date primerFecha, Date segundaFecha) throws SQLException{
        ArrayList<HistorialLaboral> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEmpleados = cn.prepareStatement(LISTADO_EMPLEADOS);
        declaracionEmpleados.setDate(1, primerFecha);
        declaracionEmpleados.setDate(2, segundaFecha);
        declaracionEmpleados.setString(3, ESTADO);
        ResultSet result = declaracionEmpleados.executeQuery();
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
        login.Desconectar();
        return list;
    }
    
    public ArrayList<HistorialLaboral> listarEmpleadosArea(Date primerFecha, Date segundaFecha, String area) throws SQLException{
        ArrayList<HistorialLaboral> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEmpleados = cn.prepareStatement(LISTADO_CON_AREA);
        declaracionEmpleados.setDate(1, primerFecha);
        declaracionEmpleados.setDate(2, segundaFecha);
        declaracionEmpleados.setString(3, area);
        declaracionEmpleados.setString(4, ESTADO);
        ResultSet result = declaracionEmpleados.executeQuery();
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
        login.Desconectar();
        return list;
    }
    
}
