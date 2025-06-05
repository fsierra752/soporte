package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO;
import com.variada.models.ExposicionVehiculoTercero;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.NuevoIncidenteVehicularPage;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class NuevoIncidenteVehicularInteraction extends GeneralInteraction {

  @Page NuevoIncidenteVehicularPage nuevoIncidenteVehicularPage;

  public NuevoIncidenteVehicularInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void ingresarPlacaVehiculoAfectado(
      List<ExposicionVehiculoTercero> datosVehiculoTercero, int j) {
    int consecutivoPlacaTercero =
        Integer.parseInt(
            datosVehiculoTercero
                .get(Integer.parseInt(VALOR_CERO.getValor()))
                .getPlacaTercero()
                .substring(3, 6));
    consecutivoPlacaTercero = consecutivoPlacaTercero + j;
    String placaVehiculoTercero =
        datosVehiculoTercero
                .get(Integer.parseInt(VALOR_CERO.getValor()))
                .getPlacaTercero()
                .substring(0, 3)
            + Integer.toString(consecutivoPlacaTercero);
    nuevoIncidenteVehicularPage
        .txtPlacaVehiculo
        .waitUntilClickable()
        .sendKeys(placaVehiculoTercero);
    realizarEsperaCarga();
  }

  public void consultarInformacionVehiculoAfectado() {
    nuevoIncidenteVehicularPage
        .btnRecuperarInformacion
        .waitUntilVisible()
        .waitUntilClickable()
        .click();
    esperarCargaElemento();
  }

  public void seleccionarLugarAtencion(String lugarAtencion) {
    realizarEsperaCarga();
    nuevoIncidenteVehicularPage.cmbLugar.clear();
    nuevoIncidenteVehicularPage.cmbLugar.typeAndTab(lugarAtencion);
    realizarEsperaCarga();
  }

  public void seleccionarPaisAtencion(String pais) {
    realizarEsperaCarga();
    nuevoIncidenteVehicularPage.cmbPais.clear();
    nuevoIncidenteVehicularPage.cmbPais.typeAndTab(pais);
    realizarEsperaCarga();
  }

  public void seleccionarDepartamentoAtencion(String departamento) {
    nuevoIncidenteVehicularPage.cmbDepartamento.clear();
    nuevoIncidenteVehicularPage.cmbDepartamento.typeAndTab(departamento);
    realizarEsperaCarga();
  }

  public void seleccionarCiudadAtencion(String ciudad) {
    nuevoIncidenteVehicularPage.cmbCiudad.clear();
    realizarEsperaCarga();
    nuevoIncidenteVehicularPage.cmbCiudad.typeAndTab(ciudad);
    realizarEsperaCarga();
  }

  public void seleccionarDireccionAtencion(String direccion) {
    nuevoIncidenteVehicularPage.cmbDireccion.clear();
    nuevoIncidenteVehicularPage.cmbDireccion.typeAndTab(direccion);
    realizarEsperaCarga();
  }

  public void seleccionarConductorVehiculoAfectado() {
    String nombreConductorTercero =
        (Serenity.sessionVariableCalled(SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO.getValor())
            .toString());
    nuevoIncidenteVehicularPage.cmbNombreConductor.clear();
    nuevoIncidenteVehicularPage.cmbNombreConductor.waitUntilEnabled();
    nuevoIncidenteVehicularPage.cmbNombreConductor.typeAndTab(nombreConductorTercero).toString();
    realizarEsperaCarga();
  }

  public void seleccionarServiciosTaller() {
    nuevoIncidenteVehicularPage.chkServicioTaller.waitUntilClickable().click();
    realizarEsperaCarga();
  }

  public void seleccionarTaller() {
    nuevoIncidenteVehicularPage.btnAgregarTaller.waitUntilClickable().click();
  }

  public boolean validarPlacaExisteFasecolda() {
    if (nuevoIncidenteVehicularPage.btnGenerarCodigoFasecolda.isVisible()) {
      nuevoIncidenteVehicularPage.btnGenerarCodigoFasecolda.click();
      realizarEsperaCarga();
      return true;
    } else {
      seleccionarConductorVehiculoAfectado();
      return false;
    }
  }
}
