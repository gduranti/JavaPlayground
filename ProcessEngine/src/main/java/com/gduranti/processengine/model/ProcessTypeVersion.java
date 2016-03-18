package com.gduranti.processengine.model;

import java.util.ArrayList;
import java.util.List;

public class ProcessTypeVersion {

    private ProcessType processType;
    private Integer version;
    private String name;
    private List<ProcessStep> steps;

    public ProcessTypeVersion() {
    }

    public ProcessTypeVersion(ProcessType processType, int version) {
        this.processType = processType;
        this.version = version;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessStep> getSteps() {
        return steps != null ? steps : (steps = new ArrayList<>());
    }

    public void setSteps(List<ProcessStep> steps) {
        this.steps = steps;
    }

    public ProcessStep getInitialStep() {
        return getSteps().get(0);
    }

    public ProcessStep getLastStep() {
        return getSteps().get(steps.size() - 1);
    }

    @Override
    public String toString() {
        return String.format("%sv%s", processType.getId(), version);
    }

}
