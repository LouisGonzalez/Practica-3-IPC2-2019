package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Cirugias {
    
    private int id, id_historial_medico, id_empleado;
    private String estado, tipo_operacion, tipo_medico;
    private float precio_cirugia, costo_cirugia;
    private Date fecha_cirugia;

    public String getTipo_medico() {
        return tipo_medico;
    }

    public void setTipo_medico(String tipo_medico) {
        this.tipo_medico = tipo_medico;
    }
    
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    public float getPrecio_cirugia() {
        return precio_cirugia;
    }

    public void setPrecio_cirugia(float precio_cirugia) {
        this.precio_cirugia = precio_cirugia;
    }

    public float getCosto_cirugia() {
        return costo_cirugia;
    }

    public void setCosto_cirugia(float costo_cirugia) {
        this.costo_cirugia = costo_cirugia;
    }
    
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
