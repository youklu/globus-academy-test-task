package com.globus.academy.kafkadatabasetranslator.translators;

import com.globus.academy.kafkadatabasetranslator.databases.Database;
import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultTranslator implements Translator {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private Database database;

    @Override
    public void translate(MultipleContainerMetrics multipleContainerMetrics) {
        log.info("Translate container metrics. Metrics: " + multipleContainerMetrics);
        database.save(multipleContainerMetrics);
    }

}
