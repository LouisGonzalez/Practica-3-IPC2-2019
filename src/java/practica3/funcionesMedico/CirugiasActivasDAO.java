package practica3.funcionesMedico;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Cirugias;

/**
 *
 * @author luisGonzalez
 */
public class CirugiasActivasDAO {

    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_CIRUGIAS = "SELECT * FROM Cirugias";
    
    private static Connection obtenerConexion(){
        login = new Conexion();
        cn  = login.getConnection();
        return cn;
    }
    
    public ArrayList<Cirugias> listarCirugias() throws SQLException{
        ArrayList<Cirugias> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionLista = cn.prepareStatement(LISTADO_CIRUGIAS);
        ResultSet result = declaracionLista.executeQuery();
        while(result.next()){
            Cirugias cirugia = new Cirugias();
            cirugia.setId(result.getInt("id"));
            cirugia.setEstado(result.getString("estado"));
            cirugia.setId_historial_medico(result.getInt("id_historial_medico"));
            cirugia.setTipo_operacion(result.getString("tipo_operacion"));
            cirugia.setFecha_cirugia(result.getDate("fecha_cirugia"));
            list.add(cirugia);
        }
        login.Desconectar();
        return list;
    }
}

