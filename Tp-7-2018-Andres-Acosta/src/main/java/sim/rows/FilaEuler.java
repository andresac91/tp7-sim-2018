package main.java.sim.rows;

import javafx.beans.property.SimpleStringProperty;

public class FilaEuler {
    private SimpleStringProperty step;
    private SimpleStringProperty metros;
    private SimpleStringProperty tiempo;
    private SimpleStringProperty dMdT;
    private SimpleStringProperty tiempoM1;
    private SimpleStringProperty metrosM1;

    public FilaEuler(String step, String metros, String tiempo, String dMdT, String tiempoM1, String metrosM1) {
        this.step = new SimpleStringProperty(step);
        this.metros =  new SimpleStringProperty(metros);
        this.tiempo =  new SimpleStringProperty(tiempo);
        this.dMdT =  new SimpleStringProperty(dMdT);
        this.tiempoM1 =  new SimpleStringProperty(tiempoM1);
        this.metrosM1 =  new SimpleStringProperty(metrosM1);
    }

    @Override
    public String toString() {
        return "FilaEuler{" +
                "step=" + step +
                ", metros=" + metros +
                ", tiempo=" + tiempo +
                ", dMdT=" + dMdT +
                ", tiempoM1=" + tiempoM1 +
                ", metrosM1=" + metrosM1 +
                '}';
    }

    public String getStep() {
        return step.get();
    }

    public SimpleStringProperty stepProperty() {
        return step;
    }

    public void setStep(String step) {
        this.step.set(step);
    }

    public String getMetros() {
        return metros.get();
    }

    public SimpleStringProperty metrosProperty() {
        return metros;
    }

    public void setMetros(String metros) {
        this.metros.set(metros);
    }

    public String getTiempo() {
        return tiempo.get();
    }

    public SimpleStringProperty tiempoProperty() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo.set(tiempo);
    }

    public String getdMdT() {
        return dMdT.get();
    }

    public SimpleStringProperty dMdTProperty() {
        return dMdT;
    }

    public void setdMdT(String dMdT) {
        this.dMdT.set(dMdT);
    }

    public String getTiempoM1() {
        return tiempoM1.get();
    }

    public SimpleStringProperty tiempoM1Property() {
        return tiempoM1;
    }

    public void setTiempoM1(String tiempoM1) {
        this.tiempoM1.set(tiempoM1);
    }

    public String getMetrosM1() {
        return metrosM1.get();
    }

    public SimpleStringProperty metrosM1Property() {
        return metrosM1;
    }

    public void setMetrosM1(String metrosM1) {
        this.metrosM1.set(metrosM1);
    }
}
