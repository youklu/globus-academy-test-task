package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.avro.ContainerKafka;
import com.globus.academy.avro.ContainerMetricsKafka;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Container;
import com.globus.academy.kafkadatabasetranslator.translators.entities.ContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerKafkaToContainerConverter implements Converter<ContainerKafka, Container> {

    private final Converter<ContainerMetricsKafka, ContainerMetrics> containerMetricsConverter;

    @Override
    public Container convert(ContainerKafka containerKafka) {
        List<ContainerMetrics> convertedContainerMetricsList = convertContainerMetricsList(containerKafka.getContainerMetricsList());
        return new Container(containerKafka.getContainerId(), convertedContainerMetricsList);
    }

    private List<ContainerMetrics> convertContainerMetricsList(List<ContainerMetricsKafka> sourceContainerMetricsList) {
        List<ContainerMetrics> convertedContainerMetricsList = new LinkedList<>();
        for (ContainerMetricsKafka sourceContainerMetrics : sourceContainerMetricsList) {
            ContainerMetrics convertedContainerMetrics = containerMetricsConverter.convert(sourceContainerMetrics);
            convertedContainerMetricsList.add(convertedContainerMetrics);
        }
        return convertedContainerMetricsList;
    }

}
