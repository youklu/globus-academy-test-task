package com.globus.academy.webapi.databases.repositories;

import com.globus.academy.webapi.databases.entities.MetricDb;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MetricRepository extends PagingAndSortingRepository<MetricDb, Integer> {

    @Query(value = "SELECT metric.* " +
            "FROM container_metrics INNER JOIN metric ON container_metrics.ID = metric.container_metrics_id " +
            "WHERE container_metrics.timestamp >= ?1 AND container_metrics.timestamp < ?2",
            countQuery = "SELECT count(metric.id) " +
                    "FROM container_metrics INNER JOIN metric ON container_metrics.ID = metric.container_metrics_id " +
                    "WHERE container_metrics.timestamp >= ?1 AND container_metrics.timestamp < ?2",
            nativeQuery = true)
    List<MetricDb> findWhereTimeFromAndTimeTo(Timestamp timeFrom, Timestamp timeTo, Pageable page);

}
