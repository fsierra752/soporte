package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AutenticacionAtrPage extends GeneralPage {

  @FindBy(id = "tempUserID")
  public WebElementFacade txtUsuario;

  @FindBy(id = "autenticacionUsuario:tipoDocUsuario")
  public WebElementFacade lstTipoDocumento;

  @FindBy(id = "autenticacionUsuario:claveUsuario")
  public WebElementFacade txtClave;

  @FindBy(id = "medidasSeg:aceptar")
  public WebElementFacade btnAceptarCondicionUso;

  @FindBy(xpath = "//option[@value='C']")
  public WebElementFacade lstCedula;

  @FindBy(xpath = "//img[@src='/SSAutenticacion/imagenes/btnIngresar.jpg']")
  public WebElementFacade btnIngresar;

  public String btnClave =
          "//img[@src='/SSAutenticacion/imagenes/teclado/btnTec_COMODIN_off.gif']";
}
