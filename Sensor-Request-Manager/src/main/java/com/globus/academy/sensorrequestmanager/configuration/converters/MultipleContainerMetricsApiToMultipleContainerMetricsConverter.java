package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.sensorrequestmanager.model.ContainerApi;
import com.globus.academy.sensorrequestmanager.model.MultipleContainerMetricsApi;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Container;
import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class MultipleContainerMetricsApiToMultipleContainerMetricsConverter implements Converter<MultipleContainerMetricsApi, MultipleContainerMetrics> {

    private final Converter<ContainerApi, Container> containerConverter;

    @Override
    public MultipleContainerMetrics convert(MultipleContainerMetricsApi multipleContainerMetricsApi) {
        List<Container> convertedContainers = convertContainers(multipleContainerMetricsApi.getContainers());
        return new MultipleContainerMetrics(convertedContainers);
    }

    private List<Container> convertContainers(List<ContainerApi> sourceContainers) {
        List<Container> convertedContainers = new LinkedList<>();
        for (ContainerApi sourceContainer : sourceContainers) {
            Container convertedContainer = containerConverter.convert(sourceContainer);
            convertedContainers.add(convertedContainer);
        }
        return convertedContainers;
    }
}
