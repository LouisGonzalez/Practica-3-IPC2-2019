package practica3.funcionesFarmaceutico;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Medicamentos;

/**
 *
 * @author luisGonzalez
 */
public class CreacionMedicamentos {
    
    private static Connection cn;
    private static Conexion login;
    private static final String NUEVO_MEDICAMENTO = "INSERT INTO Medicamentos (id, nombre, descripcion, costo_unitario, precio_venta, cant_existencia, limite_existencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearMedicamento(Medicamentos medicamento) throws SQLException{
        obtenerConexion();
        PreparedStatement decMedicamento = cn.prepareStatement(NUEVO_MEDICAMENTO);
        decMedicamento.setInt(1, 0);
        decMedicamento.setString(2, medicamento.getNombre());
        decMedicamento.setString(3, medicamento.getDescripcion());
        decMedicamento.setFloat(4, medicamento.getCosto_unitario());
        decMedicamento.setFloat(5, medicamento.getPrecio_venta());
        decMedicamento.setInt(6, medicamento.getCant_existencia());
        decMedicamento.setInt(7, medicamento.getLimite_existencia());
        decMedicamento.executeUpdate();
        login.Desconectar();
    }
}
