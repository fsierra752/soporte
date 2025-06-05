package com.variada.steps.maca.autos;

import static com.variada.utils.constantes.MenuConstante.RECLAMACION_MENU;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;
import static com.variada.utils.constantes.ReclamacionConstante.VERIFICADOR_NUMERO_SINIESTRO;

import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionAuto;
import com.variada.models.Vehiculo;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.services.ConsumoServicioCreacionSiniestroAutos;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class ConsumoServicioCreacionAvisoSiniestroAutoStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Step
  public void siniestrarPolizaAutos(
      List<ReclamacionAuto> lstReclamacionAuto,
      List<PersonaReclamacion> lstPersonaLesionada,
      List<PersonaReclamacion> lstConductor,
      List<Vehiculo> lstVehiculoParam) {
    ConsumoServicioCreacionSiniestroAutos consumoServicioCreacionSiniestroAutos =
        new ConsumoServicioCreacionSiniestroAutos();
    consumoServicioCreacionSiniestroAutos.asignarParametrosRequest(
        lstReclamacionAuto, lstPersonaLesionada, lstConductor, lstVehiculoParam);
    menuClaimInteraction.buscarReclamacion(
        RECLAMACION_MENU, Serenity.sessionVariableCalled(SESION_CC_NUMERO_SINIESTRO.getValor()));
  }

  @Step
  public void verificarSiniestro() {
    String numReclamacion = Serenity.sessionVariableCalled(SESION_CC_NUMERO_SINIESTRO.getValor());
    MatcherAssert.assertThat(
        "No se obtuvo el número del siniestro, verificar el consumo",
        numReclamacion.contains(VERIFICADOR_NUMERO_SINIESTRO));
  }
}
