package com.gduranti.processengine;

import javax.inject.Inject;

import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessInstanceStep;
import com.gduranti.processengine.model.ProcessType;
import com.gduranti.processengine.util.ProcessBuilder;

public class ProcessFacade {

    @Inject
    private ServiceExecutor serviceExecutor;

    @Inject
    private ProcessBuilder processBuilder;

    public <T> ProcessInstance openProcess(ProcessType processType, T payload) {
        ProcessInstance processInstance = processBuilder.buildInstance(processType);
        return serviceExecutor.executeService(processInstance.getNextStep(), payload);
    }

    public <T> ProcessInstance executeService(ProcessInstanceStep processInstanceStep, T payload) {
        return serviceExecutor.executeService(processInstanceStep, payload);
    }

}
