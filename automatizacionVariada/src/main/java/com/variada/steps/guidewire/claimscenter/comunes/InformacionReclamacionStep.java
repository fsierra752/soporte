package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionReclamacionInteraction;
import org.fluentlenium.core.annotation.Page;

public class InformacionReclamacionStep {

  @Page InformacionReclamacionInteraction informacionReclamacionInteraction;

  public void diligenciarInformacionIncidente(
      String causa, String valorPretension, String tipoIncidente) {
    informacionReclamacionInteraction.cerrarReclamosDuplicados();
    informacionReclamacionInteraction.escribirValorPretension(valorPretension);
    informacionReclamacionInteraction.seleccionarCausaSiniestro(causa);
    informacionReclamacionInteraction.seleccionarTipoIncidente(tipoIncidente);
    informacionReclamacionInteraction.finalizarSiniestro();
  }
}
