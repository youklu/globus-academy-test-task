package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.avro.ContainerKafka;
import com.globus.academy.avro.MultipleContainerMetricsKafka;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Container;
import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class MultipleContainerMetricsKafkaToMultipleContainerMetricsConverter implements Converter<MultipleContainerMetricsKafka, MultipleContainerMetrics> {

    private final Converter<ContainerKafka, Container> containerConverter;

    @Override
    public MultipleContainerMetrics convert(MultipleContainerMetricsKafka multipleContainerMetricsKafka) {
        List<Container> convertedContainers = convertContainers(multipleContainerMetricsKafka.getContainers());
        return new MultipleContainerMetrics(convertedContainers);
    }

    private List<Container> convertContainers(List<ContainerKafka> sourceContainers) {
        List<Container> convertedContainers = new LinkedList<>();
        for (ContainerKafka  sourceContainer : sourceContainers) {
            Container convertedContainer = containerConverter.convert(sourceContainer);
            convertedContainers.add(convertedContainer);
        }
        return convertedContainers;
    }

}
