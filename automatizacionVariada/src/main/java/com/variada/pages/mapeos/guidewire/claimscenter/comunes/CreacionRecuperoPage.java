package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreacionRecuperoPage extends GeneralPage {

  @FindBy(
      xpath =
          "//table[@id='NewRecoverySet:NewRecoveryScreen:RecoveryDetailDV:Payer-triggerWrap']//td/following-sibling::td/div")
  public WebElementFacade txtPagador;

  @FindBy(
      xpath =
          "//input[@id='NewRecoverySet:NewRecoveryScreen:RecoveryDetailDV:ReserveLineInputSet:ReserveLine-inputEl']")
  public WebElementFacade txtLineaReserva;

  @FindBy(id = "NewRecoverySet:NewRecoveryScreen:RecoveryDetailDV:RecoveryCategory-inputEl")
  public WebElementFacade txtCategoriaRecuperacion;

  @FindBy(id = "NewRecoverySet:NewRecoveryScreen:RecoveryDetailDV:SuraEditableRecoveryLineItemsLV")
  public WebElementFacade tblElementoLinea;

  @FindBy(
      xpath =
          "//a[@class='x-btn x-unselectable x-btn-toolbar x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']/span[@id='NewRecoverySet:NewRecoveryScreen:Update-btnWrap']")
  public WebElementFacade btnActualizar;

  @FindBy(
      xpath = "//span[@id='ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:ttlBar']")
  public WebElementFacade lblTituloRecupero;

  public String cmbCiudad = "City-inputEl";
}
