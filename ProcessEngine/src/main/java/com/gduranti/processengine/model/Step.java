package com.gduranti.processengine.model;

public abstract class Step<T extends Step<T>> {

    private ServiceType serviceType;
    private Integer order;

    private T previous;
    private T next;
    private String condition;

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

    public T getPrevious() {
        return previous;
    }

    public void setPrevious(T previous) {
        this.previous = previous;
    }

    public T getNext() {
        return next;
    }

    public void setNext(T next) {
        this.next = next;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
