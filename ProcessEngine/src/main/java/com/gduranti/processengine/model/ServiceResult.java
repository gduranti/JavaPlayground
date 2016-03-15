package com.gduranti.processengine.model;

public class ServiceResult {

    private ProcessStep doneStep;
    private ProcessStatus processStatus;
    private String argument;

    public ServiceResult(ProcessStep doneStep) {
        this.doneStep = doneStep;
    }

    public ServiceResult withProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
        return this;
    }

    public ServiceResult withArgument(String argument) {
        this.argument = argument;
        return this;
    }

    public ProcessStep getDoneStep() {
        return doneStep;
    }

    public ProcessInstance getProcessInstance() {
        return doneStep.getProcessInstance();
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public String getArgument() {
        return argument;
    }

}
