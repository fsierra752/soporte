package com.variada.definitions.empresariales.reclamaciones;

import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_PERSONA;
import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_EMPRESARIAL;

import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionEmpresarial;
import com.variada.steps.guidewire.claimscenter.comunes.IniciarSesionAplicativosStep;
import com.variada.steps.guidewire.claimscenter.empresariales.NuevaReclamacionAtrEmpresarialStep;
import static com.variada.utils.constantes.ReclamacionConstante.RECLAMACION_EMPRESARIAL;
import static com.variada.utils.constantes.ReclamacionConstante.ATR;

import com.variada.utils.UtilidadesCSV;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class NotificacionAvisoSiniestroAtrDefinition {

  @Steps NuevaReclamacionAtrEmpresarialStep nuevaReclamacionAtrEmpresarialStep;

  @Steps IniciarSesionAplicativosStep iniciarSesionAplicativosStep;

  @Dado("^que tenemos una póliza de (.*)$")
  public void diligenciarInformacionAsegurado(String cobertura) throws IOException {
    PersonaReclamacion aseguradoAtr =
        new PersonaReclamacion(
            UtilidadesCSV.obtenerDatosPrueba(PARAMETROS_PERSONA.getValor(), cobertura));
    nuevaReclamacionAtrEmpresarialStep.accederAvisoAtr();
    nuevaReclamacionAtrEmpresarialStep.diligenciarInformacionAsegurado(
        aseguradoAtr.getLstPersonaReclamacion());
  }

  @Cuando("^se genere un siniestro por causa (.*) con un valor de pretensión de (.*)$")
  public void diligenciarInformacionSiniestro(String causaSiniestro, String valorPretension)
      throws IOException {
    ReclamacionEmpresarial informacionSiniestro =
        new ReclamacionEmpresarial(
            UtilidadesCSV.obtenerDatosPrueba(
                RECLAMACION_EMPRESARIAL, ATR));
    nuevaReclamacionAtrEmpresarialStep.diligenciarInformacionReclamacion(
        causaSiniestro, informacionSiniestro.getLstReclamo());
    nuevaReclamacionAtrEmpresarialStep.consultarPolizaAtr();
    nuevaReclamacionAtrEmpresarialStep.diligenciarValorPretension(valorPretension);
  }

  @Entonces("^se obtiene una reclamación que podrá ser consultada en ClaimCenter$")
  public void consultarSiniestro() throws IOException {
    String numeroReclamacion = nuevaReclamacionAtrEmpresarialStep.verificarSiniestroCreadoAtr();
    iniciarSesionAplicativosStep.elegirInicioSesionCC(ANALISTA_RECLAMACION_EMPRESARIAL.getValor());
    nuevaReclamacionAtrEmpresarialStep.consultarSiniestro(numeroReclamacion);
  }
}
