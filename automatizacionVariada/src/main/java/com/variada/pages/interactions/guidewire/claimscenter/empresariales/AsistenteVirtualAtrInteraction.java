package com.variada.pages.interactions.guidewire.claimscenter.empresariales;

import static com.variada.utils.constantes.ReclamacionConstante.ASISTENTE_VIRTUAL;
import static com.variada.utils.constantes.ReclamacionConstante.HERRAMIENTAS;
import static com.variada.utils.constantes.ReclamacionConstante.RECLAMACIONES;
import static com.variada.utils.constantes.ReclamacionConstante.EMPRESAS;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.empresariales.AsistenteVirtualAtrPage;

import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class AsistenteVirtualAtrInteraction extends GeneralInteraction {

  @Page AsistenteVirtualAtrPage asistenteVirtualAtrPage;

  public AsistenteVirtualAtrInteraction(WebDriver driver) {
    super(driver);
  }

  public void accederAsistenteVirtual() {
    asistenteVirtualAtrPage.bntAsistenteVirtual.waitUntilVisible().click();
    enfocarVistaAutomatizacion();
  }

  public void accederAvisoEmpresa() {
    if (asistenteVirtualAtrPage.btnCerrarTour.isVisible()) {
      asistenteVirtualAtrPage.btnCerrarTour.click();
    }
    navegarMenu(ASISTENTE_VIRTUAL, asistenteVirtualAtrPage.mnuAsistenteVirtual);
    navegarMenu(HERRAMIENTAS, asistenteVirtualAtrPage.mnuAsistenteVirtual);
    navegarMenu(RECLAMACIONES, asistenteVirtualAtrPage.mnuAsistenteVirtual);
    navegarMenu(EMPRESAS, asistenteVirtualAtrPage.mnuAsistenteVirtual);
  }

  public void seleccionarPlanListaProducto() {
    enfocarVistaAutomatizacion();
    asistenteVirtualAtrPage.lstProducto.waitUntilVisible().click();
    asistenteVirtualAtrPage.mnuOtroProducto.waitUntilVisible().click();
    asistenteVirtualAtrPage.btnAceptar.waitUntilVisible().click();
  }

  public String obtenerTituloExpedienteCreado() {
    return asistenteVirtualAtrPage
        .lblTituloExpedienteCreado
        .waitUntilPresent()
        .waitUntilVisible()
        .getText();
  }
}
