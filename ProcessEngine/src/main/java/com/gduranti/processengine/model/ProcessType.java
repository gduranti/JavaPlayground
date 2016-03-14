package com.gduranti.processengine.model;

import java.util.List;

public class ProcessType {

    private Long id;
    private String name;
    private List<ProcessTypeStep> steps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessTypeStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ProcessTypeStep> steps) {
        this.steps = steps;
    }

    public ProcessTypeStep getInitialStep() {
        return steps.get(0);
    }

}
