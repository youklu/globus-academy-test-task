package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.sensorrequestmanager.model.ContainerMetricsApi;
import com.globus.academy.sensorrequestmanager.model.MetricApi;
import com.globus.academy.sensorrequestmanager.transmitters.entities.ContainerMetrics;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Metric;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ContainerMetricsApiToContainerMetricsConverter implements Converter<ContainerMetricsApi, ContainerMetrics> {

    private final Converter<MetricApi, Metric> metricConverter;

    @Override
    public ContainerMetrics convert(ContainerMetricsApi containerMetricsApi) {
        final long timestamp = containerMetricsApi.getTime().toEpochSecond();
        List<Metric> convertedMetrics = convertMetrics(containerMetricsApi.getMetrics());
        return new ContainerMetrics(timestamp, convertedMetrics);
    }

    private List<Metric> convertMetrics(List<MetricApi> sourceMetrics) {
        List<Metric> convertedMetrics = new LinkedList<>();
        for (MetricApi sourceMetric : sourceMetrics) {
            Metric convertedContainer = metricConverter.convert(sourceMetric);
            convertedMetrics.add(convertedContainer);
        }
        return convertedMetrics;
    }

}
