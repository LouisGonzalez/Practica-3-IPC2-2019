package practica3.reportesFarmacia;

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
import practica3.conexion.Conexion;
import practica3.objetos.FacturasDTO;
import practica3.objetos.VentasFactura;
import practica3.reportesSupervisor.EmpleadosDespedidosDAO;

/**
 *
 * @author luisGonzalez
 */
public class ReporteVentasEmpleadoDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_EMPLEADOS = "SELECT * FROM Empleados e JOIN Factura f ON e.id = f.id_empleado_venta WHERE f.estado = ? AND f.tipo = ?";
    private static final String LISTADO_FILTRO_EMPLEADO = "SELECT * FROM Empleados e JOIN Factura f ON e.id = f.id_empleado_venta WHERE f.estado = ? AND f.tipo = ? AND e.nombres = ?";
    private static final String LISTADO_FILTRO_CUI = "SELECT * FROM Empleados e JOIN Factura f ON e.id = f.id_empleado_venta WHERE f.estado = ? AND f.tipo = ? AND e.cui = ?";
    private static final String VENTAS_EMPLEADOS = "SELECT * FROM Ventas_factura WHERE id_factura = ? AND estado = ?";
    private static final String ESTADO = "CANCELADA";
    private static final String TIPO = "MEDICINA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void imprimirReporte(HttpServletRequest request){
        obtenerConexion();
        String[] parametro = request.getParameterValues("filtro");
        try {
            for (String parametro1 : parametro) {
                switch (parametro1) {
                    case "opcion1":
                        String nombreEmpleado = request.getParameter("nombreEmpleado");
                        obtenerListaEmpleados(listarFiltro1(nombreEmpleado));
                        break;
                    case "opcion2":
                        int cui = Integer.parseInt(request.getParameter("cuiEmpleado"));
                        obtenerListaEmpleados(listarFiltro2(cui));
                        break;
                    case "opcion3":
                        obtenerListaEmpleados(listarEmpleados());
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReporteVentasEmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        login.Desconectar();
    }
    
    public void imprimir(){
        File objetoFile = new File("ReporteVentasEmpleados.pdf");
        try {
            Desktop.getDesktop().open(objetoFile);
        } catch (IOException ex) {
            Logger.getLogger(ReporteVentasEmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerListaEmpleados(ArrayList<FacturasDTO> list){
        JasperPrint jasperPrint2;
            try {
            jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/practica3/reportesFarmacia/ReporteVentasEmpleado.jasper"), null, new JRBeanCollectionDataSource(list));
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteVentasEmpleados.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(EmpleadosDespedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public ArrayList<FacturasDTO> listarEmpleados() throws SQLException{
        ArrayList<FacturasDTO> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEmpleados = cn.prepareStatement(LISTADO_EMPLEADOS);
        declaracionEmpleados.setString(1, ESTADO);
        declaracionEmpleados.setString(2, TIPO);
        ResultSet result = declaracionEmpleados.executeQuery();
        while(result.next()){
            FacturasDTO factura = new FacturasDTO(listarVentas(result.getInt("f.id")));
            factura.setId_empleado_venta(result.getInt("e.id"));
            factura.setNombre_empleado(result.getString("e.nombres"));
            factura.setApellido_empleado(result.getString("e.apellidos"));
            factura.setId(result.getInt("f.id"));
            factura.setFecha_factura(result.getDate("f.fecha_factura"));
            factura.setTotal(result.getFloat("f.total"));
            list.add(factura);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<FacturasDTO> listarFiltro1(String nombreEmpleado) throws SQLException{
        ArrayList<FacturasDTO> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEmpleados = cn.prepareStatement(LISTADO_FILTRO_EMPLEADO);
        declaracionEmpleados.setString(1, ESTADO);
        declaracionEmpleados.setString(2, TIPO);
        declaracionEmpleados.setString(3, nombreEmpleado);
        ResultSet result = declaracionEmpleados.executeQuery();
        while(result.next()){
            FacturasDTO factura = new FacturasDTO(listarVentas(result.getInt("f.id")));
            factura.setId_empleado_venta(result.getInt("e.id"));
            factura.setNombre_empleado(result.getString("e.nombres"));
            factura.setApellido_empleado(result.getString("e.apellidos"));
            factura.setId(result.getInt("f.id"));
            factura.setFecha_factura(result.getDate("f.fecha_factura"));
            factura.setTotal(result.getFloat("f.total"));
            list.add(factura);
            
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<FacturasDTO> listarFiltro2(int cui) throws SQLException{
        ArrayList<FacturasDTO> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionEmpleados = cn.prepareStatement(LISTADO_FILTRO_CUI);
        declaracionEmpleados.setString(1, ESTADO);
        declaracionEmpleados.setString(2, TIPO);
        declaracionEmpleados.setInt(3, cui);
        ResultSet result = declaracionEmpleados.executeQuery();
        while(result.next()){
            FacturasDTO factura = new FacturasDTO(listarVentas(result.getInt("f.id")));
            factura.setId_empleado_venta(result.getInt("e.id"));
            factura.setNombre_empleado(result.getString("e.nombres"));
            factura.setApellido_empleado(result.getString("e.apellidos"));
            factura.setId(result.getInt("f.id"));
            factura.setFecha_factura(result.getDate("f.fecha_factura"));
            factura.setTotal(result.getFloat("f.total"));
            list.add(factura);
        }
        login.Desconectar();
        return list;
    }
    
    
    public ArrayList<VentasFactura> listarVentas(int idFactura) throws SQLException{
        ArrayList<VentasFactura> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionVenta = cn.prepareStatement(VENTAS_EMPLEADOS);
        declaracionVenta.setInt(1, idFactura);
        declaracionVenta.setString(2, ESTADO);
        ResultSet result = declaracionVenta.executeQuery();
        while(result.next()){
            VentasFactura ventas = new VentasFactura();
            ventas.setId_medicamento(result.getInt("id_medicamento"));
            ventas.setCant_producto(result.getInt("cant_producto"));
            ventas.setDescripcion(result.getString("descripcion"));
            ventas.setTotal(result.getFloat("total"));
            list.add(ventas);
        }
        login.Desconectar();
        return list;
    }
    
}
