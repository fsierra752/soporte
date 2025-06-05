package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.pages.interactions.guidewire.claimscenter.autos.FacturaVolumenInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class FacturaVolumenStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page FacturaVolumenInteraction facturaVolumenInteraction;

  @Step
  public void buscarNumeroFacturaPagoMasivo(String nombreOpcion, String subItem) {
    menuClaimInteraction.seleccionarOpcionMenuSegundoNivel(nombreOpcion, subItem);
    facturaVolumenInteraction.obtenerUltimaPagina();
    facturaVolumenInteraction.buscarNumeroFacturaPagoMasivo();
  }
}
