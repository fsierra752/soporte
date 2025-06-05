package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DatosPeatonPage extends GeneralPage {

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:InjuredBoolean_true-inputEl']")
  public WebElementFacade chkLesiones;

  @FindBy(
      xpath =
          "//span[@id='FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:AddPedestrianButton-btnInnerEl']")
  public WebElementFacade btnAgregarPeaton;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:InjuryIncidentInputSet:Severity-inputEl']")
  public WebElementFacade cmbGravedadLesion;

  @FindBy(
      xpath =
          "//textarea[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:InjuryIncidentInputSet:InjuryDescription-inputEl']")
  public WebElementFacade txtDescribirLesiones;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:InjuryIncidentInputSet:PrimaryInjuryType-inputEl']")
  public WebElementFacade cmbTipoLesion;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:InjuryIncidentInputSet:DetailedInjuryType-inputEl']")
  public WebElementFacade cmbDetalleLesion;
}
