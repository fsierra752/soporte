package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ACTIVIDADES;
import static com.variada.utils.enums.EnumConstantes.ACTIVITIES;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.CambioIdiomaInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class CambioIdiomaStep {

  @Page
  CambioIdiomaInteraction cambioIdiomaInteraction;

  @Step
  public void seleccionarIdioma() {
    cambioIdiomaInteraction.ingresarConfiguraciones();
    cambioIdiomaInteraction.elegirOpcionInternacional();
    cambioIdiomaInteraction.elegirIdioma();
  }

  @Step
  public void comprobarTextoPantalla() {
    String tipoIdioma = cambioIdiomaInteraction.seleccionarIdioma();
    if (tipoIdioma.equals(ACTIVIDADES.getValor())) {
      MatcherAssert.assertThat(
          "No cambio el idioma en la aplicacion", tipoIdioma.equals(ACTIVIDADES.getValor()));
    } else {
      MatcherAssert.assertThat(
          "No cambio el idioma en la aplicacion", tipoIdioma.equals(ACTIVITIES.getValor()));
    }
  }
}
