package main.java.sim.generators.distributions;

import main.java.sim.generators.distributions.valuegenerator.DistributionValueGenerator;

public class ConstantGenerator extends BaseRandomGenerator {

    private final double constant;

    private ConstantGenerator(double constant) {
        this.constant = constant;
    }

    public static ConstantGenerator createOf(double constant){
        return new ConstantGenerator(constant);
    }


    @Override
    public DistributionValueGenerator getDistribution() {
        return new ConstantValueGenerator();
    }

    private class ConstantValueGenerator implements DistributionValueGenerator{


        @Override
        public double getExpectedFrequency(double from, double to) {
            return 0;
        }
    }

    @Override
    public double random() {
        return constant;
    }




}
