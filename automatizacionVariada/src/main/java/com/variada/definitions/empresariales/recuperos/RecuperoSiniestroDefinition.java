package com.variada.definitions.empresariales.recuperos;

import static com.variada.utils.enums.EnumNombresCsv.RECUPERO_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PRODUCTO_EMPRESARIAL;

import com.variada.models.Recupero;
import com.variada.steps.guidewire.claimscenter.comunes.RecuperoStep;
import com.variada.utils.UtilidadesCSV;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class RecuperoSiniestroDefinition {

  @Steps RecuperoStep recuperoStep;

  Recupero recupero;

  @Cuando("^se genere un recupero con un código de retención (.*)$")
  public void diligenciarRecupero(String codigoRetencion) throws IOException {
    recupero =
        new Recupero(
            UtilidadesCSV.obtenerDatosPrueba(
                RECUPERO_SINIESTRO.getValor(),
                Serenity.sessionVariableCalled(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor())));
    recuperoStep.seleccionarNumeroReclamacion();
    recuperoStep.diligenciarCreacionRecupero(recupero.getLstRecupero(), codigoRetencion);
  }

  @Entonces("^se obtiene un reintegro de dinero al siniestro$")
  public void verificarRecupero() {
    recuperoStep.verificarCreacionRecupero(recupero.getLstRecupero());
  }
}
