package com.globus.academy.sensorrequestmanager.transmitters.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Metric {

    private final String metricName;
    private final int metricValue;

}
