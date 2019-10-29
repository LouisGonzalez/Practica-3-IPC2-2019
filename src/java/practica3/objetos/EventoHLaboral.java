package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class EventoHLaboral {

    private int id, id_historial_laboral;
    private String evento;
    private float monto;
    private Date fecha_evento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_historial_laboral() {
        return id_historial_laboral;
    }

    public void setId_historial_laboral(int id_historial_laboral) {
        this.id_historial_laboral = id_historial_laboral;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(Date fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

}
