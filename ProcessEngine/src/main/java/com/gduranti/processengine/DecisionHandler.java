package com.gduranti.processengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gduranti.processengine.model.Process;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ServiceResult;
import com.gduranti.processengine.util.ProcessFlowException;

public class DecisionHandler {

    public void handleNextSteps(ServiceResult doneResult) {
        List<ProcessStep> nextSteps = findNextSteps(doneResult);
        // List<ProcessStep>
        configureNextSteps(doneResult.getProcessInstance(), nextSteps);
    }

    private void configureNextSteps(ProcessInstance processInstance, List<ProcessStep> nextSteps) {
        if (nextSteps.isEmpty()) {
            processInstance.setNextStep(null);

        } else if (nextSteps.size() == 1) {
            processInstance.setNextStep(nextSteps.get(0));

        } else if (nextSteps.size() > 1) {
            processInstance.setNextStep(null);

            for (ProcessStep nextStep : nextSteps) {
                Process process = processInstance.getProcess();
                process.addInstance(new ProcessInstance(process, nextStep));
            }
        }
    }

    private List<ProcessStep> findNextSteps(ServiceResult doneResult) {
        ProcessStep nextSingleStep = doneResult.getDoneStep().getNext();
        if (nextSingleStep != null) {
            return Arrays.asList(nextSingleStep);

        } else {
            List<ProcessStep> matchingSteps = decideMultipleSteps(doneResult);
            if (!matchingSteps.isEmpty()) {
                return matchingSteps;
            } else if (doneResult.getDoneStep().equals(doneResult.getProcessInstance().getProcess().getLastStep())) {
                return null;
            } else {
                throw new ProcessFlowException("Can't decide next process step(s) after: " + doneResult.getDoneStep());
            }
        }
    }

    private List<ProcessStep> decideMultipleSteps(ServiceResult doneResult) {
        List<ProcessStep> matchingSteps = new ArrayList<>();
        for (ProcessStep step : doneResult.getProcessInstance().getProcess().getSteps()) {
            if (step.getPrevious().equals(doneResult.getDoneStep()) && step.matchCondition(doneResult.getArgument())) {
                matchingSteps.add(step);
            }
        }
        return matchingSteps;
    }

}
