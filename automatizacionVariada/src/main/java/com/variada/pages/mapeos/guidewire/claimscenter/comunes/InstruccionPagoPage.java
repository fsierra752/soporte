package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InstruccionPagoPage extends GeneralPage {

  @FindBy(
      xpath =
          "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckInstructionsScreen:NewPaymentInstructionsDV:CheckWizardCheckSummaryInputSet:Check_DateOfService-inputEl']")
  public WebElementFacade txtFechaPago;

  @FindBy(
      xpath =
          "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckInstructionsScreen:NewPaymentInstructionsDV:CheckWizardCheckSummaryInputSet:Check_InvoiceNumber-inputEl'][contains(@class,'x-form-field x-form-text')]")
  public WebElementFacade txtNumeroFactura;

  @FindBy(
      xpath =
          "//div[@id='NormalCreateCheckWizard:CheckWizard_CheckInstructionsScreen:NewPaymentInstructionsDV:CheckWizardCheckSummaryInputSet:Amount_Net-inputEl'][contains(@class,'x-form-display-field')]")
  public WebElementFacade txtMontoNeto;
}
