package com.gduranti.processengine;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gduranti.processengine.model.ProcessInstance;
import com.gduranti.processengine.model.ProcessStatus;
import com.gduranti.processengine.model.ProcessStep;
import com.gduranti.processengine.model.ProcessType;
import com.gduranti.processengine.model.ProcessTypeVersion;
import com.gduranti.processengine.model.ServiceType;

@RunWith(Arquillian.class)
public class ProcessFacadeIT extends AbstractIntegratedTest {

    @Inject
    private ProcessFacade processFacade;

    private ProcessType processType;
    private ProcessStep aberturaProcessoStep;
    private ProcessStep vistoriaStep;
    private ProcessStep encerramentoStep;

    @Before
    public void setUp() {
        buildTestProcessType();
    }

    private void buildTestProcessType() {
        processType = new ProcessType(101);
        ProcessTypeVersion processTypeVersion = processType.newVersion();

        aberturaProcessoStep = new ProcessStep(1, processTypeVersion, ServiceType.ABERTURA);
        vistoriaStep = new ProcessStep(2, processTypeVersion, ServiceType.VISTORIA);
        encerramentoStep = new ProcessStep(3, processTypeVersion, ServiceType.ENCERRAMENTO);

        aberturaProcessoStep.connectTo(vistoriaStep);
        vistoriaStep.connectTo(encerramentoStep, "Aprovada");
        vistoriaStep.connectTo(vistoriaStep, "Reprovada");

        processTypeVersion.getSteps().add(aberturaProcessoStep);
        processTypeVersion.getSteps().add(vistoriaStep);
        processTypeVersion.getSteps().add(encerramentoStep);
    }

    @Test
    public void test_openProcess() {

        ProcessInstance processInstance = processFacade.openProcess(processType, null);

        assertEquals(vistoriaStep, processInstance.getNextStep());
        assertEquals(1, processInstance.getProcess().getInstances().size());
        assertEquals(ProcessStatus.ABERTO, processInstance.getProcess().getStatus());
        assertEquals(processType.getCurrentVersion(), processInstance.getProcess().getType());
    }

    @Test
    public void test_executeVistoriaAprovada() {
        ProcessInstance processInstance = processFacade.openProcess(processType, null);
        processInstance = processFacade.executeService(processInstance, true);

        assertEquals(encerramentoStep, processInstance.getNextStep());
        assertEquals(1, processInstance.getProcess().getInstances().size());
        assertEquals(ProcessStatus.ABERTO, processInstance.getProcess().getStatus());
    }

    @Test
    public void test_executeVistoriaReprovada() {
        ProcessInstance processInstance = processFacade.openProcess(processType, null);
        processInstance = processFacade.executeService(processInstance, false);

        assertEquals(vistoriaStep, processInstance.getNextStep());
        assertEquals(1, processInstance.getProcess().getInstances().size());
        assertEquals(ProcessStatus.ABERTO, processInstance.getProcess().getStatus());
    }

    @Test
    public void test_executeEncerramento() {
        ProcessInstance processInstance = processFacade.openProcess(processType, null);
        processInstance = processFacade.executeService(processInstance, true);
        processInstance = processFacade.executeService(processInstance, null);

        assertEquals(null, processInstance.getNextStep());
        assertEquals(1, processInstance.getProcess().getInstances().size());
        assertEquals(ProcessStatus.ENCERRADO, processInstance.getProcess().getStatus());
    }

}
