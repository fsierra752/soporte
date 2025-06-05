package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DetalleVehiculoPage extends GeneralPage {

  @FindBy(
      xpath =
          "//span[@class='x-btn-button']/span[@class='x-btn-inner x-btn-inner-center' and contains(.,'conductor')]")
  public WebElementFacade btnAgregarConductor;

  @FindBy(
      xpath =
          "//input[@id='FNOLContactPopup:FNOLContactScreen:ContactDV:ClaimContactPerson-inputEl']/../following-sibling::td")
  public WebElementFacade cmbPersona;

  @FindBy(
      xpath =
          "//div[contains(@class,'x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box')]//ul/li/following-sibling::li")
  public WebElementFacade lstNombrePersona;

  @FindBy(xpath = "//input[@class='x-form-field x-form-checkbox x-form-cb']")
  public WebElementFacade chkServicioTaller;

  @FindBy(
      xpath = "//span[@class='x-btn-inner x-btn-inner-center' and contains(.,'Agregar Taller')]")
  public WebElementFacade btnAgregarTaller;

  @FindBy(id = "OtherServiceRequestPopup:NewServiceRequestDV:btnSearchProvider-btnInnerEl")
  public WebElementFacade btnBuscarProveedor;

  @FindBy(
      id =
          "FNOLVehicleIncidentPopup:FNOLVehicleIncidentScreen:VehicleDetailInputSet:Vehicle_LicensePlate-inputEl")
  public WebElementFacade txtPlaca;

  @FindBy(
      id =
          "FNOLVehicleIncidentPopup:FNOLVehicleIncidentScreen:VehicleDetailInputSet:fasecoldaGet-btnInnerEl")
  public WebElementFacade btnRecuperarInformacion;
}
