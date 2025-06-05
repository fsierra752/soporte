package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CalculadoraCodigoFasecoldaPage extends GeneralPage {

  @FindBy(id = "FNOLSuraFasecoldaCalculatorPopup:Vehicle_VehicleType-inputEl")
  public WebElementFacade cmbClaseVehiculo;

  @FindBy(id = "FNOLSuraFasecoldaCalculatorPopup:Vehicle_Year-inputEl")
  public WebElementFacade cmbModelo;

  @FindBy(id = "FNOLSuraFasecoldaCalculatorPopup:Vehicle_Make-inputEl")
  public WebElementFacade cmbMarca;

  @FindBy(id = "FNOLSuraFasecoldaCalculatorPopup:Vehicle_Model-inputEl")
  public WebElementFacade cmbLinea;

  @FindBy(id = "FNOLSuraFasecoldaCalculatorPopup:ClaimPolicyGeneral_CancelPolicy")
  public WebElementFacade btnValidarCodigoFasecolda;

  @FindBy(id = "FNOLSuraFasecoldaCalculatorPopup:Update")
  public WebElementFacade btnAceptar;
}
