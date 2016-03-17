package com.gduranti.processengine.model;

import java.util.ArrayList;
import java.util.List;

public class Process {

    private Long id;
    private ProcessTypeVersion type;
    private ProcessStatus status;
    private List<ProcessInstance> instances;

    public Process() {
    }

    public Process(ProcessTypeVersion type, ProcessStatus status) {
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessTypeVersion getType() {
        return type;
    }

    public void setType(ProcessTypeVersion type) {
        this.type = type;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public List<ProcessInstance> getInstances() {
        return instances != null ? instances : (instances = new ArrayList<>());
    }

    public void setInstances(List<ProcessInstance> instances) {
        this.instances = instances;
    }

    public void addInstance(ProcessInstance instance) {
        getInstances().add(instance);
    }

}
