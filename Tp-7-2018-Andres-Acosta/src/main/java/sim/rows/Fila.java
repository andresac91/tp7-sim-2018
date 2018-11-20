package main.java.sim.rows;


import javafx.beans.property.SimpleStringProperty;

public class Fila {

    private final SimpleStringProperty event;
    private final SimpleStringProperty clock;
    private final SimpleStringProperty client;
    private final SimpleStringProperty nextClient;
    private final SimpleStringProperty stateMagicCarpet;
    private final SimpleStringProperty clientMagicCarpet;
    private final SimpleStringProperty endMagicCarpet;
    private final SimpleStringProperty queueMagicCarpet;


    public Fila(String event, String clock, String client, String nextClient, String stateMagicCarpet, String clientMagicCarpet, String endMagicCarpet, String queueMagicCarpet) {
        this.event = new SimpleStringProperty(event);
        this.clock = new SimpleStringProperty(clock);
        this.client = new SimpleStringProperty(client);
        this.nextClient = new SimpleStringProperty(nextClient);
        this.stateMagicCarpet = new SimpleStringProperty(stateMagicCarpet);
        this.clientMagicCarpet = new SimpleStringProperty(clientMagicCarpet);
        this.endMagicCarpet = new SimpleStringProperty(endMagicCarpet);
        this.queueMagicCarpet = new SimpleStringProperty(queueMagicCarpet);
    }

    public String getEvent() {
        return event.get();
    }

    public SimpleStringProperty eventProperty() {
        return event;
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public String getClock() {
        return clock.get();
    }

    public SimpleStringProperty clockProperty() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock.set(clock);
    }

    public String getClient() {
        return client.get();
    }

    public SimpleStringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getNextClient() {
        return nextClient.get();
    }

    public SimpleStringProperty nextClientProperty() {
        return nextClient;
    }

    public void setNextClient(String nextClient) {
        this.nextClient.set(nextClient);
    }

    public String getStateMagicCarpet() {
        return stateMagicCarpet.get();
    }

    public SimpleStringProperty stateMagicCarpetProperty() {
        return stateMagicCarpet;
    }

    public void setStateMagicCarpet(String stateMagicCarpet) {
        this.stateMagicCarpet.set(stateMagicCarpet);
    }

    public String getClientMagicCarpet() {
        return clientMagicCarpet.get();
    }

    public SimpleStringProperty clientMagicCarpetProperty() {
        return clientMagicCarpet;
    }

    public void setClientMagicCarpet(String clientMagicCarpet) {
        this.clientMagicCarpet.set(clientMagicCarpet);
    }

    public String getEndMagicCarpet() {
        return endMagicCarpet.get();
    }

    public SimpleStringProperty endMagicCarpetProperty() {
        return endMagicCarpet;
    }

    public void setEndMagicCarpet(String endMagicCarpet) {
        this.endMagicCarpet.set(endMagicCarpet);
    }

    public String getQueueMagicCarpet() {
        return queueMagicCarpet.get();
    }

    public SimpleStringProperty queueMagicCarpetProperty() {
        return queueMagicCarpet;
    }

    public void setQueueMagicCarpet(String queueMagicCarpet) {
        this.queueMagicCarpet.set(queueMagicCarpet);
    }

    @Override
    public String toString() {
        return "Fila{" +
                "event=" + event +
                ", clock=" + clock +
                ", cliente=" + client +
                ", nextCliente=" + nextClient +
                ", stateMagicCarpet=" + stateMagicCarpet +
                ", clientMagicCarpet=" + clientMagicCarpet +
                ", endMagicCarpet=" + endMagicCarpet +
                ", queueMagicCarpet=" + queueMagicCarpet +
                '}';
    }
}
