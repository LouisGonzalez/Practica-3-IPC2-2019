package practica3.funcionesMedico;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import practica3.conexion.Conexion;
import practica3.objetos.Cirugias;

/**
 *
 * @author luisGonzalez
 */
public class CirugiaPaciente {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ACTUALIZACION_HISTORIAL = "UPDATE No_historial_medico SET total = ? WHERE id = ?";
    private static final String TOTAL_ACUMULADO = "SELECT * FROM No_historial_medico WHERE id = ?";
    private static final String PAGOS_MEDICOS = "SELECT * FROM Tarifas WHERE id = ?";
    private static final String DATO_CIRUGIA = "SELECT * FROM Cirugias WHERE id = ?";
    private static final String DATO_CIRUGIA2 = "SELECT * FROM Empleados e JOIN Trabajadores_paciente_cirugia t ON e.id = t.id_empleado JOIN Medicos m ON e.id = m.id_empleado WHERE id_cirugia = ?";   
    private static final String ASIGNACION_MEDICOS = "INSERT INTO Trabajadores_paciente_cirugia (id, id_empleado, id_cirugia, area_trabajador) VALUES (?, ?, ?, ?)";
    private static final String NUEVA_OPERACION = "INSERT INTO Cirugias (id, id_historial_medico, estado, tipo_operacion, fecha_cirugia) VALUES (?, ?, ?, ?, ?)";
    private static final String ID_CIRUGIA = "SELECT * FROM Cirugias ORDER BY id DESC LIMIT 1";
    private static final String CIRUGIA_COMPLETA = "UPDATE Cirugias SET estado = ? WHERE id = ?";
    private static final String EVENTO_HISTORIAL = "INSERT INTO Historial_medico (id, id_historial_medico, evento, cobro, fecha_evento, id_medicamento, id_empleado_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String EVENTO_COSTO_HISTORIAL = "INSERT INTO Costos_historial_medico (id, id_historial_medico, evento, total, id_evento_historial) VALUES (?, ?, ?, ?, ?)";
    private static final String REEMPLAZO_MEDICO = "UPDATE Trabajadores_paciente_cirugia SET id_empleado = ? WHERE id = ?";
    private static final String ULTIMO_EVENTO = "SELECT * FROM Historial_medico ORDER BY id DESC LIMIT 1";
    private static final String ESTADO = "ACTIVA";
    private static final String ESTADO2 = "CONCLUIDA";
    private static final String AREA = "Medicos";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearNuevaOperacion(int idHistorial, String descripcion, Date fechaCirugia) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionOperacion = cn.prepareStatement(NUEVA_OPERACION);
        declaracionOperacion.setInt(1, 0);
        declaracionOperacion.setInt(2, idHistorial);
        declaracionOperacion.setString(3, ESTADO);
        declaracionOperacion.setString(4, descripcion);
        declaracionOperacion.setDate(5, fechaCirugia);
        declaracionOperacion.executeUpdate();
        login.Desconectar();
    }
    
    public void asignarMedicos(int numFilas, HttpServletRequest request, HttpSession session) throws SQLException{
        int idCirugia = obtenerIdCirugia();
        obtenerConexion();
        for(int x = 0; x<numFilas; x++){
            if(request.getParameter("medico"+x)!= null){
                int idEmpleado = (int) session.getAttribute("idMedico"+x);
                PreparedStatement decAsignacion = cn.prepareStatement(ASIGNACION_MEDICOS);
                decAsignacion.setInt(1, 0);
                decAsignacion.setInt(2, idEmpleado);
                decAsignacion.setInt(3, idCirugia);
                decAsignacion.setString(4, AREA);
                decAsignacion.executeUpdate();
            }
        }
        login.Desconectar();
    }
                                                                                        
    public void crearEventoCirugia(int idCirugia, Cirugias cirugia) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionEvento = cn.prepareStatement(EVENTO_HISTORIAL);
        declaracionEvento.setInt(1, 0);
        declaracionEvento.setInt(2, cirugia.getId_historial_medico());
        declaracionEvento.setString(3, "CIRUGIA");
        declaracionEvento.setFloat(4, cirugia.getPrecio_cirugia());
        declaracionEvento.setDate(5, cirugia.getFecha_cirugia());
        declaracionEvento.setInt(6, 0);
        declaracionEvento.setInt(7, 0);
        declaracionEvento.executeUpdate();
        login.Desconectar(); 
    }                                                                        
    
    private int idEventoHistorial() throws SQLException{
        int idEvento = 0;
        PreparedStatement declaracionEvento = cn.prepareStatement(ULTIMO_EVENTO);
        ResultSet result = declaracionEvento.executeQuery();
        while(result.next()){
            idEvento = result.getInt("id");
        }
        return idEvento;
    }
    
    public void crearCostoCirugia(Cirugias cirugia) throws SQLException{
        obtenerConexion();
        int idEvento = idEventoHistorial();
        PreparedStatement declaracionCostos = cn.prepareStatement(EVENTO_COSTO_HISTORIAL);
        declaracionCostos.setInt(1, 0);
        declaracionCostos.setInt(2, cirugia.getId_historial_medico());
        declaracionCostos.setString(3, "CIRUGIA");
        declaracionCostos.setFloat(4, cirugia.getCosto_cirugia());
        declaracionCostos.setInt(5, idEvento);
        declaracionCostos.executeUpdate();
        float nuevoTotal = calcularTotalAcumulado(cirugia.getId_historial_medico(), cirugia.getCosto_cirugia());
        agregarNuevoTotal(nuevoTotal, cirugia.getId_historial_medico());
        login.Desconectar();
    }
    
    public void asignarPagoMedicos(int idCirugia, Cirugias cirugia) throws SQLException{
        obtenerConexion();
        float pagoMedico = obtenerPagoMedico(17);
        float pagoEspecialista = obtenerPagoMedico(16);
        ArrayList<Cirugias> listar = listaMedicosAsignados(idCirugia, cirugia.getId_historial_medico());
        if(listar.size() > 0){
            for(Cirugias listar2 : listar){
                Cirugias nuevo = listar2;
                if(nuevo.getTipo_medico().equals("Medico")){
                    crearPagosMedicos(cirugia.getId_historial_medico(), pagoMedico, cirugia.getFecha_cirugia(), nuevo.getId_empleado());
                    float nuevoTotal = calcularTotalAcumulado(cirugia.getId_historial_medico(), pagoMedico);
                    agregarNuevoTotal(nuevoTotal, cirugia.getId_historial_medico());  
                } else if(nuevo.getTipo_medico().equals("Medico Especialista")){
                    crearPagosMedicos(cirugia.getId_historial_medico(), pagoEspecialista, cirugia.getFecha_cirugia(), nuevo.getId_empleado());        
                    float nuevoTotal = calcularTotalAcumulado(cirugia.getId_historial_medico(), pagoEspecialista);
                    agregarNuevoTotal(nuevoTotal, cirugia.getId_historial_medico());        
                }                
            }
        }
        login.Desconectar();
    }
    
    public ArrayList<Cirugias> listaMedicosAsignados(int idCirugia, int idHistorial) throws SQLException{
        ArrayList<Cirugias> list = new ArrayList<>();
        PreparedStatement declaracionPago = cn.prepareStatement(DATO_CIRUGIA2);
        declaracionPago.setInt(1, idCirugia);
        ResultSet result = declaracionPago.executeQuery();
        while (result.next()) {
            Cirugias cirugia = new Cirugias();
            cirugia.setId_empleado(result.getInt("t.id_empleado"));
            cirugia.setTipo_medico(result.getString("tipo"));
            list.add(cirugia);
        }
        return list;
    } 
                                                                        
    public void finalizarCirugia(int idCirugia) throws SQLException{
        obtenerConexion();
        PreparedStatement decFinalizada = cn.prepareStatement(CIRUGIA_COMPLETA);
        decFinalizada.setString(1, ESTADO2);
        decFinalizada.setInt(2, idCirugia);
        decFinalizada.executeUpdate();
        login.Desconectar();
    } 
       
    public void reemplazarMedico(int numFilas, HttpServletRequest request, HttpSession session, int idAsignacion) throws SQLException{
        obtenerConexion();
        String[] parametro = request.getParameterValues("reemplazo");
        for(int x = 0; x < parametro.length; x++){
            if(parametro[x] != null){   
                int idEmpleado = Integer.parseInt(parametro[x]);
                PreparedStatement decReemplazo = cn.prepareStatement(REEMPLAZO_MEDICO);
                decReemplazo.setInt(1, idEmpleado);
                decReemplazo.setInt(2, idAsignacion);
                decReemplazo.executeUpdate();
            }            
        }
        login.Desconectar();   
    }
    
    private int obtenerIdCirugia() throws SQLException{
        int idCirugia = 0;
        obtenerConexion();
        PreparedStatement declaracionCirugia = cn.prepareStatement(ID_CIRUGIA);
        ResultSet result = declaracionCirugia.executeQuery();
        while(result.next()){
            idCirugia = result.getInt("id");
        }
        login.Desconectar();
        return idCirugia;
    }
    
    public String obtenerNombreCirugia(int idCirugia) throws SQLException{
        String nombreCirugia = "";
        obtenerConexion();
        PreparedStatement declaracionNombre = cn.prepareStatement(DATO_CIRUGIA);
        declaracionNombre.setInt(1, idCirugia);
        ResultSet result = declaracionNombre.executeQuery();
        while(result.next()){
            nombreCirugia = result.getString("tipo_operacion");
        }
        login.Desconectar();
        return nombreCirugia;
    }
    
    public float calcularTotales(String nombre, String DATO) throws SQLException{
        float valor = 0;
        obtenerConexion();
        PreparedStatement declaracionCosto = cn.prepareStatement(DATO);
        declaracionCosto.setString(1, nombre);
        ResultSet result = declaracionCosto.executeQuery();
        while(result.next()){
            valor = result.getFloat("total");
        }
        login.Desconectar();
        return valor;
    }
    
    public Date obtenerFechaCirugia(int idCirugia) throws SQLException{
        Date fecha = null;
        obtenerConexion();
        PreparedStatement declaracionFecha = cn.prepareStatement(DATO_CIRUGIA);
        declaracionFecha.setInt(1, idCirugia);
        ResultSet result = declaracionFecha.executeQuery();
        while(result.next()){
            fecha = result.getDate("fecha_cirugia");
        }
        login.Desconectar();
        return fecha;
    }
    
    //metodo para calcular el pago a un medico/especialista
    private float obtenerPagoMedico(int id) throws SQLException{
        float pagoMedico = 0;
        PreparedStatement declaracionPago = cn.prepareStatement(PAGOS_MEDICOS);
        declaracionPago.setInt(1, id);
        ResultSet result = declaracionPago.executeQuery();
        while(result.next()){
            pagoMedico = result.getFloat("total");
        }
        return pagoMedico;
    }
    
    private void crearPagosMedicos(int idHistorial, float cobro, Date fecha, int idEmpleado) throws SQLException{
       PreparedStatement declaracionPagos = cn.prepareStatement(EVENTO_HISTORIAL);
       declaracionPagos.setInt(1, 0);
       declaracionPagos.setInt(2, idHistorial);
       declaracionPagos.setString(3, "PAGO MEDICO");
       declaracionPagos.setFloat(4, cobro);
       declaracionPagos.setDate(5, fecha);
       declaracionPagos.setInt(6, 0);
       declaracionPagos.setInt(7, idEmpleado);
       declaracionPagos.executeUpdate();
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
        PreparedStatement declaracionTotal = cn.prepareStatement(ACTUALIZACION_HISTORIAL);
        declaracionTotal.setFloat(1, nuevoTotal);
        declaracionTotal.setInt(2, idHistorial);
        declaracionTotal.executeUpdate();
    }
    
}
