package com.variada.definitions.empresariales.reclamaciones;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PRODUCTO_EMPRESARIAL;
import static com.variada.utils.constantes.ReclamacionConstante.RECLAMACION_EMPRESARIAL;

import com.variada.models.ReclamacionEmpresarial;
import com.variada.steps.guidewire.claimscenter.comunes.BusquedaPolizaStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionBasicaReclamacionStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionReclamacionStep;
import com.variada.steps.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaStep;
import com.variada.steps.guidewire.claimscenter.comunes.PropiedadesImplicadasStep;
import com.variada.steps.guidewire.claimscenter.empresariales.NuevaReclamacionEmpresarialStep;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class NotificacionAvisoSiniestroDefinition {

  ReclamacionEmpresarial reclamacionEmpresarial;

  @Steps NuevaReclamacionEmpresarialStep nuevaReclamacionEmpresarialStep;

  @Steps BusquedaPolizaStep busquedaPolizaStep;

  @Steps InformacionReclamacionStep informacionReclamacionStep;

  @Steps
  InformacionBasicaReclamacionStep informacionBasicaReclamacionStep;

  @Steps PropiedadesImplicadasStep propiedadesImplicadasStep;

  @Steps NuevaReclamacionGuardadaStep nuevaReclamacionGuardadaStep;

  @Dado("^que se tiene una póliza de (.*)$")
  public void buscarPoliza(String tipoCobertura) throws IOException {
    Serenity.setSessionVariable(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor()).to(tipoCobertura);
    reclamacionEmpresarial =
        new ReclamacionEmpresarial(
            obtenerDatosPrueba(RECLAMACION_EMPRESARIAL, tipoCobertura));
    busquedaPolizaStep.buscarPolizaEmpresarial(reclamacionEmpresarial.getLstReclamo());
  }

  @Dado("^que se tiene un asegurado de reclamaciones tipo (.*) (.*)$")
  public void buscarAseguradoVidaGrupo(String tipoReclamacion, String numeroPoliza) throws IOException {
    Serenity.setSessionVariable(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor()).to(tipoReclamacion);
    reclamacionEmpresarial =
        new ReclamacionEmpresarial(
            obtenerDatosPrueba(RECLAMACION_EMPRESARIAL, tipoReclamacion));
    busquedaPolizaStep.buscarPolizaAseguradoVidaGrupo(reclamacionEmpresarial.getLstReclamo(), numeroPoliza);
  }

  @Cuando(
      "^se genere un siniestro por causal (.*) con un valor de pretensión de (.*) y un incidente de tipo (.*)$")
  public void tomarDatosSiniestro(
      String causaSiniestro, String valorPretension, String tipoIncidente) {
    propiedadesImplicadasStep.seleccionarPropiedadImplicada();
    informacionBasicaReclamacionStep.diligenciarInformacionBasica(reclamacionEmpresarial.getLstReclamo());
    informacionReclamacionStep.diligenciarInformacionIncidente(
        causaSiniestro, valorPretension, tipoIncidente);
  }

  @Cuando("^se genera un siniestro por diagnostico medico (.*)$")
  public void crearSiniestroRapidoVidaGrupo(String diagnostico) {
    informacionBasicaReclamacionStep.ingresarInformacionBasicaVidaGrupo(reclamacionEmpresarial.getLstReclamo());
    informacionBasicaReclamacionStep.ingresarLesionesSiniestroRapido(diagnostico, reclamacionEmpresarial.getLstReclamo());
  }

  @Cuando("^se realiza pago por (.*) con (.*) (.*) (.*) y (.*) (.*)$")
  public void seleccionarDatosBancarios(
      String transferencia,
      String nomTitular,
      String banco,
      String numCuenta,
      String tipoCuenta,
      String cuentaNueva) {
    informacionBasicaReclamacionStep.elegirPagoPorTransferencia(
        transferencia, nomTitular, banco, numCuenta, tipoCuenta, cuentaNueva);
  }

  @Cuando("^se elija una (.*)$")
  public void seleccionarCoberturaSiniestroRapido(String cobertura) {
    informacionBasicaReclamacionStep.seleccionarCoberturaDisponible(cobertura);
  }

  @Entonces("^se obtiene una reclamación que (.*) genera exposición$")
  public void verificarExposicion(String exposicion) {
    nuevaReclamacionEmpresarialStep.validarReclamacion();
    nuevaReclamacionGuardadaStep.abrirNuevaReclamacionGuardada();
    nuevaReclamacionEmpresarialStep.validarExposicionVisualizada(exposicion);
  }

  @Entonces("^que (.*) genera reserva con un monto (.*), envía correo y se asigna a un analista$")
  public void verificarReserva(String reserva, String monto) {
    nuevaReclamacionEmpresarialStep.validarReservaDatosFinancieros(
        reclamacionEmpresarial.getLstReclamo());
  }
}
