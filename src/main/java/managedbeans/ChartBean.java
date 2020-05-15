package managedbeans;

import com.google.inject.Inject;
import entities.Iniciativa;
import entities.Usuario;
import exceptions.ServicesException;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import managedbeans.IniciativaBean;
import services.ServiciosIniciativa;

@ManagedBean(name = "ChartBean")
@SessionScoped
public class ChartBean  implements Serializable  {
    private PieChartModel pieModel1;
    private List<Integer> estadistica;

    @Inject
    private ServiciosIniciativa serviciosIniciativa;

    @PostConstruct
    public void init() throws ServicesException {
        System.out.println("ENTRO AL METODO");
        createPieModels();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    private void createPieModels() throws ServicesException {
        createPieModel1();
    }

    private void createPieModel1() throws ServicesException {
        estadistica = serviciosIniciativa.getEstadisitica();

        System.out.println(estadistica);
        pieModel1 = new PieChartModel();

        pieModel1.set("finanzas",1);
        pieModel1.set("administrativo", 2);
        pieModel1.set("recursosHumanos",3);
        pieModel1.set("TI",4);
        pieModel1.set("unidadDeProyectos", 5);

        pieModel1.setTitle("Estadisitica por Areas");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public List<Integer> getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(List<Integer> estadistica) {
        this.estadistica = estadistica;
    }

    public ServiciosIniciativa getServiciosIniciativa() {
        return serviciosIniciativa;
    }

    public void setServiciosIniciativa(ServiciosIniciativa serviciosIniciativa) {
        this.serviciosIniciativa = serviciosIniciativa;
    }
}