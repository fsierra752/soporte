package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_PERSONA;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;

import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionEmpresarial;
import com.variada.services.ConsumoServicioCreacionSiniestro;
import com.variada.utils.UtilidadesCSV;
import com.variada.utils.constantes.ReclamacionConstante;
import java.io.IOException;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.hamcrest.MatcherAssert;

public class ConsumoServicioCreacionSiniestroStep {

  List<ReclamacionEmpresarial> lstSiniestroParam;
  List<PersonaReclamacion> lstParametroPersona;
  ReclamacionEmpresarial parametroSiniestro = new ReclamacionEmpresarial();
  PersonaReclamacion parametroPersona;

  ConsumoServicioCreacionSiniestro consumoServicioCreacionSiniestro =
      new ConsumoServicioCreacionSiniestro();

  @Step
  public void siniestrarPolizaEmpresarialAtr() {
    consumoServicioCreacionSiniestro.asignarParametrosRequest(
        lstSiniestroParam, lstParametroPersona);
  }

  @Step
  public void asignarValoresSiniestro(String filtroSiniestroCsv) throws IOException {
    parametroSiniestro =
        new ReclamacionEmpresarial(
                UtilidadesCSV.obtenerDatosPrueba(PARAMETROS_SINIESTRO.getValor(), filtroSiniestroCsv));
    lstSiniestroParam = parametroSiniestro.getLstReclamo();
    parametroPersona =
        new PersonaReclamacion(
                UtilidadesCSV.obtenerDatosPrueba(PARAMETROS_PERSONA.getValor(), filtroSiniestroCsv));
    lstParametroPersona = parametroPersona.getLstPersonaReclamacion();
  }

  @Step
  public void verificarSiniestro() {
    String numReclamacion = Serenity.sessionVariableCalled(SESION_CC_NUMERO_SINIESTRO.getValor());
    MatcherAssert.assertThat(
        "No se obtuvo el número del siniestro, verificar el consumo",
        numReclamacion.contains(ReclamacionConstante.VERIFICADOR_NUMERO_SINIESTRO));
  }
}
