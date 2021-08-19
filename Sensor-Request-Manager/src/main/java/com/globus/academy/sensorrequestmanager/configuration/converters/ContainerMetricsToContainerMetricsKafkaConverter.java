package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.avro.ContainerMetricsKafka;
import com.globus.academy.avro.MetricKafka;
import com.globus.academy.sensorrequestmanager.transmitters.entities.ContainerMetrics;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Metric;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerMetricsToContainerMetricsKafkaConverter implements Converter<ContainerMetrics, ContainerMetricsKafka> {

    private final Converter<Metric, MetricKafka> metricConverter;

    @Override
    public ContainerMetricsKafka convert(ContainerMetrics containerMetrics) {
        final long timestamp = containerMetrics.getTimestamp();
        List<MetricKafka> convertedMetrics = convertMetrics(containerMetrics.getMetrics());
        return new ContainerMetricsKafka(timestamp, convertedMetrics);
    }

    private List<MetricKafka> convertMetrics(List<Metric> sourceMetrics) {
        List<MetricKafka> convertedMetrics = new LinkedList<>();
        for (Metric sourceMetric : sourceMetrics) {
            MetricKafka convertedContainer = metricConverter.convert(sourceMetric);
            convertedMetrics.add(convertedContainer);
        }
        return convertedMetrics;
    }

}
