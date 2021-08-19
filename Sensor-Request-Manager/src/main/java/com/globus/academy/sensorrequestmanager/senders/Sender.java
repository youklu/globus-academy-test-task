package com.globus.academy.sensorrequestmanager.senders;

import com.globus.academy.sensorrequestmanager.transmitters.entities.MultipleContainerMetrics;

public interface Sender {

    void send(MultipleContainerMetrics multipleContainerMetrics);

}
