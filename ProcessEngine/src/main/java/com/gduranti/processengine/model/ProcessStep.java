package com.gduranti.processengine.model;

import java.util.ArrayList;
import java.util.List;

public class ProcessStep {

    private Integer id;
    private ProcessTypeVersion processType;

    private ServiceType serviceType;
    private Integer index;

    private List<Connection> incomingConnections;
    private List<Connection> outgoingConnections;

    public ProcessStep() {
    }

    public ProcessStep(Integer id, ProcessTypeVersion processType, ServiceType serviceType) {
        this.id = id;
        this.processType = processType;
        this.serviceType = serviceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcessTypeVersion getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessTypeVersion processType) {
        this.processType = processType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Connection> getIncomingConnections() {
        return incomingConnections != null ? incomingConnections : (incomingConnections = new ArrayList<>());
    }

    public void setIncomingConnections(List<Connection> incomingConnections) {
        this.incomingConnections = incomingConnections;
    }

    public void addIncomingConnection(ProcessStep step, String condition) {
        getIncomingConnections().add(new Connection(this, step, condition));
    }

    public List<Connection> getOutgoingConnections() {
        return outgoingConnections != null ? outgoingConnections : (outgoingConnections = new ArrayList<>());
    }

    public void setOutgoingConnections(List<Connection> outgoingConnections) {
        this.outgoingConnections = outgoingConnections;
    }

    public void connectTo(ProcessStep step) {
        connectTo(step, null);
    }

    public void connectTo(ProcessStep step, String condition) {
        getOutgoingConnections().add(new Connection(this, step, condition));
    }

    @Override
    public String toString() {
        return String.format("[ProcessStep #%s: %s of process %s]", id, serviceType.getName(), processType);
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(((ProcessStep) obj).id);
    }

}
