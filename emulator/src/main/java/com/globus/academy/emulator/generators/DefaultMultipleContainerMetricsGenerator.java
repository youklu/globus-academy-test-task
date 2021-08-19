package com.globus.academy.emulator.generators;

import com.globus.academy.emulator.generators.entities.Container;
import com.globus.academy.emulator.generators.entities.MultipleContainerMetrics;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class DefaultMultipleContainerMetricsGenerator implements MultipleContainerMetricsGenerator {

    private final List<ContainerGenerator> containerGenerators;

    @Override
    public MultipleContainerMetrics generate() {
        List<Container> generatedContainers = new LinkedList<>();
        for (ContainerGenerator containerGenerator : containerGenerators) {
            Container generatedContainer = containerGenerator.generate();
            generatedContainers.add(generatedContainer);
        }
        return new MultipleContainerMetrics(generatedContainers);
    }

    @Override
    public boolean canGenerate() {
        return containerGenerators.stream().allMatch(ContainerGenerator::canGenerate);
    }

}
