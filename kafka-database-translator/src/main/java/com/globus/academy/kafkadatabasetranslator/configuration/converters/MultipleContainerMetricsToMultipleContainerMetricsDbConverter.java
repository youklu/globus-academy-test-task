package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.kafkadatabasetranslator.databases.entities.ContainerDb;
import com.globus.academy.kafkadatabasetranslator.databases.entities.MultipleContainerMetricsDb;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Container;
import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class MultipleContainerMetricsToMultipleContainerMetricsDbConverter implements Converter<MultipleContainerMetrics, MultipleContainerMetricsDb> {

    private final Converter<Container, ContainerDb> containerConverter;

    @Override
    public MultipleContainerMetricsDb convert(MultipleContainerMetrics multipleContainerMetrics) {
        MultipleContainerMetricsDb convertedMultipleContainerMetrics = new MultipleContainerMetricsDb();
        List<ContainerDb> convertedContainers = convertContainers(multipleContainerMetrics.getContainers(), convertedMultipleContainerMetrics);
        convertedMultipleContainerMetrics.setContainers(convertedContainers);
        return convertedMultipleContainerMetrics;
    }

    private List<ContainerDb> convertContainers(List<Container> sourceContainers, MultipleContainerMetricsDb multipleContainerMetricsDb) {
        List<ContainerDb> convertedContainers = new LinkedList<>();
        for (Container  sourceContainer : sourceContainers) {
            ContainerDb convertedContainer = containerConverter.convert(sourceContainer);
            convertedContainer.setMultipleContainerMetrics(multipleContainerMetricsDb);
            convertedContainers.add(convertedContainer);
        }
        return convertedContainers;
    }

}
