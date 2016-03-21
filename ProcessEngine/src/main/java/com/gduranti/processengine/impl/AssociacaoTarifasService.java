package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ServiceResult;

public class AssociacaoTarifasService implements Service<Object> {

    @Override
    public ServiceResult execute(ProcessInstance processInstance, Object payload) {
        // TODO Auto-generated method stub
        return new ServiceResult(processInstance);
    }

}
