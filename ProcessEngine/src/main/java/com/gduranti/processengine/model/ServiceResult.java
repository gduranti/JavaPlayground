package com.gduranti.processengine.model;

public class ServiceResult {

    private ProcessInstanceStep doneStep;
    private ProcessStatus processStatus;
    private String argument;

    public ServiceResult(ProcessInstanceStep doneStep) {
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

    public ProcessInstanceStep getDoneStep() {
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
