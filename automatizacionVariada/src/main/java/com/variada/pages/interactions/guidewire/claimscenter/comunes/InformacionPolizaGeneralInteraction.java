package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionPolizaGeneralPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class InformacionPolizaGeneralInteraction extends GeneralInteraction {

  @Page InformacionPolizaGeneralPage informacionPolizaGeneralPage;

  public InformacionPolizaGeneralInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public String verificarEstadoPrimaPendiente() {
    String valorPrimaPendiente = informacionPolizaGeneralPage.lblValorPrimaPendiente.getText();
    return valorPrimaPendiente;
  }
}
