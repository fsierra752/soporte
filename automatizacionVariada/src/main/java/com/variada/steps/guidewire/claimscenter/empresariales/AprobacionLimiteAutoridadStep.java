package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.constantes.MenuConstante.PLAN_TRABAJO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.ConsultaReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.empresariales.PlanTrabajoActividadInteraction;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class AprobacionLimiteAutoridadStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page ConsultaReclamacionInteraction consultaReclamacionInteraction;

  @Page PlanTrabajoActividadInteraction planTrabajoActividadInteraction;

  public void cerrarNavegador() {
    planTrabajoActividadInteraction.cerrarNavegador();
  }

  @Step
  public void verificarGeneracionActividadRevisarAprobarCambioReserva(String actividadAprobarReserva) {
    String numeroReclamacion = Serenity.sessionVariableCalled(SESION_CC_NUMERO_SINIESTRO.getValor()).toString();
    consultaReclamacionInteraction.buscarReclamacion(numeroReclamacion);
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(PLAN_TRABAJO);
    String actividad = planTrabajoActividadInteraction.obtenerActividadPlanTrabajo();
    MatcherAssert.assertThat(
            "No se genero la actividad de Revisar y aprobar cambio de reserva",
            actividad.equals(actividadAprobarReserva));
  }

  public void aprobarActividadRevisarAprobarCambioReserva(String actividadAprobarReserva) {
    planTrabajoActividadInteraction.aprobarActividadRevisarAprobarCambioReserva(
        actividadAprobarReserva);
  }
}
