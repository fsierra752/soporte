package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumTablas.CABECERAS_CC;
import static com.variada.utils.enums.EnumTablas.REGISTROS_CC;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;

import com.variada.models.Reserva;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.general.GeneralPage;

import java.util.List;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class DatoFinancieroResumenInteraction extends GeneralInteraction {

  @Page GeneralPage generalPage;

  private boolean valorLineaReserva = true;
  private static final String DATO_RESERVA_DISPONIBLE = "Reservas disponibles";

  public DatoFinancieroResumenInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public boolean obtenerDatosFinancieros(List<Reserva> datosLineaReserva) {
    obtenerCabecerasTabla(
        $(
            "//div[@id='ClaimFinancialsSummary:ClaimFinancialsSummaryScreen:FinancialsSummaryPanelSet:FinancialsSummaryLV']"),
        CABECERAS_CC);
    for (int i = 0; i < datosLineaReserva.size(); i++) {
      String lineaReservaTbl =
          obtenerElementoLista(
                  generalPage.tblVerificacion,
                  CABECERAS_CC,
                  REGISTROS_CC,
                  datosLineaReserva.get(i).getLineaReserva(),
                  "")
              .getText();
      if (lineaReservaTbl.equals(datosLineaReserva.get(i).getLineaReserva())) {
        String valorReserva =
            obtenerElementoLista(
                    generalPage.tblVerificacion,
                    CABECERAS_CC,
                    REGISTROS_CC,
                    datosLineaReserva.get(i).getLineaReserva(),
                    DATO_RESERVA_DISPONIBLE)
                .getText();
        if (valorReserva.equals(datosLineaReserva.get(i).getValorReserva())) {
          valorLineaReserva = true;
        } else if (!valorReserva.equals(datosLineaReserva.get(i).getValorReserva())) {
          String totalizarLineaReserva = valorReserva;
          int totalReserva = 0;
          totalizarLineaReserva =
              totalizarLineaReserva.replaceAll(FORMATEAR_MONTOS.getValor(), "");
          String valorDeducible =
              obtenerElementoLista(
                      generalPage.tblVerificacion,
                      CABECERAS_CC,
                      REGISTROS_CC,
                      datosLineaReserva.get(i).getLineaReserva(),
                      datosLineaReserva.get(0).getValorDeducible())
                  .getText();
          valorDeducible = valorDeducible.replaceAll(FORMATEAR_MONTOS.getValor(), "");
          totalReserva = Integer.parseInt(totalizarLineaReserva) + Integer.parseInt(valorDeducible);
          if ((String.valueOf(totalReserva)).equals(datosLineaReserva.get(i).getValorReserva())) {
            valorLineaReserva = true;
          }
        }
      } else {
        valorLineaReserva = false;
        break;
      }
    }
    return valorLineaReserva;
  }
}