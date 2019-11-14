package practica3.objetos;

/**
 *
 * @author luisGonzalez
 */
public class Tarifas {
    
    private int id, dias;
    private String descripcion;
    private float total, nuevoTotal;

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public float getNuevoTotal() {
        return nuevoTotal;
    }

    public void setNuevoTotal(float nuevoTotal) {
        this.nuevoTotal = nuevoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
