package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.TIPO_POLIZA_AUTOS;
import static com.variada.utils.enums.EnumConstantes.COMODIN;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.BusquedaPolizaPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class BusquedaPolizaInteraction extends GeneralInteraction {

  @Page
  BusquedaPolizaPage busquedaPolizaPage;

  private String auxTipoDocumento = "";

  public BusquedaPolizaInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarOpcionBuscarPoliza() {
    if (busquedaPolizaPage.rbtBuscarPoliza.isVisible()) {
      busquedaPolizaPage.rbtBuscarPoliza.click();
    }
  }

  public void escribirNumeroPoliza(String numeroPoliza) {
    busquedaPolizaPage.txtNumeroPoliza.waitUntilClickable();
    busquedaPolizaPage.txtNumeroPoliza.type(numeroPoliza);
  }

  public void seleccionarFechaHoySiniestro() {
    busquedaPolizaPage.mnuFechaSiniestro.waitUntilVisible();
    busquedaPolizaPage.mnuFechaSiniestro.click();
    busquedaPolizaPage.btnFechaHoy.waitUntilVisible();
    busquedaPolizaPage.btnFechaHoy.click();
  }

  public void escribirFechaSiniestro(String fecha) {
    realizarEsperaCarga();
    busquedaPolizaPage.txtFecha.click();
    busquedaPolizaPage.txtFecha.type(fecha);
  }

  public void buscarPoliza() {
    busquedaPolizaPage.btnBuscar.waitUntilPresent().waitUntilVisible().waitUntilClickable().click();
    realizarEsperaCarga();
  }

  public void seleccionarTipoPoliza(String tipoPoliza) {
    if(!busquedaPolizaPage.lblTipoPoliza.getText().equalsIgnoreCase(TIPO_POLIZA_AUTOS.getValor())){
      busquedaPolizaPage.mnuTipoPoliza.waitUntilVisible();
      busquedaPolizaPage.mnuTipoPoliza.click();
      auxTipoDocumento = busquedaPolizaPage.itemListado.replace(COMODIN.getValor(), tipoPoliza);
      $(auxTipoDocumento).click();
      realizarEsperaCarga();
    }
  }

  @Override
  public void seleccionarPais(String strPais) {
    seleccionarElementoListado(busquedaPolizaPage.cmbPais, strPais);
  }

  public void seleccionarPoliza() {
    if (busquedaPolizaPage.btnPoliza.isVisible()) {
      busquedaPolizaPage.btnPoliza.waitUntilClickable();
      busquedaPolizaPage.btnPoliza.click();
      realizarEsperaCarga();
    }
  }

  public void seleccionarDocumentoAseguradoAtr(String tipoDocumentoAtr) {
    String lstTipoDocumentoAtr = "//option[contains(text(),'COMODIN')]";
    busquedaPolizaPage
        .txtTipoDocumentoAsegurado
        .waitUntilPresent()
        .waitUntilVisible()
        .waitUntilClickable()
        .click();
    navegarMenu(tipoDocumentoAtr, lstTipoDocumentoAtr);
  }

  public void digitarDocumentoAseguradoAtr(String numDocumentoAtr) {
    busquedaPolizaPage.txtNumeroDocumentoAtr.waitUntilVisible().type(numDocumentoAtr);
  }

  public void consultarDocumentoAseguradoAtr() {
    busquedaPolizaPage.btnConsultarDatosAseguradoATR.waitUntilVisible().click();
  }

  public void consultarPolizaAseguradoAtr() {
    busquedaPolizaPage.btnConsultarPolizaAtr.waitUntilVisible().click();
    realizarEsperaCarga();
  }

  public void seleccionarPolizaAtr() {
    busquedaPolizaPage.rbtPolizaAtr.waitUntilVisible().click();
    realizarEsperaCarga();
  }

  public void seleccionarRiegoPolizaAtr() {
    busquedaPolizaPage.rbtRiesgoPolizaAtr.waitUntilVisible().click();
  }

  public void escribirNumeroDocumento(String numDocumento) {
    busquedaPolizaPage.txtNumeroDocumento.waitUntilVisible();
    busquedaPolizaPage.txtNumeroDocumento.type(numDocumento);
  }

  public void seleccionarTipoDocumento(String tipoDocumento) {
    busquedaPolizaPage.mnuTipoDocumento.waitUntilVisible();
    busquedaPolizaPage.mnuTipoDocumento.click();
    auxTipoDocumento = busquedaPolizaPage.itemListado.replace(COMODIN.getValor(), tipoDocumento);
    $(auxTipoDocumento).click();
  }

  public void seleccionarPolizaAsegurado(String numeroPoliza) {
    realizarEsperaCarga();
    if (busquedaPolizaPage.btnDeseleccionar.isVisible()) {
      busquedaPolizaPage.btnSiguiente.click();
    } else {
      btnDinamicoSeleccionar(numeroPoliza);
      realizarEsperaCarga();
      busquedaPolizaPage.btnSiguiente.click();
    }
  }

  private void btnDinamicoSeleccionar(String numeroPoliza) {
    org.openqa.selenium.By celdaPoliza =
        org.openqa.selenium.By.xpath(
            "//tbody/tr/td[3][contains(.,'"
                + numeroPoliza
                + "')]/ancestor::tr[1]//div/a[text()='Seleccionar']");
    int paginacion = Integer.parseInt(busquedaPolizaPage.txtNumeroPaginas.getText().replace("de ", ""));
    for (int i = 0; i <= paginacion; i++) {
      try {
        if (getDriver().findElement(celdaPoliza).isEnabled()) {
          getDriver().findElement(celdaPoliza).click();
          break;
        }
      } catch (NoSuchElementException elementException) {
        busquedaPolizaPage.btnSiguientePagina.click();
      }
    }
  }
}
