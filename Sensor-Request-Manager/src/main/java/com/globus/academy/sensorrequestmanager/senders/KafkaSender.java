package com.globus.academy.sensorrequestmanager.senders;


import com.globus.academy.avro.MultipleContainerMetricsKafka;
import com.globus.academy.sensorrequestmanager.configuration.KafkaConfiguration;
import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "application", name = "metricReceiver", havingValue = "kafka")
public class KafkaSender implements Sender {

    @Autowired
    private KafkaConfiguration configuration;
    @Autowired
    private ConversionService conversionService;
    private KafkaProducer<String, MultipleContainerMetricsKafka> producer;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(configuration.getProducerProperties());
    }

    @Override
    public void send(MultipleContainerMetrics multipleContainerMetrics) {
        executor.execute(() -> {
            ProducerRecord<String, MultipleContainerMetricsKafka> record = createRecord(multipleContainerMetrics);
            producer.send(record, ((recordMetadata, exception) -> {
                if (exception == null) {
                    log.info("Record sent successfully. Record: " + record.value());
                } else {
                    log.error("Failed to sent record: " + record.value(), exception);
                }
            }));
        });
    }

    private ProducerRecord<String, MultipleContainerMetricsKafka> createRecord(MultipleContainerMetrics multipleContainerMetrics) {
        MultipleContainerMetricsKafka value = conversionService.convert(multipleContainerMetrics, MultipleContainerMetricsKafka.class);
        return new ProducerRecord<>(configuration.getContainerMetricTopic(), value);
    }

}
