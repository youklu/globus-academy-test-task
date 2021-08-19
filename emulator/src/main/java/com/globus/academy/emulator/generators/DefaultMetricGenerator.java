package com.globus.academy.emulator.generators;

import com.globus.academy.emulator.generators.entities.Metric;
import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class DefaultMetricGenerator implements MetricGenerator {

    private final String metricName;
    private final int minValue;
    private final int maxValue;

    @Override
    public Metric generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int value = random.nextInt(minValue, maxValue);
        return new Metric(metricName, value);
    }

    @Override
    public boolean canGenerate() {
        return true;
    }

}
