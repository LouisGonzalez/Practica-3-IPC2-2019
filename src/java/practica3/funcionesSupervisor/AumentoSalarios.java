package practica3.funcionesSupervisor;

import java.sql.*;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class AumentoSalarios {
    
    private static Connection cn;
    private static Conexion login;
    private static final String SALARIO_ACTUAL = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    private static final String ACTUALIZACION_SALARIO = "UPDATE No_historial_laboral SET salario_base = ? WHERE id_empleado = ?";
    private static final String ACTUALIZACION_SALARIO_FINAL = "UPDATE No_historial_laboral SET salario_descuento = ? WHERE id_empleado = ?";
    private static final String VERIFICACION_CONTRATO = "SELECT * FROM Empleados WHERE id = ?";
    private static final String NUEVO_EVENTO_HISTORIAL = "INSERT INTO historial_laboral (id, id_historial_laboral, evento, monto, fecha_evento) VALUES (?, ?, ?, ?, ?)";
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public float salarioActual(int id_empleado) throws SQLException{
        obtenerConexion();
        float salarioActual = 0;
        PreparedStatement declaracionSalario = cn.prepareStatement(SALARIO_ACTUAL);
        declaracionSalario.setInt(1, id_empleado);
        ResultSet result = declaracionSalario.executeQuery();
        while(result.next()){
            salarioActual = result.getFloat("salario_descuento");
        }
        login.Desconectar();
        return salarioActual;
    }
    
    public void confirmarAumento(int id_empleado, float salario, Date fecha_evento) throws SQLException{
        obtenerConexion();
        String tipoContrato = "";
        PreparedStatement decVerificacion = cn.prepareStatement(VERIFICACION_CONTRATO);
        decVerificacion.setInt(1, id_empleado);
        ResultSet result = decVerificacion.executeQuery();
        while(result.next()){
            tipoContrato = result.getString("tipo_contratacion");
            if(tipoContrato.equals("Contratacion Fija")){
                float salarioDescuento = salarioDescuento(salario);
                actualizarSalario(id_empleado, salario);
                actualizarSalarioDescuento(id_empleado, salarioDescuento);
                subirEventoHistorial(id_empleado, salarioDescuento, fecha_evento);
            } else if(tipoContrato.equals("Contratacion por servicios o temporadas")){
                actualizarSalario(id_empleado, salario);
                actualizarSalarioDescuento(id_empleado, salario);
                subirEventoHistorial(id_empleado, salario, fecha_evento);
            }
        }
        login.Desconectar();
    }
    
    public float salarioDescuento(float salario){
        float salarioTotal = (float) (salario - (salario*0.0483) - (salario*0.01));
        return salarioTotal;
    }
    
    public void actualizarSalario(int id_empleado, float salario) throws SQLException {
        obtenerConexion();
        PreparedStatement decSalarioBase = cn.prepareStatement(ACTUALIZACION_SALARIO);
        decSalarioBase.setFloat(1, salario);
        decSalarioBase.setInt(2, id_empleado);
        decSalarioBase.executeUpdate();
        login.Desconectar();
    }
    
    public void actualizarSalarioDescuento(int id_empleado, float salarioDescuento) throws SQLException{
        obtenerConexion();
        PreparedStatement decSalarioDescuento = cn.prepareStatement(ACTUALIZACION_SALARIO_FINAL);
        decSalarioDescuento.setFloat(1, salarioDescuento);
        decSalarioDescuento.setInt(2, id_empleado);
        decSalarioDescuento.executeUpdate();
        login.Desconectar();
    }
    
    public int idHistorialLaboral(int id_empleado) throws SQLException{
        obtenerConexion();
        int idHistorial = 0;
        PreparedStatement declaracionHistorial = cn.prepareStatement(SALARIO_ACTUAL);
        declaracionHistorial.setInt(1, id_empleado);
        ResultSet result = declaracionHistorial.executeQuery();
        while(result.next()){
            idHistorial = result.getInt("id");
        }
        login.Desconectar();
        return idHistorial;
    }
    
    public void subirEventoHistorial(int id_empleado, float salario, Date fecha_evento) throws SQLException{
        obtenerConexion();
        int idHistorial = idHistorialLaboral(id_empleado);
        PreparedStatement declaracionEvento = cn.prepareStatement(NUEVO_EVENTO_HISTORIAL);
        declaracionEvento.setInt(1, 0);
        declaracionEvento.setInt(2, idHistorial);
        declaracionEvento.setString(3, "Aumento Salarial");
        declaracionEvento.setFloat(4, salario);
        declaracionEvento.setDate(5, fecha_evento);
        declaracionEvento.executeUpdate();
        login.Desconectar();
    }
}
