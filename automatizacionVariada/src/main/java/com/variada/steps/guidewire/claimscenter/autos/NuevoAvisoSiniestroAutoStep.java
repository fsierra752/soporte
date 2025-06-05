package com.variada.steps.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumConstantes.TIPO_POLIZA_AUTOS;
import static com.variada.utils.enums.EnumConstantes.EXPOSICIONES;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_PC_NUMERO_POLIZA;
import static com.variada.utils.constantes.ReclamacionConstante.CULPABILIDAD_SOLO_RC;
import static com.variada.utils.constantes.MenuConstante.NUEVA_RECLAMACION_MENU;
import static com.variada.utils.constantes.MenuConstante.RECLAMACION_MENU;

import com.variada.models.ExposicionLesiones;
import com.variada.models.ExposicionVehiculoTercero;
import com.variada.models.ExposicionesAutomaticasAutos;
import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionAuto;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.autos.CreacionServicioInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.autos.DatosPeatonInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.BusquedaPolizaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DetalleVehiculoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionBasicaReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionSiniestroInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ExposicionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionGeneralNuevaExposicionInteraction;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class NuevoAvisoSiniestroAutoStep {

  @Page
  InformacionBasicaReclamacionInteraction informacionBasicaReclamacionInteraction;

  @Page
  BusquedaPolizaInteraction busquedaPolizaInteraction;

  @Page
  InformacionSiniestroInteraction informacionSiniestroInteraction;

  @Page
  DetalleVehiculoInteraction detalleVehiculoInteraction;

  @Page
  ExposicionInteraction exposicionInteraction;

  @Page DatosPeatonInteraction datosPeatonInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page CreacionServicioInteraction creacionServicioInteraction;

  @Page GeneralInteraction generalInteraction;

  @Page InformacionGeneralNuevaExposicionInteraction informacionGeneralNuevaExposicionInteraction;

  private void completarDetalleSiniestro(List<ReclamacionAuto> datosReclamacion) {
    datosReclamacion.forEach(
        dato -> {
          informacionSiniestroInteraction.cerrarVentanaEmergente();
          informacionSiniestroInteraction.seleccionarLugar(dato.getLugarSiniestro());
          informacionSiniestroInteraction.escribirSucedido(dato.getDescripcionHechos());
          informacionSiniestroInteraction.seleccionarCausa(dato.getCausaPerdida());
          informacionSiniestroInteraction.seleccionarOrigen(dato.getOrigenCausa());
          informacionSiniestroInteraction.escribirValorPretension(dato.getValorPretension());
          informacionSiniestroInteraction.seleccionarIntervinoAutoridad(dato.getAutoridadTransito());
        });
  }

  private void completarDatosReclamacionAutos(List<ReclamacionAuto> datosReclamacion) {
    for (ReclamacionAuto dato : datosReclamacion) {
      informacionSiniestroInteraction.seleccionarCulpabilidad(dato.getCulpabilidad());
    }
  }

  private void crearExposicionVehicular(
      List<ExposicionVehiculoTercero> datosExposicionTercero,
      List<PersonaReclamacion> datosPersonaReclamacion,
      List<ReclamacionAuto> datosReclamacionAuto) {
    informacionSiniestroInteraction.agregarExposicionVehiculoTercero();
    detalleVehiculoInteraction.agregarConductor();
    agregarPersonaConductor(datosPersonaReclamacion);
    agregarDireccionConductor(datosReclamacionAuto);
    agregarDatosExposicionTercero(datosExposicionTercero);
  }

  private void crearExposicionLesiones(
      List<PersonaReclamacion> datopersonaReclamacion,
      List<ReclamacionAuto> datosReclamacionAuto,
      List<ExposicionLesiones> datosExposicionLesiones) {
    datosPeatonInteraction.agregarPersonaLesionada();
    agregarPersonaLesionada(datopersonaReclamacion);
    agregarDireccionLesionado(datosReclamacionAuto);
    agregarDatosExposicionLesiones(datosExposicionLesiones);
  }

  private void agregarPersonaConductor(List<PersonaReclamacion> datosPersonaReclamacion) {
    for (PersonaReclamacion conductorVehiculoAfectado : datosPersonaReclamacion) {
      informacionGeneralNuevaExposicionInteraction.seleccionarTipoDocumento(
          conductorVehiculoAfectado.getTipoDocumento());
      informacionGeneralNuevaExposicionInteraction.ingresarNumeroDocumento(
          conductorVehiculoAfectado.getNumDocumento());
      informacionGeneralNuevaExposicionInteraction.ingresarPrimerNombre(
          conductorVehiculoAfectado.getPrimerNombre());
      informacionGeneralNuevaExposicionInteraction.ingresarPrimerApellido(
          conductorVehiculoAfectado.getPrimerApellido());
    }
  }

  private void agregarDireccionConductor(List<ReclamacionAuto> datosReclamacionAuto) {
    for (ReclamacionAuto direccionConductor : datosReclamacionAuto) {
      informacionGeneralNuevaExposicionInteraction.seleccionarDepartamento(
          direccionConductor.getDepartamento());
      informacionGeneralNuevaExposicionInteraction.seleccionarCiudad(
          direccionConductor.getCiudad());
      informacionGeneralNuevaExposicionInteraction.ingresarDireccion(
          direccionConductor.getDireccion());
      informacionGeneralNuevaExposicionInteraction.seleccionarTipoDireccion(
          direccionConductor.getTipoDireccion());
      generalInteraction.aceptarOpcion();
    }
  }

  private void agregarDatosExposicionTercero(
      List<ExposicionVehiculoTercero> datosExposicionTercero) {
    for (ExposicionVehiculoTercero datosVehiculo : datosExposicionTercero) {
      detalleVehiculoInteraction.ingresarVehiculoTercero(datosVehiculo.getPlacaTercero());
      detalleVehiculoInteraction.recuperarInformacionVehiculo();
      detalleVehiculoInteraction.seleccionarServicioTaller();
      detalleVehiculoInteraction.agregarTaller();
      detalleVehiculoInteraction.buscarProveedor();
      creacionServicioInteraction.seleccionarProveedor(datosVehiculo.getTallerReparacionAsignado());
      detalleVehiculoInteraction.aceptarOpcion();
      detalleVehiculoInteraction.volverPasoAnterior();
    }
  }

  private void agregarPersonaLesionada(List<PersonaReclamacion> datopersonaReclamacion) {
    for (PersonaReclamacion personaLesionada : datopersonaReclamacion) {
      informacionGeneralNuevaExposicionInteraction.seleccionarTipoDocumento(
          personaLesionada.getTipoDocumento());
      informacionGeneralNuevaExposicionInteraction.ingresarNumeroDocumento(
          personaLesionada.getNumDocumento());
      informacionGeneralNuevaExposicionInteraction.ingresarPrimerNombre(
          personaLesionada.getPrimerNombre());
      informacionGeneralNuevaExposicionInteraction.ingresarPrimerApellido(
          personaLesionada.getPrimerApellido());
    }
  }

  private void agregarDireccionLesionado(List<ReclamacionAuto> datosReclamacionAuto) {
    for (ReclamacionAuto direccionLesionado : datosReclamacionAuto) {
      informacionGeneralNuevaExposicionInteraction.seleccionarDepartamento(
          direccionLesionado.getDepartamento());
      informacionGeneralNuevaExposicionInteraction.seleccionarCiudad(
          direccionLesionado.getCiudad());
      informacionGeneralNuevaExposicionInteraction.ingresarDireccion(
          direccionLesionado.getDireccion());
      informacionGeneralNuevaExposicionInteraction.seleccionarTipoDireccion(
          direccionLesionado.getTipoDireccion());
    }
  }

  private void agregarDatosExposicionLesiones(List<ExposicionLesiones> datosExposicionLesiones) {
    for (ExposicionLesiones lesionesPersona : datosExposicionLesiones) {
      datosPeatonInteraction.seleccionarLesiones();
      datosPeatonInteraction.seleccionarGravedadLesion(lesionesPersona.getGravedadLesion());
      datosPeatonInteraction.ingresarDescripcionLesiones(lesionesPersona.getDescribirLesiones());
      datosPeatonInteraction.seleccionarTipoLesion(lesionesPersona.getTipoLesion());
      datosPeatonInteraction.seleccionarDetalleLesion(lesionesPersona.getDetallesTipoLesion());
      datosPeatonInteraction.finalizarExposicion();
    }
  }

  private void editarInformacionVehiculo(List<ReclamacionAuto> datosReclamacion) {
    informacionSiniestroInteraction.ingresarEdicionVehiculo();
    detalleVehiculoInteraction.agregarConductor();
    detalleVehiculoInteraction.seleccionarConductorVehiculoAsegurado();
    datosReclamacion.forEach(
        datoReclamacionAutos -> {
          if (!datoReclamacionAutos
              .getCulpabilidad()
              .equals(CULPABILIDAD_SOLO_RC)) {
            detalleVehiculoInteraction.seleccionarServicioTaller();
            detalleVehiculoInteraction.agregarTaller();
            detalleVehiculoInteraction.buscarProveedor();
            detalleVehiculoInteraction.realizarEsperaCarga();
            creacionServicioInteraction.seleccionarProveedor(
                datoReclamacionAutos.getTallerReparacion());
            detalleVehiculoInteraction.aceptarOpcion();
            detalleVehiculoInteraction.volverPasoAnterior();
          } else {
            detalleVehiculoInteraction.aceptarOpcion();
          }
        });
  }

  private void seleccionarNombreAutorReporte(List<ReclamacionAuto> lstReclamacionAuto) {
    lstReclamacionAuto.forEach(
        autorReporte -> {
          informacionBasicaReclamacionInteraction.seleccionarNombre();
          informacionBasicaReclamacionInteraction.validarMensajeAdvertenciaRelacionAsegurado(
              autorReporte.getRelacionAsegurado());
        });
  }

  @Step
  public void validarExposicion(List<ExposicionesAutomaticasAutos> datosExposicionAutomatica) {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(EXPOSICIONES.getValor());
    boolean exposicionAutomatica =
        exposicionInteraction.validarExposiciones(datosExposicionAutomatica);
    MatcherAssert.assertThat(
        "No coinciden todos los valores de las líneas de reserva", exposicionAutomatica);
  }

  @Step
  public void finalizarReclamacionAutos() {
    informacionSiniestroInteraction.concluirReclamacion();
  }

  @Step
  private void completarFormularioBuscarPoliza(List<ReclamacionAuto> datosReclamacion) {
    busquedaPolizaInteraction.seleccionarTipoPoliza(TIPO_POLIZA_AUTOS.getValor());
    busquedaPolizaInteraction.escribirNumeroPoliza(
        Serenity.getCurrentSession().get(SESION_PC_NUMERO_POLIZA).toString());

    for (ReclamacionAuto dato : datosReclamacion) {
      seleccionarFecha(dato.getFechaSiniestro());
      busquedaPolizaInteraction.seleccionarPais(dato.getPais());
    }
  }

  @Step
  private void seleccionarFecha(String fecha) {
    if ("Hoy".equals(fecha)) {
      busquedaPolizaInteraction.seleccionarFechaHoySiniestro();
    } else {
      busquedaPolizaInteraction.escribirFechaSiniestro(fecha);
    }
  }

  @Step
  private void seleccionarOpcionMenuPrincipal() {
    menuClaimInteraction.seleccionarOpcionMenuSegundoNivel(
        RECLAMACION_MENU, NUEVA_RECLAMACION_MENU);
  }

  @Step
  public void consultarPoliza(List<ReclamacionAuto> reclamacionAuto) {
    seleccionarOpcionMenuPrincipal();
    completarFormularioBuscarPoliza(reclamacionAuto);
    busquedaPolizaInteraction.buscarPoliza();
    busquedaPolizaInteraction.continuarSiguientePantalla();
  }

  @Step
  public void crearAvisoResponsabilidadCivil(
      List<ReclamacionAuto> datosReclamacionAuto,
      List<ExposicionVehiculoTercero> datosExposicionTercero,
      List<PersonaReclamacion> datosPersonaReclamacionVehiculoTercero,
      List<ReclamacionAuto> direccionExposicionVehicularTercero,
      List<PersonaReclamacion> datosPersonaLesionada,
      List<ReclamacionAuto> direccionExposicionLesionado,
      List<ExposicionLesiones> exposicionLesiones) {
    seleccionarNombreAutorReporte(datosReclamacionAuto);
    completarDetalleSiniestro(datosReclamacionAuto);
    editarInformacionVehiculo(datosReclamacionAuto);
    completarDatosReclamacionAutos(datosReclamacionAuto);
    crearExposicionVehicular(
        datosExposicionTercero,
        datosPersonaReclamacionVehiculoTercero,
        direccionExposicionVehicularTercero);
    crearExposicionLesiones(
        datosPersonaLesionada, direccionExposicionLesionado, exposicionLesiones);
    finalizarReclamacionAutos();
  }

  @Step
  public void crearAvisoPerdidaParcialDanos(List<ReclamacionAuto> lstReclamacionAuto) {
    seleccionarNombreAutorReporte(lstReclamacionAuto);
    completarDetalleSiniestro(lstReclamacionAuto);
    editarInformacionVehiculo(lstReclamacionAuto);
    completarDatosReclamacionAutos(lstReclamacionAuto);
    finalizarReclamacionAutos();
  }
}
