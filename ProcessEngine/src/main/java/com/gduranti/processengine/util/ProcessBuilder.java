package com.gduranti.processengine.util;

import java.util.ArrayList;
import java.util.List;

import com.gduranti.processengine.model.Process;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessStatus;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ProcessType;
import com.gduranti.processengine.model.ProcessTypeStep;

public class ProcessBuilder {

    public Process buildProcess(ProcessType processType) {
        Process process = new Process(processType, ProcessStatus.ABERTO);

        List<ProcessStep> steps = buildSteps(processType, process);
        process.setSteps(steps);

        ProcessInstance startingInstance = new ProcessInstance(process, steps.get(0));
        process.addInstance(startingInstance);

        steps.get(0).setProcessInstance(startingInstance);

        return process;
    }

    private List<ProcessStep> buildSteps(ProcessType processType, Process process) {
        List<ProcessStep> processSteps = new ArrayList<>();
        for (ProcessTypeStep step : processType.getSteps()) {
            processSteps.add(new ProcessStep(process, step));
        }
        for (ProcessStep processStep : processSteps) {
            if (processStep.getProcessTypeStep().getPrevious() != null) {
                processStep.setPrevious(find(processSteps, processStep.getProcessTypeStep().getPrevious()));
            }
            if (processStep.getProcessTypeStep().getNext() != null) {
                processStep.setNext(find(processSteps, processStep.getProcessTypeStep().getNext()));
            }
        }
        return processSteps;
    }

    private ProcessStep find(List<ProcessStep> processSteps, ProcessTypeStep searchStep) {
        for (ProcessStep processStep : processSteps) {
            if (processStep.getProcessTypeStep().equals(searchStep)) {
                return processStep;
            }
        }
        throw new RuntimeException("Step not found: " + searchStep);
    }

}
