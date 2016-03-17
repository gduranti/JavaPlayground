package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ProcessStatus;
import com.gduranti.processengine.model.ProcessTypeVersion;
import com.gduranti.processengine.model.ServiceResult;

public class AberturaProcessoService implements Service<ProcessTypeVersion> {

    @Override
    public ServiceResult execute(ProcessStep processInstanceStep, ProcessTypeVersion processType) {

        System.out.println("Abrindo processo....");

        return new ServiceResult(processInstanceStep).withProcessStatus(ProcessStatus.ABERTO);
    }

}
