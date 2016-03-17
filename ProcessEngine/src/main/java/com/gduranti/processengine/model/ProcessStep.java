package com.gduranti.processengine.model;

import java.util.List;

public class ProcessStep {

    private Long id;
    private ProcessTypeVersion processType;

    private ServiceType serviceType;
    private Integer order;

    private List<Connection> connections;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

}
