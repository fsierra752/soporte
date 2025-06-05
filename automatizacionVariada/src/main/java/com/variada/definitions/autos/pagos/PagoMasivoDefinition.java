package com.variada.definitions.autos.pagos;

import static com.variada.utils.enums.EnumFiltros.EXPOSICION_VEHICULAR_TERCERO;
import static com.variada.utils.enums.EnumFiltros.CLASE_VEHICULO;
import static com.variada.utils.enums.EnumFiltros.EXPOSICION_MANUAL_VEHICULAR;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETRO_RESPONSABILIDAD_CIVIL_VEHICULO;
import static com.variada.utils.enums.EnumNombresCsv.CODIGO_FASECOLDA;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_NAVEGACION_MENU_ACCIONES;
import static com.variada.utils.enums.EnumNombresCsv.PAGO_MASIVO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;
import static com.variada.utils.constantes.MenuConstante.ESCRITORIO_MENU;
import static com.variada.utils.constantes.MenuConstante.FACTURAS_VOLUMEN_MENU;
import static com.variada.utils.constantes.MenuConstante.DATOS_FINANCIEROS;
import static com.variada.utils.constantes.MenuConstante.PAGOS;

import com.variada.models.Vehiculo;
import com.variada.models.CodigoFasecolda;
import com.variada.models.Exposicion;
import com.variada.models.ExposicionVehiculoTercero;
import com.variada.models.PagoSiniestro;
import com.variada.models.Reserva;
import com.variada.steps.guidewire.claimscenter.autos.CargaArchivoPagoMasivoStep;
import com.variada.steps.guidewire.claimscenter.autos.DetalleFacturaVolumenStep;
import com.variada.steps.guidewire.claimscenter.autos.ResultadoArchivoProcesadoStep;
import com.variada.steps.guidewire.claimscenter.autos.ResultadoValidacionArchivoStep;
import com.variada.steps.guidewire.claimscenter.autos.BusquedaLibretaContactoStep;
import com.variada.steps.guidewire.claimscenter.autos.ProcesoBatchStep;
import com.variada.steps.guidewire.claimscenter.autos.FacturaVolumenStep;
import com.variada.steps.guidewire.claimscenter.autos.DatoFinancieroPagoStep;
import com.variada.steps.guidewire.claimscenter.autos.ExposicionVehicularManualStep;
import com.variada.steps.guidewire.claimscenter.comunes.DetalleSiniestroStep;
import com.variada.steps.guidewire.claimscenter.comunes.MenuClaimsStep;

import com.variada.utils.UtilidadesCSV;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class PagoMasivoDefinition {

  ExposicionVehiculoTercero exposicionVehiculoTercero = new ExposicionVehiculoTercero();

  @Steps
  CargaArchivoPagoMasivoStep cargaArchivoPagoMasivoStep;

  @Steps
  ResultadoValidacionArchivoStep resultadoValidacionArchivoStep;

  @Steps DetalleSiniestroStep detalleSiniestroStep;

  @Steps
  ResultadoArchivoProcesadoStep resultadoArchivoProcesadoStep;

  @Steps
  DetalleFacturaVolumenStep detalleFacturaVolumenStep;

  @Steps
  BusquedaLibretaContactoStep busquedaLibretaContactoStep;

  @Steps ProcesoBatchStep procesoBatchStep;

  @Steps FacturaVolumenStep facturaVolumenStep;

  @Steps MenuClaimsStep menuClaimsStep;

  @Steps DatoFinancieroPagoStep datoFinancieroPagoStep;

  @Steps ExposicionVehicularManualStep nuevaExposicionVehiculoStep;

  Exposicion datosExposicionPagoMasivo;

  Reserva datosReservaPagoMasivo;

  PagoSiniestro datosPagoSiniestroPagoMasivo;

  CodigoFasecolda datosCodigoFasecolda;

  @Cuando(
      "^se registra la información de las facturas del pago masivo a un proveedor de (.*) vehículos involucrados en el siniestro con coberturas (.*)")
  public void ingresarInformacionFactura(
      int numeroVehiculosInvolucradosTercero, String coberturasPoliza) throws IOException {
    Vehiculo datosVehiculos = new Vehiculo();
    nuevaExposicionVehiculoStep.consultarPlacaAsegurado();
    exposicionVehiculoTercero =
        new ExposicionVehiculoTercero(
                UtilidadesCSV.obtenerDatosPrueba(
                PARAMETRO_RESPONSABILIDAD_CIVIL_VEHICULO.getValor(),
                EXPOSICION_VEHICULAR_TERCERO.getValor()));
    datosCodigoFasecolda =
        new CodigoFasecolda(
            UtilidadesCSV.obtenerDatosPrueba(CODIGO_FASECOLDA.getValor(), CLASE_VEHICULO.getValor()));
    nuevaExposicionVehiculoStep.crearExposicionVehicularManual(
        UtilidadesCSV.obtenerDatosPrueba(
            PARAMETROS_NAVEGACION_MENU_ACCIONES.getValor(), EXPOSICION_MANUAL_VEHICULAR.getValor()),
        exposicionVehiculoTercero.getLstExposicionTerceros(),
        numeroVehiculosInvolucradosTercero,
        datosVehiculos.getLstVehiculos());
    detalleSiniestroStep.consultarInformacionSiniestro();
    datosExposicionPagoMasivo =
        new Exposicion(
                UtilidadesCSV.obtenerDatosPrueba(String.valueOf(PAGO_MASIVO.getValor()), coberturasPoliza));
    datosReservaPagoMasivo =
        new Reserva(
            UtilidadesCSV.obtenerDatosPrueba(String.valueOf(PAGO_MASIVO.getValor()), coberturasPoliza));
    datosPagoSiniestroPagoMasivo =
        new PagoSiniestro(
            UtilidadesCSV.obtenerDatosPrueba(String.valueOf(PAGO_MASIVO.getValor()), coberturasPoliza));
    cargaArchivoPagoMasivoStep.cargarArchivoXls(
        ESCRITORIO_MENU,
        FACTURAS_VOLUMEN_MENU,
        datosExposicionPagoMasivo.getLstExposicion(),
        datosReservaPagoMasivo.getLstReserva(),
        datosPagoSiniestroPagoMasivo.getLstPago());
    resultadoValidacionArchivoStep.validarNumeroRegistrosArchivo();
    resultadoArchivoProcesadoStep.consultarResultadoArchivoProcesado();
  }

  @Cuando(
      "^se ingresa el tipo de proveedor (.*) y el nombre del proveedor (.*) con el tipo de moneda (.*) de la factura y el método de pago (.*) del cheque")
  public void crearPagoMasivo(
      String tipoContacto, String contacto, String tipoMoneda, String metodoPago) {
    detalleFacturaVolumenStep.ingresarInformacionFactura(tipoMoneda, metodoPago);
    busquedaLibretaContactoStep.buscarContactoPagoMasivo(tipoContacto, contacto);
    detalleFacturaVolumenStep.crearPagoMasivo();
    procesoBatchStep.ejecutarProcesoBatch();
  }

  @Entonces(
      "^se genera un número de pago individual por cada uno de los pagos registrados en el archivo de pagos masivos con un estado de pago solicitado$")
  public void validarPagoMasivo() {
    facturaVolumenStep.buscarNumeroFacturaPagoMasivo(
        ESCRITORIO_MENU, FACTURAS_VOLUMEN_MENU);
    detalleFacturaVolumenStep.validarPagoMasivo();
    menuClaimsStep.consultarNumeroReclamacion(
        Serenity.sessionVariableCalled(SESION_CC_NUMERO_SINIESTRO.getValor()));
    datoFinancieroPagoStep.validarPagosIndividuales(
        DATOS_FINANCIEROS, PAGOS);
  }
}