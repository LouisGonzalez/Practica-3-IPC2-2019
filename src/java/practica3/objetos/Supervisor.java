package practica3.objetos;

/**
 *
 * @author luisGonzalez
 */
public class Supervisor {
    
    private int id, id_empleado, personas_a_cargo, limite_personas;
    private String area_trabajo, nombres, apellidos;

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

    public int getPersonas_a_cargo() {
        return personas_a_cargo;
    }

    public void setPersonas_a_cargo(int personas_a_cargo) {
        this.personas_a_cargo = personas_a_cargo;
    }

    public int getLimite_personas() {
        return limite_personas;
    }

    public void setLimite_personas(int limite_personas) {
        this.limite_personas = limite_personas;
    }

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }
    
    
}
