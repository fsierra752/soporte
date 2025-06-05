package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.constantes.MenuConstante.DETALLES_SINIESTRO;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DetalleSiniestroInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;

import org.fluentlenium.core.annotation.Page;

public class DetalleSiniestroStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page DetalleSiniestroInteraction detalleSiniestroInteraction;

  public void consultarInformacionSiniestro() {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(DETALLES_SINIESTRO);
    detalleSiniestroInteraction.obtenerNumeroPlacaPartesImplicadas();
  }
}
