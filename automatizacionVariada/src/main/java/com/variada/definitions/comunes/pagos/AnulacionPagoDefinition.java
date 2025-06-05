package com.variada.definitions.comunes.pagos;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumNombresCsv.PAGO_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PRODUCTO_EMPRESARIAL;

import com.variada.models.PagoSiniestro;
import com.variada.steps.guidewire.claimscenter.comunes.AnulacionPagoStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class AnulacionPagoDefinition {

  @Steps AnulacionPagoStep anulacionPagoStep;

  PagoSiniestro pagoSiniestro;

  @Cuando("^se realice la anulación del pago$")
  public void anularTransaccionPagoEmpresariales() throws IOException {
    pagoSiniestro =
        new PagoSiniestro(
            (obtenerDatosPrueba(
                PAGO_SINIESTRO.getValor(),
                Serenity.sessionVariableCalled(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor()))));
    anulacionPagoStep.ingresarAnulacionPago(pagoSiniestro.getLstPago());
  }

  @Cuando("^se anula dicho pago con cobertura (.*)$")
  public void anularTransaccionPagoAutos(String cobertura) throws IOException {
    pagoSiniestro = new PagoSiniestro(obtenerDatosPrueba(PAGO_SINIESTRO.getValor(), cobertura));
    anulacionPagoStep.ingresarAnulacionPago(pagoSiniestro.getLstPago());
  }

  @Entonces("^se debe obtener la anulación del pago, quedando en estado anulado$")
  public void verificarAnulacionPago() {
    anulacionPagoStep.verificarAnulacionPago();
  }
}
