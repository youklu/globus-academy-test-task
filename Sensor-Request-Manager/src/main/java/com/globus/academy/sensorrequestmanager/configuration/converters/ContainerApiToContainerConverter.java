package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.sensorrequestmanager.model.ContainerApi;
import com.globus.academy.sensorrequestmanager.model.ContainerMetricsApi;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Container;
import com.globus.academy.sensorrequestmanager.transmitters.entities.ContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerApiToContainerConverter implements Converter<ContainerApi, Container> {

    private final Converter<ContainerMetricsApi, ContainerMetrics> containerMetricsConverter;

    @Override
    public Container convert(ContainerApi containerApi) {
        List<ContainerMetrics> convertedMetricsList = convertContainerMetrics(containerApi.getContainerMetricsList());
        return new Container(containerApi.getContainerId(), convertedMetricsList);
    }

    private List<ContainerMetrics> convertContainerMetrics(List<ContainerMetricsApi> sourceContainerMetricsList) {
        List<ContainerMetrics> convertedMetrics = new LinkedList<>();
        for (ContainerMetricsApi sourceContainerMetrics : sourceContainerMetricsList) {
            ContainerMetrics convertedContainerMetrics = containerMetricsConverter.convert(sourceContainerMetrics);
            convertedMetrics.add(convertedContainerMetrics);
        }
        return convertedMetrics;
    }

}
