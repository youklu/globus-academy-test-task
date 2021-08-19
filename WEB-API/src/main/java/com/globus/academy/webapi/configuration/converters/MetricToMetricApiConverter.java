package com.globus.academy.webapi.configuration.converters;

import com.globus.academy.webapi.etities.Metric;
import com.globus.academy.webapi.model.MetricApi;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.ZoneOffset;

public class MetricToMetricApiConverter implements Converter<Metric, MetricApi> {

    @Override
    public MetricApi convert(Metric metric) {
        final int containerId = metric.getContainerId();
        final String name = metric.getMetricName();
        final int value = metric.getMetricValue();
        final Instant time = Instant.ofEpochSecond(metric.getTimestamp());

        MetricApi convertedMetric = new MetricApi();
        convertedMetric.
                containerId(containerId).
                metricName(name).
                metricValue(value).
                time(time.atOffset(ZoneOffset.UTC));

        return convertedMetric;
    }

}
