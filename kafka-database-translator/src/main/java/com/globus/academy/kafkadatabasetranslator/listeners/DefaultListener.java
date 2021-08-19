package com.globus.academy.kafkadatabasetranslator.listeners;

import com.globus.academy.avro.MultipleContainerMetricsKafka;
import com.globus.academy.kafkadatabasetranslator.translators.Translator;
import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultListener {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private Translator translator;

    @KafkaListener(topics = "${kafka.containerMetricTopic}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "kafka-database-translator-consumer-group")
    private void onMessage(ConsumerRecord<String, GenericRecord> record) {
        String key = record.key();
        GenericRecord value = record.value();
        log.info("Record was received. Key: " + key + " Value: " + value);
        if (MultipleContainerMetricsKafka.class.equals(value.getClass())) {
            MultipleContainerMetricsKafka multipleContainerMetricsKafka = (MultipleContainerMetricsKafka) value;
            handleContainerMetricKafkaMessage(multipleContainerMetricsKafka);
        } else {
            log.error("Can't handle record. Key: " + key + " Value: " + value);
        }
    }

    private void handleContainerMetricKafkaMessage(MultipleContainerMetricsKafka multipleContainerMetricsKafka) {
        MultipleContainerMetrics multipleContainerMetrics = conversionService.convert(multipleContainerMetricsKafka, MultipleContainerMetrics.class);
        translator.translate(multipleContainerMetrics);
    }

}
