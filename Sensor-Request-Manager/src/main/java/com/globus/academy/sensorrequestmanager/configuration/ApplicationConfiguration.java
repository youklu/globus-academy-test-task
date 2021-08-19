package com.globus.academy.sensorrequestmanager.configuration;

import com.globus.academy.sensorrequestmanager.configuration.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();

        MultipleContainerMetricsApiToMultipleContainerMetricsConverter multipleContainerMetricsApiToMultipleContainerMetricsConverter =
                createMultipleContainerMetricsApiToMultipleContainerMetricsConverter();
        conversionService.addConverter(multipleContainerMetricsApiToMultipleContainerMetricsConverter);

        MultipleContainerMetricsToMultipleContainerMetricsKafkaConverter multipleContainerMetricsToMultipleContainerMetricsKafkaConverter =
                createMultipleContainerMetricsToMultipleContainerMetricsKafkaConverter();
        conversionService.addConverter(multipleContainerMetricsToMultipleContainerMetricsKafkaConverter);

        return conversionService;
    }

    private MultipleContainerMetricsApiToMultipleContainerMetricsConverter createMultipleContainerMetricsApiToMultipleContainerMetricsConverter() {
        MetricApiToMetricConverter metricConverter = new MetricApiToMetricConverter();
        ContainerMetricsApiToContainerMetricsConverter containerMetricsConverter = new ContainerMetricsApiToContainerMetricsConverter(metricConverter);
        ContainerApiToContainerConverter containerConverter = new ContainerApiToContainerConverter(containerMetricsConverter);
        return new MultipleContainerMetricsApiToMultipleContainerMetricsConverter(containerConverter);
    }

    private MultipleContainerMetricsToMultipleContainerMetricsKafkaConverter createMultipleContainerMetricsToMultipleContainerMetricsKafkaConverter() {
        MetricToMetricKafkaConverter metricConverter = new MetricToMetricKafkaConverter();
        ContainerMetricsToContainerMetricsKafkaConverter containerMetricsConverter = new ContainerMetricsToContainerMetricsKafkaConverter(metricConverter);
        ContainerToContainerKafkaConverter containerConverter = new ContainerToContainerKafkaConverter(containerMetricsConverter);
        return new MultipleContainerMetricsToMultipleContainerMetricsKafkaConverter(containerConverter);
    }

}
