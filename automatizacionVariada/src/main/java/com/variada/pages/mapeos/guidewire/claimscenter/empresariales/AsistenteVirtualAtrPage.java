package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AsistenteVirtualAtrPage extends GeneralPage {

  @FindBy(xpath = "//img[@title='Asistente Virtual']")
  public WebElementFacade bntAsistenteVirtual;

  @FindBy(id = "slbProducto")
  public WebElementFacade lstProducto;

  @FindBy(xpath = "//option[contains(text(),'OTROS PRODUCTOS')]")
  public WebElementFacade mnuOtroProducto;

  @FindBy(xpath = "//img[@src='images/Bot_Aceptar.jpg']")
  public WebElementFacade btnAceptar;

  @FindBy(xpath = "//div[contains(.,'Bienvenido al Tour')]/a[2]")
  public WebElementFacade btnCerrarTour;

  @FindBy(xpath = "//div[contains(text(),'Expediente creado')]")
  public WebElementFacade lblTituloExpedienteCreado;
}
