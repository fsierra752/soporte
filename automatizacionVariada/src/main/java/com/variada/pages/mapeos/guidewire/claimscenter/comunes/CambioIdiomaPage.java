package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CambioIdiomaPage extends GeneralPage {

  @FindBy(id = ":TabLinkMenuButton")
  public WebElementFacade btnAjuste;

  @FindBy(id = "TabBar:LanguageTabBarLink-textEl")
  public WebElementFacade lnkInternacional;

  @FindBy(id = "TabBar:LanguageTabBarLink:languageSwitcher-textEl")
  public WebElementFacade lnkIdioma;

  @FindBy(
      xpath =
          "//div[@class='x-component x-header-text-container x-container-text-container x-container-text-container-default x-box-item x-component-default']/span/span")
  public WebElementFacade lblLetraComprobante;

  @FindBy(xpath = "//span[@id ='TabBar:LanguageTabBarLink:languageSwitcher:0:langs-textEl']")
  public WebElementFacade lnkSeleccionarLenguaje;

  public String cmbTipoIdioma = "//div/a/span[contains(.,'COMODIN')]/../..";
}
