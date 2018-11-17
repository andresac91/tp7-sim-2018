package utn.frc.sim.model.clientes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Client {

    private int clientNumber;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private LocalDateTime serveTime;

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
        return ChronoUnit.SECONDS.between(inTime, serveTime);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientNumber=" + clientNumber +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                '}';
    }
}
