package com.gduranti.processengine.model;

public class Connection {

    private ProcessStep from;
    private ProcessStep to;
    private String condition;

    public Connection() {
    }

    public Connection(ProcessStep from, ProcessStep to, String condition) {
        this.from = from;
        this.to = to;
        this.condition = condition;
    }

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

    public boolean matchCondition(String argument) {
        return (condition == argument) || (condition != null && condition.equals(argument));
    }

}
