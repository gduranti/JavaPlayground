package com.gduranti.processengine.model;

import com.gduranti.processengine.Service;
import com.gduranti.processengine.impl.AberturaProcessoService;
import com.gduranti.processengine.impl.AssociacaoTarifasService;
import com.gduranti.processengine.impl.AtualizacaoBinService;
import com.gduranti.processengine.impl.AtualizacaoSngService;
import com.gduranti.processengine.impl.ConferenciaService;
import com.gduranti.processengine.impl.EmissaoBcvaService;
import com.gduranti.processengine.impl.EmissaoCrvCrlvService;
import com.gduranti.processengine.impl.EncerramentoProcessoService;
import com.gduranti.processengine.impl.PrimeiroEmplacamentoService;
import com.gduranti.processengine.impl.VistoriaService;

public enum ServiceType {

    ABERTURA              (1, "Abertura de Processo",             AberturaProcessoService.class),
    VISTORIA              (2, "Vistoria",                         VistoriaService.class),
    PRIMEIRO_EMPLACAMENTO (4, "Primeiro Emplacamento",            PrimeiroEmplacamentoService.class),
    CONFERENCIA           (64, "Conferência",                     ConferenciaService.class),
    EMISSAO_BCVA          (14, "Emissão BCVA",                    EmissaoBcvaService.class),
    ATUALIZACAO_BIN       (70, "Atualização da BIN",              AtualizacaoBinService.class),
    ATUALIZACAO_SNG       (22, "Atualização do SNG",              AtualizacaoSngService.class),
    EMISSAO_CRV_CRLV      (20, "Emissão de CRV/CRLV",             EmissaoCrvCrlvService.class),
    PAGAMENTO_TARIFAS     (98, "Pagamento/Associação de Tarifas", AssociacaoTarifasService.class),
    ENCERRAMENTO          (99, "Encerramento de Processo",        EncerramentoProcessoService.class);

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
