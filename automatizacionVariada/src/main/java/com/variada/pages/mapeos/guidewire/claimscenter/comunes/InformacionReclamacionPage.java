package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InformacionReclamacionPage extends GeneralPage {

  @FindBy(
      xpath =
          "//span[@id='NewClaimDuplicatesWorksheet:NewClaimDuplicatesScreen:NewClaimDuplicatesWorksheet_CloseButton-btnInnerEl']")
  public WebElementFacade btnCerrar;

  @FindBy(
      xpath =
          "//input[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_LossDetailsScreen:NewClaimLossDetailsDV:LossEstimate-inputEl']")
  public WebElementFacade txtValorPretension;

  @FindBy(
      xpath =
          "//span[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_LossDetailsScreen:NewClaimLossDetailsDV:EditableFixedPropertyIncidentsLV_tb:Add-btnInnerEl']")
  public WebElementFacade btnIncidentePropiedad;

  @FindBy(
      xpath =
          "//span[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_LossDetailsScreen:NewClaimLossDetailsDV:FNOLSuraEditableContentPropertyIncidentsLV_tb:Add-btnInnerEl']")
  public WebElementFacade btnIncidenteContenido;

  @FindBy(
      xpath =
          "//span[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_LossDetailsScreen:NewClaimLossDetailsDV:EditableInjuryIncidentsLV_tb:Add-btnInnerEl']")
  public WebElementFacade btnIncidenteLesiones;

  @FindBy(
      xpath =
          "//span[.='Aceptar']/span[@id='NewFixedPropertyIncidentPopup:NewFixedPropertyIncidentScreen:Update-btnInnerEl']")
  public WebElementFacade btnAceptarIncidentePropiedad;

  @FindBy(
      xpath =
          "//span[.='Aceptar']/span[@id='NewPropertyContentsIncidentPopup:NewPropertyContentsIncidentScreen:Update-btnInnerEl']")
  public WebElementFacade btnAceptarIncidenteContenido;

  @FindBy(xpath = "//span[.='Finalizar']/span[@id='FNOLWizard:Finish-btnInnerEl']")
  public WebElementFacade btnFinalizar;

  @FindBy(xpath = "//span[@id='NewClaimSaved:NewClaimSavedScreen:ttlBar']")
  public WebElementFacade lblNuevaReclamacion;

  @FindBy(
      xpath =
          "//td[@id='FNOLWizard:GeneralPropertyWizardStepSet:NewClaimWizard_LossDetailsScreen:NewClaimLossDetailsDV:Claim_LossCause-inputCell']")
  public WebElementFacade mnuCausa;

  @FindBy(xpath = "//div[@id='causaSiniestroInformacionSiniestroEmp']/input")
  public WebElementFacade txtCausaSiniestroAtr;

  @FindBy(xpath = "//textarea[@id='detalleHechosInformacionSiniestroEmp']")
  public WebElementFacade txtDetalleHechosSiniestroAtr;

  @FindBy(xpath = "//div[2]//table[@class='sTablaContenedor']//tr[4]//td[2]/div")
  public WebElementFacade lblNombreCiudad;

  @FindBy(xpath = "//div[@id='ciudadesInformacionSiniestroEmp']//input")
  public WebElementFacade txtCiudadSiniestro;

  @FindBy(xpath = "//td[@class='GMMMP1-BMTC GMMMP1-BOTC GMMMP1-BJUC']//input")
  public WebElementFacade txtValorPretensionAtr;

  @FindBy(xpath = "//a[1][contains(.,'Enviar reclamación')]")
  public WebElementFacade btnEnviarReclamacion;

  @FindBy(xpath = "//div[@class='popupMiddleCenterInner popupContent']//tr[5]//div")
  public WebElementFacade lblNumeroSiniestroAtr;

  @FindBy(xpath = "//div[@class='suggestPopupMiddleCenterInner suggestPopupContent']")
  public WebElementFacade tblCausaSiniestroAtr;

  @FindBy(xpath = "//ul[@class='x-list-plain']")
  public WebElementFacade lstCausasSiniestroClaim;
}
