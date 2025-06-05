package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InformacionPagoPage extends GeneralPage {

  @FindBy(
      xpath =
          "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckPaymentsScreen:NewCheckPaymentPanelSet:NewPaymentDetailDV:ReserveLineInputSet:ReserveLine-inputEl']")
  public WebElementFacade cmbLineaReserva;

  @FindBy(
      xpath =
          "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckPaymentsScreen:NewCheckPaymentPanelSet:NewPaymentDetailDV:Payment_PaymentType-inputEl']")
  public WebElementFacade cmbTipoPago;

  @FindBy(
      xpath =
          "//div[@id='NormalCreateCheckWizard:CheckWizard_CheckPaymentsScreen:NewCheckPaymentPanelSet:NewPaymentDetailDV:Transaction_AvailableReserves-inputEl']")
  public WebElementFacade txtValorReserva;

  @FindBy(
      xpath =
          "//div[@id='NormalCreateCheckWizard:CheckWizard_CheckPaymentsScreen:NewCheckPaymentPanelSet:NewPaymentDetailDV:EditablePaymentLineItemsLV']")
  public WebElementFacade tblElementoLinea;

  @FindBy(xpath = "//textarea")
  public WebElementFacade txtComentarioPago;

  @FindBy(
      xpath =
          "//div[@id='centerPanel']//div[@id='NormalCreateCheckWizard/NewCheckPayments']//*[contains(text(),'Agregar')]")
  public WebElementFacade btnAgregarRetencion;

  @FindBy(
      id =
          "NormalCreateCheckWizard:CheckWizard_CheckPaymentsScreen:NewCheckPaymentPanelSet:NewPaymentDetailDV:Pending_Bonus_Payment_true-inputEl")
  public WebElementFacade rbtDescontarSaldoPrima;
}
