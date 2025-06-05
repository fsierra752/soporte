package com.variada.steps.guidewire.claimscenter.empresariales;

import com.variada.pages.interactions.guidewire.claimscenter.empresariales.AuditoriaInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class InclusionProcesoAuditoriaStep {

  @Page AuditoriaInteraction auditoriaInteraction;

  @Step
  public void marcarAuditoria(String auditoria) {
    auditoriaInteraction.seleccionarDetalleInvestigacionAuditoria();
    auditoriaInteraction.verificarMarcacion(auditoria);
  }
}
