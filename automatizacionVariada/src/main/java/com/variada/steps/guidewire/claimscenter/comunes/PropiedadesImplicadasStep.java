package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.guidewire.claimscenter.autos.PropiedadesImplicadasInteraction;
import org.fluentlenium.core.annotation.Page;

public class PropiedadesImplicadasStep {

  @Page PropiedadesImplicadasInteraction propiedadesImplicadasInteraction;

  public void seleccionarPropiedadImplicada() {
    propiedadesImplicadasInteraction.seleccionarPropiedad();
  }
}
