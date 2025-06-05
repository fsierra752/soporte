package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PAGOS_INDIVIDUALES;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.ResultadoValidacionArchivoPage;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class ResultadoValidacionArchivoInteraction extends GeneralInteraction {

  @Page ResultadoValidacionArchivoPage resultadoValidacionArchivoPage;

  public ResultadoValidacionArchivoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public int  capturarNumeroRegistrosPantalla() {
    int numeroRegistrosPantalla =
        Integer.parseInt(
            resultadoValidacionArchivoPage.lblNumeroRegistrosFactura.getText().replaceAll("\\D+", ""));

    Serenity.setSessionVariable(SESION_CC_NUMERO_PAGOS_INDIVIDUALES.getValor())
        .to(numeroRegistrosPantalla);

    return numeroRegistrosPantalla;
  }
}
