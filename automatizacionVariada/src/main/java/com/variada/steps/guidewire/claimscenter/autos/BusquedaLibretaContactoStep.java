package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.pages.interactions.guidewire.claimscenter.autos.BusquedaLibretaContactoInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class BusquedaLibretaContactoStep {
  @Page BusquedaLibretaContactoInteraction busquedaLibretaContactoInteraction;

  @Step
  public void buscarContactoPagoMasivo(String tipoContacto, String nombreContacto) {
    busquedaLibretaContactoInteraction.seleccionarTipoContacto(tipoContacto);
    busquedaLibretaContactoInteraction.ingresarNombreContacto(nombreContacto);
    busquedaLibretaContactoInteraction.buscarContacto();
    busquedaLibretaContactoInteraction.seleccionarContactoPagoMasivo();
  }
}
