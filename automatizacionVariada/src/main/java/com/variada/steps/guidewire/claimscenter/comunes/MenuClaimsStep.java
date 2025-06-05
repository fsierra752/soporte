package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import static com.variada.utils.constantes.MenuConstante.RECLAMACION_MENU;
import org.fluentlenium.core.annotation.Page;

public class MenuClaimsStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  public void consultarNumeroReclamacion(String numReclamacion) {
    menuClaimInteraction.buscarReclamacion(RECLAMACION_MENU, numReclamacion);
  }
}
