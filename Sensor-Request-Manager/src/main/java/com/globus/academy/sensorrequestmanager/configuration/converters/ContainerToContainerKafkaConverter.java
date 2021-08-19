package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.avro.ContainerKafka;
import com.globus.academy.avro.ContainerMetricsKafka;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Container;
import com.globus.academy.sensorrequestmanager.transmitters.entities.ContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerToContainerKafkaConverter implements Converter<Container, ContainerKafka> {

    private final Converter<ContainerMetrics, ContainerMetricsKafka> containerMetricsConverter;

    @Override
    public ContainerKafka convert(Container container) {
        List<ContainerMetricsKafka> convertedMetricsList = convertContainerMetrics(container.getContainerMetricsList());
        return new ContainerKafka(container.getContainerId(), convertedMetricsList);
    }

    private List<ContainerMetricsKafka> convertContainerMetrics(List<ContainerMetrics> sourceContainerMetricsList) {
        List<ContainerMetricsKafka> convertedMetrics = new LinkedList<>();
        for (ContainerMetrics sourceContainerMetrics : sourceContainerMetricsList) {
            ContainerMetricsKafka convertedContainerMetrics = containerMetricsConverter.convert(sourceContainerMetrics);
            convertedMetrics.add(convertedContainerMetrics);
        }
        return convertedMetrics;
    }

}
