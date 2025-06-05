package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.TIPO_TRANSACCION;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_TRANSACCION;
import static com.variada.utils.constantes.MenuConstante.TRANSACCIONES;
import static com.variada.utils.constantes.MenuConstante.DATOS_FINANCIEROS;

import com.variada.models.Recupero;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroTransaccionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoRecuperacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class AnulacionRecuperoStep {

  @Page
  MenuClaimInteraction menuClaimInteraction;

  @Page
  DatoRecuperacionInteraction datoRecuperacionInteraction;

  @Page
  DatoFinancieroTransaccionInteraction datoFinancieroTransaccionInteraction;

  @Step
  public void ingresarAnulacionRecupero(List<Recupero> lstRecupero) {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DATOS_FINANCIEROS, TRANSACCIONES);
    for (Recupero diligenciador : lstRecupero) {
      String strNumeroTransaccion =
          datoRecuperacionInteraction.obtenerDatoTablaCabecera(
              SESION_CC_NUMERO_TRANSACCION.getValor(), 1);
      menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
          DATOS_FINANCIEROS, TRANSACCIONES);
      datoFinancieroTransaccionInteraction.seleccionarTipoTransaccion(TIPO_TRANSACCION.getValor());
      MatcherAssert.assertThat(
          "El estado de la transaccion no permite que sea anulada",
          datoFinancieroTransaccionInteraction.ingresarDatoRecuperacion(
              strNumeroTransaccion, diligenciador.getEstadoTransaccion()));
      MatcherAssert.assertThat(
          "El número de transaccion, no tiene habilitado el boton de anular",
          datoRecuperacionInteraction.realizarAnulacionRecupero());
      Serenity.setSessionVariable(SESION_CC_NUMERO_TRANSACCION.getValor()).to(strNumeroTransaccion);
    }
  }

  @Step
  public void verificarAnulacionRecupero() {
    String strNumeroTransaccion =
        Serenity.sessionVariableCalled(SESION_CC_NUMERO_TRANSACCION.getValor());
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DATOS_FINANCIEROS, TRANSACCIONES);
    datoFinancieroTransaccionInteraction.seleccionarTipoTransaccion(TIPO_TRANSACCION.getValor());
    MatcherAssert.assertThat(
        "El recupero no quedo en estado anulado",
        datoFinancieroTransaccionInteraction.verificarEstadoAnuladoRecupero(
            strNumeroTransaccion));
  }
}
