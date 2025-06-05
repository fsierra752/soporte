package com.variada.definitions.autos.reclamaciones;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumFiltros.CREACION_AVISO_AUTOS_WS;
import static com.variada.utils.enums.EnumFiltros.PERSONA_CONDUCTOR;
import static com.variada.utils.enums.EnumFiltros.PERSONA_LESIONADA;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_RECLAMACION_PERSONA_AUTO;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_SINIESTRO_AUTOS;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_VEHICULO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_COBERTURA_AFECTADA;

import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionAuto;
import com.variada.models.Vehiculo;
import com.variada.steps.guidewire.claimscenter.comunes.MenuClaimsStep;
import com.variada.steps.maca.autos.ConsumoServicioCreacionAvisoSiniestroAutoStep;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ConsumoCreacionAvisoAutosDefinition {

  ReclamacionAuto parametroAviso = new ReclamacionAuto();
  PersonaReclamacion parametroPersonaReclamacionAuto = new PersonaReclamacion();
  PersonaReclamacion parametroPersonaConductorAuto = new PersonaReclamacion();
  Vehiculo reclamacionVehiculo = new Vehiculo();

  @Steps MenuClaimsStep menuClaimsStep;

  @Steps ConsumoServicioCreacionAvisoSiniestroAutoStep creacionAvisoSiniestroAutoStep;

  @Dado(
      "^que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de (.*) de autos que afecta la cobertura (.*)$")
  public void siniestrarPolizaServicio(String origenSinestro, String tipoCobertura) throws IOException {
    parametroPersonaReclamacionAuto =
        new PersonaReclamacion(
            obtenerDatosPrueba(
                PARAMETROS_RECLAMACION_PERSONA_AUTO.getValor(), PERSONA_LESIONADA.getValor()));
    parametroPersonaConductorAuto =
        new PersonaReclamacion(
            obtenerDatosPrueba(
                PARAMETROS_RECLAMACION_PERSONA_AUTO.getValor(), PERSONA_CONDUCTOR.getValor()));
    reclamacionVehiculo =
        new Vehiculo(obtenerDatosPrueba(PARAMETROS_VEHICULO.getValor(), origenSinestro));
    parametroAviso =
        new ReclamacionAuto(
            obtenerDatosPrueba(
                PARAMETROS_SINIESTRO_AUTOS.getValor(), CREACION_AVISO_AUTOS_WS.getValor()));
    siniestrarPoliza(tipoCobertura);
  }

  private void siniestrarPoliza(String tipoCobertura) {
    Serenity.setSessionVariable(SESION_CC_TIPO_COBERTURA_AFECTADA.getValor()).to(tipoCobertura);
    creacionAvisoSiniestroAutoStep.siniestrarPolizaAutos(
        parametroAviso.getLstReclamacionAuto(),
        parametroPersonaReclamacionAuto.getLstPersonaReclamacion(),
        parametroPersonaConductorAuto.getLstPersonaReclamacion(),
        reclamacionVehiculo.getLstVehiculos());
    menuClaimsStep.consultarNumeroReclamacion(
        Serenity.sessionVariableCalled(SESION_CC_NUMERO_SINIESTRO.getValor()));
  }

  @Entonces("^se le brindará al reclamante el número de reclamación$")
  public void verificarCreacionAviso() {
    creacionAvisoSiniestroAutoStep.verificarSiniestro();
  }
}
