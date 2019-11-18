package practica3.reportesSupervisor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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
import practica3.objetos.Medicos;

/**
 *
 * @author luisGonzalez
 */
public class MedicosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_MEDICOS_PACIENTES = "SELECT * FROM Empleados e JOIN No_historial_laboral nh ON e.id = nh.id_empleado JOIN Medicos m ON e.id = m.id_empleado JOIN Trabajadores_paciente_hospitalizado t ON e.id = t.id_empleado JOIN No_historial_medico n ON n.id = t.id_historial_medico WHERE m.tipo = ? AND nh.estado = ?";
    private static final String LISTADO_MEDICOS = "SELECT * FROM Empleados e JOIN No_historial_laboral n ON e.id = n.id_empleado JOIN Medicos m ON e.id = m.id_empleado WHERE n.estado = ?";
    private static final String LISTADO_NO_ASIGNADOS = "SELECT * FROM Empleados e JOIN Medicos m ON e.id = m.id_empleado JOIN No_historial_laboral t ON e.id = t.id_empleado WHERE tipo = ? AND estado = ?";
    private static final String ASIGNACION_NO_ASIGNADOS = "SELECT * FROM Trabajadores_paciente_hospitalizado WHERE id_empleado = ?";
    private static final String TIPO = "Medico";
    private static final String ESTADO = "ACTIVO";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimirReporte(HttpServletRequest request){
        obtenerConexion();
        String[] parametro = request.getParameterValues("verificador");
        try {

            for (String parametro1 : parametro) {
                switch (parametro1) {
                    //REPORTES MEDICOS GENERAL
                    case "opcion1":
                        imprimirReporteMedicos(listarMedicos());
                        System.out.println("opcion");
                        break;
                    //REPORTES MEDICOS ASIGNADOS A UN PACIENTE
                    case "opcion2":
                        imprimirReporteMedicos(listarMedicosAsignados());
                        System.out.println("opcion2");
                        break;
                    //REPORTES MEDICOS SIN ASIGNACION
                    case "opcion3":
                        imprimirReporteMedicos(listarMedicosNoAsignados());
                        System.out.println("opcion3");
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        login.Desconectar();
    }
    
    public void imprimir(){
            File objetoFile = new File("ReporteMedicos.pdf");
        try {
            Desktop.getDesktop().open(objetoFile);
        } catch (IOException ex) {
            Logger.getLogger(MedicosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void imprimirReporteMedicos(ArrayList<Medicos> medico){
        JasperPrint jasperPrint2;
            try {
            jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesSupervisor/ReporteMedicos.jasper"), null, new JRBeanCollectionDataSource(medico));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteMedicos.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<Medicos> listarMedicosAsignados() throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        PreparedStatement declaracionMedicos = cn.prepareStatement(LISTADO_MEDICOS_PACIENTES);
        declaracionMedicos.setString(1, TIPO);
        declaracionMedicos.setString(2, ESTADO);
        ResultSet result = declaracionMedicos.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId_empleado(result.getInt("e.id"));
            medico.setNombres(result.getString("e.nombres"));
            medico.setApellidos(result.getString("e.apellidos"));
            medico.setEspecialidad(result.getString("m.especialidad"));
            medico.setSalario_base(result.getFloat("nh.salario_base"));
            medico.setSalario_descuento(result.getFloat("nh.salario_descuento"));
            medico.setId_historial_medico(result.getInt("n.id"));
            list.add(medico);
        }
        return list;
    }
    
    private ArrayList<Medicos> listarMedicos() throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        PreparedStatement declaracionMedicos = cn.prepareStatement(LISTADO_MEDICOS);
        declaracionMedicos.setString(1, ESTADO);
        ResultSet result = declaracionMedicos.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId_empleado(result.getInt("e.id"));
            medico.setNombres(result.getString("e.nombres"));
            medico.setApellidos(result.getString("e.apellidos"));
            medico.setEspecialidad(result.getString("m.especialidad"));
            medico.setSalario_base(result.getFloat("n.salario_base"));
            medico.setSalario_descuento(result.getFloat("n.salario_descuento"));
            medico.setId_historial_medico(0);
            list.add(medico);
        }
        return list;
    }
    
    private ArrayList<Medicos> listarMedicosNoAsignados() throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        PreparedStatement declaracionMedicos = cn.prepareStatement(LISTADO_NO_ASIGNADOS);
        declaracionMedicos.setString(1, TIPO);
        declaracionMedicos.setString(2, ESTADO);
        ResultSet result = declaracionMedicos.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId_empleado(result.getInt("e.id"));
            PreparedStatement declaracionId = cn.prepareStatement(ASIGNACION_NO_ASIGNADOS);
            declaracionId.setInt(1, medico.getId_empleado());
            ResultSet result2 = declaracionId.executeQuery();
            if(result2.next() == false){
                medico.setNombres(result.getString("e.nombres"));
                medico.setApellidos(result.getString("e.apellidos"));
                medico.setEspecialidad(result.getString("m.especialidad"));
                medico.setSalario_base(result.getFloat("t.salario_base"));
                medico.setSalario_descuento(result.getFloat("t.salario_descuento"));
                medico.setId_historial_medico(0);
                list.add(medico);
            }
        }
        return list;
    }
    
    

}
