package com.gduranti.processengine;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import com.gduranti.processengine.model.ServiceType;

public class ServiceFactory {

    @Inject
    private BeanManager beanManager;

    @SuppressWarnings("unchecked")
    public <T> Service<T> createService(ServiceType serviceType) {
        Set<Bean<?>> beans = beanManager.getBeans(serviceType.getServiceClass());
        if (beans.isEmpty()) {
            throw new IllegalArgumentException("Service bean not found: " + serviceType.getServiceClass());
        }
        if (beans.size() > 1) {
            throw new IllegalArgumentException("Multiple beans found for: " + serviceType.getServiceClass());
        }

        Bean<Service<T>> bean = (Bean<Service<T>>) beanManager.resolve(beans);
        CreationalContext<Service<T>> creationalContext = beanManager.createCreationalContext(bean);
        return beanManager.getContext(bean.getScope()).get(bean, creationalContext);
    }

}
