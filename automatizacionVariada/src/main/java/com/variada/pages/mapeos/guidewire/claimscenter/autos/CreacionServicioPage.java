package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreacionServicioPage extends GeneralPage {

  @FindBy(id = "OtherServiceRequestPopup:NewServiceRequestDV:concesionarios")
  public WebElementFacade tblProveedores;
}
