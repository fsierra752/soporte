package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.CalculadoraCodigoFasecoldaPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class CalculadoraCodigoFasecoldaInteraction extends GeneralInteraction {

  @Page CalculadoraCodigoFasecoldaPage calculadoraCodigoFasecoldaPage;

  public CalculadoraCodigoFasecoldaInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void diligenciarClaseVehiculo(String claseVehiculo) {
    calculadoraCodigoFasecoldaPage.cmbClaseVehiculo.clear();
    calculadoraCodigoFasecoldaPage.cmbClaseVehiculo.typeAndTab(claseVehiculo);
    realizarEsperaCarga();
  }

  public void diligenciarModeloVehiculo(String modeloVehiculo) {
    calculadoraCodigoFasecoldaPage.cmbModelo.clear();
    calculadoraCodigoFasecoldaPage.cmbModelo.typeAndTab(modeloVehiculo);
    realizarEsperaCarga();
  }

  public void diligenciarMarcaVehiculo(String marcaVehiculo) {
    calculadoraCodigoFasecoldaPage.cmbMarca.clear();
    calculadoraCodigoFasecoldaPage.cmbMarca.typeAndTab(marcaVehiculo);
    realizarEsperaCarga();
  }

  public void diligenciarLineaVehiculo(String lineaVehiculo) {
    calculadoraCodigoFasecoldaPage.cmbLinea.clear();
    calculadoraCodigoFasecoldaPage.cmbLinea.typeAndTab(lineaVehiculo);
    realizarEsperaCarga();
  }

  public void generarCodigoFasecolda() {
    calculadoraCodigoFasecoldaPage.btnValidarCodigoFasecolda.waitUntilClickable();
    calculadoraCodigoFasecoldaPage.btnValidarCodigoFasecolda.click();
    realizarEsperaCarga();
  }

  public void crearCodigoFasecoldaVehiculo() {
    calculadoraCodigoFasecoldaPage.btnAceptar.waitUntilClickable();
    calculadoraCodigoFasecoldaPage.btnAceptar.click();
    realizarEsperaCarga();
  }
}
