package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class BusquedaLibretaContactoPage extends GeneralPage {

  @FindBy(
      id =
          "AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchDV:ContactSubtype-inputEl")
  public WebElementFacade cmbTipoContacto;

  @FindBy(xpath = "//ul[@class='x-list-plain']")
  public WebElementFacade lstTipoContacto;

  @FindBy(
      id =
          "AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchDV:NameInputSet:GlobalContactNameInputSet:Name-inputEl")
  public WebElementFacade txtNombreContacto;

  @FindBy(
      id =
          "AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchDV:SearchAndResetInputSet:SearchLinksInputSet:Search")
  public WebElementFacade btnBuscarContacto;

  @FindBy(id = "AddressBookPickerPopup:AddressBookSearchScreen:AddressBookSearchLV")
  public WebElementFacade tblResultadoBusquedaContacto;
}
