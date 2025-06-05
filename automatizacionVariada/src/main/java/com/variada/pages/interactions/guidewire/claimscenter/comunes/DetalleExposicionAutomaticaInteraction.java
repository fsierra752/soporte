package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ESTADO_LEGAL;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.DetalleExposicionAutomaticaPage;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetalleExposicionAutomaticaInteraction extends GeneralInteraction {

  @Page DetalleExposicionAutomaticaPage detalleExposicionAutomaticaPage;

  public DetalleExposicionAutomaticaInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void seleccionarCalculadoraPerdidaTotal() {
    detalleExposicionAutomaticaPage
        .lblCalculadoraPerdidaTotal
        .waitUntilVisible()
        .waitUntilClickable()
        .click();
    realizarEsperaCarga();
  }

  public void editarCalculadoraPerdidaTotal() {
    detalleExposicionAutomaticaPage.btnEditar.click();
    realizarEsperaCarga();
  }

  public void seleccionarIncineracionTotalVehiculo() {
    detalleExposicionAutomaticaPage.rbtIncineracionTotalVehiculo.click();
  }

  public void seleccionarMotorDestruidoFuego() {
    detalleExposicionAutomaticaPage.rbtMotorDestruidoFuego.click();
  }

  public void seleccionarHabitaculoPasajerosIncinerado() {
    detalleExposicionAutomaticaPage.rbtHabitaculoPasajerosIncineradoTotalmente.click();
  }

  public void actualizarCalculadoraPerdidaTotal() {
    detalleExposicionAutomaticaPage.btnActualizar.waitUntilVisible().waitUntilClickable().click();
    waitFor(
        ExpectedConditions.presenceOfElementLocated(
            By.id("ExposureDetail:ExposureDetailScreen:Edit-btnInnerEl")));
  }

  public void seleccionarDetalleExposicion() {
    realizarEsperaCarga();
    detalleExposicionAutomaticaPage
        .lblDetallesExposicion
        .waitUntilVisible()
        .waitUntilClickable()
        .click();
    waitFor(
        ExpectedConditions.presenceOfElementLocated(
            By.id(
                "ExposureDetail:ExposureDetailScreen:ExposureDetailDV:VehicleDamageDV:LegalStatus-inputEl")));
  }

  public void editarDetalleExposicion() {
    detalleExposicionAutomaticaPage.btnEditar.waitUntilClickable().click();
    realizarEsperaCarga();
  }

  public void ingresarEstadoLegalReclamacion() {
    detalleExposicionAutomaticaPage.cmbEstadoLegal.waitUntilVisible().waitUntilClickable().click();
    detalleExposicionAutomaticaPage
        .cmbEstadoLegal
        .findElement(By.xpath("//li[contains(.,'" + ESTADO_LEGAL.getValor() + "')]"))
        .click();
    realizarEsperaCarga();
  }

  public void actualizarDetalleExposicion() {
    detalleExposicionAutomaticaPage.btnActualizar.waitUntilVisible().waitUntilClickable().click();
    realizarEsperaCarga();
  }
}
