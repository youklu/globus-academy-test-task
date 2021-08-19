package com.globus.academy.webapi.databases;


import com.globus.academy.webapi.etities.Metric;

import java.util.List;

public interface Database {

    List<Metric> findMetrics(long timestampFrom, long timestampTo, int pageNumber, int countRecords);

}
