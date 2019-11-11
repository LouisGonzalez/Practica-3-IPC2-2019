package practica3.objetos;

import java.sql.*;

/**
 *
 * @author luisGonzalez
 */
public class EventoHMedico {
    
    private int id, id_historial_medico, id_medicamento;
    private String evento;
    private float cobro;
    private Date fecha_evento;

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

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public float getCobro() {
        return cobro;
    }

    public void setCobro(float cobro) {
        this.cobro = cobro;
    }

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(Date fecha_evento) {
        this.fecha_evento = fecha_evento;
    }
    
    
}
