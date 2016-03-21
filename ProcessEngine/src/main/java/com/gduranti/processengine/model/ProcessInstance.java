package com.gduranti.processengine.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class ProcessInstance {

    @Id
    private Long id;
    private Process process;
    private ProcessStep nextStep;

    @Version
    private Long version;

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

    public boolean isActive() {
        return nextStep != null;
    }

}
