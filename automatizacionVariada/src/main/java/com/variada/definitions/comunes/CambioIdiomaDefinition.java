package com.variada.definitions.comunes;

import com.variada.steps.guidewire.claimscenter.comunes.CambioIdiomaStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;

public class CambioIdiomaDefinition {

  @Steps
  CambioIdiomaStep cambioIdiomaStep;

  @Cuando("cambie el idioma a us")
  public void cambiarIdioma() {
    cambioIdiomaStep.seleccionarIdioma();
  }

  @Entonces("el idioma de reclamaciones debe ser us")
  public void verPantallaIdiomaDiferente() {
    cambioIdiomaStep.comprobarTextoPantalla();
  }
}
