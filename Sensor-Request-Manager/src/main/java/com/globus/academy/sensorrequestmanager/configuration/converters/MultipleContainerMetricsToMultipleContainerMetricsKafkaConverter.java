package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.avro.ContainerKafka;
import com.globus.academy.avro.MultipleContainerMetricsKafka;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Container;
import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class MultipleContainerMetricsToMultipleContainerMetricsKafkaConverter implements Converter<MultipleContainerMetrics, MultipleContainerMetricsKafka> {

    private final Converter<Container, ContainerKafka> containerConverter;

    @Override
    public MultipleContainerMetricsKafka convert(MultipleContainerMetrics multipleContainerMetrics) {
        List<ContainerKafka> convertedContainers = convertContainers(multipleContainerMetrics.getContainers());
        return new MultipleContainerMetricsKafka(convertedContainers);
    }

    private List<ContainerKafka> convertContainers(List<Container> sourceContainers) {
        List<ContainerKafka> convertedContainers = new LinkedList<>();
        for (Container sourceContainer : sourceContainers) {
            ContainerKafka convertedContainer = containerConverter.convert(sourceContainer);
            convertedContainers.add(convertedContainer);
        }
        return convertedContainers;
    }

}
