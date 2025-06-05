package com.variada.steps.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumConstantes.COMODIN;
import static com.variada.utils.enums.EnumConstantes.OPCION_MENU;
import static com.variada.utils.enums.EnumConstantes.PLACA;
import static com.variada.utils.enums.EnumConstantes.RECLAMANTE_CONDUCTOR_AFECTADO;
import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;

import com.variada.models.ExposicionVehiculoTercero;
import com.variada.models.Vehiculo;
import com.variada.pages.interactions.guidewire.claimscenter.autos.CreacionServicioInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.autos.NuevoIncidenteVehicularInteraction;
import java.util.List;
import java.util.Map;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.CalculadoraCodigoFasecoldaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.NuevaExposicionVehiculoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ResumenReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.DetalleVehiculoInteraction;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class ExposicionVehicularManualStep {

  @Page
  MenuClaimInteraction menuClaimInteraction;

  @Page
  ResumenReclamacionInteraction resumenReclamacionInteraction;

  @Page
  NuevaExposicionVehiculoInteraction nuevaExposicionVehiculoInteraction;

  @Page NuevoIncidenteVehicularInteraction nuevoIncidenteVehicularInteraction;

  @Page
  CalculadoraCodigoFasecoldaInteraction calculadoraCodigoFasecoldaInteraction;

  @Page
  DetalleVehiculoInteraction detalleVehiculoInteraction;

  @Page CreacionServicioInteraction creacionServicioInteraction;

  @Step
  public void consultarPlacaAsegurado() {
    Serenity.setSessionVariable(PLACA.getValor())
        .to(resumenReclamacionInteraction.consultarNumeroPlaca());
  }

  @Step
  public void crearExposicionVehicularManual(
      List<Map<String, String>> opcionesCrearExposicion,
      List<ExposicionVehiculoTercero> datosVehiculoTercero,
      int numeroVehiculosInvolucradosTercero,
      List<Vehiculo> datosVehiculos) {
    for (int j = 0; j <= numeroVehiculosInvolucradosTercero - 1; j++) {
      menuClaimInteraction.seleccionarBotonAcciones();
      for (int i = 0; i < opcionesCrearExposicion.size(); i++) {
        if (opcionesCrearExposicion
            .listIterator(i)
            .next()
            .get(OPCION_MENU.getValor())
            .equals(COMODIN.getValor())) {
          opcionesCrearExposicion
              .listIterator(i)
              .next()
              .replace(
                  OPCION_MENU.getValor(),
                  COMODIN.getValor(),
                  Serenity.sessionVariableCalled(PLACA.getValor()));
        }
        String opcionMenu =
            opcionesCrearExposicion.listIterator(i).next().get(OPCION_MENU.getValor());
        menuClaimInteraction.seleccionarOpcionMenuAccionesPrimerNivel(opcionMenu);
      }
      nuevaExposicionVehiculoInteraction.seleccionarReclamanteExposicion();
      nuevaExposicionVehiculoInteraction.seleccionarTipoReclamanteExposicion(
          RECLAMANTE_CONDUCTOR_AFECTADO.getValor());
        crearNuevoIncidenteVehicular(datosVehiculoTercero,datosVehiculos,j);
      nuevaExposicionVehiculoInteraction.actualizarNuevaExposicion();
    }
  }

  @Step
  private void crearNuevoIncidenteVehicular(List<ExposicionVehiculoTercero> datosVehiculoTercero,
                                            List<Vehiculo> datosVehiculos,
                                            int contador){
      nuevaExposicionVehiculoInteraction.crearNuevoIncidenteVehicular();
      nuevoIncidenteVehicularInteraction.ingresarPlacaVehiculoAfectado(datosVehiculoTercero, contador);
      nuevoIncidenteVehicularInteraction.consultarInformacionVehiculoAfectado();
      if (nuevoIncidenteVehicularInteraction.validarPlacaExisteFasecolda()) {
          diligenciarFasecolda(datosVehiculos);
      }
      diligenciarLugarAtencion(datosVehiculoTercero);
      nuevoIncidenteVehicularInteraction.seleccionarConductorVehiculoAfectado();
      nuevoIncidenteVehicularInteraction.aceptarOpcion();
  }

  @Step
  private void diligenciarFasecolda(List<Vehiculo> datosVehiculos){
      datosVehiculos.forEach(
              formularioCodigoFasecolda -> {
                  calculadoraCodigoFasecoldaInteraction.diligenciarClaseVehiculo(
                          formularioCodigoFasecolda.getClaseVehiculo());
                  calculadoraCodigoFasecoldaInteraction.diligenciarModeloVehiculo(
                          formularioCodigoFasecolda.getModelo());
                  calculadoraCodigoFasecoldaInteraction.diligenciarMarcaVehiculo(
                          formularioCodigoFasecolda.getMarca());
                  calculadoraCodigoFasecoldaInteraction.diligenciarLineaVehiculo(
                          formularioCodigoFasecolda.getLinea());
                  calculadoraCodigoFasecoldaInteraction.generarCodigoFasecolda();
                  calculadoraCodigoFasecoldaInteraction.crearCodigoFasecoldaVehiculo();
              });
  }

  @Step
  private void diligenciarLugarAtencion(List<ExposicionVehiculoTercero> datosVehiculoTercero){
      datosVehiculoTercero.forEach(
              formularioLugarAtencion -> {
                  nuevoIncidenteVehicularInteraction.seleccionarLugarAtencion(
                          formularioLugarAtencion.getLugarAtencion());
                  nuevoIncidenteVehicularInteraction.seleccionarPaisAtencion(
                          formularioLugarAtencion.getPaisAtencion());
                  nuevoIncidenteVehicularInteraction.seleccionarDepartamentoAtencion(
                          formularioLugarAtencion.getDepartamentoAtencion());
                  nuevoIncidenteVehicularInteraction.seleccionarCiudadAtencion(
                          formularioLugarAtencion.getCiudadAtencion());
                  nuevoIncidenteVehicularInteraction.seleccionarDireccionAtencion(
                          formularioLugarAtencion.getDireccionAtencion());
              });
      agregarServicioTaller(datosVehiculoTercero);
  }

  @Step
  private void agregarServicioTaller(List<ExposicionVehiculoTercero> datosVehiculoTercero){
      nuevoIncidenteVehicularInteraction.seleccionarServiciosTaller();
      nuevoIncidenteVehicularInteraction.seleccionarTaller();
      detalleVehiculoInteraction.buscarProveedor();
      detalleVehiculoInteraction.realizarEsperaCarga();
      creacionServicioInteraction.seleccionarProveedor(
              datosVehiculoTercero
                      .get(Integer.parseInt(VALOR_CERO.getValor()))
                      .getTallerReparacionAsignado());
      detalleVehiculoInteraction.aceptarOpcion();
  }
}
