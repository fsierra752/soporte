package com.variada.services;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_SINIESTRO;

import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionEmpresarial;
import com.variada.utils.Utilidades;
import com.sura.service.cliente.siniestro.CreacionSiniestroCliente;
import com.sura.service.creacionSiniestro.gen.ClaimsRequest;
import com.sura.service.creacionSiniestro.gen.ClaimsResponse;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.springframework.web.bind.annotation.RequestMapping;

public class ConsumoServicioCreacionSiniestro {
  int campoDato = 0;
  CreacionSiniestroFactory creacionSiniestroFactory = new CreacionSiniestroFactory();
  CreacionSiniestroCliente creacionSiniestroCliente = new CreacionSiniestroCliente();
  ClaimsResponse response;

  @RequestMapping
  public void asignarParametrosRequest(
      List<ReclamacionEmpresarial> lstParametrosSiniestro,
      List<PersonaReclamacion> lstParametroPersona) {
    asignarParametrosSiniestro(lstParametrosSiniestro);
    asignarParametrosAutor(lstParametroPersona);
    asignarParametrosValorPerdida(lstParametrosSiniestro);
    asignarParametrosContactoPrincipal(lstParametroPersona);
    asignarParametrosDireccionPrincipal(lstParametrosSiniestro);
    asignarParametrosTipoIncidente(lstParametrosSiniestro);
    asignarParametrosInformacionSiniestro(lstParametrosSiniestro);
    asignarParametrosDireccionSiniestro(lstParametrosSiniestro);
    asignarParametrosReclamante(lstParametroPersona);
    asignarParametrosDescripcionPropiedad(lstParametrosSiniestro);
    asignarParametrosDescripcionSiniestro(lstParametrosSiniestro);
    asignarParametrosLocalizacionPropiedad(lstParametrosSiniestro);
    crearRequest();
    obtenerResponse();
  }

  private void asignarParametrosSiniestro(List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setPolicyNumber(lstParametrosSiniestro.get(campoDato).getNumPoliza());
    creacionSiniestroFactory.setDescriptionLoss(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
    creacionSiniestroFactory.setNotificationDate(
        lstParametrosSiniestro.get(campoDato).getFechaAvisoSiniestro());
    creacionSiniestroFactory.setLossDate(lstParametrosSiniestro.get(campoDato).getFechaSiniestro());
    creacionSiniestroFactory.setAuthorUser(
        lstParametrosSiniestro.get(campoDato).getIdentificacionAutor());
    creacionSiniestroFactory.setLossCause(lstParametrosSiniestro.get(campoDato).getCausaPerdida());
  }

  private void asignarParametrosAutor(List<PersonaReclamacion> lstParametroPersona) {
    creacionSiniestroFactory.setDocumentTypeAuthor(
        lstParametroPersona.get(campoDato).getTipoDocumento());
    creacionSiniestroFactory.setTaxIdAuthor(lstParametroPersona.get(campoDato).getNumDocumento());
    creacionSiniestroFactory.setNameAuthor(lstParametroPersona.get(campoDato).getPrimerNombre());
  }

  private void asignarParametrosValorPerdida(List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setAmountLossEstimate(
        lstParametrosSiniestro.get(campoDato).getValorPerdidaSiniestro());
    creacionSiniestroFactory.setCurrencyLossEstimate(
        lstParametrosSiniestro.get(campoDato).getTipoMonedaPoliza());
  }

  private void asignarParametrosContactoPrincipal(List<PersonaReclamacion> lstParametroPersona) {
    creacionSiniestroFactory.setDocumentTypeMainContact(
        lstParametroPersona.get(campoDato).getTipoDocumento());
    creacionSiniestroFactory.setContactNameMainContact(
        lstParametroPersona.get(campoDato).getPrimerNombre());
    creacionSiniestroFactory.setTaxIdMainContact(
        lstParametroPersona.get(campoDato).getNumDocumento());
    creacionSiniestroFactory.setEmailAddress1MainContact(
        lstParametroPersona.get(campoDato).getCorreoElectronico());
    creacionSiniestroFactory.setCellNumberMainContact(
        lstParametroPersona.get(campoDato).getCelular());
  }

  private void asignarParametrosDireccionPrincipal(List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setStateMainContact(lstParametrosSiniestro.get(campoDato).getPais());
    creacionSiniestroFactory.setAddressLine1MainContact(
        lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroFactory.setCityMainContact(lstParametrosSiniestro.get(campoDato).getCiudad());
    creacionSiniestroFactory.setStateAnt(lstParametrosSiniestro.get(campoDato).getPais());
    creacionSiniestroFactory.setAddressLine1Ant(lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroFactory.setCityAnt(lstParametrosSiniestro.get(campoDato).getCiudad());
  }

  private void asignarParametrosTipoIncidente(List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setPolicySystemId(
        lstParametrosSiniestro.get(campoDato).getIdentificadorRiesgo());
    creacionSiniestroFactory.setFixedPropertyIncident(
        lstParametrosSiniestro.get(campoDato).getIncidentePropiedad());
    creacionSiniestroFactory.setPropertyContentsIncident(
        lstParametrosSiniestro.get(campoDato).getIncidenteContenido());
  }

  private void asignarParametrosInformacionSiniestro(
      List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setDescription(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
    creacionSiniestroFactory.setIsPolicyProperty(
        lstParametrosSiniestro.get(campoDato).getEsPolizaPropiedad());
  }

  private void asignarParametrosDireccionSiniestro(List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setStateProperty(lstParametrosSiniestro.get(campoDato).getPais());
    creacionSiniestroFactory.setAddressLine1Property(
        lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroFactory.setCityProperty(lstParametrosSiniestro.get(campoDato).getCiudad());
  }

  private void asignarParametrosReclamante(List<PersonaReclamacion> lstParametroPersona) {
    creacionSiniestroFactory.setDocumentTypeAnts(
        lstParametroPersona.get(campoDato).getTipoDocumento());
    creacionSiniestroFactory.setContactNameAnts(
        lstParametroPersona.get(campoDato).getPrimerNombre());
    creacionSiniestroFactory.setTaxIdAnts(lstParametroPersona.get(campoDato).getNumDocumento());
    creacionSiniestroFactory.setEmailAddress1Ants(
        lstParametroPersona.get(campoDato).getCorreoElectronico());
    creacionSiniestroFactory.setCellNumberAnt(lstParametroPersona.get(campoDato).getCelular());
  }

  private void asignarParametrosDescripcionPropiedad(
      List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setPropertyDesc(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
  }

  private void asignarParametrosDescripcionSiniestro(
      List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setDescription(
        lstParametrosSiniestro.get(campoDato).getDescripcionHechosSiniestro());
  }

  private void asignarParametrosLocalizacionPropiedad(
      List<ReclamacionEmpresarial> lstParametrosSiniestro) {
    creacionSiniestroFactory.setStateLossLocation(lstParametrosSiniestro.get(campoDato).getPais());
    creacionSiniestroFactory.setAddressLine1LossLocation(
        lstParametrosSiniestro.get(campoDato).getDireccion());
    creacionSiniestroFactory.setCityLossLocation(lstParametrosSiniestro.get(campoDato).getCiudad());
  }

  private ClaimsRequest crearRequest() {
    return creacionSiniestroFactory.creacionSiniestroRequestFactory();
  }

  private void obtenerResponse() {
    response = creacionSiniestroCliente.claimsResponse(crearRequest());
    Utilidades.getLogger()
        .info(
            String.format(
                "[contains(.,'Número de siniestro: %s')]", response.getResult().getClaimNumber()));
    Serenity.setSessionVariable(SESION_CC_NUMERO_SINIESTRO.getValor())
        .to(response.getResult().getClaimNumber());
  }
}
