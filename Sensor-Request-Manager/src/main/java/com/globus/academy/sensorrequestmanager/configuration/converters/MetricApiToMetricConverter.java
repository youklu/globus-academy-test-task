package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.sensorrequestmanager.model.MetricApi;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Metric;
import org.springframework.core.convert.converter.Converter;

public class MetricApiToMetricConverter implements Converter<MetricApi, Metric> {

    @Override
    public Metric convert(MetricApi metricApi) {
        final String metricName = metricApi.getMetricName();
        final int metricValue = metricApi.getMetricValue();

        Metric.MetricBuilder metricBuilder = Metric.builder();
        metricBuilder.
                metricName(metricName).
                metricValue(metricValue);

        return metricBuilder.build();
    }

}
