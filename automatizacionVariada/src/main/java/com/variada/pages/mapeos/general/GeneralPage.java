package com.variada.pages.mapeos.general;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GeneralPage extends PageObject {

  @FindBy(
      xpath =
          "//div[contains(@class,'x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box')]/div/ul")
  public WebElementFacade lstOpcionesCombobox;

  @FindBy(xpath = "//div[contains(@class,'x-mask x-mask-fixed')]")
  public WebElementFacade pgrBarCarga;

  @FindBy(xpath = "//span[contains(@id, 'Next-btn') and @class='x-btn-wrap']")
  public WebElementFacade btnSiguiente;

  @FindBy(
      xpath =
          "//span[@id='FNOLWizard:Next-btnInnerEl' or @id='NormalCreateCheckWizard:Next-btnInnerEl' or @id='NormalCreateCheckWizard:Next-btnWrap']//parent::a")
  public WebElementFacade btnCambioPagina;

  @FindBy(xpath = ".//span[@class='x-btn-inner x-btn-inner-center' and contains(.,'Aceptar')]")
  public WebElementFacade btnAceptar;

  @FindBy(xpath = ".//span[contains(@id,'Finish-btnInnerEl')]")
  public WebElementFacade btnFinalizar;

  @FindBy(
      xpath =
          "//input[@id='ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:TransactionsLVRangeInput-inputEl']")
  public WebElementFacade txtTransacciones;

  @FindBy(xpath = "//div[@class='x-panel x-panel-default x-grid']")
  public WebElementFacade tblVerificacion;

  @FindBy(xpath = "//span[contains(text(),'Número de pago')]")
  public WebElementFacade btnOrganizarFacturasAscendente;

  @FindBy(xpath = "//input")
  public WebElementFacade mnuDinamico;

  @FindBy(xpath = "//span[@class='x-btn-icon-el x-tbar-page-last ']")
  public WebElementFacade btnUltimaPagina;

  @FindBy(xpath = "//span[@class='x-btn-button']//span[contains(text(),'Anular')]//parent::span")
  public WebElementFacade btnAnular;

  @FindBy(id = "WebMessageWorksheet:WebMessageWorksheetScreen:WebMessageWorksheet_ClearButton")
  public WebElementFacade btnBorrar;

  @FindBy(id = "BulkPay:BulkPayScreen:BulkInvoicesLV")
  public WebElementFacade tblNumeroFactura;

  public String itemListado  = "//li[.='COMODIN']";

  public String cmbPais = "Country-inputEl";

  public String cmbDepartamento = "State-inputEl";

  public String cmbDepartamento2 = "State2-inputEl";

  public String cmbTipoDireccion = "Address_AddressType-inputEl";

  public String mnuAsistenteVirtual = " //span[contains(text(),'COMODIN')]";

  public List<WebElement> obtenerTablaPagos(String fila){
    return tblVerificacion.findElements(By.xpath(String.format("//tr//td//div//a[contains(text(),'%s')]//parent::div//parent::td//parent::tr//td", fila)));
  }

  public List<WebElement> obtenerTablaTransacciones(String fila){
    return tblVerificacion.findElements(By.xpath(String.format("//tr//td//div[contains(text(),'%s')]//parent::td//parent::tr//td", fila)));
  }
}
