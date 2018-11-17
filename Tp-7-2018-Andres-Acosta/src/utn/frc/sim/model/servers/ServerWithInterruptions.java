package utn.frc.sim.model.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utn.frc.sim.model.Event;
import utn.frc.sim.model.TimeEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServerWithInterruptions extends Server {

    private static final Logger logger = LogManager.getLogger(ServerWithInterruptions.class);
    private final int interruptPeriod;
    private LocalDateTime lastCleaning;
    private TimeEvent interruptEvent;

    public ServerWithInterruptions(String serverName, TimeEvent timeEvent, int hours, LocalDateTime init, TimeEvent interruptEvent) {
        super(serverName, timeEvent);
        this.interruptPeriod = hours;
        this.lastCleaning = init;
        this.interruptEvent = interruptEvent;
    }

    @Override
    public Event getEvent() {
        Event event;
        if (getState() == ServerState.OCP) {
            event = new Event(getServingClient().orElse(null));
            if (stopIsNeededForCleaning()) {
                logger.info("{}: {} hours without interruptions. Next event is cleaning.",
                        getServerName(),
                        interruptPeriod);
                lastCleaning = getNextEnd();
                setNextEnd(interruptEvent.calculateNextEventFromRandom(lastCleaning));
                setState(ServerState.OUT);

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
        return ChronoUnit.HOURS.between(lastCleaning, getNextEnd()) >= interruptPeriod;
    }
}
