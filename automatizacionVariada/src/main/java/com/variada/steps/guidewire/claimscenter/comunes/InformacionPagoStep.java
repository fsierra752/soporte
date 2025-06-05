package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.models.PagoSiniestro;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionPagoInteraction;
import java.util.List;

import com.variada.utils.Utilidades;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class InformacionPagoStep {

  @Page
  InformacionPagoInteraction informacionPagoInteraction;

  @Step
  public void ingresarInformacionPago(
      String lineaReserva, String tipoPago, List<PagoSiniestro> lstPago) {
    informacionPagoInteraction.seleccionarLineaReserva(lineaReserva);
    informacionPagoInteraction.seleccionarTipoPago(tipoPago);
    informacionPagoInteraction.ingresarComentario(
        lstPago.listIterator().next().getComentario());
  }

  @Step
  public void seleccionarPrimaPendiente(String primaPendiente) {
    if( Utilidades.transformarCadenaValorlogico(primaPendiente)) {
      informacionPagoInteraction.seleccionarOpcionDescontarSaldoPrima();
    }
  }

  @Step
  public void ingresarInformacionRetencion(List<String> codigoRetencion, String tipoPago) {
    for (int i = 1; i < codigoRetencion.size(); i++) {
      informacionPagoInteraction.ingresarCantidadPago(
          tipoPago, i, codigoRetencion.size());
      if (i < (codigoRetencion.size() - 1)) {
        informacionPagoInteraction.agregarNuevaRetencion();
      }
    }
  }
}
