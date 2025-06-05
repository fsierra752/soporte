package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionSiniestroPage;
import java.util.concurrent.TimeUnit;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class InformacionSiniestroInteraction extends GeneralInteraction {

  @Page
  InformacionSiniestroPage informacionSiniestroPage;

  public InformacionSiniestroInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void cerrarVentanaEmergente() {
    if (informacionSiniestroPage.btnCerrarVentanaEmergente.isVisible()) {
      informacionSiniestroPage.btnCerrarVentanaEmergente.waitUntilVisible();
      informacionSiniestroPage.btnCerrarVentanaEmergente.click();
      realizarEsperaCarga();
    }
  }

  public void escribirSucedido(String sucedido) {
    informacionSiniestroPage.txtDescripcionHechos.type(sucedido);
    informacionSiniestroPage.cmbCausaSiniestro.sendKeys(Keys.TAB);
    realizarEsperaCarga();
  }

  public void seleccionarCausa(String causa) {
    informacionSiniestroPage.cmbCausaSiniestro.type(causa);
    informacionSiniestroPage.cmbCausaSiniestro.sendKeys(Keys.TAB);
    realizarEsperaCarga();
  }

  public void seleccionarOrigen(String origen) {
    informacionSiniestroPage.cmbOrigenSiniestro.type(origen);
    informacionSiniestroPage.cmbOrigenSiniestro.sendKeys(Keys.ENTER);
  }

  public void escribirValorPretension(String valorPretension) {
    informacionSiniestroPage.txtPretension.withTimeoutOf(5, TimeUnit.SECONDS).click();
    informacionSiniestroPage.txtPretension.sendKeys(valorPretension);
  }

  public void seleccionarIntervinoAutoridad(String autoridad) {
    informacionSiniestroPage.txtIntervinoAutoridad.type(autoridad);
    informacionSiniestroPage.txtIntervinoAutoridad.sendKeys(Keys.ENTER);
    realizarEsperaCarga();
  }

  public void seleccionarCulpabilidad(String culpabilidad) {
    informacionSiniestroPage.cmbCulpabilidad.type(culpabilidad);
    informacionSiniestroPage.cmbCulpabilidad.sendKeys(Keys.ENTER);
  }

  public void agregarExposicionVehiculoTercero() {
    informacionSiniestroPage.btnAgregarVehiculo.waitUntilVisible().waitUntilClickable().click();
  }

  public void seleccionarLugar(String lugar) {
    informacionSiniestroPage.cmbLugar.waitUntilClickable().click();
    seleccionarOpcionCombobox(lugar);
    realizarEsperaCarga();
  }

  public void ingresarEdicionVehiculo() {
    informacionSiniestroPage.btnAbajoVehiculo.click();
    informacionSiniestroPage.btnEditarVehiculo.click();
    realizarEsperaCarga();
  }

  public void concluirReclamacion() {
    informacionSiniestroPage.btnFinalizar.waitUntilVisible();
    informacionSiniestroPage.btnFinalizar.click();
    realizarTiempoEsperaCarga();
  }
}
