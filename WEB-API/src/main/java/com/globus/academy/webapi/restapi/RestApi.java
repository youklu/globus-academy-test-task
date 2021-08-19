package com.globus.academy.webapi.restapi;

import com.globus.academy.webapi.api.DefaultApi;
import com.globus.academy.webapi.databases.Database;
import com.globus.academy.webapi.etities.Metric;
import com.globus.academy.webapi.model.MetricApi;
import com.globus.academy.webapi.model.MetricPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

@Controller
public class RestApi implements DefaultApi {

    @Autowired
    private Database database;
    @Autowired
    private ConversionService conversionService;

    @Override
    public ResponseEntity<MetricPage> getMetrics(Integer pageNumber, OffsetDateTime timeFrom, OffsetDateTime timeTo, Integer recordCountOnPage) {
        List<Metric> foundMetrics = database.findMetrics(timeFrom.toEpochSecond(), timeTo.toEpochSecond(), pageNumber, recordCountOnPage);
        List<MetricApi> convertedFoundMetrics = convertMetrics(foundMetrics);
        MetricPage metricPage = new MetricPage();
        metricPage.setMetrics(convertedFoundMetrics);
        metricPage.setNumber(pageNumber);
        return new ResponseEntity<>(metricPage, HttpStatus.OK);
    }

    private List<MetricApi> convertMetrics(List<Metric> sourceMetrics) {
        List<MetricApi> convertedMetrics = new LinkedList<>();
        for (Metric sourceMetric : sourceMetrics) {
            MetricApi convertedMetric = conversionService.convert(sourceMetric, MetricApi.class);
            convertedMetrics.add(convertedMetric);
        }
        return convertedMetrics;
    }
}
