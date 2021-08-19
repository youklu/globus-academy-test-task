package com.globus.academy.kafkadatabasetranslator.databases.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "metric")
@Data
@NoArgsConstructor
@ToString(exclude = "containerMetrics")
public class MetricDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private Integer id;
    @Column(name="name", nullable=false)
    private String metricName;
    @Column(name="value", nullable=false)
    private int metricValue;

    @ManyToOne
    @JoinColumn(name = "container_metrics_id", nullable = false)
    private ContainerMetricsDb containerMetrics;

}
