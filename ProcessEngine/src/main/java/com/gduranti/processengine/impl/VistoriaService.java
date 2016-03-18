package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ServiceResult;

public class VistoriaService implements Service<Boolean> {

    @Override
    public ServiceResult execute(ProcessInstance processInstance, Boolean aprovado) {

        System.out.println("Vistoriando....");

        ServiceResult result = new ServiceResult(processInstance);
        if (aprovado) {
            return result.withArgument("Aprovado");
        } else {
            return result.withArgument("Reprovado");
        }
    }

}
