package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class HistorialLaboral {
    
    private int id_empleado, id, no_periodo_laboral, year;
    private String nombres, apellidos, estado, area_trabajo, tipo_contratacion;
    private float salario_base, salario_descuento;
    private Date fecha_historial_laboral, fecha_contratacion;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String getTipo_contratacion() {
        return tipo_contratacion;
    }

    public void setTipo_contratacion(String tipo_contratacion) {
        this.tipo_contratacion = tipo_contratacion;
    }

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNo_periodo_laboral() {
        return no_periodo_laboral;
    }

    public void setNo_periodo_laboral(int no_periodo_laboral) {
        this.no_periodo_laboral = no_periodo_laboral;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(float salario_base) {
        this.salario_base = salario_base;
    }

    public float getSalario_descuento() {
        return salario_descuento;
    }

    public void setSalario_descuento(float salario_descuento) {
        this.salario_descuento = salario_descuento;
    }

    public Date getFecha_historial_laboral() {
        return fecha_historial_laboral;
    }

    public void setFecha_historial_laboral(Date fecha_historial_laboral) {
        this.fecha_historial_laboral = fecha_historial_laboral;
    }
    
    
}
