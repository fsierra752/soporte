package com.variada.pages.interactions.guidewire.claimscenter.autos;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.PropiedadesImplicadasPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class PropiedadesImplicadasInteraction extends GeneralInteraction {

  @Page PropiedadesImplicadasPage propiedadesImplicadasPage;

  public PropiedadesImplicadasInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarPropiedad() {
    if (propiedadesImplicadasPage.lblTituloPropiedadesImplicadas.isPresent()) {
      propiedadesImplicadasPage
          .rbtPropiedad
          .waitUntilPresent()
          .waitUntilVisible()
          .waitUntilClickable()
          .click();
      realizarEsperaCarga();
      continuarSiguientePantalla();
    }
  }
}