package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.pages.interactions.guidewire.claimscenter.autos.ResultadoValidacionArchivoInteraction;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS;

public class ResultadoValidacionArchivoStep {

  @Page ResultadoValidacionArchivoInteraction resultadoValidacionArchivoInteraction;

  public void validarNumeroRegistrosArchivo() {
    int numeroRegistrosPantalla;
    numeroRegistrosPantalla = resultadoValidacionArchivoInteraction.capturarNumeroRegistrosPantalla();
    validarNumeroRegistrosArchivoXls(numeroRegistrosPantalla);
    resultadoValidacionArchivoInteraction.continuarSiguientePantalla();
  }

  @Step
  private void validarNumeroRegistrosArchivoXls(int numeroRegistrosPantalla){
    String numeroRegistrosArchivo =
            (Serenity.sessionVariableCalled(SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS.getValor())
                    .toString());
    int numeroRegistrosArchivoXls = Integer.parseInt(numeroRegistrosArchivo);
    MatcherAssert.assertThat(
            "El número de registros de la pantalla no es igual al número de registros del archivo XLS",
            (numeroRegistrosArchivoXls == numeroRegistrosPantalla));
  }
}
