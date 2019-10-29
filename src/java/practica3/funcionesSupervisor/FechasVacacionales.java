package practica3.funcionesSupervisor;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class FechasVacacionales{
    
    private static Connection cn;
    private static Conexion login;
    private Calendar fechaInicio, fechaFin, fecha;
    private static final int DIAS_VACACIONALES = 10;
    private static Random aleatorio;
    private static final String ID_EMPLEADO = "SELECT * FROM Empleados WHERE cui = ?";
    private static final String CONSULTA_HISTORIAL = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    private static final String CREACION_VACACIONES = "INSERT INTO Fechas_vacacionales (id, id_empleado, inicio_vacaciones, fin_vacaciones, no_año_laboral) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZACION_AñO_LABORAL = "UPDATE No_historial_laboral SET años_totales = ? WHERE id_empleado = ?";
    private Date fechaGuia;
    private int mesInicio, diaInicio, añoLaboral;
     
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    //metodo para saber el id del empleado
    public int idEmpleado(int cui) throws SQLException{
        obtenerConexion();
        int id = 0;
        PreparedStatement declaracionId = cn.prepareStatement(ID_EMPLEADO);
        declaracionId.setInt(1, cui);
        ResultSet result = declaracionId.executeQuery();
        while(result.next()){
            id = result.getInt("id");
        }
        login.Desconectar();
        return id;
    }
    
    //metodo para ver la fecha de contratacion del empleado
    public Date verFecha(int cui) throws SQLException{
        obtenerConexion();
        int id_empleado = idEmpleado(cui);
        Date fechaContratacion = null;
        fechaContratacion = verFechaId(id_empleado);
        login.Desconectar();
        return fechaContratacion;
    }
    
    //metodo para ver la fecha de contratacion mediante el id
    public Date verFechaId(int id_empleado) throws SQLException{
        obtenerConexion();
        Date fechaContratacion = null;
        PreparedStatement declaracionFecha = cn.prepareStatement(CONSULTA_HISTORIAL);
        declaracionFecha.setInt(1, id_empleado);
        ResultSet result = declaracionFecha.executeQuery();
        while(result.next()){
            fechaContratacion = result.getDate("fecha_historial_laboral");
        }
        login.Desconectar();
        return fechaContratacion;
    }
    
    //metodo que genera fechas aleatorias para las vacaciones
    public void creacionFechasAleatorias(int cui) throws SQLException{
        int id_empleado = idEmpleado(cui);
        añoLaboral = consultaAñoLaboral(id_empleado);
        //fecha de contratacion
        fechaGuia = verFecha(cui);
        fecha = Calendar.getInstance();
        fecha.setTime(fechaGuia);
        fechasAleatorias(fecha, id_empleado, fechaGuia, añoLaboral);
    }  
    
    
    
    
    
    //creacion de fechas aleatorias segun la fecha
    public void fechasAleatorias(Calendar fecha, int id_empleado, Date fechaGuia, int año_laboral) throws SQLException{
        int year = fecha.get(Calendar.YEAR);
        //inicio vacaciones
        aleatorio = new Random();
        fechaInicio = Calendar.getInstance();
        fechaInicio.set(year, aleatorio.nextInt(12) + 1, aleatorio.nextInt(30) + 1);
        mesInicio = fechaInicio.get(Calendar.MONTH);
        diaInicio = fechaInicio.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");     
        if(fechaGuia.after(fechaInicio.getTime())){
            System.out.println("la fecha de contratacion es mayor a las fechas vacacionales");
            year = fecha.get(Calendar.YEAR) + 1;
            fechaInicio.set(year, mesInicio, diaInicio);
            System.out.println("La fecha ahora vale "+sdf.format(fechaInicio.getTime()));
        } else {
            System.out.println("la fecha de contratacion es menor a ls fechas vacacionales");    
            System.out.println("La fecha vale "+sdf.format(fechaInicio.getTime()));
        }     
        //calculo para la fecha final de vacaciones
        fechaFin = Calendar.getInstance();
        fechaFin.setTime(fechaInicio.getTime());
        fechaFin.add(Calendar.DAY_OF_YEAR, DIAS_VACACIONALES);
        System.out.println("La fecha final es "+sdf.format(fechaFin.getTime()));
        //transformacion de util.Date a sql.Date
        Date fechaInicioSQL = new Date(fechaInicio.getTime().getTime());
        Date fechaFinSQL = new Date(fechaFin.getTime().getTime());
        //creacion de las fechas vacacionales
        creacionVacaciones(fechaInicioSQL, fechaFinSQL, id_empleado, año_laboral);
    
    }
    
    
    
    
    public void creacionVacaciones(Date fechaInicio, Date fechaFin, int id_empleado, int año_laboral) throws SQLException{
        obtenerConexion();
        PreparedStatement decVacaciones = cn.prepareStatement(CREACION_VACACIONES);
        decVacaciones.setInt(1, 0);
        decVacaciones.setInt(2, id_empleado);
        decVacaciones.setDate(3, fechaInicio);
        decVacaciones.setDate(4, fechaFin);
        decVacaciones.setInt(5, año_laboral);
        decVacaciones.executeUpdate();
        login.Desconectar();
    }
    
    public void comparacionFechas(int id_empleado) throws SQLException{
        obtenerConexion();
        Calendar fechaContrato, nuevaFecha;
        int yearInitial, mesInicial, diaInicial;
        añoLaboral = consultaAñoLaboral(id_empleado);
        java.util.Date fechaActual = new java.util.Date();
        Date fechaContratacion = verFechaId(id_empleado);   
        if(fechaActual.after(fechaContratacion)){
            System.out.println("la fecha actual es mayor a la fecha de contratacion");
            int dias = (int) ((fechaActual.getTime()-fechaContratacion.getTime())/86400000);
            int year = (dias / 365);
            int yearAUsar = year + 1; 
            
            
            
            fechaContrato = Calendar.getInstance();
            fechaContrato.setTime(fechaContratacion);
            yearInitial = fechaContrato.get(Calendar.YEAR);
            mesInicial = fechaContrato.get(Calendar.MONTH);
            diaInicial = fechaContrato.get(Calendar.DAY_OF_MONTH);
            
            nuevaFecha = Calendar.getInstance();
            nuevaFecha.set(yearInitial + year, mesInicial, diaInicial);
            Date nuevaFechaSQL = new Date(nuevaFecha.getTime().getTime());
            System.out.println("La nueva fecha laboral es "+nuevaFechaSQL);
            if(yearAUsar > añoLaboral){
                actualizacionAñoLaboral(id_empleado, yearAUsar);
                fechasAleatorias(nuevaFecha, id_empleado, nuevaFechaSQL, yearAUsar);
                
            }
                 
            
        }
    }
    
    //metodo para saber el año laboral en el que se encuentra el empleado
    public int consultaAñoLaboral(int id_empleado) throws SQLException{
        obtenerConexion();
        int añoLaboral = 0;
        PreparedStatement declaracionAño = cn.prepareStatement(CONSULTA_HISTORIAL);
        declaracionAño.setInt(1, id_empleado);
        ResultSet result = declaracionAño.executeQuery();
        while(result.next()){
            añoLaboral = result.getInt("años_totales");
        }
        login.Desconectar();
        return añoLaboral;
    }
    
    //actualiza el año laboral dentro del historial laboral
    public void actualizacionAñoLaboral(int id_empleado, int year) throws SQLException{
        obtenerConexion();
        PreparedStatement decActualizacion = cn.prepareStatement(ACTUALIZACION_AñO_LABORAL);
        decActualizacion.setInt(1, year);
        decActualizacion.setInt(2, id_empleado);
        decActualizacion.executeUpdate();
        login.Desconectar();
    }
    
    
    
    

   
    
}
