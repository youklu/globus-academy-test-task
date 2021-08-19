package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.kafkadatabasetranslator.databases.entities.ContainerMetricsDb;
import com.globus.academy.kafkadatabasetranslator.databases.entities.MetricDb;
import com.globus.academy.kafkadatabasetranslator.translators.entities.ContainerMetrics;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Metric;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerMetricsToContainerMetricsDbConverter implements Converter<ContainerMetrics, ContainerMetricsDb> {

    private final Converter<Metric, MetricDb> metricConverter;

    @Override
    public ContainerMetricsDb convert(ContainerMetrics containerMetrics) {
        ContainerMetricsDb convertedContainerMetrics = new ContainerMetricsDb();
        List<MetricDb> convertedMetrics = convertMetrics(containerMetrics.getMetrics(), convertedContainerMetrics);
        convertedContainerMetrics.setTimestamp(Timestamp.from(Instant.ofEpochSecond(containerMetrics.getTimestamp())));
        convertedContainerMetrics.setMetrics(convertedMetrics);

        return convertedContainerMetrics;
    }

    private List<MetricDb> convertMetrics(List<Metric> sourceMetrics, ContainerMetricsDb containerMetricsDb) {
        List<MetricDb> convertedMetrics = new LinkedList<>();
        for (Metric sourceMetric : sourceMetrics) {
            MetricDb convertedMetric = metricConverter.convert(sourceMetric);
            convertedMetric.setContainerMetrics(containerMetricsDb);
            convertedMetrics.add(convertedMetric);
        }
        return convertedMetrics;
    }

}
