package com.variada.definitions.empresariales.pagos;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;

import com.variada.models.PagoSiniestro;
import com.variada.models.ReclamacionEmpresarial;
import com.variada.models.Reserva;
import com.variada.steps.guidewire.claimscenter.comunes.BusquedaPolizaStep;
import com.variada.steps.guidewire.claimscenter.comunes.ExposicionStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionBasicaReclamacionStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionReclamacionStep;
import com.variada.steps.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaStep;
import com.variada.steps.guidewire.claimscenter.empresariales.PagoAutomaticoStep;
import static com.variada.utils.enums.EnumNombresCsv.RECLAMACION_EMPRESARIAL;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETRO_LINEA_RESERVA;
import static com.variada.utils.enums.EnumNombresCsv.PAGO_SINIESTRO;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class PagoAutomaticoSiniestroDefinition {

  ReclamacionEmpresarial reclamacionEmpresarial;

  private String productoPoliza;

  @Steps PagoAutomaticoStep pagoAutomaticoStep;

  @Steps BusquedaPolizaStep busquedaPolizaStep;

  @Steps
  InformacionBasicaReclamacionStep informacionBasicaReclamacionStep;

  @Steps InformacionReclamacionStep informacionReclamacionStep;

  @Steps NuevaReclamacionGuardadaStep nuevaReclamacionGuardadaStep;

  @Steps ExposicionStep exposicionStep;

  @Dado("^que se tiene una póliza del producto (.*)$")
  public void obtenerPoliza(String producto) throws IOException {
    productoPoliza = producto;
    reclamacionEmpresarial =
        new ReclamacionEmpresarial(
            obtenerDatosPrueba(RECLAMACION_EMPRESARIAL.getValor(), productoPoliza));
    busquedaPolizaStep.buscarPolizaEmpresarial(reclamacionEmpresarial.getLstReclamo());
  }

  @Cuando("^se realiza un siniestro por causa (.*) con valor de pretensión (.*) e incidente (.*)$")
  public void realizarSiniestro(String causa, String valorPretension, String tipoIncidente) {
    informacionBasicaReclamacionStep.diligenciarInformacionBasica(reclamacionEmpresarial.getLstReclamo());
    informacionReclamacionStep.diligenciarInformacionIncidente(
        causa, valorPretension, tipoIncidente);
  }

  @Entonces("^se genera una reclamación con exposición automática (.*)$")
  public void verificarGeneracionExposicionAutomatica(String tipoExposicion) {
    nuevaReclamacionGuardadaStep.abrirNuevaReclamacionGuardada();
    exposicionStep.validarExposicionEmpresariales(tipoExposicion);
  }

  @Entonces("^una reserva automática$")
  public void verificarGeneracionReservaAutomatica() throws IOException {
    Reserva reserva =
        new Reserva(
            obtenerDatosPrueba(PARAMETRO_LINEA_RESERVA.getValor(), productoPoliza));
    pagoAutomaticoStep.verificarMontoReservaAutomatica(reserva.getLstReserva());
  }

  @Entonces("^un pago automático$")
  public void verificarGeneracionPagoAutomatico() throws IOException {
    PagoSiniestro pago =
        new PagoSiniestro(obtenerDatosPrueba(PAGO_SINIESTRO.getValor(), productoPoliza));
    pagoAutomaticoStep.verificarPagoAutomatico(pago.getLstPago());
  }
}
