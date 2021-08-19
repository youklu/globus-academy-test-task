package com.globus.academy.webapi.databases.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "container_metrics")
@Data
@NoArgsConstructor
@ToString(exclude = "container")
public class ContainerMetricsDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private Integer id;
    @Column(name="timestamp", nullable=false)
    private Timestamp timestamp;
    @OneToMany(mappedBy = "containerMetrics", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MetricDb> metrics;

    @ManyToOne
    @JoinColumn(name = "container_id", nullable = false)
    private ContainerDb container;

}
