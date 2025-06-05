package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MenuRecuperoPage extends GeneralPage {

  @FindBy(xpath = "//span[@id ='Claim:ClaimMenuActions-btnInnerEl']")
  public WebElementFacade btnAcciones;

  @FindBy(
      xpath =
          "//a[@id='Claim:ClaimMenuActions:ClaimMenuActions_NewTransaction:ClaimMenuActions_NewOtherTrans-itemEl']")
  public WebElementFacade mnuOtros;

  @FindBy(
      xpath =
          "//span[@id='Claim:ClaimMenuActions:ClaimMenuActions_NewTransaction:ClaimMenuActions_NewOtherTrans:ClaimMenuActions_NewTransaction_RecoverySet-textEl']")
  public WebElementFacade mnuRecuperos;
}
