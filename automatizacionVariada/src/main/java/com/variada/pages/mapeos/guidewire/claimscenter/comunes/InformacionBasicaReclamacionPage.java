package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class InformacionBasicaReclamacionPage extends GeneralPage {

  public String btnCambioMesAnio = "//table[@class='datePickerMonthSelector']//td[COMODIN]//div[@class='html-face']";
  public String lnkDiaMes = "//td[@class='datePickerDay ' or @class='datePickerDay datePickerDayIsWeekend '][contains(text(),'COMODIN')]";

  @FindBy(xpath = "//input[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_MainContactsScreen:NewClaimPeopleDV:ReportedBy_Name-inputEl']")
  public WebElementFacade txtNombreAutor;

  @FindBy(xpath = "//div[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box']//li[2]")
  public WebElementFacade lstAutorReporteCliente;

  @FindBy(xpath = "//textarea[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_MainContactsScreen:NewClaimPeopleDV:Description-inputEl']")
  public WebElementFacade txtDetalleHechos;

  @FindBy(xpath = " //a[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_MainContactsScreen:NewClaimPeopleDV:ReportedBy_Name:ReportedBy_NameMenuIcon']")
  public WebElementFacade btnContactManager;

  @FindBy(xpath = "//span[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_MainContactsScreen:NewClaimPeopleDV:ReportedBy_Name:MenuItem_Search-textEl']")
  public WebElementFacade btnBuscarContactoExistente;

  @FindBy(xpath = "//input[@id='AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchDV:TaxID-inputEl']")
  public WebElementFacade txtNit;

  @FindBy(xpath = "//a[@id='AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchDV:SearchAndResetInputSet:SearchLinksInputSet:Search']")
  public WebElementFacade btnBuscarNit;

  @FindBy(xpath = "//a[@id='AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchLV:0:_Select']")
  public WebElementFacade btnSeleccionarContacto;

  @FindBy(xpath = "//input[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_MainContactsScreen:NewClaimPeopleDV:Claim_ReportedByType-inputEl']")
  public WebElementFacade btnRelacionAsegurado;

  @FindBy(xpath = "//li[contains(text(),'Amigo')]")
  public WebElementFacade lstAmigo;

  @FindBy(id = "calendarfechaOcurrenciaInformacionSiniestroEmp")
  public WebElementFacade tblCalendarioFechaSiniestro;

  @FindBy(className = "datePickerMonth")
  public WebElementFacade lblAnioMes;

  @FindBy(xpath = "//input[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_BasicInfoScreen:PanelRow:BasicInfoDetailViewPanelDV:ReportedBy_Name-inputEl']/../following-sibling::td")
  public WebElementFacade cmbNombre;

  @FindBy(xpath = "//div[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box']//li/following-sibling::li")
  public WebElementFacade lstNombreAutor;

  @FindBy(xpath = ".//div[@class='message']/img[@class='error_icon']")
  public WebElementFacade msjAdvertenciaRelacionAsegurado;

  @FindBy(xpath = ".//td[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_BasicInfoScreen:PanelRow:BasicInfoDetailViewPanelDV:Claim_ReportedByType-inputCell']/input")
  public WebElementFacade txtRelacionAsegurado;

  @FindBy(xpath = "//input[@id = 'FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:ContactEFTFNOLInputSet:AuthorizationPaymentBankTransfer_true-inputEl']")
  public WebElementFacade rbtPagoTransferenciaSi;

  @FindBy(xpath = "//input[@id = 'FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:ContactEFTFNOLInputSet:AuthorizationPaymentBankTransfer_false-inputEl']")
  public WebElementFacade rbtPagoTransferenciaNo;

  @FindBy(
          id =
                  "FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:ContactEFTFNOLInputSet:EFTInformation:ContactEFTFNOLLV_tb:ToolbarButton-btnWrap")
  public WebElementFacade btnNuevaCuenta;

  @FindBy(id = "FNOLWizard:ClaimInfoBar:Insured-btnInnerEl")
  public WebElementFacade obtenerNombreAsegurado;
  //TODO: REVISAR MOVER - HANSEE
  public void elegirNombreRelacionAutor(String nombreRelacion) {
    By lstNombres = By.xpath("//li[contains(text(),'" + nombreRelacion + "')]");
    getDriver().findElement(lstNombres).click();
  }

  public void elegirLesionado(String nombreLesionado) {
    String modificarNombre = nombreLesionado.substring(nombreLesionado.length() - 26);
    By lstOpciones = By.xpath("//li[contains(text(),'" + modificarNombre + "')]");
    getDriver().findElement(lstOpciones).click();
  }
  public void indicarCobertura(String cobertura) {
    By elegirCobertura =
            By.xpath(
                    "//table/tbody/tr/td[2]/div[contains(.,'"
                            + cobertura
                            + "')]/ancestor::td/div/input[@type='button']");
    getDriver().findElement(elegirCobertura).click();
  }
  public void elegirCuenta(String numCuenta) {
    By btnSeleccionarBanco =
            By.xpath(
                    "//tbody/tr/td[4][contains(.,'"
                            + numCuenta
                            + "')]/ancestor::tr[1]//div/a[text()='Seleccionar']");
    getDriver().findElement(btnSeleccionarBanco).click();
  }
  @FindBy(xpath = "//input[@id='FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:ReportedBy_Name-inputEl']")
  public WebElementFacade txtNombreAutorSiniestroRapido;

  @FindBy(xpath = "//textarea[@id='FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:Claim_Description-inputEl']")
  public WebElementFacade txtDetalleHechosSiniestroRapido;

  @FindBy(xpath = "//input[@id='FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:Claim_ReportedByType-inputEl']")
  public WebElementFacade lstRelacionAsegurado;

  @FindBy(id = "FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:pretensionvalue-inputEl")
  public WebElementFacade txtValorPretension;
}
