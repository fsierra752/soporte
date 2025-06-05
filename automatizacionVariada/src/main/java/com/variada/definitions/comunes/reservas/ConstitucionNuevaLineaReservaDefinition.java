package com.variada.definitions.comunes.reservas;

import com.variada.steps.guidewire.claimscenter.comunes.ConsultaDatoFinancieroTransaccionStep;
import com.variada.steps.guidewire.claimscenter.comunes.MovimientoLineaReservaStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;

public class ConstitucionNuevaLineaReservaDefinition {

  @Steps MovimientoLineaReservaStep movimientoLineaReservaStep;

  @Steps ConsultaDatoFinancieroTransaccionStep consultaDatoFinancieroTransaccionStep;

  @Cuando(
      "^se crea una nueva línea de reserva por la Exposición de (.*) por (.*) con un tipo de costo (.*) por un valor de (.*)$")
  public void crearNuevaLineaReserva(
      String lineaReserva, String categoriaCosto, String tipoCosto, String valorNuevaReserva) {
    movimientoLineaReservaStep.crearNuevaLineaReserva(
        lineaReserva, tipoCosto, categoriaCosto, valorNuevaReserva);
  }

  @Entonces("^se genera una nueva línea de reserva de (.*) con un deducible de (.*)$")
  public void verificarConstitucionNuevaLineaReserva(String categoriaCosto, String deducible) {
    consultaDatoFinancieroTransaccionStep.verificarDeducibleReserva(categoriaCosto, deducible);
  }
}
