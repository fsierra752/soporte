package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PlanTrabajoActividadPage extends GeneralPage {

  @FindBy(id = "ClaimWorkplan:ClaimWorkplanScreen:WorkplanLV")
  public WebElementFacade tblPlanTrabajo;

  @FindBy(
      id =
          "ApprovalDetailWorksheet:ApprovalDetailScreen:ApprovalDetailWorksheet_ApproveButton-btnInnerEl")
  public WebElementFacade btnAprobarActividad;
}
