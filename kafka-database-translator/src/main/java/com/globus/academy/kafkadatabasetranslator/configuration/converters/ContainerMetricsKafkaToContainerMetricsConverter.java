package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.avro.ContainerMetricsKafka;
import com.globus.academy.avro.MetricKafka;
import com.globus.academy.kafkadatabasetranslator.translators.entities.ContainerMetrics;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Metric;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerMetricsKafkaToContainerMetricsConverter implements Converter<ContainerMetricsKafka, ContainerMetrics> {

    private final Converter<MetricKafka, Metric> metricConverter;

    @Override
    public ContainerMetrics convert(ContainerMetricsKafka containerMetricsKafka) {
        List<Metric> convertedMetrics = convertMetrics(containerMetricsKafka.getMetrics());
        return new ContainerMetrics(containerMetricsKafka.getTimestamp(), convertedMetrics);
    }

    private List<Metric> convertMetrics(List<MetricKafka> sourceMetrics) {
        List<Metric> convertedMetrics = new LinkedList<>();
        for (MetricKafka sourceMetric : sourceMetrics) {
            Metric convertedMetric = metricConverter.convert(sourceMetric);
            convertedMetrics.add(convertedMetric);
        }
        return convertedMetrics;
    }

}
