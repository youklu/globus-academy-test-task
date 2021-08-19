package com.globus.academy.kafkadatabasetranslator.translators.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@AllArgsConstructor
public class Metric {

    private final String metricName;
    private final int metricValue;

}
