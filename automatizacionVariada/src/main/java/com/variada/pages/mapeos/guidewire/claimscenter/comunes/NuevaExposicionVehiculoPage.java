package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NuevaExposicionVehiculoPage extends GeneralPage {

  @FindBy(
      id =
          "NewExposure:NewExposureScreen:NewExposureDV:NewClaimVehicleDamageDV:Claimant_Picker-inputEl")
  public WebElementFacade txtReclamanteExposicionVehicular;

  @FindBy(
      id =
          "NewExposure:NewExposureScreen:NewExposureDV:NewClaimVehicleDamageDV:Claimant_Type-inputEl")
  public WebElementFacade txtTipoReclamanteExposicion;

  @FindBy(
      id =
          "NewExposure:NewExposureScreen:NewExposureDV:NewClaimVehicleDamageDV:Vehicle_Incident:Vehicle_IncidentMenuIcon")
  public WebElementFacade btnNuevoIncidenteVehicular;

  @FindBy(xpath = "//span[contains(text(),'Nuevo incidente')]")
  public WebElementFacade lblNuevoIncidente;

  @FindBy(xpath = "//*[@id=\"NewExposure:NewExposureScreen:Update-btnInnerEl\"]")
  public WebElementFacade btnActualizar;
}
