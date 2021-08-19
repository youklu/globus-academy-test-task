package com.globus.academy.emulator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globus.academy.emulator.configuration.converters.ContainerMetricsToContainerMetricsApiConverter;
import com.globus.academy.emulator.configuration.converters.ContainerToContainerApiConverter;
import com.globus.academy.emulator.configuration.converters.MetricToMetricApiConverter;
import com.globus.academy.emulator.configuration.converters.MultipleContainerMetricsToMultipleContainerMetricsApiConverter;
import com.globus.academy.emulator.generators.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Value("${generatorConfigsFilePath}")
    private String generatorConfigsFilePath;

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();

        MultipleContainerMetricsToMultipleContainerMetricsApiConverter multipleContainerMetricsToMultipleContainerMetricsApiConverter = createMultipleContainerMetricsToMultipleContainerMetricsApiConverter();
        conversionService.addConverter(multipleContainerMetricsToMultipleContainerMetricsApiConverter);

        return conversionService;
    }

    private MultipleContainerMetricsToMultipleContainerMetricsApiConverter createMultipleContainerMetricsToMultipleContainerMetricsApiConverter() {
        MetricToMetricApiConverter metricConverter = new MetricToMetricApiConverter();
        ContainerMetricsToContainerMetricsApiConverter containerMetricsConverter = new ContainerMetricsToContainerMetricsApiConverter(metricConverter);
        ContainerToContainerApiConverter containerConverter = new ContainerToContainerApiConverter(containerMetricsConverter);
        return new MultipleContainerMetricsToMultipleContainerMetricsApiConverter(containerConverter);
    }

    @Bean
    public MultipleContainerMetricsGenerator createMultipleContainerMetricsGenerator() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        GeneratorConfigs generatorConfigs = mapper.readValue(Paths.get(generatorConfigsFilePath).toFile(), GeneratorConfigs.class);

        List<ContainerGenerator> containerGenerators = createContainerGenerators(generatorConfigs);
        return new DefaultMultipleContainerMetricsGenerator(containerGenerators);
    }

    private List<ContainerGenerator> createContainerGenerators(GeneratorConfigs generatorConfigs) {
        List<ContainerGenerator> containerGenerators = new LinkedList<>();
        for (int containerNumber = 0; containerNumber < generatorConfigs.getContainerCount(); ++containerNumber) {
            List<ContainerMetricsGenerator> containerMetricsGenerators = createMultipleContainerMetricsGenerators(generatorConfigs);
            ContainerGenerator containerGenerator = new DefaultContainerGenerator(containerNumber, containerMetricsGenerators);
            containerGenerators.add(containerGenerator);
        }
        return containerGenerators;
    }

    private List<ContainerMetricsGenerator> createMultipleContainerMetricsGenerators(GeneratorConfigs generatorConfigs) {
        List<MetricGenerator> metricGenerators = createMetricGenerator(generatorConfigs.getMetricConfigs());
        ContainerMetricsGenerator multipleContainerMetricsGenerator = new DefaultContainerMetricsGenerator(
                generatorConfigs.getGeneratingCount(),
                generatorConfigs.getStartTime(),
                generatorConfigs.getTimeShiftInSeconds(),
                metricGenerators);
        return Collections.singletonList(multipleContainerMetricsGenerator);
    }

    private List<MetricGenerator> createMetricGenerator(List<MetricConfig> metricConfigs) {
        List<MetricGenerator> metricGenerators = new LinkedList<>();
        for (MetricConfig metricConfig : metricConfigs) {
            MetricGenerator metricGenerator = new DefaultMetricGenerator(metricConfig.getMetricName(), metricConfig.getMinValue(), metricConfig.getMaxValue());
            metricGenerators.add(metricGenerator);
        }
        return metricGenerators;
    }

}
