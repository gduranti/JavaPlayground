package com.gduranti.processengine.model;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.impl.AberturaProcessoService;
import com.gduranti.processengine.impl.EncerramentoProcessoService;
import com.gduranti.processengine.impl.VistoriaService;

public enum ServiceType {

    ABERTURA     (1, "Abertura de Processo",     AberturaProcessoService.class),
    VISTORIA     (2, "Vistoria",                 VistoriaService.class),
    ENCERRAMENTO (3, "Encerramento de Processo", EncerramentoProcessoService.class), ;

    private final int id;
    private final String name;
    private final Class<? extends Service<?>> serviceClass;

    private ServiceType(int id, String name, Class<? extends Service<?>> serviceClass) {
        this.id = id;
        this.name = name;
        this.serviceClass = serviceClass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Service<?>> getServiceClass() {
        return serviceClass;
    }

}
