package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NuevaReclamacionGuardadaPage extends GeneralPage {

  @FindBy(
      xpath = "//div[@id='NewClaimSaved:NewClaimSavedScreen:NewClaimSavedDV:GoToClaim-inputEl']")
  public WebElementFacade lblNumeroReclamacion;
}
