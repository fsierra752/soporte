package com.variada.steps.guidewire.claimscenter.autos;

import static com.variada.utils.constantes.MenuConstante.POLIZA;
import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroTransaccionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionPolizaGeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class PagoPrimaPendienteStep {

  @Page InformacionPolizaGeneralInteraction informacionPolizaGeneralInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page DatoFinancieroTransaccionInteraction datoFinancieroTransaccionInteraction;

  String valorPrimaPendiente;

  public void verificarEstadoPrimaPendiente() {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(POLIZA);
    valorPrimaPendiente = informacionPolizaGeneralInteraction.verificarEstadoPrimaPendiente();
    verificarPrimaPendiente();
  }

  @Step
  public void verificarValorPagoPrimaPendiente() {
    MatcherAssert.assertThat(
        "No se hizo el pago de la prima pendiente",
        datoFinancieroTransaccionInteraction.verificarValorPagoPrimaPendiente(valorPrimaPendiente));
  }

  @Step
  public void verificarValorPagoMenosPrimaPendiente() {
    MatcherAssert.assertThat(
        "El valor del pago menos la prima pendiente no es correcto",
        datoFinancieroTransaccionInteraction.verificarValorPagoMenosPrimaPendiente(
            valorPrimaPendiente));
  }

  @Step
  private void verificarPrimaPendiente(){
    MatcherAssert.assertThat(
            "No tiene prima pendiente",
            !(valorPrimaPendiente.replaceAll("\\D+", "").equals(VALOR_CERO.getValor())));
  }
}
