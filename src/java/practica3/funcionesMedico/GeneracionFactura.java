package practica3.funcionesMedico;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;

/**
 *
 * @author luisGonzalez
 */
public class GeneracionFactura {
    
    private static Connection cn;
    private static Conexion login;
    private static final String PRECIO = "SELECT * FROM Medicamentos WHERE nombre = ?";
    private static final String CREACION_FACTURA = "INSERT INTO Factura (id, nombres, apellidos, ciudad, fecha_factura, estado, tipo, total, nit, id_empleado_medico, id_empleado_venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ID_FACTURA = "SELECT * FROM Factura ORDER BY id DESC LIMIT 1";
    private static final String CREACION_EVENTO = "INSERT INTO Ventas_factura (id, id_factura, id_medicamento, cant_producto, total, estado, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ID_MEDICINA = "SELECT * FROM Medicamentos WHERE nombre= ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void totalFactura(float cant, HttpServletRequest request, HttpServletResponse response, int x, HttpSession session) throws SQLException{
        obtenerConexion();
        float precioVenta, totalUnitario;
        int cantMedicamento;
        String nombreMedicamento;
        Facturas factura = new Facturas();
        //datos de la persona a quien se hace la factura
        retornoDatos(factura, request);
        for(int i = 0; i < x; i++){
            precioVenta = calcularPrecio(request, response, i);   
            //total final de toda la factura
            cant += Integer.parseInt(request.getParameter("cantMedicina"+i)) * precioVenta;
            //datos de la factura
            totalUnitario = Integer.parseInt(request.getParameter("cantMedicina"+i)) * precioVenta;
            cantMedicamento = Integer.parseInt(request.getParameter("cantMedicina"+i));
            nombreMedicamento = request.getParameter("medicinas"+i);
            //almacenamiento de datos para el momento de que la pagina sea refrescada
            session.setAttribute("nombreMedicamento"+i, nombreMedicamento);
            session.setAttribute("cantMedicamento"+i, cantMedicamento);
            session.setAttribute("totalUnitario"+i, totalUnitario);
        }
        factura.setTotal(cant);
        session.setAttribute("datosPersonales", factura);       
        login.Desconectar();
        
    }
    
    //metodo para agregar valores a la factura
    private Facturas retornoDatos(Facturas factura, HttpServletRequest request){
        factura.setNombres(request.getParameter("nombres"));
        factura.setApellidos(request.getParameter("apellidos"));
        factura.setCiudad(request.getParameter("ciudad"));
        factura.setNit(Integer.parseInt(request.getParameter("nit")));
        factura.setFecha_factura(Date.valueOf(request.getParameter("fecha")));
        return factura;
    }
    
    //metodo que calcula el precio unitario por cada medicamento recetado
    private float calcularPrecio(HttpServletRequest request, HttpServletResponse response, int i) throws SQLException{
        obtenerConexion();
        float precioVenta = 0;
        PreparedStatement declaracionPrecio = cn.prepareStatement(PRECIO);
        declaracionPrecio.setString(1, request.getParameter("medicinas" + i));
        ResultSet result = declaracionPrecio.executeQuery();
        while (result.next()) {
            precioVenta = result.getFloat("precio_venta");
        }
        login.Desconectar();
        return precioVenta;
    }
    
    public void crearFactura(Facturas factura, int id_empleado_medico, int id) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionFactura = cn.prepareStatement(CREACION_FACTURA);
        declaracionFactura.setInt(1, 0);
        declaracionFactura.setString(2, factura.getNombres());
        declaracionFactura.setString(3, factura.getApellidos());
        declaracionFactura.setString(4, factura.getCiudad());
        declaracionFactura.setDate(5, factura.getFecha_factura());
        declaracionFactura.setString(6, "PENDIENTE");
        declaracionFactura.setString(7, "MEDICINA");
        declaracionFactura.setFloat(8, factura.getTotal());
        declaracionFactura.setInt(9, factura.getNit());
        declaracionFactura.setInt(10, id_empleado_medico);
        declaracionFactura.setInt(11, id);
        declaracionFactura.executeUpdate();
        login.Desconectar();
    }
    
    //metodo que guarda cada venta de una factura especifica
    public void crearEventosFactura(int numFilas, HttpSession session) throws SQLException{
        obtenerConexion();
        int idMedicamento, cantProducto;
        float total;
        int idFactura = obtenerIdFactura();
        for(int i = 0; i < numFilas; i++){
            idMedicamento = obtenerIdMedicina((String)session.getAttribute("nombreMedicamento"+i));
            cantProducto = (int) session.getAttribute("cantMedicamento"+i);
            total = (float) session.getAttribute("totalUnitario"+i);
            PreparedStatement declaracionEvento = cn.prepareStatement(CREACION_EVENTO);
            declaracionEvento.setInt(1, 0);
            declaracionEvento.setInt(2, idFactura);
            declaracionEvento.setInt(3, idMedicamento);
            declaracionEvento.setInt(4, cantProducto);
            declaracionEvento.setFloat(5, total);
            declaracionEvento.setString(6, "PENDIENTE");
            declaracionEvento.setString(7, (String) session.getAttribute("nombreMedicamento"+i));
            declaracionEvento.executeUpdate();
        }
        login.Desconectar();
    }
    
    //metodo que obtiene el id de la factura recien creada
    private int obtenerIdFactura() throws SQLException{
        obtenerConexion();
        int idFactura = 0;
        PreparedStatement declaracionId = cn.prepareStatement(ID_FACTURA);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            idFactura = result.getInt("id");
        }
        login.Desconectar();
        return idFactura;
    } 
    
    //metodo que obtiene el id de algun medicamento dentro de una factura
    private int obtenerIdMedicina(String nombre) throws SQLException{
        obtenerConexion();
        int idMedicina = 0;
        PreparedStatement declaracionId = cn.prepareStatement(ID_MEDICINA);
        declaracionId.setString(1, nombre);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            idMedicina = result.getInt("id");
        }
        login.Desconectar();
        return idMedicina;
    }
    
    
}
