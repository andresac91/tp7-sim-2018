package utn.frc.sim;

public class Euler {

    private double x;
    private double t;
    private double z; //f(Xm;Tm)
    private double tStep;
    private double euler;



    public Euler() {

    }

    private double ca

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double gettStep() {
        return tStep;
    }

    public void settStep(double tStep) {
        this.tStep = tStep;
    }

    public double getEuler() {
        return euler;
    }

    public void setEuler(double euler) {
        this.euler = euler;
    }
}
