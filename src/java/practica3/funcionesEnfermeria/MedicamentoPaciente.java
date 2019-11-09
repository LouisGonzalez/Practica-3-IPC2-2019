package practica3.funcionesEnfermeria;

import java.sql.*;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class MedicamentoPaciente {
    
    private static Connection cn;
    private static Conexion login;
    private static final String EVENTO_HISTORIAL = "INSERT INTO Historial_medico (id, id_historial_medico, evento, cobro, fecha_evento, id_medicamento) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String TOTAL_MEDICAMENTO = "SELECT * FROM Medicamentos WHERE id = ?";
    private static final String TOTAL_ACUMULADO = "SELECT * FROM No_historial_medico WHERE id = ?";
    private static final String NUEVO_TOTAL_ACUMULADO = "UPDATE No_historial_medico SET total = ? WHERE id = ?";
    
    private static final String EVENTO = "REGISTRO MEDICAMENTO";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearEventoHistorial(int idHistorial, int idMedicamento, int cant, Date fecha) throws SQLException{
        obtenerConexion();
        
        float valorMedicamento = calcularValorMedicina(idMedicamento);
        for(int i = 0; i < cant; i++){
            PreparedStatement declaracionEvento = cn.prepareStatement(EVENTO_HISTORIAL);
            declaracionEvento.setInt(1, 0);
            declaracionEvento.setInt(2, idHistorial);
            declaracionEvento.setString(3, EVENTO);
            declaracionEvento.setFloat(4, valorMedicamento);
            declaracionEvento.setDate(5, fecha);
            declaracionEvento.setInt(6, idMedicamento);
            declaracionEvento.executeUpdate();
            float nuevoTotal = calcularTotalAcumulado(idHistorial, valorMedicamento);
            agregarNuevoTotal(nuevoTotal, idHistorial);
        }
        login.Desconectar();
    }
    
    private float calcularValorMedicina(int idMedicamento) throws SQLException{
        float totalMedicamento = 0;
        PreparedStatement declaracionValor = cn.prepareStatement(TOTAL_MEDICAMENTO);
        declaracionValor.setInt(1, idMedicamento);
        ResultSet result = declaracionValor.executeQuery();
        while(result.next()){
            totalMedicamento = result.getFloat("precio_venta");
        }
        login.Desconectar();
        return totalMedicamento;
    }
    
    private float calcularTotalAcumulado(int idHistorial, float totalAgregado) throws SQLException{
        float totalInicial = 0;
        float totalFinal = 0;
        PreparedStatement declaracionTotal = cn.prepareStatement(TOTAL_ACUMULADO);
        declaracionTotal.setInt(1, idHistorial);
        ResultSet result = declaracionTotal.executeQuery();
        while(result.next()){
            totalInicial = result.getFloat("total");
        }
        totalFinal = totalInicial + totalAgregado;
        return totalFinal;
    }
    
    private void agregarNuevoTotal(float nuevoTotal, int idHistorial) throws SQLException{
        PreparedStatement declaracionTotal = cn.prepareStatement(NUEVO_TOTAL_ACUMULADO);
        declaracionTotal.setFloat(1, nuevoTotal);
        declaracionTotal.setInt(2, idHistorial);
        declaracionTotal.executeUpdate();
    }
    
    
}
