package com.globus.academy.emulator.generators.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class ContainerMetrics {

    private final Instant time;
    private final List<Metric> metrics;

}
