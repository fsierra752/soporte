package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.CC_POSICION_VALOR_RESERVA_EMPRESARIALES;
import static com.variada.utils.enums.EnumConstantes.CC_NOMBRE_CAMPO_VALOR_NUEVA_RESERVA;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.ReservaPage;
import java.util.List;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservaInteraction extends GeneralInteraction {

  @Page
  ReservaPage reservaPage;

  public ReservaInteraction(WebDriver driver) {
    super(driver);
  }

  public void eliminarReservaVacia() {
    if (reservaPage.chkLineaReserva.isVisible()) {
      reservaPage.chkLineaReserva.click();
      reservaPage.btnQuitarLineaReserva.waitUntilClickable().click();
    }
  }

  public void diligenciarCampoLineaReserva(
      String valorCampoLineaReserva,
      String encabezadoColumnaDevolver,
      Integer posicionColumnaReserva) {
    realizarEsperaCarga();
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            reservaPage.tblLineaReserva,
            encabezadoColumnaDevolver,
            posicionColumnaReserva);
    int ubicacionFilaNuevaReserva =
        elementoEncontrado.size()
            + Integer.parseInt(CC_POSICION_VALOR_RESERVA_EMPRESARIALES.getValor());
    WebElement filaNuevaReserva = elementoEncontrado.get(ubicacionFilaNuevaReserva);
    filaNuevaReserva.click();
    if (encabezadoColumnaDevolver.equals(CC_NOMBRE_CAMPO_VALOR_NUEVA_RESERVA.getValor())) {
      evaluateJavascript(
          String.format("$('input[name|=\"NewAmount\"]').val('%s')", valorCampoLineaReserva));
      reservaPage.btnGuardarAjusteReserva.click();
    } else {
      seleccionarOpcionLista(reservaPage.cmbExposicion, valorCampoLineaReserva);
    }
    realizarEsperaCarga();
  }
}
