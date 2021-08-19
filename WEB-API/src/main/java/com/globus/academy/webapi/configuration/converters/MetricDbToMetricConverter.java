package com.globus.academy.webapi.configuration.converters;

import com.globus.academy.webapi.databases.entities.MetricDb;
import com.globus.academy.webapi.etities.Metric;
import org.springframework.core.convert.converter.Converter;

public class MetricDbToMetricConverter implements Converter<MetricDb, Metric> {

    @Override
    public Metric convert(MetricDb metricDb) {
        final int containerId = metricDb.getContainerMetrics().getContainer().getContainerId();
        final long timestamp = metricDb.getContainerMetrics().getTimestamp().toInstant().getEpochSecond();
        final String name = metricDb.getMetricName();
        final int value = metricDb.getMetricValue();

        Metric.MetricBuilder metricBuilder = Metric.builder();
        metricBuilder.
                containerId(containerId).
                timestamp(timestamp).
                metricName(name).
                metricValue(value);

        return metricBuilder.build();
    }

}
