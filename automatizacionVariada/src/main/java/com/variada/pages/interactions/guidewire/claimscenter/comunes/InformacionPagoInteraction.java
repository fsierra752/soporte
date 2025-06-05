package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.constantes.MenuConstante.CAMBIO_PLACA_PRIMA_PENDIENTE;
import static com.variada.utils.enums.EnumConstantes.TIPO_PAGO;
import static com.variada.utils.enums.EnumConstantes.CANTIDAD;
import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumConstantes.PORCENTAJE;
import static com.variada.utils.enums.EnumTablas.CABECERAS_CC;
import static com.variada.utils.enums.EnumTablas.REGISTROS_PAGOS_CC;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PLACA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_LINEA_RESERVA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PAGO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_VALOR_PAGO;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionPagoPage;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class InformacionPagoInteraction extends GeneralInteraction {

  @Page
  InformacionPagoPage informacionPagoPage;

  private Integer intCalculoVrReserva;

  public InformacionPagoInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarLineaReserva(String strLineaReserva) {
    String strLineaReservaDos = "";
    realizarEsperaCarga();
    informacionPagoPage.cmbLineaReserva.waitUntilClickable().click();
    if (strLineaReserva.contains(CAMBIO_PLACA_PRIMA_PENDIENTE)) {
      strLineaReservaDos =
          strLineaReserva.replace(
                  CAMBIO_PLACA_PRIMA_PENDIENTE,
              Serenity.getCurrentSession().get(SESION_CC_NUMERO_PLACA).toString());
    } else {
      strLineaReservaDos = strLineaReserva;
    }
    seleccionarOpcionCombobox(strLineaReservaDos);
    Serenity.setSessionVariable(SESION_CC_LINEA_RESERVA.getValor()).to(strLineaReservaDos);
    realizarEsperaCarga();
  }

  public void seleccionarTipoPago(String strTipoPago) {
    informacionPagoPage.cmbTipoPago.waitUntilVisible().waitUntilClickable().click();
    esperarCargaElemento();
    seleccionarOpcionCombobox(strTipoPago);
    Serenity.setSessionVariable(SESION_CC_TIPO_PAGO.getValor()).to(strTipoPago);
  }

  public void ingresarComentario(String strComentario) {
    realizarEsperaCarga();
    informacionPagoPage.txtComentarioPago.waitUntilVisible().waitUntilClickable().click();
    informacionPagoPage.txtComentarioPago.sendKeys(strComentario);
  }

  private double obtenerValorPagoReserva() {
    String strValorReserva = informacionPagoPage.txtValorReserva.getText();
    strValorReserva = strValorReserva.replaceAll(FORMATEAR_MONTOS.getValor(), "");
    Double dblValorReserva;
    dblValorReserva = Double.parseDouble(strValorReserva);
    return dblValorReserva;
  }

  private Integer calcularCantidadPago(String strTipoPago, int cantidadCodigosRetencion) {
    double dblValorReserva = obtenerValorPagoReserva();
    Double dblCalculoVrReserva;
    if (strTipoPago.equals(TIPO_PAGO.getValor())) {
      dblCalculoVrReserva = Double.parseDouble(PORCENTAJE.getValor()) * dblValorReserva;
    } else {
      dblCalculoVrReserva = dblValorReserva / (cantidadCodigosRetencion - 1);
    }
    intCalculoVrReserva = dblCalculoVrReserva.intValue();
    return intCalculoVrReserva;
  }

  public void ingresarCantidadPago(
      String strTipoPago, int posicionIngresoDato, int cantidadCodigosRetencion) {
    calcularCantidadPago(strTipoPago, cantidadCodigosRetencion);
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocidoPago(
            informacionPagoPage.tblElementoLinea,
                CANTIDAD.getValor(),
            posicionIngresoDato);
    elementoEncontrado.get(Integer.parseInt(VALOR_CERO.getValor())).click();
    evaluateJavascript(
        String.format("$('input[name|=\"Amount\"]').val('%s')", intCalculoVrReserva));
    Serenity.setSessionVariable(SESION_CC_VALOR_PAGO.getValor()).to(intCalculoVrReserva);
  }

  public void agregarNuevaRetencion() {
    realizarEsperaCarga();
    Actions actions = new Actions(driver);
    actions
        .moveToElement(informacionPagoPage.btnAgregarRetencion)
        .click()
        .build()
        .perform();
    informacionPagoPage.btnAgregarRetencion.click();
  }

  private List<WebElement> obtenerElementoTablaDatoDesconocidoPago(
      WebElementFacade elemento, String encabezadoColumnaDevolver, int posicionFila) {
    final int POSICION_COLUMNA_TABLA = 1;
    List<String> cabeceraTabla = obtenerCabecerasTabla(elemento, CABECERAS_CC);
    int posicionColumna = cabeceraTabla.indexOf(encabezadoColumnaDevolver) + POSICION_COLUMNA_TABLA;
    List<WebElement> elementoEncontrado = obtenerFilasTabla(elemento, REGISTROS_PAGOS_CC);
    return elementoEncontrado
        .stream()
        .map(
            fila ->
                fila.findElement(
                    By.xpath(String.format("./tr[%d]/td[%d]/div", posicionFila, posicionColumna))))
        .collect(Collectors.toList());
  }

  public void seleccionarOpcionDescontarSaldoPrima() {
    realizarEsperaCarga();
    informacionPagoPage.rbtDescontarSaldoPrima.waitUntilClickable().click();
    esperarCargaElemento();
  }
}
