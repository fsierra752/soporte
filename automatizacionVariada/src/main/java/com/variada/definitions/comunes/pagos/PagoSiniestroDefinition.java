package com.variada.definitions.comunes.pagos;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumFiltros.CLASE_VEHICULO;
import static com.variada.utils.enums.EnumFiltros.EXPOSICION_MANUAL_VEHICULAR;
import static com.variada.utils.enums.EnumFiltros.EXPOSICION_VEHICULAR_TERCERO;
import static com.variada.utils.enums.EnumNombresCsv.CODIGO_FASECOLDA;
import static com.variada.utils.enums.EnumNombresCsv.PAGO_SINIESTRO;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_NAVEGACION_MENU_ACCIONES;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETRO_RESPONSABILIDAD_CIVIL_VEHICULO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_LINEA_RESERVA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_COBERTURA_AFECTADA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_TIPO_PAGO;

import com.variada.steps.guidewire.claimscenter.comunes.ValidacionSarlaftStep;
import com.variada.models.ExposicionVehiculoTercero;
import com.variada.models.PagoSiniestro;
import com.variada.models.Vehiculo;
import com.variada.steps.guidewire.claimscenter.autos.ExposicionVehicularManualStep;
import com.variada.steps.guidewire.claimscenter.autos.PagoPrimaPendienteStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionBeneficiarioPagoStep;
import com.variada.steps.guidewire.claimscenter.comunes.InformacionPagoStep;
import com.variada.steps.guidewire.claimscenter.comunes.InstruccionPagoStep;
import com.variada.steps.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaStep;
import com.variada.steps.guidewire.claimscenter.comunes.NuevoPagoStep;
import com.variada.steps.guidewire.claimscenter.empresariales.InclusionProcesoAuditoriaStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class PagoSiniestroDefinition {

  PagoSiniestro pagoSiniestro;

  @Steps NuevoPagoStep nuevoPagoStep;

  @Steps ExposicionVehicularManualStep nuevaExposicionVehiculoStep;

  @Steps InformacionBeneficiarioPagoStep informacionBeneficiarioPagoStep;

  @Steps InformacionPagoStep informacionPagoStep;

  @Steps InstruccionPagoStep instruccionPagoStep;

  @Steps InclusionProcesoAuditoriaStep inclusionProcesoAuditoriaStep;

  @Steps NuevaReclamacionGuardadaStep nuevaReclamacionGuardadaStep;

  @Steps PagoPrimaPendienteStep pagoPrimaPendienteStep;

  @Steps ValidacionSarlaftStep validacionSarlaftStep;

  @Cuando(
      "^se genere un pago (.*) al beneficiario (.*) por el medio de pago de (.*) sobre la línea de reserva (.*) donde el responsable (.*) es Sura, (.*) tiene prima pendiente y se aplican las siguientes retenciones$")
  public void crearPagoPerdidaTotal(
      String tipoPago,
      String beneficiarioPago,
      String metodoPago,
      String lineaReserva,
      String aplicaSoloSura,
      String primaPendiente,
      DataTable codigoRetencion)
      throws IOException {
    nuevoPagoStep.declararReclamacionPerdidaTotal();
    nuevoPagoStep.ingresarEstadoLegalReclamacion();
    pagoSiniestro =
        new PagoSiniestro(
            (obtenerDatosPrueba(
                PAGO_SINIESTRO.getValor(),
                Serenity.sessionVariableCalled(SESION_CC_TIPO_COBERTURA_AFECTADA.getValor()))));
    crearNuevoPago(beneficiarioPago, metodoPago, aplicaSoloSura,lineaReserva, tipoPago,primaPendiente, codigoRetencion);
  }

  @Cuando(
      "^se genere un pago por siniestro de auto (.*) al beneficiario (.*) por el medio de pago de (.*) sobre las líneas de reserva (.*) cuyo responsable (.*) es Sura donde existe (.*) vehículo involucrado del tercero en el siniestro, (.*) tiene prima pendiente y se aplican las siguientes retenciones$")
  public void crearMultiPago(
      String tipoPago,
      String beneficiarioPago,
      String metodoPago,
      String lineaReserva,
      String aplicaSoloSura,
      int numeroVehiculosInvolucradosTercero,
      String primaPendiente,
      DataTable codigoRetencion)
      throws IOException {
    Vehiculo datosVehiculos;
    nuevaExposicionVehiculoStep.consultarPlacaAsegurado();
    ExposicionVehiculoTercero exposicionVehiculoTercero =
        new ExposicionVehiculoTercero(
            obtenerDatosPrueba(
                PARAMETRO_RESPONSABILIDAD_CIVIL_VEHICULO.getValor(),
                EXPOSICION_VEHICULAR_TERCERO.getValor()));
    datosVehiculos =
        new Vehiculo(obtenerDatosPrueba(CODIGO_FASECOLDA.getValor(), CLASE_VEHICULO.getValor()));
    nuevaExposicionVehiculoStep.crearExposicionVehicularManual(
        obtenerDatosPrueba(
            PARAMETROS_NAVEGACION_MENU_ACCIONES.getValor(), EXPOSICION_MANUAL_VEHICULAR.getValor()),
        exposicionVehiculoTercero.getLstExposicionTerceros(),
        numeroVehiculosInvolucradosTercero,
        datosVehiculos.getLstVehiculos());
    nuevoPagoStep.declararReclamacionPerdidaTotal();
    nuevoPagoStep.ingresarEstadoLegalReclamacion();
    pagoSiniestro =
        new PagoSiniestro(
            (obtenerDatosPrueba(
                PAGO_SINIESTRO.getValor(),
                Serenity.sessionVariableCalled(SESION_CC_TIPO_COBERTURA_AFECTADA.getValor()))));
    crearNuevoPago(beneficiarioPago, metodoPago, aplicaSoloSura,lineaReserva, tipoPago,primaPendiente,codigoRetencion);
  }

  @Entonces("^se genera una orden de pago para que le sea entregado al usuario$")
  public void verificarPago() {
    instruccionPagoStep.verificarPagoRealizado(pagoSiniestro.getLstPago());
  }

  @Cuando("^(.*)se notifique el proceso al área de auditoría$")
  public void notificarProcesoAuditoria(String requiereAuditoria) {
    nuevaReclamacionGuardadaStep.obtenerNumeroReclamacionGuardada();
    inclusionProcesoAuditoriaStep.marcarAuditoria(requiereAuditoria);
  }

  @Dado("^que se declaró la reclamación como perdida total$")
  public void declararReclamacionPerdidaTotal() {
    nuevoPagoStep.marcarReclamacionAutosPerdidaTotal();
  }

  @Dado("^que se tiene una reclamación que tuvo un pago (.*) al beneficiario (.*) por el medio de pago de (.*) sobre la línea de reserva (.*) con cobertura de  (.*) donde el responsable (.*) es Sura, (.*) tiene prima pendiente y se aplicaron las siguientes retenciones$")
  @Cuando("^se realiza un pago (.*) al beneficiario (.*) por el medio de pago de (.*) sobre la línea de reserva (.*) con cobertura de (.*) donde el responsable (.*) es Sura, (.*) tiene prima pendiente y se aplican las siguientes retenciones$")
  public void ingresarPagoReclamacion(
      String tipoPago,
      String beneficiarioPago,
      String metodoPago,
      String lineaReserva,
      String cobertura,
      String aplicaSoloSura,
      String primaPendiente,
      DataTable codigoRetencion)
      throws IOException {
    pagoSiniestro =
        new PagoSiniestro(
            (obtenerDatosPrueba(String.valueOf(PAGO_SINIESTRO.getValor()), cobertura)));
    crearNuevoPago(beneficiarioPago, metodoPago, aplicaSoloSura,lineaReserva, tipoPago,primaPendiente,codigoRetencion);
  }

  @Dado("^la póliza esta marcada como financiada, con prima pendiente por pagar$")
  public void verificarExistenciaPrimaPendiente() {
    pagoPrimaPendienteStep.verificarEstadoPrimaPendiente();
  }

  @Entonces("^se generan el registro de prima pendiente$")
  public void verificarPagoPrimaPendiente() {
    pagoPrimaPendienteStep.verificarValorPagoPrimaPendiente();
  }

  @Entonces("^se genera el registro del valor del pago menos la prima pendiente")
  public void verificarValorPago() {
    pagoPrimaPendienteStep.verificarValorPagoMenosPrimaPendiente();
  }

  @Dado("^se valida en sarlaft el beneficiario (.*)$")
  public void validarSarlaftBeneficiario(String beneficiarioPago) throws IOException {
    validacionSarlaftStep.validarSarlaft(beneficiarioPago);
  }

  private void crearNuevoPago(String beneficiarioPago, String metodoPago, String aplicaSoloSura, String lineaReserva, String tipoPago, String primaPendiente,DataTable codigoRetencion){
    List<String> retencion = codigoRetencion.asList(String.class);
    nuevoPagoStep.crearNuevoPago();
    informacionBeneficiarioPagoStep.ingresarInformacionBeneficiarioPago(
            beneficiarioPago, metodoPago, aplicaSoloSura, pagoSiniestro.getLstPago());
    informacionPagoStep.ingresarInformacionPago(lineaReserva, tipoPago, pagoSiniestro.getLstPago());
    informacionPagoStep.seleccionarPrimaPendiente(primaPendiente);
    informacionPagoStep.ingresarInformacionRetencion(
            retencion, Serenity.sessionVariableCalled(SESION_CC_TIPO_PAGO.getValor()));
    instruccionPagoStep.finalizarCreacionPago(
            pagoSiniestro.getLstPago(),
            Serenity.sessionVariableCalled(SESION_CC_LINEA_RESERVA.getValor()));
  }
}
