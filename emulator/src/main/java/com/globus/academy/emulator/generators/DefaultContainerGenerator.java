package com.globus.academy.emulator.generators;

import com.globus.academy.emulator.generators.entities.Container;
import com.globus.academy.emulator.generators.entities.ContainerMetrics;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class DefaultContainerGenerator implements ContainerGenerator {

    private final int containerId;
    private final List<ContainerMetricsGenerator> containerMetricsGenerators;

    @Override
    public Container generate() {
        List<ContainerMetrics> generatedContainerMetricsList = new LinkedList<>();
        for (ContainerMetricsGenerator containerMetricsGenerator : containerMetricsGenerators) {
            ContainerMetrics generatedContainerMetrics = containerMetricsGenerator.generate();
            generatedContainerMetricsList.add(generatedContainerMetrics);
        }
        return new Container(containerId, generatedContainerMetricsList);
    }

    @Override
    public boolean canGenerate() {
        return containerMetricsGenerators.stream().allMatch(ContainerMetricsGenerator::canGenerate);
    }

}
