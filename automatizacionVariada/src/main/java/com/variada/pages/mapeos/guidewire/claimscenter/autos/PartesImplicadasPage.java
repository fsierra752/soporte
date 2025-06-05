package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PartesImplicadasPage extends GeneralPage {

  @FindBy(
      xpath =
          "//div[@id='ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:"
              + "PeopleInvolvedDetailedLV']")
  public WebElementFacade tblPartesImplicadas;

  @FindBy(id = "Claim:MenuLinks:Claim_ClaimPartiesGroup")
  public WebElementFacade mnuPartesImplicadas;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV_tb:"
              + "SarlaftValidationToolbarButtonSet:SarlaftValidationToolbarButtons_SarlaftValidationButton")
  public WebElementFacade btnValidarSarlaft;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV_tb:"
              + "ContactDetailToolbarButtonSet:Edit-btnWrap")
  public WebElementFacade btnEditar;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV:"
              + "AdditionalInfoInputSet:IdentificationIssueDate_Ext-inputEl")
  public WebElementFacade txtFechaExpedicionDocumento;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV:"
              + "AdditionalInfoInputSet:CountryOfBirth_Ext-inputEl")
  public WebElementFacade cmbPaisNacimiento;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV_tb:"
              + "ContactDetailToolbarButtonSet:CustomUpdateButton2-btnEl")
  public WebElementFacade btnActualizar;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV:"
              + "BusinessContactInfoInputSet:Work:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl")
  public WebElementFacade txtNumeroTrabajo;

  @FindBy(
      id =
          "ClaimContacts:ClaimContactsScreen:PeopleInvolvedDetailedListDetail:ContactBasicsDV:"
              + "BusinessContactInfoInputSet:Work:GlobalPhoneInputSet:CountryCode-inputEl")
  public WebElementFacade cmbCodigoRegion;
}
