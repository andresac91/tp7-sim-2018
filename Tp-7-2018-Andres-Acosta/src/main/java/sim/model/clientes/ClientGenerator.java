package main.java.sim.model.clientes;

import main.java.sim.model.EventGenerator;
import main.java.sim.model.TimeEvent;

import java.time.LocalDateTime;
import java.util.Optional;

public class ClientGenerator implements EventGenerator {
    private LocalDateTime nextClientEvent;
    private int lastClient;
    private TimeEvent timeEvent;

    public ClientGenerator(LocalDateTime initTime, TimeEvent timeEvent) {
        this.timeEvent = timeEvent;
        this.lastClient = 0;
        this.nextClientEvent = initTime;
    }

    public Client getNextClient() {

            calculateNextEvent();

          //  nextClientEvent = null;

        lastClient++;
        return new Client(lastClient);
    }

    private void calculateNextEvent() {
        calculateNextEventFrom(nextClientEvent);
    }

    public void forceNewNextEventFromClock(LocalDateTime clock){
        calculateNextEventFrom(clock);
    }

    private void calculateNextEventFrom(LocalDateTime timeFrom){
        nextClientEvent = timeEvent.calculateNextEventFromRandom(timeFrom);
    }
    public LocalDateTime getNextClientEvent() {
        return nextClientEvent;
    }
    @Override
    public Optional<LocalDateTime> getNextEvent() {
        return Optional.ofNullable(nextClientEvent);
    }

    @Override
    public boolean isEventFrom(LocalDateTime clock) {
        return nextClientEvent != null && nextClientEvent.equals(clock);
    }
}
