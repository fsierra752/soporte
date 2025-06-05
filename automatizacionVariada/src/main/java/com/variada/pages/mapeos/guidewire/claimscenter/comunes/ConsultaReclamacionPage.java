package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ConsultaReclamacionPage extends GeneralPage {

  @FindBy(
      id =
          "ClaimLossDetails:ClaimLossDetailsScreen:LossDetailsPanelSet:LossDetailsCardCV:LossDetailsDV:ClaimNumber-inputEl")
  public WebElementFacade lblNumeroSiniestro;

  @FindBy(id = "TabBar:ClaimTab-btnInnerEl")
  public WebElementFacade lblNumeroReclamacion;
}
