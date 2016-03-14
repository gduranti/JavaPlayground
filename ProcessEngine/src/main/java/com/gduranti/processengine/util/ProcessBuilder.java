package com.gduranti.processengine.util;

import java.util.ArrayList;
import java.util.List;

import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessInstanceStep;
import com.gduranti.processengine.model.ProcessType;
import com.gduranti.processengine.model.ProcessTypeStep;

public class ProcessBuilder {

    public ProcessInstance buildInstance(ProcessType processType) {
        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setProcessType(processType);

        List<ProcessInstanceStep> steps = buildInstanceSteps(processType, processInstance);
        processInstance.setSteps(steps);
        processInstance.setNextStep(steps.get(0));

        return processInstance;
    }

    private List<ProcessInstanceStep> buildInstanceSteps(ProcessType processType, ProcessInstance processInstance) {
        List<ProcessInstanceStep> instanceSteps = new ArrayList<>();
        for (ProcessTypeStep step : processType.getSteps()) {
            instanceSteps.add(new ProcessInstanceStep(processInstance, step));
        }
        for (ProcessInstanceStep processInstanceStep : instanceSteps) {
            if (processInstanceStep.getProcessStep().getPrevious() != null) {
                processInstanceStep.setPrevious(find(instanceSteps, processInstanceStep.getProcessStep().getPrevious()));
            }
            if (processInstanceStep.getProcessStep().getNext() != null) {
                processInstanceStep.setNext(find(instanceSteps, processInstanceStep.getProcessStep().getNext()));
            }
        }
        return instanceSteps;
    }

    private ProcessInstanceStep find(List<ProcessInstanceStep> instanceSteps, ProcessTypeStep searchStep) {
        for (ProcessInstanceStep instanceStep : instanceSteps) {
            if (instanceStep.getProcessStep().equals(searchStep)) {
                return instanceStep;
            }
        }
        throw new RuntimeException("Step not found: " + searchStep);
    }

}
