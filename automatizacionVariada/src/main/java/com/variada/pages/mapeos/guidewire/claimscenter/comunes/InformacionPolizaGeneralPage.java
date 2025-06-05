package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InformacionPolizaGeneralPage extends GeneralPage {

  @FindBy(
      id =
          "ClaimPolicyGeneral:ClaimPolicyGeneralScreen:PolicyGeneralPanelSet:PolicyGeneralDV:FinancedPolicyBalanceSAP-inputEl")
  public WebElementFacade lblValorPrimaPendiente;
}
