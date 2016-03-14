package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessInstanceStep;
import com.gduranti.processengine.model.ProcessStatus;
import com.gduranti.processengine.model.ProcessType;
import com.gduranti.processengine.model.ServiceResult;

public class AberturaProcessoService implements Service<ProcessType> {

    @Override
    public ServiceResult execute(ProcessInstanceStep processInstanceStep, ProcessType processType) {

        System.out.println("Abrindo processo....");

        return new ServiceResult(processInstanceStep).withProcessStatus(ProcessStatus.ABERTO);
    }

}
