package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PropiedadesImplicadasPage extends GeneralPage {

  @FindBy(xpath = "//td/div[@class='x-grid-cell-inner ']/div")
  public WebElementFacade rbtPropiedad;

  @FindBy(xpath = "//span[@id='FNOLWizard:FNOLWizard_PickPolicyRiskUnitsScreen:ttlBar']")
  public WebElementFacade lblTituloPropiedadesImplicadas;
}
