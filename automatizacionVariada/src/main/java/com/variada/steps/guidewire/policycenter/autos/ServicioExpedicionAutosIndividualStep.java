package com.variada.steps.guidewire.policycenter.autos;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_PC_NUMERO_POLIZA;
import static com.sura.service.util.enums.EnumUrlBase.URL_EDGE_AUTOS_EXPEDICION;
import static com.sura.service.util.enums.EnumCredencialesConsumoServicio.USR_EDGE_CA;

import com.variada.models.Asegurado;
import com.variada.models.CoberturaVehiculo;
import com.variada.models.Tomador;
import com.variada.models.Vehiculo;
import com.variada.models.comunes.ExpedicionAutosIndividualRequest;
import com.variada.models.comunes.ExpedicionAutosIndividualResponse;
import com.variada.services.ExpedicionAutosIndividualFactory;
import com.variada.utils.Fecha;
import com.sura.service.clientes.comunes.ClienteGenericoConsumoREST;
import java.text.ParseException;
import java.util.List;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class ServicioExpedicionAutosIndividualStep {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ServicioExpedicionAutosIndividualStep.class);

  @Steps
  ExpedicionAutosIndividualFactory expedicionAutosIndividualFactory =
      new ExpedicionAutosIndividualFactory();

  ResponseEntity<ExpedicionAutosIndividualResponse> responseServicioExpedicion;

  @Step("Asignar información de fechas")
  public void asignarInformacionFechas(
      String vigencia, String terminoInicioVigencia, int cantidadDias) {
    final String FORMATO_FECHA_YYYYMMDDTHHMMSSSSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    String fechaActual = Fecha.obtenerFechaActualFormato(FORMATO_FECHA_YYYYMMDDTHHMMSSSSSZ);
    String fechaInicioVigencia =
        Fecha.obtenerFechaInicioVigenciaSegunTerminoInicio(cantidadDias, terminoInicioVigencia);
    expedicionAutosIndividualFactory.setFechaInicioVigencia(fechaInicioVigencia);
    expedicionAutosIndividualFactory.setFechaFinVigencia(
        Fecha.obtenerFechaFinVigencia(fechaInicioVigencia, vigencia));
    expedicionAutosIndividualFactory.setFechaTarifa(fechaInicioVigencia);
    try {
      expedicionAutosIndividualFactory.setFechaActualizacion(
          Fecha.convertirFechaUnix(fechaActual, FORMATO_FECHA_YYYYMMDDTHHMMSSSSSZ));
    } catch (ParseException e) {
      LOGGER.error("No se pudo convertir la fecha correctamente", 0);
    }
  }

  @Step("Asignar información del cliente")
  public void asignarInformacionCliente(Tomador tomador, Asegurado asegurado) {
    expedicionAutosIndividualFactory.setTomador(tomador);
    expedicionAutosIndividualFactory.setAsegurado(asegurado);
  }

  @Step("Asignar información del vehículo")
  public void asignarInformacionVehiculo(
      Vehiculo vehiculo, List<CoberturaVehiculo> lstCoberturasVehiculo) {
    expedicionAutosIndividualFactory.setVehiculo(vehiculo);
    expedicionAutosIndividualFactory.setLstCoberturasVehiculo(lstCoberturasVehiculo);
  }

  @Step("Consumir servicio de expedición de autos individual")
  public void expedirPolizaIndividual() {
    responseServicioExpedicion =
        new ClienteGenericoConsumoREST<
                ExpedicionAutosIndividualRequest, ExpedicionAutosIndividualResponse>(
                URL_EDGE_AUTOS_EXPEDICION, USR_EDGE_CA)
            .ejecutarConsumo(
                expedicionAutosIndividualFactory.construirRequestExpedicion(),
                HttpMethod.POST,
                ExpedicionAutosIndividualResponse.class);
  }

  @Step("Verificar creación correcta de la póliza")
  public void verificarCreacionPoliza() {
    String numeroPoliza = responseServicioExpedicion.getBody().getResult().getQuotingData().getPolicyNumber();
    MatcherAssert.assertThat(
        "No se obtuvo el número de póliza correctamente"
            + responseServicioExpedicion.getBody().getResult(),numeroPoliza != null);
    Serenity.setSessionVariable(SESION_PC_NUMERO_POLIZA).to(numeroPoliza);
  }
}
