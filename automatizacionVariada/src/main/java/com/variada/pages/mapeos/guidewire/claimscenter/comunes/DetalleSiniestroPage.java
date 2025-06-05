package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DetalleSiniestroPage extends GeneralPage {

  @FindBy(
      id =
          "ClaimLossDetails:ClaimLossDetailsScreen:LossDetailsPanelSet:LossDetailsCardCV:LossDetailsDV:EditableVehicleIncidentsLV")
  public WebElementFacade tblPlacasVehiculosInvolucrados;

  @FindBy(id = "Claim:ClaimInfoBar:LicensePlate-btnInnerEl")
  public WebElementFacade lblPlacaAsegurado;
}
