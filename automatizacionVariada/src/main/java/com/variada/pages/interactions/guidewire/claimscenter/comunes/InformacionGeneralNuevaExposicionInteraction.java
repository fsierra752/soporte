package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionGeneralNuevaExposicionPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class InformacionGeneralNuevaExposicionInteraction extends GeneralInteraction {

  @Page InformacionGeneralNuevaExposicionPage informacionGeneralNuevaExposicionPage;

  public InformacionGeneralNuevaExposicionInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void seleccionarTipoDocumento(String tipoDocumento) {
    informacionGeneralNuevaExposicionPage.cmbTipoDocumento.clear();
    informacionGeneralNuevaExposicionPage.cmbTipoDocumento.sendKeys(tipoDocumento);
    informacionGeneralNuevaExposicionPage.cmbTipoDocumento.sendKeys(Keys.ENTER);
    realizarEsperaCarga();
  }

  public void ingresarNumeroDocumento(String numDocumento) {
    informacionGeneralNuevaExposicionPage.txtNumeroDocumento.sendKeys(numDocumento);
  }

  public void ingresarPrimerNombre(String primerNombre) {
    informacionGeneralNuevaExposicionPage.txtPrimerNombre.sendKeys(primerNombre);
  }

  public void ingresarPrimerApellido(String primerApellido) {
    informacionGeneralNuevaExposicionPage.txtPrimerApellido.sendKeys(primerApellido);
    realizarEsperaCarga();
  }

  public void seleccionarDepartamento(String strDepartamento) {
    seleccionarElementoListado(informacionGeneralNuevaExposicionPage.cmbDepartamento2, strDepartamento);
    realizarEsperaCarga();
  }

  public void seleccionarCiudad(String strCiudad) {
    seleccionarElementoListado(informacionGeneralNuevaExposicionPage.cmbCiudad, strCiudad);
    realizarEsperaCarga();
  }

  public void ingresarDireccion(String direccion) {
    informacionGeneralNuevaExposicionPage.txtDireccion.sendKeys(direccion);
    realizarEsperaCarga();
  }

  public void seleccionarTipoDireccion(String strTipoDireccion) {
    seleccionarElementoListado(informacionGeneralNuevaExposicionPage.cmbTipoDireccion, strTipoDireccion);
    realizarEsperaCarga();
  }
}