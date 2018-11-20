package main.java.sim.model.servers;

import main.java.sim.model.Event;
import main.java.sim.model.TimeEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServerWithInterruptions extends Server {

  //  private static final Logger logger = LogManager.getLogger(ServerWithInterruptions.class);
    private final int interruptPeriod;
    private LocalDateTime lastCleaning;
    private TimeEvent interruptEvent;
    private final int interruptorPeriod2;
    private TimeEvent interruptEvent2;
    private LocalDateTime lastInterruption;

    public ServerWithInterruptions(String serverName, TimeEvent timeEvent, int hours, LocalDateTime init,LocalDateTime initInterruption ,TimeEvent interruptEvent,int minutes,TimeEvent interruptEvent2) {
        super(serverName, timeEvent);
        this.interruptPeriod = hours;
        this.lastCleaning = init;
        this.lastInterruption = initInterruption;
        this.interruptEvent = interruptEvent;
        this.interruptorPeriod2 = minutes;
        this.interruptEvent2 = interruptEvent2;
    }



    @Override
    public Event getEvent() {
        Event event;
        if (getState() == ServerState.OCP) {
            event = new Event(getServingClient().orElse(null));
            if (stopIsNeededForCleaning()) {
               /** logger.info("{}: {} hours without interruptions. Next event is cleaning.",
                        getServerName(),
                        interruptPeriod);*/
                lastCleaning = geNextEnd();
                setNextEnd(interruptEvent.calculateNextEventFromRandom(lastCleaning));
                setState(ServerState.OUT);

            } else if (stopForInterruption()){
                lastInterruption = geNextEnd();
                setNextEnd(interruptEvent2.calculateNextEventFromRandom(lastInterruption));
                setState(ServerState.OUT2);
            } else {
                setNextEnd(null);
                setState(ServerState.LBR);
            }

        } else {
            setNextEnd(null);
            setServingClient(null);
            setState(ServerState.LBR);
            event = new Event();
        }
        setServingClient(null);
        return event;
    }

    private boolean stopIsNeededForCleaning() {
        return ChronoUnit.HOURS.between(lastCleaning, geNextEnd()) >= interruptPeriod;
    }

    private boolean stopForInterruption()
    {
        return ChronoUnit.MINUTES.between(lastInterruption, geNextEnd()) >= interruptorPeriod2;
    }
}
