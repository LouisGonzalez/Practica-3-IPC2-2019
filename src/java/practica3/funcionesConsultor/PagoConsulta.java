package practica3.funcionesConsultor;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Facturas;

/**
 *
 * @author luisGonzalez
 */
public class PagoConsulta {
    
    private static Connection cn;
    private static Conexion login;
    private static final String PAGO_CONSULTA = "INSERT INTO Factura (id, nombres, apellidos, ciudad, fecha_factura, estado, tipo, total, nit, id_empleado_medico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";    
    private static final String DATO_CONSULTA = "SELECT * FROM Consulta WHERE id = ?";
    private static final String TOTAL_CONSULTA = "SELECT * FROM Tarifas WHERE id = 2";
    private static final String CONSULTA_CANCELADA = "UPDATE Consulta SET estado = ? WHERE id = ?";
    private static final String ESTADO = "CANCELADA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    //crea la factura dentro del sistema
    public void pagoCita(Facturas factura) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionPago = cn.prepareStatement(PAGO_CONSULTA);
        declaracionPago.setInt(1, 0);
        declaracionPago.setString(2, factura.getNombres());
        declaracionPago.setString(3, factura.getApellidos());
        declaracionPago.setString(4, factura.getCiudad());
        declaracionPago.setDate(5, factura.getFecha_factura());
        declaracionPago.setString(6, factura.getEstado());
        declaracionPago.setString(7, factura.getTipo());
        declaracionPago.setFloat(8, factura.getTotal());
        declaracionPago.setInt(9, factura.getNit());
        declaracionPago.setInt(10, factura.getId_empleado_medico());
        declaracionPago.executeUpdate();
        login.Desconectar();
    }
    
    //metodo que sirve para saber datos especificos de una consulta
    public String datos(int idCita, String columna) throws SQLException{
        obtenerConexion();
        String dato = "";
        PreparedStatement declaracionDatos = cn.prepareStatement(DATO_CONSULTA);
        declaracionDatos.setInt(1, idCita);
        ResultSet result = declaracionDatos.executeQuery();
        while(result.next()){
            dato = result.getString(columna);
        }
        login.Desconectar();
        return dato;
    }
    
    //metodo para obtener el valor de las consultas
    public float calculoTotalConsulta() throws SQLException{
        obtenerConexion();
        float total = 0;
        PreparedStatement declaracionTotal = cn.prepareStatement(TOTAL_CONSULTA);
        ResultSet result = declaracionTotal.executeQuery();
        while(result.next()){
            total = result.getFloat("total");
        }
        login.Desconectar();
        return total;
    }
    
    //metodo que marca en la tabla Consulta que la consulta ha sido pagada por completo
    public void cancelarConsulta(int idConsulta) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionPago = cn.prepareStatement(CONSULTA_CANCELADA);
        declaracionPago.setString(1, ESTADO);
        declaracionPago.setInt(2, idConsulta);
        declaracionPago.executeUpdate();
        login.Desconectar();
    }
}
