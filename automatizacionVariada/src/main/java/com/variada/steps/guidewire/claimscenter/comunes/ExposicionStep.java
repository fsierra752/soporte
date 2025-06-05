package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.constantes.ReclamacionConstante.EXPOSICIONES;
import static com.variada.utils.enums.EnumConstantes.TIPO;
import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class ExposicionStep {

  @Page GeneralInteraction generalInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Step
  public void validarExposicionEmpresariales(String tipoExposicion) {
    boolean tipoExposicionEmpresarial;
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(EXPOSICIONES);
    String valorTipoExposicion =
        generalInteraction.obtenerDatoTablaCabecera(
            TIPO.getValor(), Integer.parseInt(POSICION_FILA.getValor()));
    if (!valorTipoExposicion.equals(tipoExposicion)) {
      tipoExposicionEmpresarial = false;
    } else {
      tipoExposicionEmpresarial = true;
    }
    MatcherAssert.assertThat("El tipo de exposición no es la esperada", tipoExposicionEmpresarial);
  }
}
