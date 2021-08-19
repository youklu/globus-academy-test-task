package com.globus.academy.sensorrequestmanager.configuration.converters;

import com.globus.academy.avro.MetricKafka;
import com.globus.academy.sensorrequestmanager.transmitters.entities.Metric;
import org.springframework.core.convert.converter.Converter;

public class MetricToMetricKafkaConverter implements Converter<Metric, MetricKafka> {

    @Override
    public MetricKafka convert(Metric metric) {
        final String metricName = metric.getMetricName();
        final int metricValue = metric.getMetricValue();

        MetricKafka.Builder metricKafkaBuilder = MetricKafka.newBuilder();
        metricKafkaBuilder.
                setMetricName(metricName).
                setMetricValue(metricValue);

        return metricKafkaBuilder.build();
    }

}
