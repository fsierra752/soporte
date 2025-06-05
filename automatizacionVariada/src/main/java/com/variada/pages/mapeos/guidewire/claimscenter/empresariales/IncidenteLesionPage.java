package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class IncidenteLesionPage extends PageObject {

    @FindBy(
            id =
                    "FNOLWizard:FNOLWizard_NewQuickClaimScreen:QuickClaimDV:EditableInjuryIncidentsLV_tb:Add-btnInnerEl")
    public WebElementFacade btnAgrearLesion;

    @FindBy(
            xpath =
                    "//input[@id='NewInjuryIncidentPopup:NewInjuryIncidentScreen:InjuryIncidentDV:Injured_Picker-inputEl']")
    public WebElementFacade txtPersonaLesionada;

    @FindBy(
            id =
                    "NewInjuryIncidentPopup:NewInjuryIncidentScreen:InjuryIncidentDV:InjuryIncidentInputSet:MedicalDiagnosisLVInput:MedicalDiagnosisLV_tb:Add-btnInnerEl")
    public WebElementFacade btnAgregarDiagnostico;

    @FindBy(xpath = "//tbody/tr/td[3][contains(.,'')]/preceding-sibling::td[1]/div")
    public WebElementFacade celdaCodigoCie;

    @FindBy(name = "ICDCode")
    public WebElementFacade txtCodigoCie;

    @FindBy(
            id =
                    "NewInjuryIncidentPopup:NewInjuryIncidentScreen:InjuryIncidentDV:InjuryIncidentInputSet:MedicalDiagnosisLVInput:MedicalDiagnosisLV:0:ICDCode:SelectICDCode")
    public WebElementFacade btnBuscadorCie;

    @FindBy(id = "ICDCodePopup:0:_Select")
    public WebElementFacade btnSeleccionarLesion;

    @FindBy(xpath = "//a[@id='NewInjuryIncidentPopup:NewInjuryIncidentScreen:Update']")
    public WebElementFacade btnAceptar;

    @FindBy(
            xpath =
                    "//input[@id='NewInjuryIncidentPopup:NewInjuryIncidentScreen:InjuryIncidentDV:InjuryIncidentInputSet:Severity-inputEl']")
    public WebElementFacade txtGravedad;
}
