package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class BeanReporteAdmin {
    
    private int id_medicamento;
    private float ingreso_farmacia, ingreso_consulta, ingreso_hospital;
    private float total_farmacia, total_consulta, total_hospital;
    private float acumulado_farmacia, acumulado_consulta, acumulado_hospital;
    private Date fecha_farmacia, fecha_consulta, fecha_hospital;
    private String nombre_medicamento, nombre_consulta, apellido_consulta, nombre_hospital, apellido_hospital;

    public float getAcumulado_farmacia() {
        return acumulado_farmacia;
    }

    public void setAcumulado_farmacia(float acumulado_farmacia) {
        this.acumulado_farmacia = acumulado_farmacia;
    }

    public float getAcumulado_consulta() {
        return acumulado_consulta;
    }

    public void setAcumulado_consulta(float acumulado_consulta) {
        this.acumulado_consulta = acumulado_consulta;
    }

    public float getAcumulado_hospital() {
        return acumulado_hospital;
    }

    public void setAcumulado_hospital(float acumulado_hospital) {
        this.acumulado_hospital = acumulado_hospital;
    }
    
    
    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }
    
    public float getIngreso_farmacia() {
        return ingreso_farmacia;
    }

    public void setIngreso_farmacia(float ingreso_farmacia) {
        this.ingreso_farmacia = ingreso_farmacia;
    }

    public float getIngreso_consulta() {
        return ingreso_consulta;
    }

    public void setIngreso_consulta(float ingreso_consulta) {
        this.ingreso_consulta = ingreso_consulta;
    }

    public float getIngreso_hospital() {
        return ingreso_hospital;
    }

    public void setIngreso_hospital(float ingreso_hospital) {
        this.ingreso_hospital = ingreso_hospital;
    }

    public float getTotal_farmacia() {
        return total_farmacia;
    }

    public void setTotal_farmacia(float total_farmacia) {
        this.total_farmacia = total_farmacia;
    }

    public float getTotal_consulta() {
        return total_consulta;
    }

    public void setTotal_consulta(float total_consulta) {
        this.total_consulta = total_consulta;
    }

    public float getTotal_hospital() {
        return total_hospital;
    }

    public void setTotal_hospital(float total_hospital) {
        this.total_hospital = total_hospital;
    }

    public Date getFecha_farmacia() {
        return fecha_farmacia;
    }

    public void setFecha_farmacia(Date fecha_farmacia) {
        this.fecha_farmacia = fecha_farmacia;
    }

    public Date getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(Date fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public Date getFecha_hospital() {
        return fecha_hospital;
    }

    public void setFecha_hospital(Date fecha_hospital) {
        this.fecha_hospital = fecha_hospital;
    }

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public String getNombre_consulta() {
        return nombre_consulta;
    }

    public void setNombre_consulta(String nombre_consulta) {
        this.nombre_consulta = nombre_consulta;
    }

    public String getApellido_consulta() {
        return apellido_consulta;
    }

    public void setApellido_consulta(String apellido_consulta) {
        this.apellido_consulta = apellido_consulta;
    }

    public String getNombre_hospital() {
        return nombre_hospital;
    }

    public void setNombre_hospital(String nombre_hospital) {
        this.nombre_hospital = nombre_hospital;
    }

    public String getApellido_hospital() {
        return apellido_hospital;
    }

    public void setApellido_hospital(String apellido_hospital) {
        this.apellido_hospital = apellido_hospital;
    }

}
