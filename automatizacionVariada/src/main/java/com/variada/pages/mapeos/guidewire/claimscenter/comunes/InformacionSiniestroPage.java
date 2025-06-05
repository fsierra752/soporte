package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InformacionSiniestroPage extends GeneralPage {

  @FindBy(
      id =
          "NewClaimDuplicatesWorksheet:NewClaimDuplicatesScreen:NewClaimDuplicatesWorksheet_CloseButton-btnEl")
  public WebElementFacade btnCerrarVentanaEmergente;

  @FindBy(
      xpath =
          ".//input[@role='textbox' and @id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsAddressDV:LossEstimate-inputEl']")
  public WebElementFacade txtPretension;

  @FindBy(xpath = ".//label[contains(.,'Causa')]/../following-sibling::td//input")
  public WebElementFacade cmbCausaSiniestro;

  @FindBy(
      xpath =
          "//input[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsAddressDV:OriginCause-inputEl']")
  public WebElementFacade cmbOrigenSiniestro;

  @FindBy(
      xpath =
          "//td[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:CategorizationDV:Notification_Fault-inputCell']//input")
  public WebElementFacade cmbCulpabilidad;

  @FindBy(
      xpath =
          "//td[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsAddressDV:AddressDetailInputSetRef:CCAddressInputSet:globalAddressContainer:Address_Picker-inputCell']/following-sibling::td")
  public WebElementFacade cmbLugar;

  @FindBy(xpath = "//td[@class='g-after-input-cell']/a/img")
  public WebElementFacade btnAbajoVehiculo;

  @FindBy(xpath = "//div/a/span[contains(.,'Editar')]")
  public WebElementFacade btnEditarVehiculo;

  @FindBy(
      xpath =
          ".//td[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsAddressDV:AuthorityTransit-inputCell']/input")
  public WebElementFacade txtIntervinoAutoridad;

  @FindBy(
      id =
          "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsAddressDV:Description-inputEl")
  public WebElementFacade txtDescripcionHechos;

  @FindBy(
      id =
          "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:AddVehicleButton-btnInnerEl")
  public WebElementFacade btnAgregarVehiculo;

  @FindBy(xpath = ".//span[@class='g-underlined' and contains(.,'F')]")
  public WebElementFacade btnFinalizar;
}
