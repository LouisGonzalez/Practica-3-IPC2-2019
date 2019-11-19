package practica3.objetos;

import java.sql.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author luisGonzalez
 */
public class IngresosDTO {
    
    private JRBeanCollectionDataSource source_farmacia, source_consulta, source_hospital;
    private float total_final;
    private float acumulado_farmacia, acumulado_consulta, acumulado_hospital;
    
    public IngresosDTO(List<BeanReporteAdmin> subFarmacia, List<BeanReporteAdmin> subConsulta, List<BeanReporteAdmin> subHospital){
        source_farmacia = new JRBeanCollectionDataSource(subFarmacia);
        source_consulta = new JRBeanCollectionDataSource(subConsulta);
        source_hospital = new JRBeanCollectionDataSource(subHospital);        
    }

    public float getAcumulado_farmacia() {
        return acumulado_farmacia;
    }

    public void setAcumulado_farmacia(float acumulado_farmacia) {
        this.acumulado_farmacia = acumulado_farmacia;
    }

    public float getAcumulado_consulta() {
        return acumulado_consulta;
    }

    public void setAcumulado_consulta(float acumulado_consulta) {
        this.acumulado_consulta = acumulado_consulta;
    }

    public float getAcumulado_hospital() {
        return acumulado_hospital;
    }

    public void setAcumulado_hospital(float acumulado_hospital) {
        this.acumulado_hospital = acumulado_hospital;
    }

    public float getTotal_final() {
        return total_final;
    }

    public void setTotal_final(float total_final) {
        this.total_final = total_final;
    }

    public JRBeanCollectionDataSource getSource_farmacia() {
        return source_farmacia;
    }

    public void setSource_farmacia(JRBeanCollectionDataSource source_farmacia) {
        this.source_farmacia = source_farmacia;
    }

    public JRBeanCollectionDataSource getSource_consulta() {
        return source_consulta;
    }

    public void setSource_consulta(JRBeanCollectionDataSource source_consulta) {
        this.source_consulta = source_consulta;
    }

    public JRBeanCollectionDataSource getSource_hospital() {
        return source_hospital;
    }

    public void setSource_hospital(JRBeanCollectionDataSource source_hospital) {
        this.source_hospital = source_hospital;
    }

}
