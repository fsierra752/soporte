package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class InformacionBeneficiarioPage extends GeneralPage {


  public String cmbCiudad = "Sura_Colombian_City-inputEl";

  public String cmbCiudadDos = "Sura_Colombian_City2-inputEl";

  @FindBy(
      xpath =
          "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:NewCheckPayeeDV:PrimaryPayee_Name-inputEl']")
  public WebElementFacade cmbNombreBeneficiario;

  @FindBy(
      xpath =
          "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:NewCheckPayeeDV:PrimaryPayee_Type-inputEl']")
  public WebElementFacade cmbTipoBeneficiario;

  @FindBy(xpath = "//input[contains(@class, 'x-form-field x-form-radio x-form-cb')]")
  public WebElementFacade rbtPago;

  @FindBy(
      xpath =
          "//div[@id='NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:NewCheckPayeeDV:contactEFTLVid:ContactEFTSAPCheckLV']")
  public WebElementFacade tblCuentaElectronica;

  @FindBy(xpath = "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:NewCheckPayeeDV:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:State-inputEl']")
  public WebElementFacade btnBuscarDepartamento;

  @FindBy(xpath = "//input[@id='NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:NewCheckPayeeDV:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:Sura_Colombian_City-inputEl']")
  public WebElementFacade btnBuscarCiudad;
}
