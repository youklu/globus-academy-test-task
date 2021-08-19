package com.globus.academy.sensorrequestmanager.transmitters;

import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;

public interface MetricTransmitter {

    void transmit(MultipleContainerMetrics multipleContainerMetrics);

}
