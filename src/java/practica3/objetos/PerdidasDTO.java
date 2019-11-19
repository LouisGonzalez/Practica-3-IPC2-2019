package practica3.objetos;

import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author luisGonzalez
 */
public class PerdidasDTO {
    
    private JRBeanCollectionDataSource source_empleados, source_cirugia, source_alta_medica;
    private float total_final;
    private float acumulado_empleados, acumulado_cirugia, acumulado_alta_medica;
    
    public PerdidasDTO(List<BeanReporteAdmin2> subEmpleados, List<BeanReporteAdmin2> subCirugia, List<BeanReporteAdmin2> subAltaMedica){
        source_empleados = new JRBeanCollectionDataSource(subEmpleados);
        source_cirugia = new JRBeanCollectionDataSource(subCirugia);
        source_alta_medica = new JRBeanCollectionDataSource(subAltaMedica);
    }

    public JRBeanCollectionDataSource getSource_empleados() {
        return source_empleados;
    }

    public void setSource_empleados(JRBeanCollectionDataSource source_empleados) {
        this.source_empleados = source_empleados;
    }

    public JRBeanCollectionDataSource getSource_cirugia() {
        return source_cirugia;
    }

    public void setSource_cirugia(JRBeanCollectionDataSource source_cirugia) {
        this.source_cirugia = source_cirugia;
    }

    public JRBeanCollectionDataSource getSource_alta_medica() {
        return source_alta_medica;
    }

    public void setSource_alta_medica(JRBeanCollectionDataSource source_alta_medica) {
        this.source_alta_medica = source_alta_medica;
    }

    public float getTotal_final() {
        return total_final;
    }

    public void setTotal_final(float total_final) {
        this.total_final = total_final;
    }

    public float getAcumulado_empleados() {
        return acumulado_empleados;
    }

    public void setAcumulado_empleados(float acumulado_empleados) {
        this.acumulado_empleados = acumulado_empleados;
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
    

}
