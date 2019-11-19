package practica3.objetos;

import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author luisGonzalez
 */
public class MedicamentosDTO {
    private int id, cant_existencia, limite_existencia;
    private float costo_unitario, precio_venta, ganancia;
    private String nombre, descripcion;
    private JRBeanCollectionDataSource source;
    
    public MedicamentosDTO(List<Facturas> list){
        source = new JRBeanCollectionDataSource(list);
    }
    
    public JRBeanCollectionDataSource getSource() {
        return source;
    }

    public void setSource(JRBeanCollectionDataSource source) {
        this.source = source;
    }
    
    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }
    
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
