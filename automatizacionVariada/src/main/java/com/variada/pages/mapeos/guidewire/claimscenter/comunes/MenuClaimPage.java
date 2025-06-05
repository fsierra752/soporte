package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MenuClaimPage extends GeneralPage {

  @FindBy(xpath = ".//*[@id=':tabs-innerCt']")
  public WebElementFacade mnuPrimerNivel;

  @FindBy(
      xpath =
          ".//div[contains(@id,'menu-') and @class='x-panel x-layer x-panel-default x-menu x-border-box']")
  public WebElementFacade mnuSegundoNivel;

  @FindBy(
      xpath =
          ".//div[contains(@id,'ext-gen') and @class='x-panel x-layer x-panel-default x-menu x-border-box']")
  public WebElementFacade mnuSegundoNivelEscritorio;

  @FindBy(xpath = " //div[@id='westPanel-innerCt']")
  public WebElementFacade mnuLateralPrimerNivel;

  @FindBy(xpath = "//input[@id='TabBar:ClaimTab:ClaimTab_FindClaim-inputEl']")
  public WebElementFacade mnuBuscar;

  @FindBy(xpath = "//span[@id='Claim:ClaimMenuActions-btnIconEl']")
  public WebElementFacade btnAcciones;

  @FindBy(
      xpath =
          "//table[@class='x-columnmenu-table']//td//a[contains(@id, 'Claim:ClaimMenuActions')]")
  public List<WebElementFacade> mnuPanelOpcionesPrimerNivel;
}
