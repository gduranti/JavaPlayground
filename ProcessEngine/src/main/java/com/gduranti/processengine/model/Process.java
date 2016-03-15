package com.gduranti.processengine.model;

import java.util.ArrayList;
import java.util.List;

public class Process {

    private Long id;
    private ProcessType type;
    private ProcessStatus status;
    private List<ProcessStep> steps;
    private List<ProcessInstance> instances;

    public Process() {
    }

    public Process(ProcessType type, ProcessStatus status) {
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessType getType() {
        return type;
    }

    public void setType(ProcessType type) {
        this.type = type;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public List<ProcessStep> getSteps() {
        return steps != null ? steps : (steps = new ArrayList<>());
    }

    public void setSteps(List<ProcessStep> steps) {
        this.steps = steps;
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

    public ProcessStep getInitialStep() {
        return getSteps().get(0);
    }

    public ProcessStep getLastStep() {
        return getSteps().get(steps.size() - 1);
    }

}
