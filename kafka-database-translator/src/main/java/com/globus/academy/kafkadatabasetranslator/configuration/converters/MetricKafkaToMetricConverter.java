package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.avro.MetricKafka;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Metric;
import org.springframework.core.convert.converter.Converter;

public class MetricKafkaToMetricConverter implements Converter<MetricKafka, Metric> {

    @Override
    public Metric convert(MetricKafka metricKafka) {
        return new Metric(metricKafka.getMetricName().toString(), metricKafka.getMetricValue());
    }

}
