package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.NUMERO_PAGO;
import static com.variada.utils.enums.EnumConstantes.COP;
import static com.variada.utils.enums.EnumConstantes.USD;
import static com.variada.utils.enums.EnumConstantes.ESTADO_ANULACION;
import static com.variada.utils.enums.EnumConstantes.ITERACIONES_PAGO;
import static com.variada.utils.enums.EnumConstantes.UBICACION_ESTADO_PAGO;
import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;

import com.variada.models.PagoSiniestro;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.general.GeneralPage;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.DatoFinancieroPagoPage;

import java.util.ArrayList;
import java.util.List;

import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatoFinancieroPagoInteraction extends GeneralInteraction {

  @Page DatoFinancieroPagoPage datoFinancieroPagoPage;
  @Page
  GeneralPage generalPage;

  public DatoFinancieroPagoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public String obtenerNumeroPagoRealizado() {
    realizarEsperaCarga();
    return obtenerDatoTablaCabecera(NUMERO_PAGO.getValor(), 1);
  }

  public boolean verificarPagoMenuTransaccion(String datoValidar, List<String> lstFilaPago) {
    for (int i = 0; i < lstFilaPago.size(); i++) {
      String strDatoPantalla = lstFilaPago.get(i);
      if (strDatoPantalla.contains(COP.getValor()) || strDatoPantalla.contains(USD.getValor())) {
        strDatoPantalla = strDatoPantalla.replaceAll(FORMATEAR_MONTOS.getValor(), "");
      }
      if (strDatoPantalla.equals(datoValidar)) {
        return true;
      }
    }
    return false;
  }

  public boolean verificarEstadoAnuladoPago(String strNumeroTransaccion) {
    List<WebElement> lstPago = generalPage.obtenerTablaPagos(strNumeroTransaccion);
    for (int i = 0; i < lstPago.size(); i++) {
      if (lstPago.get(i).getText().equals(ESTADO_ANULACION.getValor())) {
        return true;
      }
    }
    return false;
  }

  public boolean ingresarDetalleCheque(String strNumeroTransaccion, String strEstadoPrevio) {
    List<WebElement> lstTransaccion;
    boolean estadoTransaccionPantalla = false;
    for (int i = 0; i <= Integer.parseInt(ITERACIONES_PAGO.getValor()); i++) {
      realizarEsperaCarga();
      lstTransaccion = generalPage.obtenerTablaPagos(strNumeroTransaccion);
      WebElement elementoXpath =
          lstTransaccion.get(Integer.parseInt(UBICACION_ESTADO_PAGO.getValor()));
      estadoTransaccionPantalla = actualizarPantalla(strEstadoPrevio, elementoXpath);
      if (estadoTransaccionPantalla) {
        lstTransaccion.get(Integer.parseInt(VALOR_CERO.getValor())).click();
        lstTransaccion
            .get(Integer.parseInt(VALOR_CERO.getValor()))
            .findElement(
                By.xpath(
                    String.format(
                        "//a[@class='g-actionable'][contains(text(),'%s')]", strNumeroTransaccion)))
            .click();
        break;
      }
    }
    realizarEsperaCarga();
    return estadoTransaccionPantalla;
  }

  public  List<String> obtenerNumerosPagosMasivos() {
    List<WebElement> numeroPagosMasivo =
        obtenerElementoTablaDatoDesconocidoDatosFinancieroPagoMasivo(
            datoFinancieroPagoPage.tblPagoIndividual);
    List<String> resultados = new ArrayList<>();
    for (int i = 0; i <= numeroPagosMasivo.size() - 1; i++) {
      resultados.add(numeroPagosMasivo.get(i).getText());
    }
    return resultados;
  }

  public String obtenerCantidadPagosIndividuales(){
    List<WebElement> numeroPagosIndividuales =
            obtenerElementoTablaDatoDesconocidoDatosFinanciero(
                    datoFinancieroPagoPage.tblPagoIndividual);
    return String.valueOf(numeroPagosIndividuales.size());
  }


  public List<String> obtenerListaPagos(PagoSiniestro pago){
    List<WebElement> lstFilaPago = new ArrayList<>();
    List<String> pagos = new ArrayList<>();
    ordenarTablaAscendente();
    for (int i = 0; i <= Integer.parseInt(ITERACIONES_PAGO.getValor()); i++) {
      realizarEsperaCarga();
      lstFilaPago = generalPage.obtenerTablaPagos(obtenerNumeroPagoRealizado());
      WebElement elementoXpath =
              lstFilaPago.get(Integer.parseInt(UBICACION_ESTADO_PAGO.getValor()));
      boolean estadoTransaccionPantalla =
              actualizarPantalla(pago.getEstadoTransaccion(), elementoXpath);
      if (estadoTransaccionPantalla) break;
    }
    for (WebElement element : lstFilaPago ) {
      pagos.add(element.getText());
    }
    return pagos;
  }
}