package com.globus.academy.kafkadatabasetranslator.configuration.converters;

import com.globus.academy.kafkadatabasetranslator.databases.entities.MetricDb;
import com.globus.academy.kafkadatabasetranslator.translators.entities.Metric;
import org.springframework.core.convert.converter.Converter;

public class MetricToMetricDbConverter implements Converter<Metric, MetricDb> {

    @Override
    public MetricDb convert(Metric metric) {
        MetricDb convertedMetric = new MetricDb();
        convertedMetric.setMetricName(metric.getMetricName());
        convertedMetric.setMetricValue(metric.getMetricValue());
        return convertedMetric;
    }

}
