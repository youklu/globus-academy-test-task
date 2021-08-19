package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.kafkadatabasetranslator.databases.entities.ContainerDb;
import com.globus.academy.kafkadatabasetranslator.databases.entities.ContainerMetricsDb;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Container;
import com.globus.academy.kafkadatabasetranslator.translators.entities.ContainerMetrics;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerToContainerDbConverter implements Converter<Container, ContainerDb> {

    private final Converter<ContainerMetrics, ContainerMetricsDb> containerMetricsConverter;

    @Override
    public ContainerDb convert(Container container) {
        ContainerDb convertedContainer = new ContainerDb();
        List<ContainerMetricsDb> convertedContainerMetricsList = convertContainerMetricsList(container.getContainerMetricsList(), convertedContainer);
        convertedContainer.setContainerId(container.getContainerId());
        convertedContainer.setContainerMetricsList(convertedContainerMetricsList);
        return convertedContainer;
    }

    private List<ContainerMetricsDb> convertContainerMetricsList(List<ContainerMetrics> sourceContainerMetricsList, ContainerDb containerDb) {
        List<ContainerMetricsDb> convertedContainerMetricsList = new LinkedList<>();
        for (ContainerMetrics sourceContainerMetrics : sourceContainerMetricsList) {
            ContainerMetricsDb convertedContainerMetrics = containerMetricsConverter.convert(sourceContainerMetrics);
            convertedContainerMetrics.setContainer(containerDb);
            convertedContainerMetricsList.add(convertedContainerMetrics);
        }
        return convertedContainerMetricsList;
    }

}
