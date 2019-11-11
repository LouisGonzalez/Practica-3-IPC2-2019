package practica3.objetos;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Empleados {
    
    private String nombres, apellidos, area_trabajo, tipo_contratacion;
    private int edad, CUI, id;
    private Date fecha_contratacion;

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

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }

    public String getTipo_contratacion() {
        return tipo_contratacion;
    }

    public void setTipo_contratacion(String tipo_contratacion) {
        this.tipo_contratacion = tipo_contratacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCUI() {
        return CUI;
    }

    public void setCUI(int CUI) {
        this.CUI = CUI;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
    
    
    
}
