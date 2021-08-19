package com.globus.academy.kafkadatabasetranslator.databases;

import com.globus.academy.kafkadatabasetranslator.translators.entities.MultipleContainerMetrics;

public interface Database {

    void save(MultipleContainerMetrics multipleContainerMetrics);

}
