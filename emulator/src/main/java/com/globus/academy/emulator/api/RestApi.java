package com.globus.academy.emulator.api;

import com.globus.academy.emulator.generators.MultipleContainerMetricsGenerator;
import com.globus.academy.emulator.generators.entities.MultipleContainerMetrics;
import com.globus.academy.emulator.senders.MetricSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Controller
@Slf4j
public class RestApi implements DefaultApi {

    @Autowired
    private MultipleContainerMetricsGenerator multipleContainerMetricsGenerator;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MetricSender metricSender;
    private State state = State.NOT_STARTED;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public ResponseEntity<Void> startGenerator() {
        if (state.equals(State.NOT_STARTED)) {
            executor.execute(() -> {
                state = State.STARTED;
                log.info("Generator started");
                while (multipleContainerMetricsGenerator.canGenerate()) {
                    MultipleContainerMetrics generatedMultipleContainerMetrics = multipleContainerMetricsGenerator.generate();
                    log.info("MultipleContainerMetrics is generated. MultipleContainerMetrics: " + generatedMultipleContainerMetrics);
                    metricSender.send(generatedMultipleContainerMetrics);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e.getCause());
                        break;
                    }
                }
                state = State.FINISHED;
                log.info("Generator finished");
            });
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private enum State {
        NOT_STARTED,
        STARTED,
        FINISHED
    }

}
