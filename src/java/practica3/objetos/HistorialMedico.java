package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class HistorialMedico {
    
    private int id, cui_paciente, dias_hospitalizado, no_cama, no_habitacion, id_enfermera_mando;
    private String nombres, apellidos, padecimiento, estado;
    private Date fecha_historial_medico;
    private float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCui_paciente() {
        return cui_paciente;
    }

    public void setCui_paciente(int cui_paciente) {
        this.cui_paciente = cui_paciente;
    }

    public int getDias_hospitalizado() {
        return dias_hospitalizado;
    }

    public void setDias_hospitalizado(int dias_hospitalizado) {
        this.dias_hospitalizado = dias_hospitalizado;
    }

    public int getNo_cama() {
        return no_cama;
    }

    public void setNo_cama(int no_cama) {
        this.no_cama = no_cama;
    }

    public int getNo_habitacion() {
        return no_habitacion;
    }

    public void setNo_habitacion(int no_habitacion) {
        this.no_habitacion = no_habitacion;
    }

    public int getId_enfermera_mando() {
        return id_enfermera_mando;
    }

    public void setId_enfermera_mando(int id_enfermera_mando) {
        this.id_enfermera_mando = id_enfermera_mando;
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

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_historial_medico() {
        return fecha_historial_medico;
    }

    public void setFecha_historial_medico(Date fecha_historial_medico) {
        this.fecha_historial_medico = fecha_historial_medico;
    }
    
    
    
    
    
}
