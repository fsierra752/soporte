package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ResumenReclamacionPage extends GeneralPage {

  @FindBy(xpath = "//a[@id='ClaimExposures:ClaimExposuresScreen:ExposuresLV:0:Type']")
  public WebElementFacade lnkTipoExposicion;

  @FindBy(
      xpath =
          "//a[@id='ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:TransactionsLV:0:Amount']")
  public WebElementFacade lnkReservaTransaccion;

  @FindBy(
      xpath =
          "//span[@id='Claim:ClaimInfoBar:LicensePlate-btnInnerEl']//child::span[@class='infobar_elem_val']")
  public WebElementFacade lblNumeroPlaca;

  @FindBy(id = "ClaimStatus:Edit-btnEl")
  public WebElementFacade btnEditar;

  @FindBy(id = "ClaimStatus:4:ClaimIndicatorInputSet:CoverageInQuestion_false-inputEl")
  public WebElementFacade rbtDesmarcarCoberturaEnDuda;

  @FindBy(id = "ClaimStatus:Update-btnEl")
  public WebElementFacade btnActualizar;
}
