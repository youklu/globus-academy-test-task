package com.globus.academy.webapi.databases.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "multiple_container_metrics")
@Data
@NoArgsConstructor
@ToString
public class MultipleContainerMetricsDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private Integer id;
    @OneToMany(mappedBy = "multipleContainerMetrics", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContainerDb> containers;

}
