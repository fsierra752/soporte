package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaPage;
import com.variada.utils.Utilidades;
import static com.variada.utils.enums.EnumVariables.FORMATEAR_MONTOS;
import java.util.concurrent.TimeUnit;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class NuevaReclamacionGuardadaInteraction extends GeneralInteraction {

  @Page NuevaReclamacionGuardadaPage nuevaReclamacionGuardadaPage;

  public NuevaReclamacionGuardadaInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void abrirReclamacion() {
    nuevaReclamacionGuardadaPage
        .lblNumeroReclamacion
        .withTimeoutOf(180, TimeUnit.SECONDS)
        .waitUntilVisible()
        .waitUntilClickable();
    nuevaReclamacionGuardadaPage.lblNumeroReclamacion.click();
  }

  public String obtenerNumeroReclamacion() {
    String numeroReclamacion;
    nuevaReclamacionGuardadaPage.lblNumeroReclamacion.waitUntilVisible().waitUntilClickable();
    numeroReclamacion = nuevaReclamacionGuardadaPage.lblNumeroReclamacion.getText();
    numeroReclamacion = numeroReclamacion.replaceAll(FORMATEAR_MONTOS.getValor(), "");
    Utilidades.getLogger()
        .info(String.format("el número de reclamación generado es: %s%n", numeroReclamacion));
    nuevaReclamacionGuardadaPage.lblNumeroReclamacion.click();
    return numeroReclamacion;
  }
}
