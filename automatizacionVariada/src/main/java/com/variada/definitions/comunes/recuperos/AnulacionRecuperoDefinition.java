package com.variada.definitions.comunes.recuperos;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumNombresCsv.RECUPERO_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PRODUCTO_EMPRESARIAL;

import com.variada.models.Recupero;
import com.variada.steps.guidewire.claimscenter.comunes.AnulacionRecuperoStep;
import com.variada.steps.guidewire.claimscenter.comunes.RecuperoStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class AnulacionRecuperoDefinition {

  @Steps AnulacionRecuperoStep anulacionRecuperoStep;

  @Steps RecuperoStep recuperoStep;

  Recupero recupero;

  @Cuando("^se realice la anulación del recupero$")
  public void anularTransaccionRecuperoEmpresariales() throws IOException {
    recupero =
        new Recupero(
            obtenerDatosPrueba(
                RECUPERO_SINIESTRO.getValor(),
                Serenity.sessionVariableCalled(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor())));
    anulacionRecuperoStep.ingresarAnulacionRecupero(recupero.getLstRecupero());
  }

  @Cuando("^se anula el ingreso con cobertura (.*)$")
  public void anularTransaccionRecuperoAutos(String cobertura) throws IOException {
    recupero = new Recupero(obtenerDatosPrueba(RECUPERO_SINIESTRO.getValor(), cobertura));
    anulacionRecuperoStep.ingresarAnulacionRecupero(recupero.getLstRecupero());
  }

  @Cuando(
      "^una transacción de recupero, de un siniestro de una póliza empresarial con código de retención (.*)$")
  public void crearRecuperoAvisoSiniestro(String strCodigoRetencion)
      throws IOException {
    recupero =
        new Recupero(
            obtenerDatosPrueba(
                RECUPERO_SINIESTRO.getValor(),
                Serenity.sessionVariableCalled(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor())));
    recuperoStep.diligenciarCreacionRecupero(recupero.getLstRecupero(), strCodigoRetencion);
  }

  @Entonces("^se debe obtener la anulación del recupero, quedando en estado anulado$")
  public void verificarAnulacionRecupero() {
    anulacionRecuperoStep.verificarAnulacionRecupero();
  }
}
