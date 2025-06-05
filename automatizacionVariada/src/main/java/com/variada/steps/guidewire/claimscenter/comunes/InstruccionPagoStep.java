package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.LINEA_RESERVA_LESIONES_CORPORALES;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TOTAL_PAGO_RESERVAS;

import com.variada.models.PagoSiniestro;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroPagoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InstruccionPagoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.empresariales.AuditoriaInteraction;
import java.util.List;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class InstruccionPagoStep {

  @Page
  GeneralInteraction generalInteraction;

  @Page
  AuditoriaInteraction auditoriaInteraction;

  @Page
  InstruccionPagoInteraction instruccionPagoInteraction;

  @Page
  DatoFinancieroPagoInteraction datoFinancieroPagoInteraction;

  private static final String MENSAJE_RECHAZO_PAGO =
      "Elementos de línea : Para realizar el pago, primero debe verificar los detalles de investigación de auditoría";

  @Step
  public void finalizarCreacionPago(List<PagoSiniestro> lstPago, String lineaReserva) {
    generalInteraction.irSiguientePagina();
    if (auditoriaInteraction.verificarMensajeRechazo()) {
      MatcherAssert.assertThat(
          "No generó la validación de NO pago a asegurado por proceso de auditoría",
          auditoriaInteraction.capturarMensajeRechazo().equalsIgnoreCase(MENSAJE_RECHAZO_PAGO));
    } else if (!lineaReserva.equals(LINEA_RESERVA_LESIONES_CORPORALES.getValor())) {
      instruccionPagoInteraction.ingresarFechaFactura();
      instruccionPagoInteraction.ingresarNumeroFactura(
          lstPago.listIterator().next().getNumeroFactura());
    }
    instruccionPagoInteraction.obtenerPagoReservas();
    instruccionPagoInteraction.finalizarProceso();
  }

  @Step
  public void verificarPagoRealizado(List<PagoSiniestro> lstPago) {
    lstPago.forEach(
        (PagoSiniestro validador) -> {
          List<String> listaPagos =datoFinancieroPagoInteraction.obtenerListaPagos(validador);
          String strValorReserva =
              (Serenity.sessionVariableCalled(SESION_CC_TOTAL_PAGO_RESERVAS.getValor()).toString());
          MatcherAssert.assertThat(
              "El valor reservado no es igual al enviado",
              datoFinancieroPagoInteraction.verificarPagoMenuTransaccion(
                  strValorReserva, listaPagos));
          MatcherAssert.assertThat(
              "No llego a SAP el pago",
              datoFinancieroPagoInteraction.verificarPagoMenuTransaccion(
                  validador.getEstadoTransaccion(), listaPagos));
        });
  }
}
