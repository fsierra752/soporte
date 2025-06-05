package com.variada.definitions.autos.reclamaciones;

import static com.variada.utils.enums.EnumNombresCsv.ASEGURADO;
import static com.variada.utils.enums.EnumNombresCsv.COBERTURAS_AUTOS;
import static com.variada.utils.enums.EnumNombresCsv.DICCIONARIO_COBERTURAS_AUTOS;
import static com.variada.utils.enums.EnumNombresCsv.PARAMETROS_VEHICULO;
import static com.variada.utils.enums.EnumNombresCsv.TOMADOR;
import static com.variada.utils.enums.EnumSeparador.SEPARADOR_VIRGULILLA;
import static com.variada.utils.enums.EnumFiltros.ASEGURADO_RIESGO_ESTANDAR;
import static com.variada.utils.enums.EnumFiltros.TOMADOR_RIESGO_ESTANDAR;
import static com.variada.utils.enums.EnumFiltros.VEHICULO_RIESGO_ESTANDAR;
import static com.variada.utils.enums.EnumConstantes.SUPER_USUARIO;

import com.variada.models.Asegurado;
import com.variada.models.CoberturaVehiculo;
import com.variada.models.Tomador;
import com.variada.models.Vehiculo;
import com.variada.steps.guidewire.claimscenter.comunes.IniciarSesionAplicativosStep;
import com.variada.steps.guidewire.policycenter.autos.ServicioExpedicionAutosIndividualStep;
import com.variada.utils.UtilidadesCSV;
import io.cucumber.java.es.Dado;
import java.io.IOException;
import java.util.List;
import net.thucydides.core.annotations.Steps;

public class CreacionPolizaIndividualDefinition {

  @Steps ServicioExpedicionAutosIndividualStep servicioExpedicionAutosIndividualStep;
  @Steps
  IniciarSesionAplicativosStep iniciarSesionAplicativosStep;

  @Dado(
      "^se tiene una póliza de autos individual con plan (.+) de vigencia (.+) con (\\d+) días de (.+)$")
  public void consumirServicioExpedicion(
      String planAutos, String tipoVigencia, int cantidadDias, String terminoInicioVigencia)
      throws IOException {
    final String FILTRO_COBERTURAS;
    iniciarSesionAplicativosStep.elegirInicioSesionCC(SUPER_USUARIO.getValor());
    Asegurado asegurado =
        new Asegurado(
            UtilidadesCSV.obtenerPrimerDatoPrueba(ASEGURADO.getValor(), ASEGURADO_RIESGO_ESTANDAR.getValor()));
    Tomador tomador =
        new Tomador(UtilidadesCSV.obtenerPrimerDatoPrueba(TOMADOR.getValor(), TOMADOR_RIESGO_ESTANDAR.getValor()));
    Vehiculo vehiculo =
        new Vehiculo(
            UtilidadesCSV.obtenerPrimerDatoPrueba(PARAMETROS_VEHICULO.getValor(), VEHICULO_RIESGO_ESTANDAR.getValor()));
    FILTRO_COBERTURAS =
        String.format(
            "%s%s%s%s%s",
            vehiculo.getClaseVehiculo(),
            SEPARADOR_VIRGULILLA.getValor(),
            planAutos,
            SEPARADOR_VIRGULILLA.getValor(),
            vehiculo.getModelo());
    List<CoberturaVehiculo> lstCoberturasVehiculo =
        CoberturaVehiculo.obtenerListaCoberturas(
            UtilidadesCSV.obtenerDatosPrueba(COBERTURAS_AUTOS.getValor(), FILTRO_COBERTURAS),
            UtilidadesCSV.obtenerDatosPrueba(DICCIONARIO_COBERTURAS_AUTOS.getValor(), ""));
    servicioExpedicionAutosIndividualStep.asignarInformacionFechas(
        tipoVigencia, terminoInicioVigencia, cantidadDias);
    servicioExpedicionAutosIndividualStep.asignarInformacionCliente(tomador, asegurado);
    servicioExpedicionAutosIndividualStep.asignarInformacionVehiculo(
        vehiculo, lstCoberturasVehiculo);
    servicioExpedicionAutosIndividualStep.expedirPolizaIndividual();
    servicioExpedicionAutosIndividualStep.verificarCreacionPoliza();

  }
}
