package com.gduranti.processengine;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.gduranti.processengine.model.Process;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ServiceResult;

public class ServiceExecutor {

    @Inject
    private ServiceFactory serviceFactory;

    @Inject
    private DecisionHandler decisionHandler;

    @Inject
    private ProcessRepository repository;

    @Inject
    private Event<ServiceResult> event;

    public <T> ProcessInstance executeService(ProcessInstance processInstance, T payload) {
        Service<T> service = serviceFactory.createService(processInstance.getNextStep().getServiceType());
        ServiceResult result = service.execute(processInstance, payload);

        Process process = processInstance.getProcess();
        if (result.getProcessStatus() != null) {
            process.setStatus(result.getProcessStatus());
        }

        decisionHandler.handleNextSteps(result);

        event.fire(result);

        repository.save(process);
        return processInstance;
    }

}
