package com.globus.academy.emulator.generators.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Metric {

    private final String metricName;
    private final int metricValue;

}
