package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.SI;
import static com.variada.utils.enums.EnumConstantes.NO;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;
import static com.variada.utils.constantes.ReclamacionConstante.EXPOSICION_CONTENIDO;
import static com.variada.utils.constantes.ReclamacionConstante.EXPOSICION_PROPIEDAD;
import static com.variada.utils.constantes.ReclamacionConstante.EXPOSICION_GENERAL;
import static com.variada.utils.constantes.ReclamacionConstante.EXPOSICION_VIDA_GRUPO_LESIONES;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.ResumenReclamacionPage;
import com.variada.utils.Utilidades;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class ResumenReclamacionInteraction extends GeneralInteraction {

  @Page ResumenReclamacionPage resumenReclamacionPage;

  public ResumenReclamacionInteraction(WebDriver driver) {
    super(driver);
  }

  public String validarExposicion() {
    String validadorExposicion;
    String validador;
    if (resumenReclamacionPage.lnkTipoExposicion.isVisible()) {
      validador = resumenReclamacionPage.lnkTipoExposicion.waitUntilVisible().getText();
      switch (validador) {
        case EXPOSICION_CONTENIDO:
        case EXPOSICION_PROPIEDAD:
        case EXPOSICION_GENERAL:
        case EXPOSICION_VIDA_GRUPO_LESIONES:
          validadorExposicion = SI.getValor();
          break;
        default:
          validadorExposicion = NO.getValor();
          break;
      }
    } else {
      validadorExposicion = NO.getValor();
    }
    return validadorExposicion;
  }

  public String validarReservaTransaccion(String montoReserva) {
    String validarReservaTransaccion;
    if (resumenReclamacionPage.lnkReservaTransaccion.isVisible()) {
      validarReservaTransaccion =
          resumenReclamacionPage.lnkReservaTransaccion.waitUntilVisible().getText();
      validarReservaTransaccion =
          validarReservaTransaccion.replaceAll(FORMATEAR_MONTOS.getValor(), "");
    } else {
      validarReservaTransaccion = montoReserva;
      Utilidades.getLogger().info("No se ha generado reserva en la sección de transacciones");
    }
    return validarReservaTransaccion;
  }

  public String consultarNumeroPlaca() {
    String numeroPlaca;
    numeroPlaca = resumenReclamacionPage.lblNumeroPlaca.waitUntilVisible().getText();
    return numeroPlaca;
  }

  public void desmarcarCoberturaEnDuda() {
    resumenReclamacionPage.btnEditar.waitUntilVisible().click();
    realizarEsperaCarga();
    resumenReclamacionPage.rbtDesmarcarCoberturaEnDuda.waitUntilVisible().waitUntilClickable().click();
    realizarEsperaCarga();
    resumenReclamacionPage.btnActualizar.waitUntilVisible().waitUntilClickable().click();
    realizarEsperaCarga();
  }
}
