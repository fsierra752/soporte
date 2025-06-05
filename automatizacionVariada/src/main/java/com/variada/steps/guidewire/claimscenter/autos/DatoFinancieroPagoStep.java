package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.DatoFinancieroPagoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

import java.util.List;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PAGO_INDIVIDUAL;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS;

public class DatoFinancieroPagoStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page DatoFinancieroPagoInteraction datoFinancieroPagoInteraction;

  @Step
  public void validarPagosIndividuales(String nombreOpcion, String subItem) {
    String contadorPagoIndividual =
            Serenity.getCurrentSession().get(SESION_CC_NUMERO_PAGO_INDIVIDUAL.getValor()).toString();
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(nombreOpcion, subItem);
    List<String> resultados = datoFinancieroPagoInteraction.obtenerNumerosPagosMasivos();
    int tamanoLista = resultados.size();
    for (int i = 0; i <= tamanoLista - 1; i++) {
      MatcherAssert.assertThat(
              "El número del pago masivo generado no se encuentra en la consulta de pagos del siniestro",
              (contadorPagoIndividual.equals(resultados.get(i))));
    }
    MatcherAssert.assertThat(
            "La cantidad de pagos individuales no igual a la de partes implicadas",
            (Serenity.getCurrentSession().get(SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS.getValor()).toString()
                    .equals(datoFinancieroPagoInteraction.obtenerCantidadPagosIndividuales())));
  }
}
