package com.gduranti.processengine.model;

public class ProcessTypeStep extends Step<ProcessTypeStep> {

    private ProcessType processType;

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

}
