package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DatoFinancieroTransaccionPage extends GeneralPage {

  @FindBy(
      xpath =
          "//div[@id='ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:TransactionsLV']")
  public WebElementFacade tblTransaccion;

  @FindBy(
      id = "ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:TransactionsLV:0:Amount")
  public WebElementFacade lnkReservaTransaccion;

  @FindBy(id = "ClaimFinancialsChecks:ClaimFinancialsChecksScreen:ChecksLV")
  public WebElementFacade tblDatosFinancierosPagos;
}
