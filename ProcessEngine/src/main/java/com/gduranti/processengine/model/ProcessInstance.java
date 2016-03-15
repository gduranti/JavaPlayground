package com.gduranti.processengine.model;

public class ProcessInstance {

    private Long id;
    private Process process;
    private ProcessStep nextStep;

    public ProcessInstance() {
    }

    public ProcessInstance(Process process, ProcessStep nextStep) {
        this.process = process;
        this.nextStep = nextStep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public ProcessStep getNextStep() {
        return nextStep;
    }

    public void setNextStep(ProcessStep nextStep) {
        this.nextStep = nextStep;
    }

}
