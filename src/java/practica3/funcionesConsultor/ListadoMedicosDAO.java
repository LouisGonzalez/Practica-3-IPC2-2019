package practica3.funcionesConsultor;

import java.sql.*;
import java.util.ArrayList;
import practica3.conexion.Conexion;
import practica3.objetos.Medicos;

/**
 *
 * @author luisGonzalez
 */
public class ListadoMedicosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO_MEDICOS = "SELECT * FROM Medicos WHERE tipo = ? ORDER BY id";
    private static final String NOMBRE_MEDICOS = "SELECT * FROM Empleados WHERE id = ?";

    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Medicos> listarMedicos() throws SQLException{
        ArrayList<Medicos> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement decListadoMedicos = cn.prepareStatement(LISTADO_MEDICOS);
        decListadoMedicos.setString(1, "Medico");
        ResultSet result = decListadoMedicos.executeQuery();
        while(result.next()){
            Medicos medico = new Medicos();
            medico.setId_empleado(result.getInt("id_empleado"));
            medico.setEspecialidad(result.getString("especialidad"));
            //statement para obtener el nombre de los medicos   
            PreparedStatement decNombreMedicos = cn.prepareStatement(NOMBRE_MEDICOS);
            decNombreMedicos.setInt(1, medico.getId_empleado());
            ResultSet result2 = decNombreMedicos.executeQuery();
            while(result2.next()){
                medico.setNombres(result2.getString("nombres"));
                medico.setApellidos(result2.getString("apellidos"));
            }
            list.add(medico);
        }
        login.Desconectar();
        return list;
    }
    
    
}

