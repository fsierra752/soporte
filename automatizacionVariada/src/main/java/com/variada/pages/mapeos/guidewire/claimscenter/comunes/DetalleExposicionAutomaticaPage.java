package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DetalleExposicionAutomaticaPage extends GeneralPage {

  @FindBy(
      id =
          "ExposureDetail:ExposureDetailScreen:ExposureDetailDV:SubView_AutoTotalLossCalculatorCardTab-btnInnerEl")
  public WebElementFacade lblCalculadoraPerdidaTotal;

  @FindBy(id = "ExposureDetail:ExposureDetailScreen:Edit-btnInnerEl")
  public WebElementFacade btnEditar;

  @FindBy(
      id =
          "ExposureDetail:ExposureDetailScreen:ExposureDetailDV:TotalLossCalculatorDV:FireBurnDash_true-inputEl")
  public WebElementFacade rbtIncineracionTotalVehiculo;

  @FindBy(
      id =
          "ExposureDetail:ExposureDetailScreen:ExposureDetailDV:TotalLossCalculatorDV:FireBurnEngine_true-inputEl")
  public WebElementFacade rbtMotorDestruidoFuego;

  @FindBy(
      id =
          "ExposureDetail:ExposureDetailScreen:ExposureDetailDV:TotalLossCalculatorDV:FireBurnWindshield_true-inputEl")
  public WebElementFacade rbtHabitaculoPasajerosIncineradoTotalmente;

  @FindBy(id = "ExposureDetail:ExposureDetailScreen:Update-btnInnerEl")
  public WebElementFacade btnActualizar;

  @FindBy(
      id =
          "ExposureDetail:ExposureDetailScreen:ExposureDetailDV:VehicleDamage_DetailsCardTab-btnInnerEl")
  public WebElementFacade lblDetallesExposicion;

  @FindBy(
      xpath =
          "//input[@id='ExposureDetail:ExposureDetailScreen:ExposureDetailDV:VehicleDamageDV:LegalStatus-inputEl']")
  public WebElementFacade cmbEstadoLegal;
}
