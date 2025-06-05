package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumConstantes.VALIDADOR_NUEVA_RECLAMACION;
import static com.variada.utils.constantes.ReclamacionConstante.EXPOSICIONES;

import com.variada.models.ReclamacionEmpresarial;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ResumenReclamacionInteraction;

import java.util.List;

import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class NuevaReclamacionEmpresarialStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page InformacionReclamacionInteraction informacionReclamacionInteraction;

  @Page ResumenReclamacionInteraction resumenReclamacionInteraction;

  @Step
  public void validarReclamacion() {
    String verificar;
    verificar = informacionReclamacionInteraction.obtenerTituloReclamacionGenerada();
    MatcherAssert.assertThat(
        "No se ha obtenido el número de reclamación",
        verificar.equals(VALIDADOR_NUEVA_RECLAMACION.getValor()));
  }

  @Step
  public void validarExposicionVisualizada(String exposicion) {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(EXPOSICIONES);
    MatcherAssert.assertThat(
        "No generó exposición, verificar las reglas de administración de exposiciones o data ingresada",
        resumenReclamacionInteraction.validarExposicion().equals(exposicion));
  }

  @Step
  public void validarReservaDatosFinancieros(List<ReclamacionEmpresarial> datoReserva) {
    datoReserva.forEach(
        reserva -> {
          String validar =
              resumenReclamacionInteraction.validarReservaTransaccion(
                  reserva.getReservaTransaccion());
          MatcherAssert.assertThat(
              "Se esperaba una reserva de: "
                  + reserva.getReservaTransaccion()
                  + ", pero se ha obtenido una reserva de: "
                  + validar,
              reserva.getReservaTransaccion().equals(validar));
        });
  }
}
