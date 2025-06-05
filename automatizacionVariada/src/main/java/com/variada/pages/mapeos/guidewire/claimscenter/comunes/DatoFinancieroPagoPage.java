package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DatoFinancieroPagoPage extends GeneralPage {

  @FindBy(xpath = ".//div[@class='x-panel x-panel-default x-grid']")
  public WebElementFacade tblPagoIndividual;
}
