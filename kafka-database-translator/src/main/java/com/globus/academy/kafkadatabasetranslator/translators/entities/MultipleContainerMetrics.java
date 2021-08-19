package com.globus.academy.kafkadatabasetranslator.translators.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class MultipleContainerMetrics {

    private final List<Container> containers;

}
