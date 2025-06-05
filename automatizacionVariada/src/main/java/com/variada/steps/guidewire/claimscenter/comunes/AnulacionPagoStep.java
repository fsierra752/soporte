package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.PAGOS;
import static com.variada.utils.constantes.MenuConstante.DATOS_FINANCIEROS;

import com.variada.models.PagoSiniestro;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroPagoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DetalleChequeInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class AnulacionPagoStep {

  @Page DatoFinancieroPagoInteraction datoFinancieroPagoInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page DetalleChequeInteraction detalleChequeInteraction;

  String strNumeroCheque;

  @Step
  public void ingresarAnulacionPago(List<PagoSiniestro> lstPago) {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DATOS_FINANCIEROS, PAGOS.getValor());
    strNumeroCheque = datoFinancieroPagoInteraction.obtenerNumeroPagoRealizado();
    for (PagoSiniestro diligenciador : lstPago) {
      MatcherAssert.assertThat(
          "El estado de la transaccion no permite que sea anulada",
          datoFinancieroPagoInteraction.ingresarDetalleCheque(
              strNumeroCheque, diligenciador.getEstadoTransaccion()));
      MatcherAssert.assertThat(
          "El número de transaccion, no tiene habilitado el boton de anular",
          detalleChequeInteraction.realizarAnulacionCheque());
    }
  }

  @Step
  public void verificarAnulacionPago() {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DATOS_FINANCIEROS, PAGOS.getValor());
    MatcherAssert.assertThat(
        "El pago no quedo en estado anulado",
        datoFinancieroPagoInteraction.verificarEstadoAnuladoPago(
            strNumeroCheque));
  }
}
