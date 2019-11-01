package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Consultas {
    
    private int id, cui, id_empleado_medico;
    private String nombres, apellidos, estado;
    private Date fecha_consulta;

    public int getId_empleado_medico() {
        return id_empleado_medico;
    }

    public void setId_empleado_medico(int id_empleado_medico) {
        this.id_empleado_medico = id_empleado_medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCui() {
        return cui;
    }

    public void setCui(int cui) {
        this.cui = cui;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(Date fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }
    
    
}
