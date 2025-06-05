package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NuevoIncidenteVehicularPage extends GeneralPage {

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:Vehicle_LicensePlate-inputEl")
  public WebElementFacade txtPlacaVehiculo;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:fasecolda-btnInnerEl")
  public WebElementFacade btnRecuperarInformacion;

  @FindBy(
      xpath =
          "//input[@id='NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:Driver_Picker-inputEl'][contains(@class,'x-form-field x-form-text')]")
  public WebElementFacade cmbNombreConductor;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:OtherServicesLVInputGroupInputSet:OtherServicesInputGroup:_checkbox")
  public WebElementFacade chkServicioTaller;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:OtherServicesLVInputGroupInputSet:OtherServicesInputGroup:OtherServicesLVInputSet:OtherServicesLV_tb:AddAutoRepairShopServiceRequest-btnInnerEl")
  public WebElementFacade btnAgregarTaller;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:fasecoldaPopup-btnInnerEl")
  public WebElementFacade btnGenerarCodigoFasecolda;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:CCAddressInputSet:globalAddressContainer:Address_Picker-inputEl")
  public WebElementFacade cmbLugar;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:Country-inputEl")
  public WebElementFacade cmbPais;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:State2-inputEl")
  public WebElementFacade cmbDepartamento;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:Sura_Colombian_City2-inputEl")
  public WebElementFacade cmbCiudad;

  @FindBy(
      id =
          "NewVehicleIncidentPopup:NewVehicleIncidentScreen:FNOLSuraVehIncidentDetailDV:FNOLSuraVehicleIncidentDV:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:AddressLine1-inputEl")
  public WebElementFacade cmbDireccion;
}
