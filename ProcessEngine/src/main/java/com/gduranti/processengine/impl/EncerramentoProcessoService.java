package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ProcessStatus;
import com.gduranti.processengine.model.ServiceResult;

public class EncerramentoProcessoService implements Service<Object> {

    @Override
    public ServiceResult execute(ProcessStep processInstanceStep, Object payload) {

        System.out.println("Encerando....");

        return new ServiceResult(processInstanceStep).withProcessStatus(ProcessStatus.ENCERRADO);
    }

}
