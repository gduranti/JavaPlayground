package com.gduranti.processengine.model;

public class ProcessStep extends Step<ProcessStep> {

    private Process process;
    private ProcessInstance processInstance;
    private ProcessTypeStep processTypeStep;

    public ProcessStep() {
    }

    public ProcessStep(Process process, ProcessTypeStep processTypeStep) {
        this.process = process;
        this.processTypeStep = processTypeStep;
        setServiceType(processTypeStep.getServiceType());
        setOrder(processTypeStep.getOrder());
        setCondition(processTypeStep.getCondition());
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public ProcessTypeStep getProcessTypeStep() {
        return processTypeStep;
    }

    public void setProcessTypeStep(ProcessTypeStep processTypeStep) {
        this.processTypeStep = processTypeStep;
    }

    public boolean matchCondition(String argument) {
        return getCondition() == null || getCondition().equals(argument);
    }

}
