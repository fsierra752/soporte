package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DatoReservaPage extends GeneralPage {

  @FindBy(
      xpath =
          "//div[@id='ClaimFinancialsTransactionsDetail:ClaimFinancialsTransactionsDetailScreen:TransactionDetailPanelSet:TransactionReserveDV:TransactionBasicsInputSet:Amount-inputEl']")
  public WebElementFacade lblCantidad;
}
