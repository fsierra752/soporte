package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CrearCuentaBancariaPage extends GeneralPage {

  public static final String ID_TXT_NOMBRE_BANCO ="ContactEFTEditPopup:BankName-inputEl";
  public static final String ID_TXT_TIPO_CUENTA ="ContactEFTEditPopup:AccountType-inputEl";

  @FindBy(id = "ContactEFTEditPopup:accountname-inputEl")
  public WebElementFacade txtTitularCuenta;

  @FindBy(id = "ContactEFTEditPopup:AccountNumber-inputEl")
  public WebElementFacade txtNumeroCuenta;
}
