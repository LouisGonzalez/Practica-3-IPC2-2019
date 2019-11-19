package practica3.objetos;

import java.sql.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author luisGonzalez
 */
public class FacturasDTO {

    private int id, id_empleado_medico, nit, cant_producto, id_empleado_venta;
    private String nombres, estado, tipo, apellidos, ciudad, nombre_empleado, apellido_empleado;
    private Date fecha_factura;
    private float total;
    private JRBeanCollectionDataSource source;
    
    public FacturasDTO(List<VentasFactura> list){
        source = new JRBeanCollectionDataSource(list);
    }

    public JRBeanCollectionDataSource getSource() {
        return source;
    }

    public void setSource(JRBeanCollectionDataSource source) {
        this.source = source;
    }
    
    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getApellido_empleado() {
        return apellido_empleado;
    }

    public void setApellido_empleado(String apellido_empleado) {
        this.apellido_empleado = apellido_empleado;
    }
    
    public int getId_empleado_venta() {
        return id_empleado_venta;
    }

    public void setId_empleado_venta(int id_empleado_venta) {
        this.id_empleado_venta = id_empleado_venta;
    }
   
    public int getCant_producto() {
        return cant_producto;
    }

    public void setCant_producto(int cant_producto) {
        this.cant_producto = cant_producto;
    }
    
    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getId_empleado_medico() {
        return id_empleado_medico;
    }

    public void setId_empleado_medico(int id_empleado_medico) {
        this.id_empleado_medico = id_empleado_medico;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
