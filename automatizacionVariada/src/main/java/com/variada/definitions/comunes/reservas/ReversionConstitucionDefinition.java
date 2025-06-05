package com.variada.definitions.comunes.reservas;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumNombresCsv.RECLAMACION_EMPRESARIAL;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PRODUCTO_EMPRESARIAL;

import com.variada.models.ReclamacionEmpresarial;
import com.variada.steps.guidewire.claimscenter.comunes.BusquedaPolizaStep;
import com.variada.steps.guidewire.claimscenter.comunes.ConsultaDatoFinancieroTransaccionStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionBasicaReclamacionStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionReclamacionStep;
import com.variada.steps.guidewire.claimscenter.comunes.MovimientoLineaReservaStep;
import com.variada.steps.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaStep;
import com.variada.steps.guidewire.claimscenter.comunes.PropiedadesImplicadasStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ReversionConstitucionDefinition {

  private static final String TIPO_CATEGORIA_COSTO_RESERVA = "Costo";

  @Steps MovimientoLineaReservaStep movimientoLineaReservaStep;

  @Steps ConsultaDatoFinancieroTransaccionStep consultaDatoFinancieroTransaccionStep;

  @Steps InformacionReclamacionStep informacionReclamacionStep;

  @Steps BusquedaPolizaStep busquedaPolizaStep;

  @Steps PropiedadesImplicadasStep propiedadesImplicadasStep;

  @Steps
  InformacionBasicaReclamacionStep informacionBasicaReclamacionStep;

  @Steps NuevaReclamacionGuardadaStep nuevaReclamacionGuardadaStep;

  @Dado(
      "^que se genera un siniestro del producto (.*) con causa (.*), valor de pretensión (.*) y tipo incidente de (.*)$")
  public void consultarReserva(
      String producto, String causaSiniestro, String valorPretension, String tipoIncidente)
      throws IOException {
    Serenity.setSessionVariable(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor()).to(producto);
    ReclamacionEmpresarial reserva =
        new ReclamacionEmpresarial(
            obtenerDatosPrueba(RECLAMACION_EMPRESARIAL.getValor(), producto));
    busquedaPolizaStep.buscarPolizaEmpresarial(reserva.getLstReclamo());
    propiedadesImplicadasStep.seleccionarPropiedadImplicada();
    informacionBasicaReclamacionStep.diligenciarInformacionBasica(reserva.getLstReclamo());
    informacionReclamacionStep.diligenciarInformacionIncidente(
        causaSiniestro, valorPretension, tipoIncidente);
    nuevaReclamacionGuardadaStep.obtenerNumeroReclamacionGuardada();
  }

  @Dado("^que se tiene un (.*) y (.*)$")
  public void consultarReservaVidaGrupo(String producto, String estado) throws IOException {
    Serenity.setSessionVariable(SESION_CC_TIPO_PRODUCTO_EMPRESARIAL.getValor()).to(producto);
    ReclamacionEmpresarial consultarPoliza =
        new ReclamacionEmpresarial(
            obtenerDatosPrueba(RECLAMACION_EMPRESARIAL.getValor(), producto));
    busquedaPolizaStep.buscarPolizaPagoVidaGrupo(consultarPoliza.getLstReclamo());
    consultaDatoFinancieroTransaccionStep.verificarEstadoTransaccionReserva(estado);
  }

  @Cuando("^se ajuste la reserva con un valor de (.*)$")
  public void ajustarReserva(String ajusteReserva) {
    movimientoLineaReservaStep.ajustarReserva(ajusteReserva);
  }

  @Entonces(
      "^se obtiene una reversión de constitución y el deducible es generado por un valor (.*)$")
  public void verificarReversionConstitucion(String deducible) {
    consultaDatoFinancieroTransaccionStep.verificarDeducibleReserva(
        TIPO_CATEGORIA_COSTO_RESERVA, deducible);
  }
}
