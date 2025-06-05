package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ESTADO_ANULACION;
import static com.variada.utils.enums.EnumConstantes.ITERACIONES_RECUPERO;
import static com.variada.utils.enums.EnumConstantes.UBICACION_ESTADO_RECUPERO;
import static com.variada.utils.enums.EnumConstantes.ESTADO;
import static com.variada.utils.enums.EnumConstantes.CANTIDAD;
import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_VALOR_PAGO;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.general.GeneralPage;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.DatoFinancieroTransaccionPage;
import com.variada.utils.Utilidades;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatoFinancieroTransaccionInteraction extends GeneralInteraction {

  @Page
  DatoFinancieroTransaccionPage datoFinancieroTransaccionPage;

  @Page
  GeneralPage generalPage;

  public static final String VALOR_TOTAL = "Valor total";

  public DatoFinancieroTransaccionInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public String obtenerEstadoReservaRealizada(int posicionEstadoVerificar) {
    return obtenerDatoTablaCabecera(ESTADO.getValor(), posicionEstadoVerificar);
  }

  public boolean verificarEstadoAnuladoRecupero(
      String strNumeroTransaccion) {
    List<WebElement> lstTransaccion = generalPage.obtenerTablaTransacciones(strNumeroTransaccion);
    for (int i = 0; i < lstTransaccion.size(); i++) {
      if (lstTransaccion.get(i).getText().equals(ESTADO_ANULACION.getValor())) {
        return true;
      }
    }
    return false;
  }

  public void ingresarDatoReserva() {
    irUltimaPagina();
    datoFinancieroTransaccionPage.tblTransaccion.waitUntilPresent();
    List<WebElement> elementroEncontrado =
        obtenerElementoTablaDatoDesconocido(
            datoFinancieroTransaccionPage.tblTransaccion,
            CANTIDAD.getValor(),
            Integer.parseInt(POSICION_FILA.getValor()));
    int longitudTabla = elementroEncontrado.size();
    int datoPosicionReserva = longitudTabla - Integer.parseInt(POSICION_FILA.getValor());
    elementroEncontrado
        .listIterator()
        .next()
        .findElement(
            By.xpath(
                "//a[@id='ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:TransactionsLV:"
                    + datoPosicionReserva
                    + ":Amount']"))
        .click();
  }

  public boolean ingresarDatoRecuperacion(String strNumeroTransaccion, String strEstadoPrevio) {
    final int POSICION_VALOR_MONTO_RECUPERO = 2;
    List<WebElement> lstTransaccion;
    boolean estadoTransaccionPantalla = false;
    for (int i = 0; i <= Integer.parseInt(ITERACIONES_RECUPERO.getValor()); i++) {
      realizarEsperaCarga();
      lstTransaccion = generalPage.obtenerTablaTransacciones(strNumeroTransaccion);
      WebElement elementoXpath =
          lstTransaccion.get(Integer.parseInt(UBICACION_ESTADO_RECUPERO.getValor()));
      estadoTransaccionPantalla = actualizarPantalla(strEstadoPrevio, elementoXpath);
      if (estadoTransaccionPantalla) {
        String strMontoRecupero = lstTransaccion.get(POSICION_VALOR_MONTO_RECUPERO).getText();
        lstTransaccion
            .get(POSICION_VALOR_MONTO_RECUPERO)
            .findElement(
                By.xpath(
                    String.format(
                        "//a[@class='g-actionable'][contains(text(),'" + strMontoRecupero + "')]",
                        strNumeroTransaccion)))
            .click();
        break;
      }
    }
    realizarEsperaCarga();
    return estadoTransaccionPantalla;
  }

  public String obtenerMontoReserva() {
    String validarReservaTransaccion = "";
    if (datoFinancieroTransaccionPage.lnkReservaTransaccion.isVisible()) {
      validarReservaTransaccion =
          datoFinancieroTransaccionPage.lnkReservaTransaccion.waitUntilVisible().getText();
      validarReservaTransaccion =
          validarReservaTransaccion.replaceAll(FORMATEAR_MONTOS.getValor(), "");
    } else {
      Utilidades.getLogger().info("No se ha generado reserva en la sección de transacciones");
    }
    return validarReservaTransaccion;
  }

  public boolean verificarValorPagoPrimaPendiente(String valorPrimaPendiente) {
    List<WebElement> lstValorTotal =
        obtenerElementoTablaDatoDesconocido(
            datoFinancieroTransaccionPage.tblDatosFinancierosPagos,
            VALOR_TOTAL,
            Integer.parseInt(POSICION_FILA.getValor()));
    for (int i = 0; i < lstValorTotal.size(); i++) {
      if (valorPrimaPendiente.equals(lstValorTotal.get(i).getText())) {
        return true;
      }
    }
    return false;
  }

  public boolean verificarValorPagoMenosPrimaPendiente(String valorPrimaPendiente) {
    int valorPago = (Serenity.sessionVariableCalled(SESION_CC_VALOR_PAGO.getValor()));
    int valorPagoMenosPrimaPendiente =
        valorPago - Integer.parseInt(valorPrimaPendiente.replaceAll("\\D+", ""));
    List<WebElement> lstValorTotal =
        obtenerElementoTablaDatoDesconocido(
            datoFinancieroTransaccionPage.tblDatosFinancierosPagos,
            VALOR_TOTAL,
            Integer.parseInt(POSICION_FILA.getValor()));
    for (int i = 0; i < lstValorTotal.size(); i++) {
      String valorTransaccionPago = lstValorTotal.get(i).getText().replaceAll("\\D+", "");
      if (Integer.parseInt(valorTransaccionPago) == valorPagoMenosPrimaPendiente) {
        return true;
      }
    }
    return false;
  }
}
