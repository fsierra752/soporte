package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.guidewire.claimscenter.autos.PartesImplicadasInteraction;
import java.io.IOException;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class ValidacionSarlaftStep {

  @Page PartesImplicadasInteraction partesImplicadasInteraction;

  @Step
  public void validarSarlaft(String beneficiarioPago) throws IOException {
    boolean sarlaftValidado;
    sarlaftValidado = partesImplicadasInteraction.validarEstadoSarlaft(beneficiarioPago);
    if (!sarlaftValidado) {
      completarDatosContacto();
      partesImplicadasInteraction.validarSarlaft();
    }
  }

  @Step
  public void completarDatosContacto() throws IOException {
    partesImplicadasInteraction.completarDatosBeneficiario();
  }
}
