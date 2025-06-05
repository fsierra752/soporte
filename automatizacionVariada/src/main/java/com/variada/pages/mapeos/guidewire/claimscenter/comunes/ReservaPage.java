package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ReservaPage extends GeneralPage {

  @FindBy(xpath = "//img[@class='x-grid-checkcolumn']")
  public WebElementFacade chkLineaReserva;

  @FindBy(xpath = "//span[@id='NewReserveSet:NewReserveSetScreen:Remove-btnInnerEl']")
  public WebElementFacade btnQuitarLineaReserva;

  @FindBy(id = "NewReserveSet:NewReserveSetScreen:ReservesSummaryDV:EditableReservesLV")
  public WebElementFacade tblLineaReserva;

  @FindBy(xpath = "//span[@id='NewReserveSet:NewReserveSetScreen:Update-btnInnerEl']")
  public WebElementFacade btnGuardarAjusteReserva;

  @FindBy(xpath = "//ul[@class='x-list-plain']")
  public WebElementFacade cmbExposicion;
}
