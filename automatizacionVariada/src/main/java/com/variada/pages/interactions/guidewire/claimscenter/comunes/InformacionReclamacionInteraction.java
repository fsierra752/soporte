package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionReclamacionPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class InformacionReclamacionInteraction extends GeneralInteraction {

  @Page InformacionReclamacionPage informacionReclamacionPage;

  public InformacionReclamacionInteraction(WebDriver driver) {
    super(driver);
  }

  public void cerrarReclamosDuplicados() {
    if (informacionReclamacionPage.btnCerrar.isVisible()) {
      informacionReclamacionPage.btnCerrar.waitUntilClickable();
      informacionReclamacionPage.btnCerrar.click();
      realizarEsperaCarga();
    }
  }

  public void seleccionarCausaSiniestro(String causa) {
    informacionReclamacionPage.mnuCausa.waitUntilPresent().click();
    seleccionarOpcionLista(informacionReclamacionPage.lstCausasSiniestroClaim, causa);
    realizarEsperaCarga();
  }

  public void escribirValorPretension(String valor) {
    informacionReclamacionPage.txtValorPretension.waitUntilVisible();
    informacionReclamacionPage.txtValorPretension.sendKeys(valor);
  }

  public void seleccionarTipoIncidente(String tipoIncidente) {
    if ("Propiedad".equalsIgnoreCase(tipoIncidente)) {
      informacionReclamacionPage.btnIncidentePropiedad.waitUntilClickable();
      informacionReclamacionPage.btnIncidentePropiedad.click();
      informacionReclamacionPage.btnAceptarIncidentePropiedad.waitUntilVisible();
      informacionReclamacionPage.btnAceptarIncidentePropiedad.click();
      realizarEsperaCarga();
    }
    if ("Contenido".equalsIgnoreCase(tipoIncidente)) {
      informacionReclamacionPage.btnIncidenteContenido.waitUntilClickable();
      informacionReclamacionPage.btnIncidenteContenido.click();
      informacionReclamacionPage.btnAceptarIncidenteContenido.waitUntilVisible();
      informacionReclamacionPage.btnAceptarIncidenteContenido.click();
      realizarEsperaCarga();
    }
    if ("Lesiones".equalsIgnoreCase(tipoIncidente)) {
      informacionReclamacionPage.btnIncidenteLesiones.waitUntilClickable();
      informacionReclamacionPage.btnIncidenteLesiones.click();
      informacionReclamacionPage.btnIncidenteLesiones.waitUntilVisible();
      informacionReclamacionPage.btnIncidenteLesiones.click();
      realizarEsperaCarga();
    }
  }

  public void finalizarSiniestro() {
    informacionReclamacionPage.btnFinalizar.waitUntilVisible().click();
    realizarTiempoEsperaCarga();
  }

  public String obtenerTituloReclamacionGenerada() {
    return informacionReclamacionPage.lblNuevaReclamacion.waitUntilVisible().getText();
  }

  public void seleccionarCausaSiniestroAtr(String causa) {
    informacionReclamacionPage.txtCausaSiniestroAtr.waitUntilPresent().waitUntilClickable().click();
    seleccionarOpcionTabla(informacionReclamacionPage.tblCausaSiniestroAtr, causa);
  }

  public void diligenciarDetalleHechosAtr(String detalleHechos) {
    informacionReclamacionPage.txtDetalleHechosSiniestroAtr.waitUntilVisible().type(detalleHechos);
  }

  public void seleccionarCiudadSiniestro() {
    String ciudad = informacionReclamacionPage.lblNombreCiudad.waitUntilVisible().getText();
    informacionReclamacionPage.txtCiudadSiniestro.waitUntilVisible().typeAndEnter(ciudad);
  }

  public void ingresarValorPretensionAtr(String valorPretension) {
    informacionReclamacionPage.txtValorPretensionAtr.waitUntilVisible().type(valorPretension);
  }

  public void enviarReclamacion() {
    informacionReclamacionPage.btnEnviarReclamacion.waitUntilVisible().click();
  }

  public String obtenerNumeroSiniestroAtr() {
    String numeroSiniestro =
        informacionReclamacionPage.lblNumeroSiniestroAtr.waitUntilVisible().getText();
    cerrarNavegador();
    return numeroSiniestro;
  }
}