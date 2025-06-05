package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TOTAL_PAGO_RESERVAS;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InstruccionPagoPage;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

import static com.variada.utils.Utilidades.obtenerFechaActual;

public class InstruccionPagoInteraction extends GeneralInteraction {

  @Page
  InstruccionPagoPage instruccionPagoPage;

  public InstruccionPagoInteraction(WebDriver driver) {
    super(driver);
  }

  public void obtenerPagoReservas() {
    String totalPagoReservas = instruccionPagoPage.txtMontoNeto.getText();
    totalPagoReservas = totalPagoReservas.replaceAll(FORMATEAR_MONTOS.getValor(), "");
    Serenity.setSessionVariable(SESION_CC_TOTAL_PAGO_RESERVAS.getValor()).to(totalPagoReservas);
  }

  public void ingresarFechaFactura() {
    if (instruccionPagoPage.txtFechaPago.isVisible()) {
      instruccionPagoPage.txtFechaPago.waitUntilClickable();
      instruccionPagoPage.txtFechaPago.sendKeys(obtenerFechaActual());
    }
  }

  public void ingresarNumeroFactura(String strNumeroFactura) {
    instruccionPagoPage.txtNumeroFactura.click();
    instruccionPagoPage.txtNumeroFactura.sendKeys(strNumeroFactura);
  }
}
