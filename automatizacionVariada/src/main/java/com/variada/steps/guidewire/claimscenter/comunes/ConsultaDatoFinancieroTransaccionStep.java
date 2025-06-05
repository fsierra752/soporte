package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ITERACIONES_PAGO;
import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;
import static com.variada.utils.constantes.MenuConstante.TRANSACCIONES;
import static com.variada.utils.constantes.MenuConstante.DATOS_FINANCIEROS;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.ConsultaReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroTransaccionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoReservaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.empresariales.PlanTrabajoActividadInteraction;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class ConsultaDatoFinancieroTransaccionStep {

  private static final String TIPO_CATEGORIA_COSTO_GASTO = "Gasto";

  @Page DatoFinancieroTransaccionInteraction datoFinancieroTransaccionInteraction;

  @Page DatoReservaInteraction datoReservaInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page PlanTrabajoActividadInteraction planTrabajoActividadInteraction;

  @Page ConsultaReclamacionInteraction consultaReclamacionInteraction;

  @Step
  public void verificarEstadoTransaccionReserva(String strEstadoTransaccionReserva) {
    final String TRANSACCION_RESERVA = "Reservas";
    final String ESTADO_SOLICITADO = "Solicitado";
    final int POSICION_ESTADO_SOLICITADO = 2;
    String strEstadoTransaccion = "";
    String numeroReclamacion = "";
    int posicionEstadoVerificar;
    if (strEstadoTransaccionReserva.equals(ESTADO_SOLICITADO)) {
      posicionEstadoVerificar = POSICION_ESTADO_SOLICITADO;
    } else {
      posicionEstadoVerificar = Integer.parseInt(POSICION_FILA.getValor());
    }
    for (int i = 0; i <= Integer.parseInt(ITERACIONES_PAGO.getValor()); i++) {
      planTrabajoActividadInteraction.realizarEsperaCarga();
      menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
          DATOS_FINANCIEROS, TRANSACCIONES);
      datoFinancieroTransaccionInteraction.seleccionarTipoTransaccion(TRANSACCION_RESERVA);
      strEstadoTransaccion =
          datoFinancieroTransaccionInteraction.obtenerEstadoReservaRealizada(
              posicionEstadoVerificar);
      boolean estadoTransaccionPantalla = strEstadoTransaccionReserva.equals(strEstadoTransaccion);
      if (estadoTransaccionPantalla) {
        break;
      }
    }
    MatcherAssert.assertThat(
        "El estado de la reserva es diferente al de " + strEstadoTransaccionReserva,
        strEstadoTransaccionReserva.equals(strEstadoTransaccion));
    numeroReclamacion = consultaReclamacionInteraction.obtenerNumeroSiniestro();
    Serenity.setSessionVariable(SESION_CC_NUMERO_SINIESTRO.getValor()).to(numeroReclamacion);
  }

  @Step
  public void verificarDeducibleReserva(String categoriaCosto, String deducible) {
    String deducibleVisualizado;
    if (categoriaCosto.contains(TIPO_CATEGORIA_COSTO_GASTO)) {
      deducibleVisualizado = VALOR_CERO.getValor();
    } else {
      datoFinancieroTransaccionInteraction.ingresarDatoReserva();
      deducibleVisualizado = datoReservaInteraction.obtenerCantidadReserva();
    }
    MatcherAssert.assertThat(
        "Se esperaba un deducible de: "
            + deducible
            + " Pero se obtuvo un deducible de: "
            + deducibleVisualizado,
        deducibleVisualizado.equals(deducible));
  }
}
