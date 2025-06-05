package com.variada.pages.interactions.guidewire.claimscenter.autos;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.DatosPeatonPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DatosPeatonInteraction extends GeneralInteraction {

  @Page DatosPeatonPage datosPeatonPage;

  public DatosPeatonInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void agregarPersonaLesionada() {
    datosPeatonPage.btnAgregarPeaton.waitUntilVisible().click();
  }

  public void seleccionarLesiones() {
    datosPeatonPage.chkLesiones.waitUntilVisible().click();
    realizarEsperaCarga();
  }

  public void seleccionarGravedadLesion(String gravedadLesion) {
    datosPeatonPage.cmbGravedadLesion.clear();
    datosPeatonPage.cmbGravedadLesion.sendKeys(gravedadLesion);
    datosPeatonPage.cmbGravedadLesion.sendKeys(Keys.ENTER);
    realizarEsperaCarga();
  }

  public void ingresarDescripcionLesiones(String describirLesiones) {
    datosPeatonPage.txtDescribirLesiones.sendKeys(describirLesiones);
  }

  public void seleccionarTipoLesion(String tipoLesion) {
    datosPeatonPage.cmbTipoLesion.clear();
    datosPeatonPage.cmbTipoLesion.sendKeys(tipoLesion);
    datosPeatonPage.cmbTipoLesion.sendKeys(Keys.ENTER);
    realizarEsperaCarga();
  }

  public void seleccionarDetalleLesion(String detallesTipoLesion) {
    datosPeatonPage.cmbDetalleLesion.clear();
    datosPeatonPage.cmbDetalleLesion.sendKeys(detallesTipoLesion);
    datosPeatonPage.cmbDetalleLesion.sendKeys(Keys.ENTER);
    realizarEsperaCarga();
  }

  public void finalizarExposicion() {
    aceptarOpcion();
    realizarEsperaCarga();
  }
}
