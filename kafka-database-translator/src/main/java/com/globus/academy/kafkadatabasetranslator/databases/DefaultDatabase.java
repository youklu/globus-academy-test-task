package com.globus.academy.kafkadatabasetranslator.databases;

import com.globus.academy.kafkadatabasetranslator.databases.entities.MultipleContainerMetricsDb;
import com.globus.academy.kafkadatabasetranslator.databases.repositories.MetricRepository;
import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultDatabase implements Database {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MetricRepository metricRepository;

    @Override
    // Suppress "Argument 'containerMetricDB' might be null".
    // ConversionService can't return null
    @SuppressWarnings("all")
    public void save(MultipleContainerMetrics multipleContainerMetrics) {
        log.info("Save into database. " + multipleContainerMetrics);
        MultipleContainerMetricsDb multipleContainerMetricsDb = conversionService.convert(multipleContainerMetrics, MultipleContainerMetricsDb.class);
        metricRepository.save(multipleContainerMetricsDb);
    }

}
