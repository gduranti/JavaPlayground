package com.gduranti.processengine.model;

public class Connection {

    private ProcessStep from;
    private ProcessStep to;
    private String condition;

    public ProcessStep getFrom() {
        return from;
    }

    public void setFrom(ProcessStep from) {
        this.from = from;
    }

    public ProcessStep getTo() {
        return to;
    }

    public void setTo(ProcessStep to) {
        this.to = to;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
