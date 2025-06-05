package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InformacionGeneralNuevaExposicionPage extends GeneralPage {

  @FindBy(
      xpath =
          "//td[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:DocumentType_Ext-inputCell']//input")
  public WebElementFacade cmbTipoDocumento;

  @FindBy(xpath = "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:TaxID-inputEl']")
  public WebElementFacade txtNumeroDocumento;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:FNOLContactInputSet:GlobalPersonNameInputSet:FirstName-inputEl']")
  public WebElementFacade txtPrimerNombre;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:FNOLContactInputSet:GlobalPersonNameInputSet:LastName-inputEl']")
  public WebElementFacade txtPrimerApellido;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:FNOLContactInputSet:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:AddressLine1-inputEl']")
  public WebElementFacade txtDireccion;

  public String cmbCiudad = "City2-inputEl";
}
