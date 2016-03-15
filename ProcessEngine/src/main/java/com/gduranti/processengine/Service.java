package com.gduranti.processengine;

import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ServiceResult;

public interface Service<T> {

    ServiceResult execute(ProcessStep processInstanceStep, T payload);

}
