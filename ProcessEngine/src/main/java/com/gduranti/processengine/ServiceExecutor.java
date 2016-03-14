package com.gduranti.processengine;

import javax.inject.Inject;

import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessInstanceStep;
import com.gduranti.processengine.model.ServiceResult;

public class ServiceExecutor {

    @Inject
    private ServiceFactory serviceFactory;

    @Inject
    private DecisionHandler decisionHandler;

    @Inject
    private ProcessRepository repository;

    public <T> ProcessInstance executeService(ProcessInstanceStep processInstanceStep, T payload) {
        Service<T> service = serviceFactory.createService(processInstanceStep.getProcessStep().getServiceType());
        ServiceResult result = service.execute(processInstanceStep, payload);

        ProcessInstance processInstance = result.getProcessInstance();
        processInstance.setProcessStatus(result.getProcessStatus());

        ProcessInstanceStep nextStep = decisionHandler.decideNextStep(result);
        processInstance.setNextStep(nextStep);

        return repository.save(processInstance);
    }

}
