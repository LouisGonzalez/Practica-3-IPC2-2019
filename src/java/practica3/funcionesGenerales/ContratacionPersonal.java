package practica3.funcionesGenerales;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import practica3.conexion.Conexion;
import practica3.objetos.Empleados;
import practica3.objetos.Supervisor;

/**
 *
 * @author luisGonzalez
 */
public class ContratacionPersonal {
    
    private static Connection cn;
    private static Conexion login;
    private static final String NUEVO_EMPLEADO = "INSERT INTO Empleados (id, nombres, apellidos, area_trabajo, edad, cui, tipo_contratacion, fecha_contratacion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String NUEVO_MEDICO = "INSERT INTO Medicos (id, id_empleado, especialidad, tipo) VALUES (?, ?, ?, ?)";
    private static final String NUEVO_SUPERVISOR = "INSERT INTO Supervisor (id, id_empleado, empleados_a_cargo, limite_empleados, area_trabajo) VALUES (?, ?, ?, ?, ?)";
    private static final String NUEVA_CUENTA = "INSERT INTO Sesion_empleados (username, password, tipo_cuenta, id_empleado) VALUES (?, ?, ?, ?)";
    private static final String NUEVO_HISTORIAL_LABORAL = "INSERT INTO No_historial_laboral (id, id_empleado, nombres, apellidos, supervisor, no_periodo_laboral, salario_base, salario_descuento, fecha_historial_laboral, estado, años_totales) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String PRIMER_EVENTO_HISTORIAL_LABORAL = "INSERT INTO historial_laboral (id, id_historial_laboral, evento, monto, fecha_evento) VALUES (?, ?, ?, ?, ?)";
    private static final String ID_EMPLEADO = "SELECT * FROM Empleados WHERE cui = ?";
    private static final String ID_HISTORIAL = "SELECT * FROM No_historial_laboral WHERE id_empleado = ?";
    private static final String ASIGNACION_SUPERVISOR = "UPDATE No_historial_laboral SET supervisor = ? WHERE id_empleado = ?";
    private static final String SUMA_EMPLEADOS_SUPERVISOR = "UPDATE Supervisor SET empleados_a_cargo = ? WHERE id = ?";
    private static final String EMPLEADOS_A_CARGO = "SELECT * FROM Supervisor WHERE id = ?";
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearEmpleado(Empleados empleado, HttpServletRequest request, HttpServletResponse response) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionEmpleado = cn.prepareStatement(NUEVO_EMPLEADO);
        declaracionEmpleado.setInt(1, 0);
        declaracionEmpleado.setString(2, empleado.getNombres());
        declaracionEmpleado.setString(3, empleado.getApellidos());
        declaracionEmpleado.setString(4, empleado.getArea_trabajo());
        declaracionEmpleado.setInt(5, empleado.getEdad());
        declaracionEmpleado.setInt(6, empleado.getCUI());
        declaracionEmpleado.setString(7, empleado.getTipo_contratacion());
        declaracionEmpleado.setDate(8, empleado.getFecha_contratacion());
        declaracionEmpleado.executeUpdate();
        if(!empleado.getArea_trabajo().equals("Cocina") && !empleado.getArea_trabajo().equals("Seguridad") && !empleado.getArea_trabajo().equals("Conserjeria")){
            crearCuenta(empleado);        
        }
        
        login.Desconectar();
    }
    
    public void crearMedico(Empleados empleado, String especialidad, String tipo) throws SQLException{
        obtenerConexion();
        int id_empleado = idEmpleado(empleado.getCUI());
        PreparedStatement declaracionMedico = cn.prepareStatement(NUEVO_MEDICO);
        declaracionMedico.setInt(1, 0);
        declaracionMedico.setInt(2, id_empleado);
        declaracionMedico.setString(3, especialidad);
        declaracionMedico.setString(4, tipo);
        declaracionMedico.executeUpdate();
        login.Desconectar();
    }
    
    public void crearSupervisor(Supervisor supervisor, int cui) throws SQLException{
        obtenerConexion();
        int id_empleado = idEmpleado(cui);
        PreparedStatement declaracionSupervisor = cn.prepareStatement(NUEVO_SUPERVISOR);
        declaracionSupervisor.setInt(1, 0);
        declaracionSupervisor.setInt(2, id_empleado);
        declaracionSupervisor.setInt(3, 0);
        declaracionSupervisor.setInt(4, 30);
        declaracionSupervisor.setString(5, supervisor.getArea_trabajo());
        declaracionSupervisor.executeUpdate();
        login.Desconectar();
    }
    
    public void asignarSupervisor(Empleados empleado, int idSupervisor) throws SQLException{
        obtenerConexion();
        int id_empleado = idEmpleado(empleado.getCUI());
        PreparedStatement declaracionAsignacion = cn.prepareStatement(ASIGNACION_SUPERVISOR);
        declaracionAsignacion.setString(1, Integer.toString(idSupervisor));
        declaracionAsignacion.setInt(2, id_empleado);
        declaracionAsignacion.executeUpdate();
        login.Desconectar();        
    }
    
    public int personasMando(int idSupervisor) throws SQLException{
        obtenerConexion();
        int personasMando = 0;
        PreparedStatement decPersonasMando = cn.prepareStatement(EMPLEADOS_A_CARGO);
        decPersonasMando.setInt(1, idSupervisor);
        ResultSet result = decPersonasMando.executeQuery();
        while(result.next()){
            personasMando = result.getInt("empleados_a_cargo");
        }
        login.Desconectar();
        return personasMando;
    }
    
    public void sumarPersonasMando(int idSupervisor) throws SQLException{
        obtenerConexion();
        int personasMando = personasMando(idSupervisor);
        int nuevoValor = personasMando + 1;
        PreparedStatement declaracionSuma = cn.prepareStatement(SUMA_EMPLEADOS_SUPERVISOR);
        declaracionSuma.setInt(1, nuevoValor);
        declaracionSuma.setInt(2, idSupervisor);
        declaracionSuma.executeUpdate();
        login.Desconectar();
    }
    
    
    public void crearCuenta(Empleados empleado) throws SQLException{
        obtenerConexion();
        int id_empleado = idEmpleado(empleado.getCUI());
        int suma = id_empleado + 433;
        PreparedStatement declaracionCuenta = cn.prepareStatement(NUEVA_CUENTA);
        declaracionCuenta.setString(1, empleado.getNombres()+""+id_empleado);
        declaracionCuenta.setString(2, empleado.getArea_trabajo()+""+empleado.getApellidos()+""+suma);
        declaracionCuenta.setString(3, empleado.getArea_trabajo());
        declaracionCuenta.setInt(4, id_empleado);
        declaracionCuenta.executeUpdate();
        login.Desconectar();
        
    }
    
    public float calcularSalario(String area) throws SQLException{
        obtenerConexion();
        float salario = 0;
        String SALARIO = "SELECT * FROM Tarifas WHERE descripcion = ?";
        PreparedStatement declaracionSalario = cn.prepareStatement(SALARIO);
        declaracionSalario.setString(1, "Salario mensual "+area);
        ResultSet result = declaracionSalario.executeQuery();
        while(result.next()){
            salario = result.getFloat("total");
        }
        login.Desconectar();
        return salario;
    }
    
    //Dependiendo el tipo de contratacion se hace el respectivo descuento al salario
    public float calcularDescuento(String tipoContratacion, String area) throws SQLException{
        float salarioFinal = 0;
        if(tipoContratacion.equals("Contratacion Fija")){
            salarioFinal = (float) (calcularSalario(area) - (calcularSalario(area)*0.0483) - (calcularSalario(area)*0.01));
        } else if(tipoContratacion.equals("Contratacion por servicios o temporadas")){
            salarioFinal = calcularSalario(area);
        }
        return salarioFinal;
    }
   
    //metodo para encontrar el id del empleado e insertarlo dentro de su historial laboral
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
    
    public int idHistorial(int id_empleado) throws SQLException{
        int id_historial = 0;
        PreparedStatement declaracionHistorial = cn.prepareStatement(ID_HISTORIAL);
        declaracionHistorial.setInt(1, id_empleado);
        ResultSet result = declaracionHistorial.executeQuery();
        while(result.next()){
            id_historial = result.getInt("id");
        }
        login.Desconectar();
        return id_historial;
    }
    
    public void crearHistorial(Empleados empleado) throws SQLException {
        obtenerConexion();
        int id_empleado = idEmpleado(empleado.getCUI());
        PreparedStatement declaracionHistorial = cn.prepareStatement(NUEVO_HISTORIAL_LABORAL);
        declaracionHistorial.setInt(1, 0);
        declaracionHistorial.setInt(2, id_empleado);
        declaracionHistorial.setString(3, empleado.getNombres());
        declaracionHistorial.setString(4, empleado.getApellidos());
        declaracionHistorial.setString(5, "--");
        declaracionHistorial.setInt(6, 1);
        declaracionHistorial.setDouble(7, calcularSalario(empleado.getArea_trabajo()));
        declaracionHistorial.setDouble(8, calcularDescuento(empleado.getTipo_contratacion(), empleado.getArea_trabajo()));
        declaracionHistorial.setDate(9, empleado.getFecha_contratacion());
        declaracionHistorial.setString(10, "ACTIVO");
        declaracionHistorial.setInt(11, 1);
        declaracionHistorial.executeUpdate();
        crearPrimerEvento(id_empleado, empleado);
        login.Desconectar();
    }
    
    public void crearPrimerEvento(int id_empleado, Empleados empleado) throws SQLException{
        obtenerConexion();
        int id_historial = idHistorial(id_empleado);
        PreparedStatement declaracionEvento = cn.prepareStatement(PRIMER_EVENTO_HISTORIAL_LABORAL);
        declaracionEvento.setInt(1, 0);
        declaracionEvento.setInt(2, id_historial);
        declaracionEvento.setString(3, "Contratacion");
        declaracionEvento.setDouble(4, 0);
        declaracionEvento.setDate(5, empleado.getFecha_contratacion());
        declaracionEvento.executeUpdate();
        login.Desconectar();      
    }
}
