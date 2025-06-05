package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.pages.interactions.guidewire.claimscenter.autos.DetalleFacturaVolumenInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class DetalleFacturaVolumenStep {

  @Page DetalleFacturaVolumenInteraction detalleFacturaVolumenInteraction;

  @Step
  public void ingresarInformacionFactura(String tipoMoneda, String metodoPago) {
    detalleFacturaVolumenInteraction.seleccionarTipoMoneda(tipoMoneda);
    detalleFacturaVolumenInteraction.seleccionarMetodoPago(metodoPago);
    detalleFacturaVolumenInteraction.buscarBeneficiario();
    detalleFacturaVolumenInteraction.buscarBeneficiarioPago();
  }

  @Step
  public void crearPagoMasivo() {
    detalleFacturaVolumenInteraction.obtenerNumeroFacturaPagoMasivo();
    detalleFacturaVolumenInteraction.finalizarPagoMasivo();
    detalleFacturaVolumenInteraction.enviarPagoMasivo();
  }

  @Step
  public void validarPagoMasivo() {
    String estadoPagoMasivo = "Solicitando";
    MatcherAssert.assertThat(
            "El estado del pago es incorrecto, estado diferente a Solicitando o Solicitado",
            (estadoPagoMasivo.equals(detalleFacturaVolumenInteraction.obtenerEstadoPago())));
    detalleFacturaVolumenInteraction.regresarFacturasVolumen();
    detalleFacturaVolumenInteraction.obtenerNumeroPagoIndividual();
  }
}
