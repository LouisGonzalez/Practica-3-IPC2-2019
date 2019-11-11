package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Cirugias {
    
    private int id, id_historial_medico;
    private String estado, tipo_operacion;
    private Date fecha_cirugia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_historial_medico() {
        return id_historial_medico;
    }

    public void setId_historial_medico(int id_historial_medico) {
        this.id_historial_medico = id_historial_medico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public Date getFecha_cirugia() {
        return fecha_cirugia;
    }

    public void setFecha_cirugia(Date fecha_cirugia) {
        this.fecha_cirugia = fecha_cirugia;
    }
    
    
}
