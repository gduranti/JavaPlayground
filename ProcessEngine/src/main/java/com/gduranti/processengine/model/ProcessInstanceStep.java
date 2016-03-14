package com.gduranti.processengine.model;

public class ProcessInstanceStep extends Step<ProcessInstanceStep> {

    private ProcessInstance processInstance;
    private ProcessTypeStep processStep;

    public ProcessInstanceStep() {
    }

    public ProcessInstanceStep(ProcessInstance processInstance, ProcessTypeStep processStep) {
        this.processInstance = processInstance;
        this.processStep = processStep;
        setServiceType(processStep.getServiceType());
        setOrder(processStep.getOrder());
        setCondition(processStep.getCondition());
    }

    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public ProcessTypeStep getProcessStep() {
        return processStep;
    }

    public void setProcessStep(ProcessTypeStep processStep) {
        this.processStep = processStep;
    }

}
