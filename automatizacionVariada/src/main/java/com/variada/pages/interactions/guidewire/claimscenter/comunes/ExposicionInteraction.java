package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.TIPO;
import static com.variada.utils.enums.EnumTablas.CABECERAS_CC;
import static com.variada.utils.enums.EnumTablas.REGISTROS_CC;

import com.variada.models.ExposicionesAutomaticasAutos;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.ExposicionPage;
import java.util.List;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class ExposicionInteraction extends GeneralInteraction {

  private static final String EXPOSICION_DANOS_ASEGURADO = "Daños";

  @Page ExposicionPage exposicionPage;

  public ExposicionInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void seleccionarExposicion() {
    obtenerCabecerasTabla(exposicionPage.tblExposicionesAutomaticas, CABECERAS_CC);
    obtenerTextoElementoLista(
            exposicionPage.tblExposicionesAutomaticas,
            CABECERAS_CC,
            REGISTROS_CC,
            EXPOSICION_DANOS_ASEGURADO,
            TIPO.getValor())
        .click();
    realizarEsperaCarga();
  }

  public boolean validarExposiciones(
      List<ExposicionesAutomaticasAutos> datosExposicionesAutomaticas) {
    boolean valorLineaReserva = true;
    obtenerCabecerasTabla(
        $("//div[@id='ClaimExposures:ClaimExposuresScreen:ExposuresLV']"), CABECERAS_CC);
    for (int i = 0; i < datosExposicionesAutomaticas.size(); i++) {
      String lineaReservaTbl =
          obtenerElementoLista(
                  exposicionPage.tblExposicionesAutomaticas,
                  CABECERAS_CC,
                  REGISTROS_CC,
                  datosExposicionesAutomaticas.get(i).getExposicionAutomatica(),
                  datosExposicionesAutomaticas.get(i).getDatoDevolverTablaExposiciones())
              .getText();
      if (lineaReservaTbl.equals(datosExposicionesAutomaticas.get(i).getExposicionAutomatica())) {
        obtenerElementoLista(
                exposicionPage.tblExposicionesAutomaticas,
                CABECERAS_CC,
                REGISTROS_CC,
                datosExposicionesAutomaticas.get(i).getExposicionAutomatica(),
                datosExposicionesAutomaticas.get(0).getDatoDevolverTablaExposiciones())
            .getText();
      } else {
        valorLineaReserva = false;
        break;
      }
    }
    return valorLineaReserva;
  }
}