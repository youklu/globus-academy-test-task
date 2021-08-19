package com.globus.academy.emulator.senders;

import com.globus.academy.emulator.generators.entities.MultipleContainerMetrics;

public interface MetricSender {

    void send(MultipleContainerMetrics multipleContainerMetrics);

}
