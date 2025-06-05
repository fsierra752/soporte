package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class BusquedaPolizaPage extends GeneralPage {

  @FindBy(xpath = "//input[@id='FNOLWizard:FNOLWizard_FindPolicyScreen:ScreenMode_true-inputEl']")
  public WebElementFacade rbtBuscarPoliza;

  @FindBy(
      xpath =
          "//input[@id='FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:basicSearchSura:FNOLWizardFindPolicyInputSet:policyNumber-inputEl']")
  public WebElementFacade txtNumeroPoliza;

  @FindBy(
      xpath =
          "//td[.='Tipo de póliza']//div[@class='x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-first']")
  public WebElementFacade mnuTipoPoliza;

  @FindBy(xpath = "//td[.='Fecha del siniestro']//div")
  public WebElementFacade mnuFechaSiniestro;

  @FindBy(xpath = "//span[.='Hoy']//span[@class='x-btn-button']")
  public WebElementFacade btnFechaHoy;

  @FindBy(
      xpath =
          "//input[@id='FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:basicSearchSura:FNOLWizardFindPolicyInputSet:date-inputEl']")
  public WebElementFacade txtFecha;

  @FindBy(xpath = "//span[@class='g-underlined'][contains(text(),'s')]")
  public WebElementFacade btnBuscar;

  @FindBy(
      xpath =
          "//a[@id='FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:PolicyResultLV:0:selectButton']")
  public WebElementFacade btnPoliza;

  @FindBy(id = "lnkConsultarAseguradoInformacionAsegurado")
  public WebElementFacade btnConsultarDatosAseguradoATR;

  @FindBy(id = "idAseguradoInformacionAsegurado")
  public WebElementFacade txtNumeroDocumentoAtr;

  @FindBy(id = "tipoIdInformacionAsegurado")
  public WebElementFacade txtTipoDocumentoAsegurado;

  @FindBy(id = "lnkConsultarPolizasInformacionSiniestroEmp")
  public WebElementFacade btnConsultarPolizaAtr;

  @FindBy(xpath = "//input[@name='Pólizas EmpresarialesOption']")
  public WebElementFacade rbtPolizaAtr;

  @FindBy(xpath = "//input[@name='RiesgosOption']")
  public WebElementFacade rbtRiesgoPolizaAtr;

  @FindBy(id = "FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:basicSearchSura:FNOLWizardFind" +
          "PolicyInputSet:PolicyType-inputEl")
  public WebElementFacade lblTipoPoliza;

  @FindBy(xpath = "//input[@id='FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:basicSearchSura:FNOLWizardFindPolicyInputSet:ssn-inputEl']")
  public WebElementFacade txtNumeroDocumento;

  @FindBy(xpath = "//td[.='Tipo documento del asegurado']//div[@class='x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-first']")
  public WebElementFacade mnuTipoDocumento;

  @FindBy(id = "FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:PolicyResultLV:0:unselectButton")
  public WebElementFacade btnDeseleccionar;

  @FindBy(xpath = "//div[contains(text(),'de ')]")
  public WebElementFacade txtNumeroPaginas;

  @FindBy(xpath = "//a[@data-qtip='Siguiente página']")
  public WebElementFacade btnSiguientePagina;
}
