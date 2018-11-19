package utn.frc.sim.model;

import utn.frc.sim.generators.distributions.DistributionRandomGenerator;
import utn.frc.sim.generators.distributions.NormalDistributionGenerator;
import utn.frc.sim.model.clientes.Client;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TimeEventEuler {
    private static final Map<KeyOfEuler, Long> valuesCalculated = new HashMap<>();
    private DistributionRandomGenerator kGenerator;
    private static final double K_LOW_LIMIT = 0.15;
    private static final double K_HIGH_LIMIT = 0.25;

    public TimeEventEuler() {
        initKGenerator();
    }

    private void initKGenerator() {
        kGenerator = NormalDistributionGenerator.createOf(0.2, 0.7);
    }

    public LocalDateTime calculateNextEventFromDistribution(LocalDateTime timeFrom, Client client) {
      //  client.setK(getKValue());
        long seconds = getTimeInSecondsForClient(client);
        return timeFrom.plus(seconds, ChronoUnit.SECONDS);
    }

    private long getTimeInSecondsForClient(Client client) {
        double k = 0;
        double l = 0;
        KeyOfEuler key = new KeyOfEuler(k, l);
        Optional<Long> value = Optional.ofNullable(valuesCalculated.get(key));
        return value.orElse(calculateNewValueAndAddToMap(key));
    }

    private Long calculateNewValueAndAddToMap(KeyOfEuler key) {
        double metros = 0;
        double tiempo = 0;
        double h = 0.002;
        double dMdT = 0;
        double tiempoM1 = 0;
        double metrosM1 = 0;
        while (metrosM1 < 120) {

            //z3 = calculateZ3(k, z1, z2);
        }
        long longT = (long) tiempo;
        valuesCalculated.put(key, longT);
        return longT;
    }

    private double calculateZ3(double k, double z1, double z2) {
        return -20 * z2 - k * z1;
    }

    private double getKValue() {
        double k = kGenerator.random();
        if (k < K_LOW_LIMIT) {
            return K_LOW_LIMIT;
        } else if (k > K_HIGH_LIMIT) {
            return K_HIGH_LIMIT;
        }
        return k;
    }
}
