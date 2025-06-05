package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ExposicionPage extends GeneralPage {

  @FindBy(id = "ClaimExposures:ClaimExposuresScreen:ExposuresLV")
  public WebElementFacade tblExposicionesAutomaticas;
}
