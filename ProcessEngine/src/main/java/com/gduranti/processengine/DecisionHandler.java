package com.gduranti.processengine;

import com.gduranti.processengine.model.ProcessInstanceStep;
import com.gduranti.processengine.model.ServiceResult;
import com.gduranti.processengine.util.ProcessFlowException;

public class DecisionHandler {

    public ProcessInstanceStep decideNextStep(ServiceResult doneResult) {

        ProcessInstanceStep nextSingleStep = doneResult.getDoneStep().getNext();
        if (nextSingleStep != null) {
            return nextSingleStep;

        } else {
            ProcessInstanceStep next = decideMultipleSteps(doneResult);
            if (next != null) {
                return next;
            } else if (doneResult.getDoneStep().equals(doneResult.getProcessInstance().getLastStep())) {
                return null;
            } else {
                throw new ProcessFlowException("Can't decide next process step after: " + doneResult.getDoneStep());
            }
        }
    }

    private ProcessInstanceStep decideMultipleSteps(ServiceResult doneResult) {
        for (ProcessInstanceStep step : doneResult.getProcessInstance().getSteps()) {
            if (step.getPrevious().equals(doneResult.getDoneStep()) && step.getCondition().equals(doneResult.getArgument())) {
                return step;
            }
        }
        return null;
    }

}
