package com.globus.academy.kafkadatabasetranslator.translators;

import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;

public interface Translator {

    void translate(MultipleContainerMetrics multipleContainerMetrics);

}
