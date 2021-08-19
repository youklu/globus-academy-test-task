package com.globus.academy.kafkadatabasetranslator.translators.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class ContainerMetrics {

    private final long timestamp;
    private final List<Metric> metrics;

}
