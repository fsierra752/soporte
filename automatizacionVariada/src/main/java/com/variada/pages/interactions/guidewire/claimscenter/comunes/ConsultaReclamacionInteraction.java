package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.constantes.MenuConstante.RECLAMACION_MENU;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.ConsultaReclamacionPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class ConsultaReclamacionInteraction extends GeneralInteraction {

  @Page ConsultaReclamacionPage consultaReclamacionPage;
  @Page MenuClaimInteraction menuClaimInteraction;

  public ConsultaReclamacionInteraction(WebDriver driver) {
    super(driver);
  }

  public void buscarReclamacion(String numeroReclamacion) {
    menuClaimInteraction.buscarReclamacion(RECLAMACION_MENU, numeroReclamacion);
  }

  public String obtenerLabelNumeroSiniestro() {
    return consultaReclamacionPage.lblNumeroSiniestro.waitUntilPresent().waitUntilVisible().getText();
  }

  public String obtenerNumeroSiniestro() {
    String numeroReclamacion =
        consultaReclamacionPage.lblNumeroReclamacion.getText().replaceAll("\\D+", "");
    realizarEsperaCarga();
    return numeroReclamacion;
  }
}
