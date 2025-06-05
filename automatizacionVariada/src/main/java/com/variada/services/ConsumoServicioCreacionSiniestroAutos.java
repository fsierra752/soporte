package com.variada.services;

import static com.variada.utils.constantes.MenuConstante.NUMERO_DIAS_RESTAR_FECHA;
import static com.variada.utils.constantes.MenuConstante.TIPO_POLIZA_RETROACTIVA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_PC_NUMERO_POLIZA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PLACA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;
import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionAuto;
import com.variada.models.Vehiculo;
import com.variada.utils.Fecha;
import com.variada.utils.Utilidades;
import com.sura.service.cliente.siniestro.CreacionSiniestroAutoCliente;
import com.sura.service.creacionSiniestro.gen.ClaimsRequest;
import com.sura.service.creacionSiniestro.gen.ClaimsResponse;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.springframework.web.bind.annotation.RequestMapping;

public class ConsumoServicioCreacionSiniestroAutos {
  int campoDato = 0;
  CreacionSiniestroAutosFactory creacionSiniestroAutosFactory = new CreacionSiniestroAutosFactory();

  @RequestMapping
  public void asignarParametrosRequest(
      List<ReclamacionAuto> lstParametrosSiniestro,
      List<PersonaReclamacion> lstPersonaLesionada,
      List<PersonaReclamacion> lstConductor,
      List<Vehiculo> lstParametrosVehiculo) {
    asignarParametrosSiniestro(lstParametrosSiniestro);
    asignarParametrosAutor(lstConductor);
    asignarParametrosValorPerdida(lstParametrosSiniestro);
    asignarParametrosContactoPrincipal(lstConductor);
    asignarParametrosDireccionPrincipal(lstConductor);
    asignarParametrosInformacionSiniestro(lstParametrosSiniestro);
    asignarParametrosIncidenteVehiculo(lstParametrosSiniestro);
    asignarParametrosConductorVehiculo(lstConductor);
    asignarParametrosDireccionConductor(lstConductor);
    asignarParametrosDireccionContactoPrincipal(lstParametrosSiniestro);
    asignarParametrosVehiculo(lstParametrosVehiculo);
    asignarParametrosReclamante(lstConductor);
    asignarParametrosDescripcionSiniestro(lstParametrosSiniestro);
    asignarParametrosIncidenteLesion(lstPersonaLesionada);
    asignarParametrosLesionado(lstPersonaLesionada);
    agregarDireccionLesionado(lstParametrosSiniestro);
    asignarParametrosDetalleParteCuerpo(lstPersonaLesionada);
    asignarParametrosDireccionSiniestro(lstParametrosSiniestro);
    crearRequest();
    obtenerResponse();
  }

  private void asignarParametrosSiniestro(List<ReclamacionAuto> lstParametrosSiniestro) {
    String fechaSiniestro =
        Fecha.obtenerFechaInicioVigenciaSegunTerminoInicio(
                NUMERO_DIAS_RESTAR_FECHA, TIPO_POLIZA_RETROACTIVA);
    String fechaNotificacionSiniestro =
        Fecha.obtenerFechaInicioVigenciaSegunTerminoInicio(
                NUMERO_DIAS_RESTAR_FECHA, TIPO_POLIZA_RETROACTIVA);

    creacionSiniestroAutosFactory.setPolicyNumber(lstParametrosSiniestro.get(campoDato).getNumPoliza());
    creacionSiniestroAutosFactory.setLossDate(fechaSiniestro);
    creacionSiniestroAutosFactory.setNotificationDate(fechaNotificacionSiniestro);
    creacionSiniestroAutosFactory.setLossType(lstParametrosSiniestro.get(campoDato).getTipoPerdida());
    creacionSiniestroAutosFactory.setLossCause(lstParametrosSiniestro.get(campoDato).getCausaPerdida());
    creacionSiniestroAutosFactory.setDescription(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechos());
    creacionSiniestroAutosFactory.setMacaNumber(lstParametrosSiniestro.get(campoDato).getNumeroMaca());
    creacionSiniestroAutosFactory.setFaultRating(
        lstParametrosSiniestro.get(campoDato).getCodigoCulpabilidad());
    creacionSiniestroAutosFactory.setAuthorUser(
        lstParametrosSiniestro.get(campoDato).getIdentificacionAutor());
    creacionSiniestroAutosFactory.setIsSuspect(lstParametrosSiniestro.get(campoDato).getSospechoso());
    creacionSiniestroAutosFactory.setSuspectDesc(
        Serenity.getCurrentSession().get(SESION_PC_NUMERO_POLIZA).toString());
    creacionSiniestroAutosFactory.setOriginCause(lstParametrosSiniestro.get(campoDato).getOrigenCausa());
    creacionSiniestroAutosFactory.setSegment(lstParametrosSiniestro.get(campoDato).getSegmento());
    creacionSiniestroAutosFactory.setAuthorityTransit(
        lstParametrosSiniestro.get(campoDato).getAutoridadTransito());
  }

  private void asignarParametrosAutor(List<PersonaReclamacion> lstConductor) {
    creacionSiniestroAutosFactory.setDocumentType(lstConductor.get(campoDato).getTipoDocumento());
    creacionSiniestroAutosFactory.setTaxIDAuthor(lstConductor.get(campoDato).getNumDocumento());
  }

  private void asignarParametrosValorPerdida(List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setAmountLossEstimate(
        lstParametrosSiniestro.get(campoDato).getValorPerdidaSiniestro());
    creacionSiniestroAutosFactory.setCurrencyLossEstimate(
        lstParametrosSiniestro.get(campoDato).getTipoMonedaPoliza());
  }

  private void asignarParametrosContactoPrincipal(
      List<PersonaReclamacion> lstPersonaReclamacionAuto) {
    creacionSiniestroAutosFactory.setDocumentTypeMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getTipoDocumento());
    creacionSiniestroAutosFactory.setTaxIDMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getNumDocumento());
    creacionSiniestroAutosFactory.setEmailAddress1MainContact(
        lstPersonaReclamacionAuto.get(campoDato).getCorreoElectronico());
    creacionSiniestroAutosFactory.setCellNumberMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getCelular());
    creacionSiniestroAutosFactory.setFirstNameMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getPrimerNombre());
    creacionSiniestroAutosFactory.setMiddleNameMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getSegundoNombre());
    creacionSiniestroAutosFactory.setLastNameMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getPrimerApellido());
    creacionSiniestroAutosFactory.setSecondLastNameMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getSegundoApellido());
    creacionSiniestroAutosFactory.setWorkNumberMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getNumeroTrabajo());
    creacionSiniestroAutosFactory.setPolicyRoleMainContact(
        lstPersonaReclamacionAuto.get(campoDato).getPolicyRole());
  }

  private void asignarParametrosDireccionPrincipal(List<PersonaReclamacion> lstConductor) {
    creacionSiniestroAutosFactory.setAddressLine1MainContact(
        lstConductor.get(campoDato).getDireccion());
    creacionSiniestroAutosFactory.setAddressTypeMainContact(
        lstConductor.get(campoDato).getTipoDireccion());
    creacionSiniestroAutosFactory.setCityMainContact(lstConductor.get(campoDato).getCiudad());
  }

  private void asignarParametrosInformacionSiniestro(List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setDescription(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
  }

  private void asignarParametrosIncidenteVehiculo(List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setDescriptionVehicleIncident(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
    creacionSiniestroAutosFactory.setRepairShopVehicleIncident(
        lstParametrosSiniestro.get(campoDato).getTallerReparacion());
    creacionSiniestroAutosFactory.setLossPartyVehicleIncident(
        lstParametrosSiniestro.get(campoDato).getPartePerdida());
    creacionSiniestroAutosFactory.setDriverRelationVehicleIncident(
        lstParametrosSiniestro.get(campoDato).getRelacionAsegurado());
  }

  private void asignarParametrosConductorVehiculo(List<PersonaReclamacion> lstConductor) {
    creacionSiniestroAutosFactory.setFirstNameDriver(lstConductor.get(campoDato).getPrimerNombre());
    creacionSiniestroAutosFactory.setMiddleNameDriver(
        lstConductor.get(campoDato).getSegundoNombre());
    creacionSiniestroAutosFactory.setLastNameDriver(
        lstConductor.get(campoDato).getPrimerApellido());
    creacionSiniestroAutosFactory.setSecondLastNameDriver(
        lstConductor.get(campoDato).getSegundoApellido());
    creacionSiniestroAutosFactory.setWorkNumberDriver(
        lstConductor.get(campoDato).getNumeroTrabajo());
    creacionSiniestroAutosFactory.setCellNumberDriver(lstConductor.get(campoDato).getCelular());
    creacionSiniestroAutosFactory.setEmailAddress1Driver(
        lstConductor.get(campoDato).getCorreoElectronico());
    creacionSiniestroAutosFactory.setPolicyRoleDriver(lstConductor.get(campoDato).getPolicyRole());
    creacionSiniestroAutosFactory.setDocumentTypeDriver(
        lstConductor.get(campoDato).getTipoDocumento());
    creacionSiniestroAutosFactory.setTaxIDDriver(lstConductor.get(campoDato).getNumDocumento());
    String conductorTerceroAfectado;
    conductorTerceroAfectado =
        lstConductor.get(campoDato).getPrimerNombre()
            + " "
            + lstConductor.get(campoDato).getSegundoNombre()
            + " "
            + lstConductor.get(campoDato).getPrimerApellido()
            + " "
            + lstConductor.get(campoDato).getSegundoApellido();
    Serenity.setSessionVariable(SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO.getValor())
        .to(conductorTerceroAfectado);
  }

  private void asignarParametrosDireccionConductor(List<PersonaReclamacion> lstConductor) {
    creacionSiniestroAutosFactory.setAddressLine1Driver(lstConductor.get(campoDato).getDireccion());
    creacionSiniestroAutosFactory.setAddressTypeDriver(
        lstConductor.get(campoDato).getTipoDireccion());
    creacionSiniestroAutosFactory.setCityDriver(lstConductor.get(campoDato).getCiudad());
  }

  private void asignarParametrosDireccionContactoPrincipal(
      List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setAddressLine1MainContact(
        lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroAutosFactory.setAddressTypeMainContact(
        lstParametrosSiniestro.get(campoDato).getTipoDireccion());
    creacionSiniestroAutosFactory.setCityMainContact(lstParametrosSiniestro.get(campoDato).getCiudad());
  }

  private void asignarParametrosVehiculo(List<Vehiculo> lstParametrosVehiculo) {
    creacionSiniestroAutosFactory.setLicensePlateVehicle(
        Serenity.getCurrentSession().get(SESION_CC_NUMERO_PLACA).toString());
    creacionSiniestroAutosFactory.setMakeVehicle(lstParametrosVehiculo.get(campoDato).getMarca());
    creacionSiniestroAutosFactory.setModelVehicle(lstParametrosVehiculo.get(campoDato).getModelo());
    creacionSiniestroAutosFactory.setEngineNumberVehicle(
        lstParametrosVehiculo.get(campoDato).getMotor());
    creacionSiniestroAutosFactory.setYearVehicle(lstParametrosVehiculo.get(campoDato).getAnio());
    creacionSiniestroAutosFactory.setColorVehicle(lstParametrosVehiculo.get(campoDato).getColor());
    creacionSiniestroAutosFactory.setVehicleType(lstParametrosVehiculo.get(campoDato).getTipoVehiculo());
    creacionSiniestroAutosFactory.setFasecoldaCode(
        lstParametrosVehiculo.get(campoDato).getCodigoFasecolda());
    creacionSiniestroAutosFactory.setVinVehicle(lstParametrosVehiculo.get(campoDato).getChasis());
  }

  private void asignarParametrosReclamante(List<PersonaReclamacion> lstConductor) {
    creacionSiniestroAutosFactory.setDocumentTypeAnt(
        lstConductor.get(campoDato).getTipoDocumento());
    creacionSiniestroAutosFactory.setContactNameAnt(lstConductor.get(campoDato).getPrimerNombre());
    creacionSiniestroAutosFactory.setTaxIdAnt(lstConductor.get(campoDato).getNumDocumento());
    creacionSiniestroAutosFactory.setEmailAddress1Ant(
        lstConductor.get(campoDato).getCorreoElectronico());
    creacionSiniestroAutosFactory.setCellNumberAnt(lstConductor.get(campoDato).getCelular());
  }

  private void asignarParametrosDescripcionSiniestro(List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setDescription(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
  }

  private void asignarParametrosIncidenteLesion(List<PersonaReclamacion> lstPersonaLesionada) {
    creacionSiniestroAutosFactory.setLossPartyInjuryIncident(
        lstPersonaLesionada.get(campoDato).getParteLesionada());
    creacionSiniestroAutosFactory.setSeverityInjuryIncident(
        lstPersonaLesionada.get(campoDato).getGravedadLesion());
    creacionSiniestroAutosFactory.setDescriptionInjuryIncident(
        lstPersonaLesionada.get(campoDato).getDescripcionLesion());
    creacionSiniestroAutosFactory.setGeneralInjuryType(
        lstPersonaLesionada.get(campoDato).getLesionGeneral());
    creacionSiniestroAutosFactory.setDetailedInjuryType(
        lstPersonaLesionada.get(campoDato).getDetalleLesion());
  }

  private void asignarParametrosLesionado(List<PersonaReclamacion> lstPersonaLesionada) {
    creacionSiniestroAutosFactory.setFirstNameInjured(
        lstPersonaLesionada.get(campoDato).getPrimerNombre());
    creacionSiniestroAutosFactory.setMiddleNameInjured(
        lstPersonaLesionada.get(campoDato).getSegundoNombre());
    creacionSiniestroAutosFactory.setLastNameInjured(
        lstPersonaLesionada.get(campoDato).getPrimerApellido());
    creacionSiniestroAutosFactory.setSecondLastNameInjured(
        lstPersonaLesionada.get(campoDato).getSegundoApellido());
    creacionSiniestroAutosFactory.setWorkNumberInjured(
        lstPersonaLesionada.get(campoDato).getNumeroTrabajo());
    creacionSiniestroAutosFactory.setCellNumberInjured(
        lstPersonaLesionada.get(campoDato).getCelular());
    creacionSiniestroAutosFactory.setEmailAddress1Injured(
        lstPersonaLesionada.get(campoDato).getCorreoElectronico());
    creacionSiniestroAutosFactory.setDocumentTypeInjured(
        lstPersonaLesionada.get(campoDato).getTipoDocumento());
    creacionSiniestroAutosFactory.setTaxIDInjured(
        lstPersonaLesionada.get(campoDato).getNumDocumento());
  }

  private void agregarDireccionLesionado(List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setAddressLine1Injured(
        lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroAutosFactory.setAddressTypeInjured(
        lstParametrosSiniestro.get(campoDato).getTipoDireccion());
    creacionSiniestroAutosFactory.setCityInjured(lstParametrosSiniestro.get(campoDato).getCiudad());
  }

  private void asignarParametrosDetalleParteCuerpo(List<PersonaReclamacion> lstPersonaLesionada) {
    creacionSiniestroAutosFactory.setPrimaryBodyPart1(
        lstPersonaLesionada.get(campoDato).getParteCuerpo());
    creacionSiniestroAutosFactory.setDetailedBodyPartType1(
        lstPersonaLesionada.get(campoDato).getDetalleParteCuerpo());
    creacionSiniestroAutosFactory.setPrimaryBodyPart2(
        lstPersonaLesionada.get(campoDato).getParteCuerpo());
    creacionSiniestroAutosFactory.setDetailedBodyPartType2(
        lstPersonaLesionada.get(campoDato).getDetalleParteCuerpo());
  }

  private void asignarParametrosDireccionSiniestro(List<ReclamacionAuto> lstParametrosSiniestro) {
    creacionSiniestroAutosFactory.setCountryLossLocation(
        lstParametrosSiniestro.get(campoDato).getDepartamento());
    creacionSiniestroAutosFactory.setAddressLine1LossLocation(
        lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroAutosFactory.setCityLossLocation(lstParametrosSiniestro.get(campoDato).getCiudad());
  }

  private ClaimsRequest crearRequest() {
    return creacionSiniestroAutosFactory.creacionSiniestroAutoRequestFactory();
  }

  private void obtenerResponse() {
    ClaimsResponse response;
    CreacionSiniestroAutoCliente creacionSiniestroAutoCliente = new CreacionSiniestroAutoCliente();
    response = creacionSiniestroAutoCliente.claimsResponse(crearRequest());
    Utilidades.getLogger()
        .info(
            String.format(
                "[contains(.,'Número de siniestro: %s')]", response.getResult().getClaimNumber()));
    Serenity.setSessionVariable(SESION_CC_NUMERO_SINIESTRO.getValor())
        .to(response.getResult().getClaimNumber());
  }
}
