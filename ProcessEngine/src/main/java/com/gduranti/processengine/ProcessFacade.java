package com.gduranti.processengine;

import javax.inject.Inject;

import com.gduranti.processengine.model.Process;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ProcessTypeVersion;
import com.gduranti.processengine.util.ProcessBuilder;

public class ProcessFacade {

    @Inject
    private ServiceExecutor serviceExecutor;

    @Inject
    private ProcessBuilder processBuilder;

    @Inject
    private ProcessRepository repository;

    public <T> ProcessInstance openProcess(ProcessTypeVersion processType, T payload) {
        Process process = processBuilder.buildProcess(processType);
        return serviceExecutor.executeService(process.getInitialStep(), payload);
    }

    public <T> ProcessInstance executeService(ProcessStep processStep, T payload) {
        return serviceExecutor.executeService(processStep, payload);
    }

    public Process load(Long id) {
        return repository.load(id);
    }

}
