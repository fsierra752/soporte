package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.DatoReservaPage;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class DatoReservaInteraction extends GeneralInteraction {

  @Page DatoReservaPage datoReservaPage;

  public DatoReservaInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public String obtenerCantidadReserva() {
    String cantidadReserva = datoReservaPage.lblCantidad.getText();
    return cantidadReserva.replaceAll(FORMATEAR_MONTOS.getValor(), "");
  }
}