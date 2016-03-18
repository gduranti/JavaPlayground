package com.gduranti.processengine.model;

import java.util.List;

public class ProcessStep {

    private Long id;
    private ProcessTypeVersion processType;

    private ServiceType serviceType;
    private Integer index;

    private List<Connection> incomingConnections;
    private List<Connection> outgoingConnections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return incomingConnections;
    }

    public void setIncomingConnections(List<Connection> incomingConnections) {
        this.incomingConnections = incomingConnections;
    }

    public List<Connection> getOutgoingConnections() {
        return outgoingConnections;
    }

    public void setOutgoingConnections(List<Connection> outgoingConnections) {
        this.outgoingConnections = outgoingConnections;
    }

    @Override
    public String toString() {
        return String.format("[ProcessStep #%s: %s of process %s]", id, serviceType.getName(), processType);
    }

}
