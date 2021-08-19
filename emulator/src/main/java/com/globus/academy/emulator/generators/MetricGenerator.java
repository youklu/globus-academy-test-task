package com.globus.academy.emulator.generators;

import com.globus.academy.emulator.generators.entities.Metric;

public interface MetricGenerator extends EntityGenerator {

    Metric generate();

}
