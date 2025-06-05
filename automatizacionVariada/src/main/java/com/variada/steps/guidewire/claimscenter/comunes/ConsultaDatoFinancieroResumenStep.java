package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.DATOS_FINANCIEROS;

import com.variada.models.Reserva;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroResumenInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class ConsultaDatoFinancieroResumenStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page DatoFinancieroResumenInteraction datoFinancieroResumenInteraction;

  @Step
  public void validarValorReservas(List<Reserva> lineaReserva) {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(DATOS_FINANCIEROS.getValor());
    boolean valorLineaReserva =
        datoFinancieroResumenInteraction.obtenerDatosFinancieros(lineaReserva);
    MatcherAssert.assertThat(
        "No coinciden todos los valores de las líneas de reserva", valorLineaReserva);
  }
}
