package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.DESARROLLO;
import static com.variada.utils.enums.EnumConstantes.LABORATORIO;
import static net.serenitybdd.core.pages.PageObject.withParameters;

import com.variada.models.Credencial;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.LoginClaimInteraction;
import com.variada.utils.AmbientesUtil;
import java.io.IOException;
import java.util.List;

import com.variada.utils.UtilidadesCSV;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.fluentlenium.core.annotation.Page;

public class IniciarSesionAplicativosStep {

  @Steps Credencial credencial;
  @Page LoginClaimInteraction loginClaimInteraction;

  AmbientesUtil ambientesUtil = new AmbientesUtil();
  @Step
  public void abrirClaims() {
    loginClaimInteraction.open(ambientesUtil.getAmbiente(), withParameters(""));
  }

  @Step
  public void iniciarSesionUAT(List<Credencial> datosCredencial) {
    datosCredencial.forEach(
        dato -> loginClaimInteraction.iniciarSesionLAB(dato.getUsuario(), dato.getContrasena()));
    if (loginClaimInteraction.mensajeLoginCredenciales()){
      loginClaimInteraction.evadirMensajeLoginCredenciales();
      datosCredencial.forEach(
              dato -> loginClaimInteraction.iniciarSesionLAB(dato.getUsuario(), dato.getContrasena()));
    }
  }

  @Step
  public void iniciarSesionDllo(List<Credencial> datosCredencial) {
    datosCredencial.forEach(
        dato -> loginClaimInteraction.iniciarSesionDLLO(dato.getUsuario(), dato.getContrasena()));
  }

  @Step
  public void iniciarSesionLab(String analista) throws IOException {
    credencial = new Credencial(UtilidadesCSV.obtenerDatosPrueba("credencial", analista));
    abrirClaims();
    iniciarSesionUAT(credencial.getCredenciales());
  }

  @Step
  public void iniciarSesionAmbienteDllo() throws IOException {
    credencial = new Credencial(UtilidadesCSV.obtenerDatosPrueba("credencial", "analistaDllo"));
    abrirClaims();
    iniciarSesionDllo(credencial.getCredenciales());
  }

  @Step("Se inicia autenticación en Claims Center (CC)")
  public void elegirInicioSesionCC(String rolEmpleado) throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      iniciarSesionLab(rolEmpleado);
    } else if (DESARROLLO.getValor().equals(ambientesUtil.getAmbiente())) {
      iniciarSesionAmbienteDllo();
    }
  }
}
