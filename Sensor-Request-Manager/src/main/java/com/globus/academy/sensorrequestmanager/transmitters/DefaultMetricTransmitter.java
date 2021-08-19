package com.globus.academy.sensorrequestmanager.transmitters;

import com.globus.academy.sensorrequestmanager.senders.Sender;
import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultMetricTransmitter implements MetricTransmitter {

    @Autowired
    private Sender sender;

    public void transmit(MultipleContainerMetrics multipleContainerMetrics) {
        log.info("Transmit container metrics. Metrics: " + multipleContainerMetrics);
        sender.send(multipleContainerMetrics);
    }

}
