package com.variada.pages.mapeos.guidewire.claimscenter.autos;

import com.variada.pages.mapeos.general.GeneralPage;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProcesoBatchPage extends GeneralPage {

  @FindBy(id = "QuickJump-inputEl")
  public WebElementFacade txtBuscar;

  @FindBy(id = "BatchProcessInfo:BatchProcessScreen:BatchProcessesLV")
  public WebElementFacade tblNombreProcesoBatch;

  @FindBy(id = "ServerTools:InternalToolsMenuActions")
  public WebElementFacade mnuAcciones;

  @FindBy(xpath = "//div[contains(@class, 'x-box-inner x-vertical-box-overflow-body')]")
  public List<WebElementFacade> mnuPanelOpcionesPrimerNivel;
}
