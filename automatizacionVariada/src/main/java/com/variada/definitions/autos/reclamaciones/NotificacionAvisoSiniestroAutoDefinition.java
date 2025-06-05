package com.variada.definitions.autos.reclamaciones;

import static com.variada.utils.UtilidadesCSV.obtenerDatosPrueba;
import static com.variada.utils.enums.EnumFiltros.DIRECCION_EXPOSICION_LESIONES;
import static com.variada.utils.enums.EnumFiltros.DIRECCION_EXPOSICION_VEHICULAR;
import static com.variada.utils.enums.EnumFiltros.EXPOSICIONES_ARCHIVO;
import static com.variada.utils.enums.EnumFiltros.EXPOSICIONES_RESPONSABILIDAD_CIVIL;
import static com.variada.utils.enums.EnumFiltros.EXPOSICIONES_SOLO_RESPONSABILIDAD_CIVIL;
import static com.variada.utils.enums.EnumFiltros.LINEA_RESERVA_ARCHIVO;
import static com.variada.utils.enums.EnumFiltros.RECLAMACION_RESPONSABILIDAD_CIVIL;
import static com.variada.utils.enums.EnumFiltros.RECLAMACION_SOLO_RESPONSABILIDAD_CIVIL;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_DIRECCION_SINIESTRO;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_EXPOSICION_AUTOMATICA;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_PERSONA;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_RECLAMACION;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_VEHICULO;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETRO_LINEA_RESERVA;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETRO_RESPONSABILIDAD_CIVIL_LESIONES;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETRO_RESPONSABILIDAD_CIVIL_VEHICULO;

import com.variada.models.ExposicionLesiones;
import com.variada.models.ExposicionVehiculoTercero;
import com.variada.models.ExposicionesAutomaticasAutos;
import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionAuto;
import com.variada.models.Reserva;
import com.variada.models.Vehiculo;
import com.variada.steps.guidewire.claimscenter.autos.NuevoAvisoSiniestroAutoStep;
import com.variada.steps.guidewire.claimscenter.comunes.ConsultaDatoFinancieroResumenStep;
import com.variada.steps.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class NotificacionAvisoSiniestroAutoDefinition {

  @Steps private NuevoAvisoSiniestroAutoStep reclamacionStep;

  @Steps ConsultaDatoFinancieroResumenStep consultaDatoFinancieroResumenStep;

  @Steps private NuevaReclamacionGuardadaStep nuevaReclamacionGuardadaStep;

  private ReclamacionAuto reclamacionAuto;
  private Vehiculo vehiculo;
  private Reserva reserva;
  private ExposicionesAutomaticasAutos exposicionesAutomaticasAutos;

  @Cuando(
      "^se genere un siniestro por la causa y la culpabilidad Responsabilidad civil daños persona y Responsabilidad civil daños vehículo$")
  public void ingresarDatosSiniestroResponsabilidadCivil(DataTable parametrosSiniestro)
      throws IOException {
    ExposicionVehiculoTercero exposicionVehiculoTercero;
    final String RESPONSABILIDAD_CIVIL_LESIONES = parametrosSiniestro.cells().get(1).get(2);
    final String RESPONSABILIDAD_CIVIL_VEHICULO = parametrosSiniestro.cells().get(1).get(3);
    exposicionVehiculoTercero =
        new ExposicionVehiculoTercero(
            obtenerDatosPrueba(
                PARAMETRO_RESPONSABILIDAD_CIVIL_VEHICULO.getValor(),
                RESPONSABILIDAD_CIVIL_VEHICULO));
    PersonaReclamacion personaResponsabilidadCivilVehiculo =
        new PersonaReclamacion(
            obtenerDatosPrueba(PARAMETROS_PERSONA.getValor(), RESPONSABILIDAD_CIVIL_VEHICULO));
    ReclamacionAuto direccionExposicionVehicularTercero =
        new ReclamacionAuto(
            obtenerDatosPrueba(
                PARAMETROS_DIRECCION_SINIESTRO.getValor(),
                DIRECCION_EXPOSICION_VEHICULAR.getValor()));
    PersonaReclamacion personaReclamacionLesionado =
        new PersonaReclamacion(
            obtenerDatosPrueba(PARAMETROS_PERSONA.getValor(), RESPONSABILIDAD_CIVIL_LESIONES));
    ReclamacionAuto direccionExposicionLesionado =
        new ReclamacionAuto(
            obtenerDatosPrueba(
                PARAMETROS_DIRECCION_SINIESTRO.getValor(),
                DIRECCION_EXPOSICION_LESIONES.getValor()));
    ExposicionLesiones exposicionLesiones =
        new ExposicionLesiones(
            obtenerDatosPrueba(
                PARAMETRO_RESPONSABILIDAD_CIVIL_LESIONES.getValor(),
                RESPONSABILIDAD_CIVIL_LESIONES));
    reclamacionStep.crearAvisoResponsabilidadCivil(
        reclamacionAuto.getLstReclamacionAuto(),
        exposicionVehiculoTercero.getLstExposicionTerceros(),
        personaResponsabilidadCivilVehiculo.getLstPersonaReclamacion(),
        direccionExposicionVehicularTercero.getLstReclamacionAuto(),
        personaReclamacionLesionado.getLstPersonaReclamacion(),
        direccionExposicionLesionado.getLstReclamacionAuto(),
        exposicionLesiones.getLstExposicionLesiones());
    nuevaReclamacionGuardadaStep.abrirNuevaReclamacionGuardada();
  }

  @Entonces(
      "^se obtendrán exposiciones automáticas y cada una con su respectiva reserva, según la culpabilidad marcada Responsabilidad Civil$")
  public void generarReclamacionResponsabilidadCivil() throws IOException {
    exposicionesAutomaticasAutos =
        new ExposicionesAutomaticasAutos(
            obtenerDatosPrueba(
                PARAMETROS_EXPOSICION_AUTOMATICA.getValor(),
                EXPOSICIONES_RESPONSABILIDAD_CIVIL.getValor()));
    validarExposicionesAutomaticas();
    reserva =
        new Reserva(
            obtenerDatosPrueba(
                PARAMETRO_LINEA_RESERVA.getValor(), RECLAMACION_RESPONSABILIDAD_CIVIL.getValor()));
    consultaDatoFinancieroResumenStep.validarValorReservas(reserva.getLstReserva());
  }

  @Dado("^que se tiene una póliza con las coberturas para una reclamación tipo (.*)$")
  public void recibirReclamo(String tipoReclamacion) throws IOException {
    reclamacionAuto =
        new ReclamacionAuto(
            obtenerDatosPrueba(PARAMETROS_RECLAMACION.getValor(), tipoReclamacion));
    vehiculo =
        new Vehiculo(
            obtenerDatosPrueba(PARAMETROS_VEHICULO.getValor(), tipoReclamacion));
    reclamacionStep.consultarPoliza(reclamacionAuto.getLstReclamacionAuto());
  }

  @Cuando("se genere un siniestro$")
  public void ingresarDatosSiniestro() {
    reclamacionStep.crearAvisoPerdidaParcialDanos(reclamacionAuto.getLstReclamacionAuto());
    nuevaReclamacionGuardadaStep.abrirNuevaReclamacionGuardada();
  }

  @Entonces(
      "^se obtendrán exposiciones automáticas de exposición, y cada una con su respectiva reserva, según la culpabilidad marcada Archivo$")
  public void generarReclamacionArchivo() throws IOException {
    exposicionesAutomaticasAutos =
        new ExposicionesAutomaticasAutos(
            obtenerDatosPrueba(
                PARAMETROS_EXPOSICION_AUTOMATICA.getValor(), EXPOSICIONES_ARCHIVO.getValor()));
    validarExposicionesAutomaticas();
    reserva =
        new Reserva(
            obtenerDatosPrueba(
                PARAMETRO_LINEA_RESERVA.getValor(), LINEA_RESERVA_ARCHIVO.getValor()));
    consultaDatoFinancieroResumenStep.validarValorReservas(reserva.getLstReserva());
  }

  @Entonces(
      "^se obtendrán las exposiciones automáticas para cada tipo de responsabilidad, con su respectiva reserva$")
  public void generarReclamacionSoloResponsabilidadCivil() throws IOException {
    exposicionesAutomaticasAutos =
        new ExposicionesAutomaticasAutos(
            obtenerDatosPrueba(
                PARAMETROS_EXPOSICION_AUTOMATICA.getValor(),
                EXPOSICIONES_SOLO_RESPONSABILIDAD_CIVIL.getValor()));
    validarExposicionesAutomaticas();
    reserva =
        new Reserva(
            obtenerDatosPrueba(
                PARAMETRO_LINEA_RESERVA.getValor(),
                RECLAMACION_SOLO_RESPONSABILIDAD_CIVIL.getValor()));
    consultaDatoFinancieroResumenStep.validarValorReservas(reserva.getLstReserva());
  }

  private void validarExposicionesAutomaticas() {
    reclamacionStep.validarExposicion(exposicionesAutomaticasAutos.getLstExposiciones());
  }
}
