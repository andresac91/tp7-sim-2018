package main.java.sim.rows;

import javafx.beans.property.SimpleStringProperty;

public class FilaClient {

    private SimpleStringProperty numberOfClient;
    private SimpleStringProperty stateOfClient;
    private SimpleStringProperty inTime;
    private SimpleStringProperty serverTime;
    private SimpleStringProperty outTime;

    public FilaClient(String numberOfClient, String stateOfClient, String inTime, String serverTime, String outTime) {
        this.numberOfClient = new SimpleStringProperty(numberOfClient);
        this.stateOfClient = new SimpleStringProperty(stateOfClient);
        this.inTime = new SimpleStringProperty(inTime);
        this.serverTime = new SimpleStringProperty(serverTime);
        this.outTime = new SimpleStringProperty(outTime);
    }

    public String getNumberOfClient() {
        return numberOfClient.get();
    }

    public SimpleStringProperty numberOfClientProperty() {
        return numberOfClient;
    }

    public void setNumberOfClient(String numberOfClient) {
        this.numberOfClient.set(numberOfClient);
    }

    public String getStateOfClient() {
        return stateOfClient.get();
    }

    public SimpleStringProperty stateOfClientProperty() {
        return stateOfClient;
    }

    public void setStateOfClient(String stateOfClient) {
        this.stateOfClient.set(stateOfClient);
    }

    public String getInTime() {
        return inTime.get();
    }

    public SimpleStringProperty inTimeProperty() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime.set(inTime);
    }

    public String getServerTime() {
        return serverTime.get();
    }

    public SimpleStringProperty serverTimeProperty() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime.set(serverTime);
    }

    public String getOutTime() {
        return outTime.get();
    }

    public SimpleStringProperty outTimeProperty() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime.set(outTime);
    }

    @Override
    public String toString() {
        return "FilaClient{" +
                "numberOfClient=" + numberOfClient +
                ", stateOfClient=" + stateOfClient +
                ", inTime=" + inTime +
                ", serverTime=" + serverTime +
                ", outTime=" + outTime +
                '}';
    }
}
