package practica3.objetos;

/**
 *
 * @author luisGonzalez
 */
public class Medicos {
    
    private int id, id_empleado, id_historial_medico;
    private String especialidad, tipo, nombres, apellidos;
    private float salario_base, salario_descuento;

    public int getId_historial_medico() {
        return id_historial_medico;
    }

    public void setId_historial_medico(int id_historial_medico) {
        this.id_historial_medico = id_historial_medico;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
