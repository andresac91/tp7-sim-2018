package main.java.sim.Euler;

import main.java.sim.rows.FilaEuler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Euler {


    private double metros;
    private double tiempo;
    private double h;
    private double dMdT;
    private double tiempoM1;
    private double metrosM1;
    private List<FilaEuler> listEuler;
    private int step;


    public void initValues(){
        metros = 0;
        tiempo = 0;
        h = 0.002;
        dMdT = 0;
        tiempoM1 = 0;
        metrosM1 = 0;
        listEuler = new ArrayList<>();
        step = 0;
    }

    public void addTiempo(){
        this.tiempoM1 = tiempo + h;
    }

    public double calculateMetrosM1(){
        this.metrosM1 = metros + h * dMdT;
        return  metrosM1;
    }

    public void calculateDMDT(){
       dMdT = 0.6* Math.pow(metros,2) - 0.15 * metros +8;
    }

    public List<FilaEuler> resultado(){
        DecimalFormat df = new DecimalFormat("0.0000");
        initValues();
        String resultado = "";
        while (metrosM1 < 120){
            step ++;
            metros = metrosM1;
            tiempo = tiempoM1;
            calculateDMDT();
            calculateMetrosM1();
            addTiempo();
            listEuler.add(new FilaEuler( String.valueOf(step), df.format(tiempo), df.format(Math.round(metros)), df.format(dMdT), df.format(tiempoM1), df.format(metrosM1)));
          //  resultado = " "+ df.format(tiempo) + " " +df.format(Math.round(metros))+ " " + df.format(dMdT) + " " + df.format(tiempoM1) + " " + df.format(metrosM1) + "\n";

        }
        return listEuler;
    }

    public List<FilaEuler> getListEuler() {
        return listEuler;
    }

    public void setListEuler(List<FilaEuler> listEuler) {
        this.listEuler = listEuler;
    }

    public double getMetros() {
        return metros;
    }

    public void setMetros(double metros) {
        this.metros = metros;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getdMdT() {
        return dMdT;
    }

    public void setdMdT(double dMdT) {
        this.dMdT = dMdT;
    }

    public String getTiempoM1() {
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(tiempoM1);


    }

    public void setTiempoM1(double tiempoM1) {
        this.tiempoM1 = tiempoM1;
    }

    public double getMetrosM1() {
        return metrosM1;
    }

    public void setMetrosM1(double metrosM1) {
        this.metrosM1 = metrosM1;
    }

    @Override
    public String toString() {
        return "Euler{" +
                "metros=" + metros +
                ", tiempo=" + tiempo +
                ", h=" + h +
                ", dMdT=" + dMdT +
                ", tiempoM1=" + tiempoM1 +
                ", metrosM1=" + metrosM1 +
                '}';
    }
}
