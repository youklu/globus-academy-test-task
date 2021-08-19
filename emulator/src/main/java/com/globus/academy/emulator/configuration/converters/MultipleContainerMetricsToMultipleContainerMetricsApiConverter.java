package com.globus.academy.emulator.configuration.converters;

import com.globus.academy.emulator.generators.entities.Container;
import com.globus.academy.emulator.generators.entities.MultipleContainerMetrics;
import com.globus.academy.sensorrequestmanager.model.ContainerApi;
import com.globus.academy.sensorrequestmanager.model.MultipleContainerMetricsApi;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class MultipleContainerMetricsToMultipleContainerMetricsApiConverter implements Converter<MultipleContainerMetrics, MultipleContainerMetricsApi> {

    private final Converter<Container, ContainerApi> containerConverter;

    @Override
    public MultipleContainerMetricsApi convert(MultipleContainerMetrics multipleContainerMetrics) {
        List<ContainerApi> convertedContainers = convertContainers(multipleContainerMetrics.getContainers());
        MultipleContainerMetricsApi convertedMultipleContainerMetrics = new MultipleContainerMetricsApi();
        convertedMultipleContainerMetrics.containers(convertedContainers);
        return convertedMultipleContainerMetrics;
    }

    private List<ContainerApi> convertContainers(List<Container> sourceContainers) {
        List<ContainerApi> convertedContainers = new LinkedList<>();
        for (Container sourceContainer : sourceContainers) {
            ContainerApi convertedContainerMetric = containerConverter.convert(sourceContainer);
            convertedContainers.add(convertedContainerMetric);
        }
        return convertedContainers;
    }

}
