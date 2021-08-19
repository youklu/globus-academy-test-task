package com.globus.academy.emulator.configuration.converters;

import com.globus.academy.emulator.generators.entities.Container;
import com.globus.academy.emulator.generators.entities.ContainerMetrics;
import com.globus.academy.sensorrequestmanager.model.ContainerApi;
import com.globus.academy.sensorrequestmanager.model.ContainerMetricsApi;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerToContainerApiConverter implements Converter<Container, ContainerApi> {

    private final Converter<ContainerMetrics, ContainerMetricsApi> containerMetricsConverter;

    @Override
    public ContainerApi convert(Container container) {
        List<ContainerMetricsApi> convertedContainerMetricsList = convertContainerMetricsList(container.getContainerMetricsList());
        ContainerApi convertedContainer = new ContainerApi();
        convertedContainer.containerId(container.getContainerId()).containerMetricsList(convertedContainerMetricsList);
        return convertedContainer;
    }

    private List<ContainerMetricsApi> convertContainerMetricsList(List<ContainerMetrics> sourceContainerMetricsList) {
        List<ContainerMetricsApi> convertedContainerMetrics = new LinkedList<>();
        for (ContainerMetrics sourceContainerMetrics : sourceContainerMetricsList) {
            ContainerMetricsApi convertedContainerMetric = containerMetricsConverter.convert(sourceContainerMetrics);
            convertedContainerMetrics.add(convertedContainerMetric);
        }
        return convertedContainerMetrics;
    }

}
