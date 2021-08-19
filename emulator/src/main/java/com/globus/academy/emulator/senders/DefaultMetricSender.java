package com.globus.academy.emulator.senders;

import com.globus.academy.emulator.generators.entities.MultipleContainerMetrics;
import com.globus.academy.sensorrequestmanager.api.DefaultApi;
import com.globus.academy.sensorrequestmanager.client.ApiClient;
import com.globus.academy.sensorrequestmanager.model.MultipleContainerMetricsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class DefaultMetricSender implements MetricSender {

    @Value("${sensorRequestManager.host}")
    private String sensorRequestManagerHost;
    @Value("${sensorRequestManager.port}")
    private int sensorRequestManagerPort;
    @Autowired
    private ConversionService conversionService;

    private DefaultApi sensorRequestManager;

    @PostConstruct
    private void init() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://" + sensorRequestManagerHost + ":" + sensorRequestManagerPort);
        sensorRequestManager = new DefaultApi(apiClient);
    }

    @Override
    public void send(MultipleContainerMetrics multipleContainerMetrics) {
        log.info("Sending MultipleContainerMetrics to SensorRequestManager. MultipleContainerMetrics: " + multipleContainerMetrics);
        MultipleContainerMetricsApi convertedMultipleContainerMetrics = conversionService.convert(multipleContainerMetrics, MultipleContainerMetricsApi.class);
        sensorRequestManager.sendMetrics(convertedMultipleContainerMetrics);
    }

}
