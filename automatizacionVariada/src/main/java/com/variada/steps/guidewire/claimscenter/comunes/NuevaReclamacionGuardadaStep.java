package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class NuevaReclamacionGuardadaStep {

  @Page NuevaReclamacionGuardadaInteraction nuevaReclamacionGuardadaInteraction;

  @Step
  public void obtenerNumeroReclamacionGuardada() {
    nuevaReclamacionGuardadaInteraction.obtenerNumeroReclamacion();
  }

  public void abrirNuevaReclamacionGuardada() {
    nuevaReclamacionGuardadaInteraction.abrirReclamacion();
  }
}
