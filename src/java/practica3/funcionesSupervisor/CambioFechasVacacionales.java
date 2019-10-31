package practica3.funcionesSupervisor;

import java.sql.*;
import java.util.Calendar;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class CambioFechasVacacionales {
    
    private static Connection cn;
    private static Conexion login;
    private Calendar fechaInicio, fechaFin, fechaActualC, fechaInicioFinal;
    private int añoSugerido, mesSugerido, diaSugerido;
    private static final String FECHAS = "SELECT * FROM Fechas_vacacionales WHERE id_empleado = ?";
    private static final String DIAS_VACACIONALES = "SELECT * FROM Tarifas WHERE id = 4";
    private FechasVacacionales fechas = new FechasVacacionales();
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    //metodo que busca dentro del sistema el total de dias que la ley permite tener como vacaciones anuales
    public int diasVacacionales() throws SQLException{
        obtenerConexion();
        int dias = 0;
        PreparedStatement declaracionDias = cn.prepareStatement(DIAS_VACACIONALES);
        ResultSet result = declaracionDias.executeQuery();
        while(result.next()){
            dias = result.getInt("total");
        }
        return dias;
    }
    
    public Date fechas(int id_empleado, String numFecha) throws SQLException{
        obtenerConexion();
        Date fecha = null;
        PreparedStatement decFechaInicial = cn.prepareStatement(FECHAS);
        decFechaInicial.setInt(1, id_empleado);
        ResultSet result = decFechaInicial.executeQuery();
        while(result.next()){
            fecha = result.getDate(numFecha);
        }
        login.Desconectar();
        return fecha;
    }
    
    public void actualizarFechas(int id_empleado, Date fechaInicial) throws SQLException{
        obtenerConexion();
        Calendar fechaContrato;
        int añoContrato, mesContrato, diaContrato;
        //Calendar de la fecha que el usuario escribe 
        fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(fechaInicial);
        añoSugerido = fechaInicio.get(Calendar.YEAR);
        mesSugerido = fechaInicio.get(Calendar.MONTH);
        diaSugerido = fechaInicio.get(Calendar.DAY_OF_MONTH);
        //Calendar de la fecha que se usara dentro de los parametros 
        fechaInicioFinal = Calendar.getInstance();
        //dias totales dentro de la empresa             
        java.util.Date fechaActual = new java.util.Date();
        fechaActualC = Calendar.getInstance();
        fechaActualC.setTime(fechaActual);      
        Date fechaContratacion = fechas.verFechaId(id_empleado);
        fechaContrato = Calendar.getInstance();
        fechaContrato.setTime(fechaContratacion);
        añoContrato = fechaContrato.get(Calendar.YEAR);
        mesContrato = fechaContrato.get(Calendar.MONTH);
        diaContrato = fechaContrato.get(Calendar.DAY_OF_MONTH);
        int dias = (int) ((fechaActual.getTime() - fechaContratacion.getTime())/86400000);
        int añosTotales = (dias / 365);
        retornoFechaInicial(fechaInicioFinal, añoSugerido, añoContrato, añosTotales, mesContrato, diaContrato, fechaFin, id_empleado, añosTotales + 1);    
    }
    
    //retorna la fecha que sera tomada para las vacaciones
    public void retornoFechaInicial(Calendar fechaInicioFinal, int añoSugerido, int añoContrato, int añosTotales, int mesContrato, int diaContrato, Calendar fechaFin, int id_empleado, int año_laboral) throws SQLException{
        //restricciones dentro de los parametros de fecha que ingrese el usuario
        Date fecha1 = null;
        Date fecha2 = null;
        if(añoSugerido == añoContrato + añosTotales){
            if(mesSugerido > mesContrato){
                fechaInicioFinal.set(añoContrato + añosTotales, mesSugerido, diaSugerido);
                fechaFin = fechaFinal(fechaInicioFinal);
                publicacionFechas(fecha1, fecha2, id_empleado, año_laboral, fechaInicioFinal, fechaFin);
            } else if(mesSugerido == mesContrato && diaSugerido >= diaContrato){
                fechaInicioFinal.set(añoContrato + añosTotales, mesSugerido, diaSugerido);
                fechaFin = fechaFinal(fechaInicioFinal);
                publicacionFechas(fecha1, fecha2, id_empleado, año_laboral, fechaInicioFinal, fechaFin);
            }            
        } else if (añoSugerido == añoContrato + añosTotales + 1){
            if(mesSugerido < mesContrato){
                fechaInicioFinal.set(añoContrato + añosTotales + 1, mesSugerido, diaSugerido);
                fechaFin = fechaFinal(fechaInicioFinal);
                publicacionFechas(fecha1, fecha2, id_empleado, año_laboral, fechaInicioFinal, fechaFin);
            } else if(mesSugerido == mesContrato && diaSugerido < diaContrato - 10){
                fechaInicioFinal.set(añoContrato + añosTotales + 1, mesSugerido, diaSugerido);
                fechaFin = fechaFinal(fechaInicioFinal);
                publicacionFechas(fecha1, fecha2, id_empleado, año_laboral, fechaInicioFinal, fechaFin);
            }
        }
    }
    
    //fecha final de las vacaciones de un empleado
    public Calendar fechaFinal(Calendar fechaInicioFinal) throws SQLException{
        Calendar fechaFinal = null;
        fechaFinal = Calendar.getInstance();
        fechaFinal.setTime(fechaInicioFinal.getTime());
        fechaFinal.add(Calendar.DAY_OF_YEAR, diasVacacionales());
        return fechaFinal;  
    }
    
    //metodo para el cambio de fechas dentro de la base de datos
    public void cambioFechas(int id_empleado, Date fecha, String campo, int año_laboral) throws SQLException{
        obtenerConexion();
        String MODIFICACION_FECHAS = "UPDATE Fechas_vacacionales SET "+campo+" = ? WHERE id_empleado = ? AND no_año_laboral = ?";
        PreparedStatement declaracionFechas = cn.prepareStatement(MODIFICACION_FECHAS);
        declaracionFechas.setDate(1, fecha);
        declaracionFechas.setInt(2, id_empleado);
        declaracionFechas.setInt(3, año_laboral);
        declaracionFechas.executeUpdate();
        login.Desconectar();
    }
    
    private void publicacionFechas(Date fecha1, Date fecha2, int id_empleado, int año_laboral, Calendar fechaInicioFinal, Calendar fechaFin) throws SQLException {
        fecha1 = new Date(fechaInicioFinal.getTime().getTime());
        fecha2 = new Date(fechaFin.getTime().getTime());
        cambioFechas(id_empleado, fecha1, "inicio_vacaciones", año_laboral);
        cambioFechas(id_empleado, fecha2, "fin_vacaciones", año_laboral);
    }
    
    
    
}
