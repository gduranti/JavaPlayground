package com.gduranti.processengine.model;

import java.util.List;

public class ProcessInstance {

    private Long id;
    private ProcessType processType;
    private ProcessStatus processStatus;
    private ProcessInstanceStep nextStep;
    private List<ProcessInstanceStep> steps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public ProcessInstanceStep getNextStep() {
        return nextStep;
    }

    public void setNextStep(ProcessInstanceStep nextStep) {
        this.nextStep = nextStep;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public List<ProcessInstanceStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ProcessInstanceStep> steps) {
        this.steps = steps;
    }

    public ProcessInstanceStep getLastStep() {
        return steps.get(steps.size() - 1);
    }

}
