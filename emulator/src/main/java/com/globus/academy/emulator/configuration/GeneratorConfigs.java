package com.globus.academy.emulator.configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class GeneratorConfigs {

    private int containerCount;
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant startTime;
    private int timeShiftInSeconds;
    private int generatingCount;
    private List<MetricConfig> metricConfigs;

}
