package com.gduranti.processengine;

import javax.inject.Inject;

import com.gduranti.processengine.model.Process;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ServiceResult;

public class ServiceExecutor {

    @Inject
    private ServiceFactory serviceFactory;

    @Inject
    private DecisionHandler decisionHandler;

    @Inject
    private ProcessRepository repository;

    public <T> ProcessInstance executeService(ProcessStep processStep, T payload) {
        Service<T> service = serviceFactory.createService(processStep.getServiceType());
        ServiceResult result = service.execute(processStep, payload);

        ProcessInstance processInstance = result.getProcessInstance();

        Process process = processInstance.getProcess();
        process.setStatus(result.getProcessStatus());

        decisionHandler.handleNextSteps(result);

        repository.save(process);
        return processInstance;
    }

}
