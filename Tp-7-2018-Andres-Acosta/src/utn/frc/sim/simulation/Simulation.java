package utn.frc.sim.simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utn.frc.sim.generators.distributions.DistributionRandomGenerator;
import utn.frc.sim.model.clientes.Client;
import utn.frc.sim.model.clientes.ClientGenerator;
import utn.frc.sim.model.servers.Server;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class Simulation {

    private static final Logger logger = LogManager.getLogger(Simulation.class);

    private LocalDateTime minuteStop45;
    private LocalDateTime last4HorusStop;
    private LocalDateTime clock;
    private LocalDateTime dayFirstEvent;
    private boolean is4hoursBreak;
    private boolean is45MinuteBreak;
    private Queue<Client> magicCarpetQueue;
    private Server magicCarpet;
    private Client clientOfEvent;
    private ClientGenerator clientGenerator;
    private Events lastEventGenerator;
    private int limitOfSimulations;
    private int maxAmountLast40Hours;
    private DistributionRandomGenerator capacityGenerator;

    private Simulation(int days){

    }

    public static Simulation ofType(int days){
        return new Simulation(days);
    }

    private void initEvent(){
        lastEventGenerator = Events.INICIO;
    }

    private void initFirstEventOfDay(){
    dayFirstEvent = LocalDateTime.of(2018,1,1,9,0,0);
    last4HorusStop = dayFirstEvent;
    minuteStop45 = dayFirstEvent;
    }

    private void initMaxAmountLast40Hours(){
        maxAmountLast40Hours = 0;
    }

    private void initMagicQueue(){
        magicCarpetQueue = new LinkedList<>();
    }

    private void initGlobalParameters(){
        is4hoursBreak = false;
        is45MinuteBreak = false;
    }

    private void initMagicCarpet(){
       // magicCarpet = new ServerWithInterruptions("Magic Carpet", timeEvent, 4, dayFirstEvent, timeEventOfInterruptions);
        //eventGenerators.add(magicCarpet);
    }





}
