package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.constantes.MenuConstante.RESERVA;
import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumConstantes.CC_NOMBRE_CAMPO_VALOR_NUEVA_RESERVA;
import static com.variada.utils.enums.EnumConstantes.CC_POSICION_VALOR_RESERVA_EMPRESARIALES;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ReservaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import org.fluentlenium.core.annotation.Page;

public class MovimientoLineaReservaStep {

  private static final String NOMBRE_CAMPO_CATEGORIA_COSTO_RESERVA_EMPRESARIALES =
      "Categoría de costo";
  private static final String NOMBRE_CAMPO_EXPOSICION_LINEA_RESERVA_EMPRESARIALES = "Exposición";
  private static final String NOMBRE_CAMPO_TIPO_COSTO_RESERVA_EMPRESARIALES = "Tipo de costo";

  @Page
  ReservaInteraction reservaInteraction;

  @Page GeneralInteraction generalInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  public void crearNuevaLineaReserva(
      String lineaReserva, String tipoCosto, String categoriaCosto, String valorNuevaReserva) {
    menuClaimInteraction.seleccionarBotonAcciones();
    menuClaimInteraction.seleccionarOpcionMenuAccionesPrimerNivel(RESERVA);
    generalInteraction.realizarEsperaCarga();
    reservaInteraction.diligenciarCampoLineaReserva(
        lineaReserva,
        NOMBRE_CAMPO_EXPOSICION_LINEA_RESERVA_EMPRESARIALES,
        Integer.valueOf(VALOR_CERO.getValor()));
    reservaInteraction.diligenciarCampoLineaReserva(
        categoriaCosto,
        NOMBRE_CAMPO_CATEGORIA_COSTO_RESERVA_EMPRESARIALES,
        Integer.valueOf(VALOR_CERO.getValor()));
    reservaInteraction.diligenciarCampoLineaReserva(
        tipoCosto,
        NOMBRE_CAMPO_TIPO_COSTO_RESERVA_EMPRESARIALES,
        Integer.valueOf(VALOR_CERO.getValor()));
    reservaInteraction.diligenciarCampoLineaReserva(
        valorNuevaReserva,
        CC_NOMBRE_CAMPO_VALOR_NUEVA_RESERVA.getValor(),
        Integer.parseInt(CC_POSICION_VALOR_RESERVA_EMPRESARIALES.getValor()));
  }

  public void ajustarReserva(String valorAjustar) {
    menuClaimInteraction.seleccionarBotonAcciones();
    menuClaimInteraction.seleccionarOpcionMenuAccionesPrimerNivel(RESERVA);
    reservaInteraction.eliminarReservaVacia();
    reservaInteraction.diligenciarCampoLineaReserva(
        valorAjustar,
        CC_NOMBRE_CAMPO_VALOR_NUEVA_RESERVA.getValor(),
        Integer.parseInt(CC_POSICION_VALOR_RESERVA_EMPRESARIALES.getValor()));
  }
}
