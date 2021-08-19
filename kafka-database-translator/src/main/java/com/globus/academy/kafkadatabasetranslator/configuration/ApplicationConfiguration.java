package com.globus.academy.kafkadatabasetranslator.configuration;

import com.globus.academy.kafkadatabasetranslator.configuration.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();

        MultipleContainerMetricsKafkaToMultipleContainerMetricsConverter multipleContainerMetricsKafkaToMultipleContainerMetricsConverter = createMultipleContainerMetricsKafkaToMultipleContainerMetricsConverter();
        conversionService.addConverter(multipleContainerMetricsKafkaToMultipleContainerMetricsConverter);

        MultipleContainerMetricsToMultipleContainerMetricsDbConverter multipleContainerMetricsToMultipleContainerMetricsDbConverter = createMultipleContainerMetricsToMultipleContainerMetricsDbConverter();
        conversionService.addConverter(multipleContainerMetricsToMultipleContainerMetricsDbConverter);

        return conversionService;
    }

    private MultipleContainerMetricsKafkaToMultipleContainerMetricsConverter createMultipleContainerMetricsKafkaToMultipleContainerMetricsConverter() {
        MetricKafkaToMetricConverter metricConverter = new MetricKafkaToMetricConverter();
        ContainerMetricsKafkaToContainerMetricsConverter containerMetricsConverter = new ContainerMetricsKafkaToContainerMetricsConverter(metricConverter);
        ContainerKafkaToContainerConverter containerConverter = new ContainerKafkaToContainerConverter(containerMetricsConverter);
        return new MultipleContainerMetricsKafkaToMultipleContainerMetricsConverter(containerConverter);
    }

    private MultipleContainerMetricsToMultipleContainerMetricsDbConverter createMultipleContainerMetricsToMultipleContainerMetricsDbConverter() {
        MetricToMetricDbConverter metricConverter = new MetricToMetricDbConverter();
        ContainerMetricsToContainerMetricsDbConverter containerMetricsConverter = new ContainerMetricsToContainerMetricsDbConverter(metricConverter);
        ContainerToContainerDbConverter containerConverter = new ContainerToContainerDbConverter(containerMetricsConverter);
        return new MultipleContainerMetricsToMultipleContainerMetricsDbConverter(containerConverter);
    }

}
