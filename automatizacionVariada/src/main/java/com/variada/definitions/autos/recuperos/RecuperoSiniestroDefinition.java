package com.variada.definitions.autos.recuperos;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumNombresCsv.RECUPERO_SINIESTRO;

import com.variada.models.Recupero;
import com.variada.steps.guidewire.claimscenter.comunes.RecuperoStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class RecuperoSiniestroDefinition {

  @Steps RecuperoStep recuperoStep;

  Recupero recupero;

  @Dado("^que se creó el recupero con un código de retención (.*) a una cobertura (.*)$")
  @Cuando("^se crea el recupero con un código de retención (.*) a una cobertura (.*)$")
  public void crearRecuperoReclamacionAutos(String codigoRetencion, String cobertura)
      throws IOException {
    recupero = new Recupero((obtenerDatosPrueba(RECUPERO_SINIESTRO.getValor(), cobertura)));
    recuperoStep.diligenciarCreacionRecupero(recupero.getLstRecupero(), codigoRetencion);
  }

  @Entonces("^se obtiene un ingreso de dinero sobre el siniestro$")
  public void verificarRecuperoAutos() {
    recuperoStep.verificarCreacionRecupero(recupero.getLstRecupero());
  }
}
