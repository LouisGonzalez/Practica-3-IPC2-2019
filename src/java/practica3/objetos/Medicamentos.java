package practica3.objetos;

/**
 *
 * @author luisGonzalez
 */
public class Medicamentos {
    
    private int id, cant_existencia, limite_existencia;
    private float costo_unitario, precio_venta;
    private String nombre, descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCant_existencia() {
        return cant_existencia;
    }

    public void setCant_existencia(int cant_existencia) {
        this.cant_existencia = cant_existencia;
    }

    public int getLimite_existencia() {
        return limite_existencia;
    }

    public void setLimite_existencia(int limite_existencia) {
        this.limite_existencia = limite_existencia;
    }

    public float getCosto_unitario() {
        return costo_unitario;
    }

    public void setCosto_unitario(float costo_unitario) {
        this.costo_unitario = costo_unitario;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
