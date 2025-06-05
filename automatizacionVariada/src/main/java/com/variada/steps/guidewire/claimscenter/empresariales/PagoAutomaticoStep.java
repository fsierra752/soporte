package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.constantes.MenuConstante.TRANSACCIONES;
import static com.variada.utils.enums.EnumConstantes.DATOS_FINANCIEROS;
import static com.variada.utils.enums.EnumConstantes.PAGOS;

import com.variada.models.PagoSiniestro;
import com.variada.models.Reserva;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroPagoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroTransaccionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import java.util.List;

import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class PagoAutomaticoStep {

  @Page DatoFinancieroTransaccionInteraction datoFinancieroTransaccionInteraction;

  @Page DatoFinancieroPagoInteraction datoFinancieroPagoInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Step
  public void verificarMontoReservaAutomatica(List<Reserva> lstReserva) {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DATOS_FINANCIEROS.getValor(), TRANSACCIONES);
    lstReserva.forEach(
        reserva ->
            MatcherAssert.assertThat(
                "El valor de la reserva es diferente a:"
                    + reserva.getValorReserva()
                    + ". Revisar en configuración comercial la parametrización de reservas automáticas.",
                datoFinancieroTransaccionInteraction
                    .obtenerMontoReserva()
                    .equals(reserva.getValorReserva())));
  }

  @Step
  public void verificarPagoAutomatico(List<PagoSiniestro> lstPago) {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DATOS_FINANCIEROS.getValor(), PAGOS.getValor());
    lstPago.forEach(
        pago -> {
            List<String> listaPagos =datoFinancieroPagoInteraction.obtenerListaPagos(pago);
          MatcherAssert.assertThat(
              "No se realizó el pago automático. Revisar en configuración comercial la parametrización de pagos automáticos.",
              datoFinancieroPagoInteraction.verificarPagoMenuTransaccion(
                  pago.getEsPagoAutomatico(), listaPagos));
          MatcherAssert.assertThat(
              "El valor del pago es diferente a: " + pago.getValorTransaccion(),
              datoFinancieroPagoInteraction.verificarPagoMenuTransaccion(
                  pago.getValorTransaccion(), listaPagos));
          MatcherAssert.assertThat(
              "No llego a SAP el pago",
              (datoFinancieroPagoInteraction.verificarPagoMenuTransaccion(
                  pago.getEstadoTransaccion(), listaPagos)));
        });
  }
}
