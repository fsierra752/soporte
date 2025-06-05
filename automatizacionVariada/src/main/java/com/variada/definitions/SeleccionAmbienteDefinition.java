package com.variada.definitions;

import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_ATR;
import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_EMPRESARIAL;
import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_EMPRESARIAL_SUPER_USUARIO;
import static com.variada.utils.enums.EnumConstantes.DESARROLLO;
import static com.variada.utils.enums.EnumConstantes.LABORATORIO;
import static com.variada.utils.enums.EnumConstantes.SUPER_USUARIO;
import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_APRENDIZ;
import static com.variada.utils.enums.EnumConstantes.ANALISTA_RECLAMACION_EMPRESARIAL_PAGOS_SINIESTROS;

import com.variada.steps.guidewire.claimscenter.comunes.IniciarSesionAplicativosStep;
import com.variada.steps.guidewire.claimscenter.empresariales.LoginAtrStep;
import com.variada.utils.AmbientesUtil;
import io.cucumber.java.Before;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class SeleccionAmbienteDefinition {

  @Steps
  IniciarSesionAplicativosStep loginClaimStep;

  @Steps
  LoginAtrStep loginAtrStep;

  AmbientesUtil ambientesUtil = new AmbientesUtil();

  @Before("@claimsEmpresarial")
  public void seleccionarAmbienteEmpresarial() throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionLab(ANALISTA_RECLAMACION_EMPRESARIAL.getValor());
    } else if (DESARROLLO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionAmbienteDllo();
    }
  }

  @Before("@claimsEmpresarialVG")
  public void seleccionarAmbienteClaimCenter() throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionLab(ANALISTA_RECLAMACION_APRENDIZ.getValor());
    } else {
      loginClaimStep.iniciarSesionAmbienteDllo();
    }
  }

  @Before("@claimsEmpresarialVGPagoSiniestro")
  public void seleccionarAmbienteClaimCenterPagoSiniestro() throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionLab(ANALISTA_RECLAMACION_EMPRESARIAL_PAGOS_SINIESTROS.getValor());
    } else {
      loginClaimStep.iniciarSesionAmbienteDllo();
    }
  }

  @Before("@claimsEmpresarialSuperUsuario")
  public void seleccionarAmbienteEmpresarialSuperUsuario() throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionLab(ANALISTA_RECLAMACION_EMPRESARIAL_SUPER_USUARIO.getValor());
    } else if (DESARROLLO.equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionAmbienteDllo();
    }
  }

  @Before("@claimsAuto")
  public void seleccionarAmbienteAuto() throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginClaimStep.iniciarSesionLab(SUPER_USUARIO.getValor());
    } else {
      if (DESARROLLO.getValor().equals(ambientesUtil.getAmbiente())) {
        loginClaimStep.iniciarSesionAmbienteDllo();
      }
    }
  }

  @Before("@ATR")
  public void seleccionarAmbienteATR() throws IOException {
    if (LABORATORIO.getValor().equals(ambientesUtil.getAmbiente())) {
      loginAtrStep.obtenerCredenciales(ANALISTA_RECLAMACION_ATR.getValor());
    }
  }
}
