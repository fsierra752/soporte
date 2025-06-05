package com.variada.definitions.empresariales.reclamaciones;

import com.variada.steps.guidewire.claimscenter.empresariales.ConsumoServicioCreacionSiniestroStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class ConsumoCreacionSiniestroEmpresarialDefinition {

  @Steps ConsumoServicioCreacionSiniestroStep consumoServicioCreacionSiniestroStep;

  @Dado("^que se tiene una póliza (.*) de empresariales$")
  public void parametrizarValoresSiniestro(String filtroCsv) throws IOException {
    consumoServicioCreacionSiniestroStep.asignarValoresSiniestro(filtroCsv);
  }

  @Cuando("^se genera un siniestro$")
  public void siniestrarPolizaServicio() {
    consumoServicioCreacionSiniestroStep.siniestrarPolizaEmpresarialAtr();
  }

  @Entonces("^se le brindara al reportante un numero de reclamacion$")
  public void verificarCreacionSiniestro() {
    consumoServicioCreacionSiniestroStep.verificarSiniestro();
  }
}
