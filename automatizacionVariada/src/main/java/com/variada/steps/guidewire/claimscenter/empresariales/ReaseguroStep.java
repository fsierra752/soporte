package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumConstantes.REASEGURO_DETALLADO;
import static com.variada.utils.enums.EnumConstantes.RETENCION_PURA;

import com.variada.models.Contrato;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.empresariales.ReaseguroDetalladoTransaccionInteraction;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class ReaseguroStep {

  @Page ReaseguroDetalladoTransaccionInteraction reaseguroDetalladoTransaccionInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Step
  public void verificarReaseguro(List<Contrato> lstContrato, String strTransaccion) {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(REASEGURO_DETALLADO.getValor());
    lstContrato.forEach(
        verificador ->
            MatcherAssert.assertThat(
                "El reaseguro no se distribuyó de forma correcta",
                reaseguroDetalladoTransaccionInteraction.verificarReaseguro(
                    Double.parseDouble(RETENCION_PURA.getValor()),
                    strTransaccion,
                    verificador.getPorcentajeRetenido(),
                    verificador.getProporcionCuotaParte(),
                    verificador.getPorcentajeCoaseguroCedido())));
  }
}
