package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ResultadoArchivoProcesadoPage extends GeneralPage {

  @FindBy(id = "BulkPayWizard:BulkPayWizard_BulkItemsLoadScreen:BulkPayWizard_InvoiceItemsLV")
  public WebElementFacade tblResultadoArchivoProcesado;
}
