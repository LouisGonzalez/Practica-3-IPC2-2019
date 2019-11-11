package practica3.funcionesMedico;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import practica3.conexion.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class CirugiaPaciente {
    
    private static Connection cn;
    private static Conexion login;
    private static final String ASIGNACION_MEDICOS = "INSERT INTO Trabajadores_paciente_cirugia (id, id_empleado, id_cirugia, area_trabajador) VALUES (?, ?, ?, ?)";
    private static final String NUEVA_OPERACION = "INSERT INTO Cirugias (id, id_historial_medico, estado, tipo_operacion, fecha_cirugia) VALUES (?, ?, ?, ?, ?)";
    private static final String ID_CIRUGIA = "SELECT * FROM Cirugias ORDER BY id DESC LIMIT 1";
    private static final String CIRUGIA_COMPLETA = "UPDATE Cirugias SET estado = ? WHERE id = ?";
    private static final String NUEVO_EVENTO = "";
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
    
    public void finalizarCirugia(int numFilas, HttpServletRequest request, HttpSession session) throws SQLException{
        obtenerConexion();
        for(int x =1; x <= numFilas; x++){
            int idCirugia = (int) session.getAttribute("idCirugia"+x);
            PreparedStatement decFinalizada = cn.prepareStatement(CIRUGIA_COMPLETA);
            decFinalizada.setString(1, ESTADO2);
            decFinalizada.setInt(2, idCirugia);
            decFinalizada.executeUpdate();
        }
        login.Desconectar();
    }
    
    private int obtenerIdCirugia() throws SQLException{
        int idCirugia = 0;
        PreparedStatement declaracionCirugia = cn.prepareStatement(ID_CIRUGIA);
        ResultSet result = declaracionCirugia.executeQuery();
        while(result.next()){
            idCirugia = result.getInt("id");
        }
        return idCirugia;
    }
    
}
