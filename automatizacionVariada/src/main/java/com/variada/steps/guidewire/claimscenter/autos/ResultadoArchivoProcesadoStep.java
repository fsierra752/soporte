package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.autos.ResultadoArchivoProcesadoInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

import java.util.List;

public class ResultadoArchivoProcesadoStep {

  @Page ResultadoArchivoProcesadoInteraction resultadoArchivoProcesadoInteraction;

  @Page GeneralInteraction generalInteraction;

  @Step
  public void consultarResultadoArchivoProcesado() {
    List<String> resultados = resultadoArchivoProcesadoInteraction.ObtenerResultadosArchivoProcesado();
    int tamanoLista = resultados.size();
    String resultadoValidacionArchivoXls = "Sí";
    for (int i = 0; i <= tamanoLista - 1; i++) {
      MatcherAssert.assertThat("El archivo Xls no cumple con las validaciones del sistema",
              (resultadoValidacionArchivoXls.equals(resultados.get(i))));
    }
    generalInteraction.continuarSiguientePantalla();
  }
}
