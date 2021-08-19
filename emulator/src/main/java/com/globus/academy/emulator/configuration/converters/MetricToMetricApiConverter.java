package com.globus.academy.emulator.configuration.converters;

import com.globus.academy.emulator.generators.entities.Metric;
import com.globus.academy.sensorrequestmanager.model.MetricApi;
import org.springframework.core.convert.converter.Converter;

public class MetricToMetricApiConverter implements Converter<Metric, MetricApi> {

    @Override
    public MetricApi convert(Metric metric) {
        MetricApi convertedMetric = new MetricApi();
        convertedMetric.
                metricName(metric.getMetricName()).
                metricValue(metric.getMetricValue());
        return convertedMetric;
    }

}
