package com.variada.definitions.empresariales.reaseguro;

import static com.variada.utils.enums.EnumConstantes.RESERVA;
import static com.variada.utils.enums.EnumNombresCsv.CONTRATO;
import static com.variada.utils.enums.EnumNombresCsv.RECUPERO_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PRODUCTO_EMPRESARIAL;

import com.variada.models.Contrato;
import com.variada.models.Recupero;
import com.variada.steps.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaStep;
import com.variada.steps.guidewire.claimscenter.comunes.RecuperoStep;
import com.variada.steps.guidewire.claimscenter.empresariales.ReaseguroStep;
import com.variada.utils.UtilidadesCSV;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ReaseguroDefinition {

  private String strTipoContrato =
      Serenity.sessionVariableCalled(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor());

  @Steps ReaseguroStep reaseguroStep;

  @Steps RecuperoStep recuperoStep;

  @Steps NuevaReclamacionGuardadaStep nuevaReclamacionGuardadaStep;

  @Cuando("^se realice al siniestro un recupero con un código de retención (.*)$")
  public void realizarRecuperoSiniestroEmpresarial(String strCodigoRetencionRecupero)
      throws IOException {
    Recupero recupero =
        new Recupero(UtilidadesCSV.obtenerDatosPrueba(RECUPERO_SINIESTRO.getValor(), strTipoContrato));
    recuperoStep.diligenciarCreacionRecupero(recupero.getLstRecupero(), strCodigoRetencionRecupero);
  }

  @Entonces(
      "^para la transacción (.*) se distribuye el reaseguro según el retenido y el cedido de manera adecuada$")
  public void verificarReaseguro(String strTransaccion) throws IOException {
    if (strTransaccion.equals(RESERVA.getValor())) {
      nuevaReclamacionGuardadaStep.obtenerNumeroReclamacionGuardada();
    }
    Contrato contrato =
        new Contrato(UtilidadesCSV.obtenerDatosPrueba(CONTRATO.getValor(), strTipoContrato));
    reaseguroStep.verificarReaseguro(contrato.getLstContrato(), strTransaccion);
  }
}
