package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ResultadoValidacionArchivoPage extends GeneralPage {

  @FindBy(xpath = ".//label[contains(@class,'x-component x-component-default')]")
  public WebElementFacade lblNumeroRegistrosFactura;
}
