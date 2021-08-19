package com.globus.academy.kafkadatabasetranslator.databases.repositories;

import com.globus.academy.kafkadatabasetranslator.databases.entities.MultipleContainerMetricsDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends CrudRepository<MultipleContainerMetricsDb, Integer> {
}
