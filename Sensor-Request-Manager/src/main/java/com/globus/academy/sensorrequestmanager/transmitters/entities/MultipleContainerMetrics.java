package com.globus.academy.sensorrequestmanager.transmitters.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class MultipleContainerMetrics {

    List<Container> containers;

}
