package com.variada.pages.mapeos.guidewire.claimscenter.empresariales;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AuditoriaPage extends GeneralPage {

  @FindBy(xpath = "//span[@id='SIDetails:SIDetailsScreen:Edit-btnInnerEl']")
  public WebElementFacade btnEditarProcesoAuditoria;

  @FindBy(
      id =
          "SIDetails:SIDetailsScreen:SIDetailsDV:SITotalScoreEscalationInputSet:SIinfo_SIescalateSIU-inputEl")
  public WebElementFacade cmbRequiereAuditoria;

  @FindBy(id = "SIDetails:SIDetailsScreen:Update-btnInnerEl")
  public WebElementFacade btnActualizar;

  @FindBy(xpath = "//span//img[@src='images/app/indicator_icon_siu.png']")
  public WebElementFacade imgAuditoria;

  @FindBy(xpath = "//div[contains(text(),'Elementos de línea : Para realizar el pago')]")
  public WebElementFacade msgRechazoPago;

  @FindBy(
      name =
          "SIDetails:SIDetailsScreen:SIDetailsDV:SITotalScoreEscalationInputSet:SIinfo_SIEscalateSIUNote")
  public WebElementFacade txtAreaComentario;
}
