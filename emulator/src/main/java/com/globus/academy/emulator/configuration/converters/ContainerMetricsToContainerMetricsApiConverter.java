package com.globus.academy.emulator.configuration.converters;

import com.globus.academy.emulator.generators.entities.ContainerMetrics;
import com.globus.academy.emulator.generators.entities.Metric;
import com.globus.academy.sensorrequestmanager.model.ContainerMetricsApi;
import com.globus.academy.sensorrequestmanager.model.MetricApi;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerMetricsToContainerMetricsApiConverter implements Converter<ContainerMetrics, ContainerMetricsApi> {

    private final Converter<Metric, MetricApi> metricConverter;

    @Override
    public ContainerMetricsApi convert(ContainerMetrics containerMetrics) {
        List<MetricApi> convertedMetrics = convertMetrics(containerMetrics.getMetrics());
        ContainerMetricsApi convertedContainerMetricsApi = new ContainerMetricsApi();
        convertedContainerMetricsApi.
                time(containerMetrics.getTime().atOffset(ZoneOffset.UTC)).
                metrics(convertedMetrics);
        return convertedContainerMetricsApi;
    }

    private List<MetricApi> convertMetrics(List<Metric> sourceMetrics) {
        List<MetricApi> convertedMetrics = new LinkedList<>();
        for (Metric sourceMetric : sourceMetrics) {
            MetricApi convertedMetric = metricConverter.convert(sourceMetric);
            convertedMetrics.add(convertedMetric);
        }
        return convertedMetrics;
    }

}
