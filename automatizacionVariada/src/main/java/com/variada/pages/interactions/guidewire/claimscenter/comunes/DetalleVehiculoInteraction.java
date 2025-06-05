package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.DetalleVehiculoPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class DetalleVehiculoInteraction extends GeneralInteraction {

  @Page DetalleVehiculoPage detalleVehiculoPage;

  public DetalleVehiculoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void agregarConductor() {
    detalleVehiculoPage.btnAgregarConductor.waitUntilVisible().waitUntilClickable().click();
  }

  public void seleccionarConductorVehiculoAsegurado() {
    seleccionarConductor();
  }

  private void seleccionarConductor() {
    detalleVehiculoPage.cmbPersona.waitUntilVisible().click();
    detalleVehiculoPage.lstNombrePersona.click();
    realizarEsperaCarga();
    aceptarOpcion();
    realizarEsperaCarga();
  }

  public void buscarProveedor() {
    esperarCargaElemento();
    detalleVehiculoPage.btnBuscarProveedor.waitUntilVisible().click();
    realizarEsperaCarga();
  }

  public void ingresarVehiculoTercero(String placa) {
    detalleVehiculoPage.txtPlaca.sendKeys(placa);
    realizarEsperaCarga();
  }

  public void recuperarInformacionVehiculo() {
    detalleVehiculoPage.btnRecuperarInformacion.waitUntilVisible().click();
    realizarEsperaCarga();
  }

  public void agregarTaller() {
    esperarCargaElemento();
    detalleVehiculoPage.btnAgregarTaller.waitUntilVisible().click();
  }

  public void seleccionarServicioTaller() {
    esperarCargaElemento();
    detalleVehiculoPage.chkServicioTaller.waitUntilVisible().click();
  }

  public void volverPasoAnterior() {
    esperarCargaElemento();
    aceptarOpcion();
    realizarEsperaCarga();
  }
}
