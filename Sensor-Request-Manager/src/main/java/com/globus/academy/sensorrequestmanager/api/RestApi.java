package com.globus.academy.sensorrequestmanager.api;

import com.globus.academy.sensorrequestmanager.model.MultipleContainerMetricsApi;
import com.globus.academy.sensorrequestmanager.transmitters.MetricTransmitter;
import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class RestApi implements DefaultApi {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MetricTransmitter metricTransmitter;

    @Override
    public ResponseEntity<Void> sendMetrics(MultipleContainerMetricsApi multipleContainerMetricsApi) {
        log.info("Containers metrics received. Metrics: " + multipleContainerMetricsApi);
        MultipleContainerMetrics multipleContainerMetrics =
                conversionService.convert(multipleContainerMetricsApi, MultipleContainerMetrics.class);
        metricTransmitter.transmit(multipleContainerMetrics);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
