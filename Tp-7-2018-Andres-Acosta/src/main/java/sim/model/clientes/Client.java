package main.java.sim.model.clientes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Client {

    private int clientNumber;
    private LocalDateTime inTime ;
    private LocalDateTime outTime;
    private LocalDateTime serveTime;
    private StateClient state;

    public StateClient getState() {
        return state;
    }

    public void setState(StateClient state) {
        this.state = state;
    }

    public Client(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public LocalDateTime getServeTime() {
        return serveTime;
    }

    public void setServeTime(LocalDateTime serveTime) {
        this.serveTime = serveTime;
    }

    public long getSecondsOfWaiting() {
        if (inTime == null || serveTime == null) {
            throw new IllegalStateException();
        }
        return (ChronoUnit.SECONDS.between(inTime,serveTime));
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientNumber=" + clientNumber +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                '}';
    }

    public String getClientNumberString() {
        return Integer.toString(clientNumber);
    }

    public String getStateString(){
        return state.toString();
    }

    public String getInTimeString() {
        return inTime.toString();
    }

    public String getServeTimeString() {
        return serveTime == null ? "-" :  serveTime.toString();
    }

    public String getOutTimeString() {
        return outTime== null ? "-" : outTime.toString();
    }
}
