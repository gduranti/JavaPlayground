package com.gduranti.processengine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gduranti.processengine.model.Connection;
import com.gduranti.processengine.model.Process;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ServiceResult;
import com.gduranti.processengine.util.ProcessFlowException;

public class DecisionHandler {

    public void handleNextSteps(ServiceResult doneResult) {
        List<ProcessStep> nextSteps = findNextSteps(doneResult);
        validate(nextSteps, doneResult.getDoneStep());
        filterParallelism(nextSteps, doneResult);
        configureNextSteps(doneResult.getProcessInstance(), nextSteps);
    }

    private List<ProcessStep> findNextSteps(ServiceResult doneResult) {
        List<ProcessStep> matchingSteps = new ArrayList<>();
        for (Connection outgoingConnection : doneResult.getDoneStep().getOutgoingConnections()) {
            if (outgoingConnection.matchCondition(doneResult.getArgument())) {
                matchingSteps.add(outgoingConnection.getTo());
            }
        }
        return matchingSteps;
    }

    private void validate(List<ProcessStep> nextSteps, ProcessStep doneStep) {
        if (nextSteps.isEmpty() && !doneStep.equals(doneStep.getProcessType().getLastStep())) {
            throw new ProcessFlowException("Can't decide next process step(s) after: " + doneStep);
        }
    }

    private void filterParallelism(List<ProcessStep> nextSteps, ServiceResult doneResult) {

        for (Iterator<ProcessStep> iterator = nextSteps.iterator(); iterator.hasNext();) {
            ProcessStep processStep = iterator.next();

            for (Connection incomingConnection : processStep.getIncomingConnections()) {
                if (!incomingConnection.getFrom().equals(doneResult.getDoneStep()) && existsActiveInstanceFor(incomingConnection.getFrom(), doneResult)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    private void configureNextSteps(ProcessInstance processInstance, List<ProcessStep> nextSteps) {

        if (nextSteps.size() == 1) {
            processInstance.setNextStep(nextSteps.get(0));

        } else {
            processInstance.setNextStep(null);

            if (nextSteps.size() > 1) {
                for (ProcessStep nextStep : nextSteps) {
                    Process process = processInstance.getProcess();
                    process.addInstance(new ProcessInstance(process, nextStep));
                }
            }
        }
    }

    private boolean existsActiveInstanceFor(ProcessStep incomingConnectionForNextStep, ServiceResult doneResult) {
        for (ProcessInstance instance : doneResult.getProcessInstance().getProcess().getActiveInstances()) {
            if (instance.getNextStep().equals(incomingConnectionForNextStep)) {
                return true;
            }
        }
        return false;
    }
}
