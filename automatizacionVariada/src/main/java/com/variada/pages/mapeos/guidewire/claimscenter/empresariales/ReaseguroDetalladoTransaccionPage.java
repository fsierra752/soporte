package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ReaseguroDetalladoTransaccionPage extends GeneralPage {

  @FindBy(xpath = "//div[@class='x-container g-screen x-container-page x-table-layout-ct']")
  public WebElementFacade tblReaseguroDetalladoTransaccion;
}
