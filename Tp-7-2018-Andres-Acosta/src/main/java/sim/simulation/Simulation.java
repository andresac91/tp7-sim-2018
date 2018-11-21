package main.java.sim.simulation;


import main.java.sim.Euler.Euler;
import main.java.sim.generators.distributions.ConstantGenerator;
import main.java.sim.generators.distributions.DistributionRandomGenerator;
import main.java.sim.generators.distributions.UniformDistributionGenerator;
import main.java.sim.model.Event;
import main.java.sim.model.EventGenerator;
import main.java.sim.model.TimeEvent;
import main.java.sim.model.clientes.Client;
import main.java.sim.model.clientes.ClientGenerator;
import main.java.sim.model.clientes.StateClient;
import main.java.sim.model.servers.Server;
import main.java.sim.model.servers.ServerWithInterruptions;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Simulation {



    private LocalDateTime minuteStop45;
    private LocalDateTime last4HorusStop;
    private LocalDateTime clock;
    private LocalDateTime dayFirstEvent;
    private Queue<Client> magicCarpetQueue;
    private Server magicCarpet;
    private Client clientOfEvent;
    private ClientGenerator clientGenerator;
    private Events lastEventDescription;
    private int maxAmountLast40Hours;
    private List<Client> client;
    private double maxDurationInQueue;
    private Client clientOfMaxDuration;
    private List<EventGenerator> eventGenerators;
    private Double timeEuler;

    private void initListOfEventGenerators() {
        eventGenerators = new ArrayList<>();
    }

    private Simulation( ){
        initSimulation();
    }

    public static Simulation start( ) {
        return new Simulation();
    }

    private void initSimulation( ) {
        initEuler();
        initClient();
        initListOfEventGenerators();
        initFirstEventOfDay();
        initStatistics();
        initClientGenerator();
        initMagicCarpet();
        initEvent();


    }

    private void initEuler() {
        Euler euler = new Euler();
        euler.resultado();
        timeEuler = euler.getTiempo();
        //System.out.println(timeEuler);
    }

    private void initClient() {
        client = new ArrayList<>();
    }

    public void step() throws SimulationFinishedException {
        clock = getNextEvent();
        handleEventFromFirstEvent(clock);
    }

    private void initClientGenerator() {
        DistributionRandomGenerator generator = UniformDistributionGenerator.createOf(2.92,3.08);
        TimeEvent timeEvent = TimeEvent.create(generator, ChronoUnit.MINUTES, ChronoUnit.SECONDS);
        clientGenerator = new ClientGenerator(dayFirstEvent, timeEvent);
        eventGenerators.add(clientGenerator);
    }

    private void initStatistics() {
        maxDurationInQueue = 0;
        maxAmountLast40Hours = 0;
    }



    private void initEvent(){
        lastEventDescription = Events.INICIO;
    }

    private void initFirstEventOfDay(){
        dayFirstEvent = LocalDateTime.of(2018,1,1,9,0,0);
        last4HorusStop = dayFirstEvent;
        minuteStop45 = dayFirstEvent;
    }

    private void initMagicCarpet(){
        magicCarpetQueue = new LinkedList<>();
        DistributionRandomGenerator generator = ConstantGenerator.createOf(timeEuler);
        TimeEvent timeEvent = TimeEvent.create(generator, ChronoUnit.MINUTES, ChronoUnit.SECONDS);
        DistributionRandomGenerator generatorTimeOfInterruptions = ConstantGenerator.createOf(15.0);
        TimeEvent timeOfInterruption = TimeEvent.create(generatorTimeOfInterruptions, ChronoUnit.MINUTES, ChronoUnit.SECONDS);
        DistributionRandomGenerator generatorTimeOfInterruptions2 = ConstantGenerator.createOf(10);
        TimeEvent timeOfInterruption2 = TimeEvent.create(generatorTimeOfInterruptions2,ChronoUnit.MINUTES,ChronoUnit.SECONDS);
        magicCarpet = new ServerWithInterruptions("Magic Carpet",timeEvent, 4, dayFirstEvent, dayFirstEvent,timeOfInterruption,45,timeOfInterruption2);
        eventGenerators.add(magicCarpet);
    }

    private void handleEventFromFirstEvent(LocalDateTime clock) throws SimulationFinishedException{
        if (clock.getDayOfMonth() >= 2 && clock.getHour() >= 16  ) {
            throw new SimulationFinishedException();
        }
        calculateMaxAmountOfQueue();
        if (dayFirstEvent.isEqual(clock)) {
            lastEventDescription = Events.INICIO_DEL_DIA;
            dayFirstEvent = dayFirstEvent.plus(1, ChronoUnit.DAYS);
            clientOfEvent = null;
        } else {
            handleEventFromClients(clock);
        }
    }

    private void handleEventFromClients (LocalDateTime clock){
        if (clientGenerator.isEventFrom(clock)){
            Client nextClient = clientGenerator.getNextClient();
            nextClient.setInTime(clock);

            client.add(nextClient);

            if (magicCarpet.isFree()) {
                nextClient.setServeTime(clock);
                nextClient.setState(StateClient.EN_ALFOMBRA);
                magicCarpet.serveToClient(clock, nextClient);
            } else {
                nextClient.setState(StateClient.EN_COLA);
                magicCarpetQueue.add(nextClient);
            }
            clientOfEvent = nextClient;
            lastEventDescription = Events.LLEGADA_PERSONA;
        } else {
            handleEventFromMagicCarpet(clock);
        }
    }

    private void handleEventFromMagicCarpet(LocalDateTime clock) {
        if (magicCarpet.isEventFrom(clock)){
            lastEventDescription = Events.FIN_TIRADA;
            Event event = magicCarpet.getEvent();
            if (event.hasClient()){
                Client finishedClient = event.getClient();
                finishedClient.setOutTime(clock);
                finishedClient.setState(StateClient.TERMINADO);
                calculateMaxDurationInQueue(finishedClient);
                clientOfEvent = finishedClient;
            }else if (stopLimpieza()){
                lastEventDescription = Events.FIN_LIMPIEZA;
                clientOfEvent = null;
            }else if (stop45MinuteBreak()){
                lastEventDescription = Events.FIN_INTERRUPTION;
                clientOfEvent = null;
            }
            if (!magicCarpetQueue.isEmpty() && magicCarpet.isFree()) {
                Client firstClient = magicCarpetQueue.poll();
                assert firstClient != null;
                firstClient.setServeTime(clock);
                magicCarpet.serveToClient(clock, firstClient);
                firstClient.setState(StateClient.EN_ALFOMBRA);
            }
        }

    }

    private LocalDateTime getNextEvent() {
        final LocalDateTime firstEvent = dayFirstEvent;

        Optional<LocalDateTime> possibleFirstEvent = eventGenerators.stream()
                .map(EventGenerator::getNextEvent)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(localDateTime -> localDateTime.isBefore(firstEvent))
                .min(LocalDateTime::compareTo);

        return possibleFirstEvent.orElse(firstEvent);
    }


    private boolean stop45MinuteBreak() {
        return ChronoUnit.MINUTES.between(minuteStop45, clock) >= 45;
    }

    private boolean stopLimpieza() {
        return ChronoUnit.HOURS.between(last4HorusStop, clock) >= 4;
    }

    private void calculateMaxAmountOfQueue() {
        if(magicCarpetQueue.size() > maxAmountLast40Hours){
            maxAmountLast40Hours = magicCarpetQueue.size();
        }
    }

    private void calculateMaxDurationInQueue(Client client){
        if (client.getSecondsOfWaiting() > maxDurationInQueue){
            maxDurationInQueue = client.getSecondsOfWaiting();
            clientOfMaxDuration = client;
        }
    }

    public Server getMagicCarpet() {
        return magicCarpet;
    }

    public Queue<Client> getMagicCarpetQueue() {
        return magicCarpetQueue;
    }

    public LocalDateTime getClock() {
        return clock;
    }

    public Events getLastEventDescription() {
        return lastEventDescription;
    }

    public ClientGenerator getClientGenerator() {
        return clientGenerator;
    }

    public Optional<Client> getClientOfEvent() {
        return Optional.ofNullable(clientOfEvent);
    }

    public Optional<Client> getClientOfMaxDuration() {
        return Optional.ofNullable(clientOfMaxDuration);
    }

    public double getMaxDurationInQueue() {
        return maxDurationInQueue;
    }

    public int getMaxAmountLast40Hours() {
        return maxAmountLast40Hours;
    }

    public List<Client> getClient(){
        return client;
    }
}

