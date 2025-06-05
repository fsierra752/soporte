package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_FACTURA_PAGO_MASIVO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PAGO_INDIVIDUAL;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.DetalleFacturaVolumenPage;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetalleFacturaVolumenInteraction extends GeneralInteraction {

  @Page DetalleFacturaVolumenPage detalleFacturaVolumenPage;

  public DetalleFacturaVolumenInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void seleccionarTipoMoneda(String tipoMoneda) {
    detalleFacturaVolumenPage.cmbTipoMoneda.click();
    detalleFacturaVolumenPage
        .lstTipoMoneda
        .findElement(org.openqa.selenium.By.xpath("./li[contains(.,'" + tipoMoneda + "')]"))
        .click();
  }

  public void buscarBeneficiario() {
    detalleFacturaVolumenPage.btnBuscarBeneficiario.waitUntilClickable();
    detalleFacturaVolumenPage.btnBuscarBeneficiario.click();
  }

  public void buscarBeneficiarioPago() {
    detalleFacturaVolumenPage.btnBuscarBeneficiarioPago.waitUntilPresent();
    detalleFacturaVolumenPage.btnBuscarBeneficiarioPago.click();
  }

  public void seleccionarMetodoPago(String metodoPago) {
    detalleFacturaVolumenPage.rbtMetodoPago.waitUntilClickable();
    detalleFacturaVolumenPage
        .rbtMetodoPago
        .findElement(
            By.xpath(
                "//following-sibling::label[contains( .,'"
                    + metodoPago
                    + "')]//preceding-sibling::input"))
        .click();
  }

  public void finalizarPagoMasivo() {
    detalleFacturaVolumenPage.btnFinalizarPagoMasivo.waitUntilClickable();
    detalleFacturaVolumenPage.btnFinalizarPagoMasivo.click();
    realizarEsperaCarga();
  }

  public void enviarPagoMasivo() {
    detalleFacturaVolumenPage.btnEnviarPagoMasivo.waitUntilClickable();
    detalleFacturaVolumenPage.btnEnviarPagoMasivo.click();
    realizarEsperaCarga();
  }

  public void obtenerNumeroFacturaPagoMasivo() {
    detalleFacturaVolumenPage.lblNumeroFacturaPagoMasivo.getText();
    Serenity.setSessionVariable(SESION_CC_NUMERO_FACTURA_PAGO_MASIVO.getValor())
        .to(detalleFacturaVolumenPage.lblNumeroFacturaPagoMasivo.getText());
  }

  public void regresarFacturasVolumen() {
    detalleFacturaVolumenPage.lblIrAFacturaPorVolumen.click();
    esperarCargaElemento();
  }

  public String obtenerEstadoPago(){
    esperarCargaElemento();
    return detalleFacturaVolumenPage.lblEstadoPagoMasivo.getText();
  }

  public void obtenerNumeroPagoIndividual() {
    List<String> numeroPagosIndividuales = new ArrayList<String>();
    int aux;
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocidoPagoMasivo(
            detalleFacturaVolumenPage.tblNumeroFactura,
            Integer.parseInt(POSICION_FILA.getValor()));

    int tamanoLista = elementoEncontrado.size();
    for (int i = 0; i <= tamanoLista - 1; i++) {
      aux = i;
      numeroPagosIndividuales.add(i, elementoEncontrado.get(i).getText());
      String numeroFactura = elementoEncontrado.get(i).getText();

      if (numeroFactura.equals(
          Serenity.getCurrentSession()
              .get(SESION_CC_NUMERO_FACTURA_PAGO_MASIVO.getValor())
              .toString())) {

        Serenity.setSessionVariable(SESION_CC_NUMERO_PAGO_INDIVIDUAL.getValor())
            .to(numeroPagosIndividuales.get(aux));
      }
    }
  }
}
