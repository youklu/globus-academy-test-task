package com.globus.academy.kafkadatabasetranslator.databases.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "container")
@Data
@NoArgsConstructor
@ToString(exclude = "multipleContainerMetrics")
public class ContainerDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private Integer id;
    @Column(name="container_id", nullable=false)
    private Integer containerId;
    @OneToMany(mappedBy = "container", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContainerMetricsDb> containerMetricsList;

    @ManyToOne
    @JoinColumn(name = "multiple_container_metrics_id", nullable = false)
    private MultipleContainerMetricsDb multipleContainerMetrics;

}
