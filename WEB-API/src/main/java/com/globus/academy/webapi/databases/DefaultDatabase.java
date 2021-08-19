package com.globus.academy.webapi.databases;

import com.globus.academy.webapi.databases.entities.MetricDb;
import com.globus.academy.webapi.databases.entities.MultipleContainerMetricsDb;
import com.globus.academy.webapi.databases.repositories.MetricRepository;
import com.globus.academy.webapi.etities.Metric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DefaultDatabase implements Database {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MetricRepository metricRepository;

    @Override
    public List<Metric> findMetrics(long timestampFrom, long timestampTo, int pageNumber, int countRecords) {
        Timestamp timeFrom = Timestamp.from(Instant.ofEpochSecond(timestampFrom));
        Timestamp timeTo = Timestamp.from(Instant.ofEpochSecond(timestampTo));
        Pageable pageable = PageRequest.of(pageNumber, countRecords);
        List<Metric> foundMetrics = new ArrayList<>();
        List<MetricDb> a = metricRepository.findWhereTimeFromAndTimeTo(timeFrom, timeTo, pageable);
        for (MetricDb metricDb : metricRepository.findWhereTimeFromAndTimeTo(timeFrom, timeTo, pageable)) {
            Metric foundMetric = conversionService.convert(metricDb, Metric.class);
            foundMetrics.add(foundMetric);
        }
        return foundMetrics;
    }

}
