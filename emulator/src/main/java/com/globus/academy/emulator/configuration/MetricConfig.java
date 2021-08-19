package com.globus.academy.emulator.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricConfig {
    private String metricName;
    private int minValue;
    private int maxValue;
}
