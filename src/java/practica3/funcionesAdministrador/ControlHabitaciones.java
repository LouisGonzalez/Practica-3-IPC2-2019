package practica3.funcionesAdministrador;

import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.Habitaciones;

/**
 *
 * @author luisGonzalez
 */
public class ControlHabitaciones {

    private static Connection cn;
    private static Conexion login;
    private static final String CREACION_HABITACION = "INSERT INTO Habitaciones (id, no_camas, estado, costo_diario) VALUES (?, ?, ?, ?)";
    private static final String CREACION_CAMAS = "INSERT INTO Camas (id, id_habitacion, estado) VALUES (?, ?, ?)";
    private static final String TARIFA = "SELECT * FROM Tarifas WHERE id = ?";
    private static final String ULTIMO_ID_HABITACION = "SELECT * FROM Habitaciones ORDER BY id DESC LIMIT 1";
    private static final String ESTADO_CAMA = "DESOCUPADA";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    private float tarifaHabitacion() throws SQLException{
        obtenerConexion();
        float tarifaGlobal = 0;
        PreparedStatement declaracionTarifa = cn.prepareStatement(TARIFA);
        declaracionTarifa.setInt(1, 15);
        ResultSet result = declaracionTarifa.executeQuery();
        while(result.next()){
            tarifaGlobal = result.getFloat("total");
        }
        login.Desconectar();
        return tarifaGlobal;
    }
    
    public void crearHabitacion(int numHabitaciones, Habitaciones habitacion) throws SQLException{
        obtenerConexion();
        habitacion.setCosto_diario(tarifaHabitacion());
        for(int i = 0; i < numHabitaciones; i++){
            PreparedStatement decHabitaciones = cn.prepareStatement(CREACION_HABITACION);
            decHabitaciones.setInt(1, 0);
            decHabitaciones.setInt(2, habitacion.getNo_camas());
            decHabitaciones.setString(3, habitacion.getEstado());
            decHabitaciones.setFloat(4, habitacion.getCosto_diario());
            decHabitaciones.executeUpdate();
            habitacion.setId(verUltimoId());
            crearCamas(habitacion.getId());
            
        }
        login.Desconectar();
    }
    
    private int verUltimoId() throws SQLException{
        obtenerConexion();
        int idHabitacion = 0;
        PreparedStatement declaracionId = cn.prepareStatement(ULTIMO_ID_HABITACION);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            idHabitacion = result.getInt("id");
        }
        login.Desconectar();
        return idHabitacion;
    }
    
    private void crearCamas(int idHabitacion) throws SQLException{
        for(int i = 0; i < 2; i++){
            PreparedStatement declaracionCamas = cn.prepareStatement(CREACION_CAMAS);
            declaracionCamas.setInt(1, 0);
            declaracionCamas.setInt(2, idHabitacion);
            declaracionCamas.setString(3, ESTADO_CAMA);
            declaracionCamas.executeUpdate();
        }
    }
    
}
