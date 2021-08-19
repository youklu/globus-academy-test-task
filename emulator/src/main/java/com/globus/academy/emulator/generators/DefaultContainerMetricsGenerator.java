package com.globus.academy.emulator.generators;

import com.globus.academy.emulator.generators.entities.ContainerMetrics;
import com.globus.academy.emulator.generators.entities.Metric;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class DefaultContainerMetricsGenerator implements ContainerMetricsGenerator {

    private int remainingNumberGenerations;
    private Instant lastUnusedTime;
    private final long timeShiftInSeconds;
    private final List<MetricGenerator> metricGenerators;

    @Override
    public ContainerMetrics generate() {
        List<Metric> generatedMetrics = new LinkedList<>();
        for (MetricGenerator metricGenerator : metricGenerators) {
            Metric generatedMetric = metricGenerator.generate();
            generatedMetrics.add(generatedMetric);
        }
        ContainerMetrics generatedContainerMetrics = new ContainerMetrics(lastUnusedTime, generatedMetrics);

        increaseLastUnusedTime();
        --remainingNumberGenerations;

        return generatedContainerMetrics;
    }

    private void increaseLastUnusedTime() {
        lastUnusedTime = lastUnusedTime.plusSeconds(timeShiftInSeconds);
    }

    @Override
    public boolean canGenerate() {
        return remainingNumberGenerations > 0 && metricGenerators.stream().allMatch(EntityGenerator::canGenerate);
    }

}
