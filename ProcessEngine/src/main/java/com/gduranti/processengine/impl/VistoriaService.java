package com.gduranti.processengine.impl;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ServiceResult;

public class VistoriaService implements Service<Boolean> {

    @Override
    public ServiceResult execute(ProcessStep processInstanceStep, Boolean aprovado) {

        System.out.println("Vistoriando....");

        ServiceResult result = new ServiceResult(processInstanceStep);
        if (aprovado) {
            return result.withArgument("Aprovado");
        } else {
            return result.withArgument("Reprovado");
        }
    }

}
