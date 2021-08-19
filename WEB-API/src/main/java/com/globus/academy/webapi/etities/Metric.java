package com.globus.academy.webapi.etities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Metric {

    private final int containerId;
    private final long timestamp;
    private final String metricName;
    private final int metricValue;

}
