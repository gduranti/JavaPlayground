package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ServiceResult;

public class ConferenciaService implements Service<Boolean> {

    @Override
    public ServiceResult execute(ProcessInstance processInstance, Boolean aprovada) {
        // TODO Auto-generated method stub
        return new ServiceResult(processInstance).withArgument(aprovada ? "Aprovada" : "Reprovada");
    }

}
