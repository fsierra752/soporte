package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class FacturaVolumenPage extends GeneralPage {

  @FindBy(xpath = ".//a[@data-qtip='Última página']")
  public WebElementFacade lblObtenerUltimaPagina;
}
