package com.variada.pages.interactions.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.empresariales.PlanTrabajoActividadPage;
import java.util.List;

import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlanTrabajoActividadInteraction extends GeneralInteraction {

  @Page PlanTrabajoActividadPage planTrabajoActividadPage;

  private static final String CAMPO_NOMBRE_ACTIVIDAD = "Asunto";

  public PlanTrabajoActividadInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public String obtenerActividadPlanTrabajo() {
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            planTrabajoActividadPage.tblPlanTrabajo,
            CAMPO_NOMBRE_ACTIVIDAD,
            Integer.parseInt(POSICION_FILA.getValor()));
    return elementoEncontrado.get(Integer.parseInt(VALOR_CERO.getValor())).getText();
  }

  public void aprobarActividadRevisarAprobarCambioReserva(String actividadAprobarReserva) {
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            planTrabajoActividadPage.tblPlanTrabajo,
            CAMPO_NOMBRE_ACTIVIDAD,
            Integer.parseInt(POSICION_FILA.getValor()));
    if (elementoEncontrado
        .get(Integer.parseInt(VALOR_CERO.getValor()))
        .getText()
        .equals(actividadAprobarReserva)) {
      elementoEncontrado.get(Integer.parseInt(VALOR_CERO.getValor())).click();
      realizarEsperaCarga();
      planTrabajoActividadPage.btnAprobarActividad.click();
    }
  }
}
