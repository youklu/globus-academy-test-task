package com.globus.academy.webapi.configuration;

import com.globus.academy.webapi.configuration.converters.MetricDbToMetricConverter;
import com.globus.academy.webapi.configuration.converters.MetricToMetricApiConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new MetricDbToMetricConverter());
        conversionService.addConverter(new MetricToMetricApiConverter());
        return conversionService;
    }

}
