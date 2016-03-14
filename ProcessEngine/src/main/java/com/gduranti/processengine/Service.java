package com.gduranti.processengine;

import com.gduranti.processengine.model.ProcessInstanceStep;
import com.gduranti.processengine.model.ServiceResult;

public interface Service<T> {

    ServiceResult execute(ProcessInstanceStep processInstanceStep, T payload);

}
