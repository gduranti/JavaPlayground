package com.gduranti.processengine;

import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ServiceResult;

public interface Service<T> {

    ServiceResult execute(ProcessInstance processInstance, T payload);

}
