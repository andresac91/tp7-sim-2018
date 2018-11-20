package main.java.sim.generators.distributions;

import main.java.sim.generators.RandomGenerator;
import main.java.sim.generators.distributions.valuegenerator.DistributionValueGenerator;

public interface DistributionRandomGenerator extends RandomGenerator {
    DistributionValueGenerator getDistribution();
}
