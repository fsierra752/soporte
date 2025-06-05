package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DetalleFacturaVolumenPage extends GeneralPage {

  @FindBy(
      id =
          "BulkPayWizard:BulkPayWizard_BulkInvoiceDetailScreen:BulkInvoiceDetailDV:Currency-inputEl")
  public WebElementFacade cmbTipoMoneda;

  @FindBy(xpath = "//ul[@class='x-list-plain']")
  public WebElementFacade lstTipoMoneda;

  @FindBy(xpath = "//label[@class='x-form-cb-label x-form-cb-label-after']")
  public WebElementFacade rbtMetodoPago;

  @FindBy(
      id =
          "BulkPayWizard:BulkPayWizard_BulkInvoiceDetailScreen:BulkInvoiceDetailDV:Payee:PayeeMenuIcon")
  public WebElementFacade btnBuscarBeneficiario;

  @FindBy(
      id =
          "BulkPayWizard:BulkPayWizard_BulkInvoiceDetailScreen:BulkInvoiceDetailDV:Payee:MenuItem_Search-textEl")
  public WebElementFacade btnBuscarBeneficiarioPago;

  @FindBy(id = "BulkPayWizard:Finish-btnInnerEl")
  public WebElementFacade btnFinalizarPagoMasivo;

  @FindBy(id = "EditBulkInvoiceDetail:BulkInvoiceDetailScreen:SubmitButton-btnInnerEl")
  public WebElementFacade btnEnviarPagoMasivo;

  @FindBy(
      id =
          "BulkPayWizard:BulkPayWizard_BulkInvoiceDetailScreen:BulkInvoiceDetailDV:InvoiceNumber-bodyEl")
  public WebElementFacade lblNumeroFacturaPagoMasivo;

  @FindBy(
      xpath =
          "//div[@id='EditBulkInvoiceDetail:BulkInvoiceDetailScreen:BulkInvoiceDetailDV:Status-inputEl']")
  public WebElementFacade lblEstadoPagoMasivo;

  @FindBy(id = "EditBulkInvoiceDetail:EditBulkInvoiceDetail_UpLink")
  public WebElementFacade lblIrAFacturaPorVolumen;
}
