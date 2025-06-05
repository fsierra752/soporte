package com.variada.definitions.empresariales.aprobaciones;

import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_EMPRESARIAL_SUPER_USUARIO;

import com.variada.steps.guidewire.claimscenter.comunes.ConsultaDatoFinancieroTransaccionStep;
import com.variada.steps.guidewire.claimscenter.comunes.IniciarSesionAplicativosStep;
import com.variada.steps.guidewire.claimscenter.empresariales.AprobacionLimiteAutoridadStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class AprobacionLimiteAutoridadDefinition {

  @Steps AprobacionLimiteAutoridadStep aprobacionLimiteAutoridadStep;

  @Steps
  IniciarSesionAplicativosStep loginClaimStep;

  @Steps ConsultaDatoFinancieroTransaccionStep consultaDatoFinancieroTransaccionStep;

  @Entonces("^el estado de la transacción de reserva queda en (.*)$")
  public void verificarEstadoTransaccion(String strEstadoTransaccionReserva) {
    consultaDatoFinancieroTransaccionStep.verificarEstadoTransaccionReserva(
        strEstadoTransaccionReserva);
  }

  @Dado(
      "^se genera la actividad, (.*) al Director o Gerente de atención de reclamaciones Empresariales$")
  public void verificarGeneracionActividadRevisarAprobarCambioReserva(
      String actividadAprobarReserva) throws IOException {
    aprobacionLimiteAutoridadStep.cerrarNavegador();
    loginClaimStep.iniciarSesionLab(ANALISTA_RECLAMACION_EMPRESARIAL_SUPER_USUARIO.getValor());
    aprobacionLimiteAutoridadStep.verificarGeneracionActividadRevisarAprobarCambioReserva(
        actividadAprobarReserva);
  }

  @Cuando("^es aprobada la actividad (.*)$")
  public void aprobarActividadRevisarAprobarCambioReserva(String actividadAprobarReserva) {
    aprobacionLimiteAutoridadStep.aprobarActividadRevisarAprobarCambioReserva(
        actividadAprobarReserva);
  }
}
