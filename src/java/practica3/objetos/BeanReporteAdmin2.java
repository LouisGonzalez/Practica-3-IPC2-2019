package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class BeanReporteAdmin2 {

    private float perdida_empleado, perdida_cirugia, perdida_alta_medica;
    private float total_empleado, total_cirugia, total_alta_medica;
    private float acumulado_empleado, acumulado_cirugia, acumulado_alta_medica;
    private Date fecha_empleado, fecha_cirugia, fecha_alta_medica;
    private String nombres_cirugia, apellidos_cirugia, nombres_empleado, apellidos_empleado, nombres_alta_medica, apellidos_alta_medica;

    public float getPerdida_empleado() {
        return perdida_empleado;
    }

    public void setPerdida_empleado(float perdida_empleado) {
        this.perdida_empleado = perdida_empleado;
    }

    public float getPerdida_cirugia() {
        return perdida_cirugia;
    }

    public void setPerdida_cirugia(float perdida_cirugia) {
        this.perdida_cirugia = perdida_cirugia;
    }

    public float getPerdida_alta_medica() {
        return perdida_alta_medica;
    }

    public void setPerdida_alta_medica(float perdida_alta_medica) {
        this.perdida_alta_medica = perdida_alta_medica;
    }

    public float getTotal_empleado() {
        return total_empleado;
    }

    public void setTotal_empleado(float total_empleado) {
        this.total_empleado = total_empleado;
    }

    public float getTotal_cirugia() {
        return total_cirugia;
    }

    public void setTotal_cirugia(float total_cirugia) {
        this.total_cirugia = total_cirugia;
    }

    public float getTotal_alta_medica() {
        return total_alta_medica;
    }

    public void setTotal_alta_medica(float total_alta_medica) {
        this.total_alta_medica = total_alta_medica;
    }

    public float getAcumulado_empleado() {
        return acumulado_empleado;
    }

    public void setAcumulado_empleado(float acumulado_empleado) {
        this.acumulado_empleado = acumulado_empleado;
    }

    public float getAcumulado_cirugia() {
        return acumulado_cirugia;
    }

    public void setAcumulado_cirugia(float acumulado_cirugia) {
        this.acumulado_cirugia = acumulado_cirugia;
    }

    public float getAcumulado_alta_medica() {
        return acumulado_alta_medica;
    }

    public void setAcumulado_alta_medica(float acumulado_alta_medica) {
        this.acumulado_alta_medica = acumulado_alta_medica;
    }

    public Date getFecha_empleado() {
        return fecha_empleado;
    }

    public void setFecha_empleado(Date fecha_empleado) {
        this.fecha_empleado = fecha_empleado;
    }

    public Date getFecha_cirugia() {
        return fecha_cirugia;
    }

    public void setFecha_cirugia(Date fecha_cirugia) {
        this.fecha_cirugia = fecha_cirugia;
    }

    public Date getFecha_alta_medica() {
        return fecha_alta_medica;
    }

    public void setFecha_alta_medica(Date fecha_alta_medica) {
        this.fecha_alta_medica = fecha_alta_medica;
    }

    public String getNombres_cirugia() {
        return nombres_cirugia;
    }

    public void setNombres_cirugia(String nombres_cirugia) {
        this.nombres_cirugia = nombres_cirugia;
    }

    public String getApellidos_cirugia() {
        return apellidos_cirugia;
    }

    public void setApellidos_cirugia(String apellidos_cirugia) {
        this.apellidos_cirugia = apellidos_cirugia;
    }

    public String getNombres_empleado() {
        return nombres_empleado;
    }

    public void setNombres_empleado(String nombres_empleado) {
        this.nombres_empleado = nombres_empleado;
    }

    public String getApellidos_empleado() {
        return apellidos_empleado;
    }

    public void setApellidos_empleado(String apellidos_empleado) {
        this.apellidos_empleado = apellidos_empleado;
    }

    public String getNombres_alta_medica() {
        return nombres_alta_medica;
    }

    public void setNombres_alta_medica(String nombres_alta_medica) {
        this.nombres_alta_medica = nombres_alta_medica;
    }

    public String getApellidos_alta_medica() {
        return apellidos_alta_medica;
    }

    public void setApellidos_alta_medica(String apellidos_alta_medica) {
        this.apellidos_alta_medica = apellidos_alta_medica;
    }
    
}
