package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumNombresCsv.CREDENCIAL;
import static net.serenitybdd.core.pages.PageObject.withParameters;

import com.variada.models.Credencial;
import com.variada.pages.interactions.guidewire.claimscenter.empresariales.AutenticacionAtrInteraction;
import com.variada.utils.AmbientesUtil;
import java.io.IOException;
import java.util.List;

import com.variada.utils.UtilidadesCSV;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.fluentlenium.core.annotation.Page;

public class LoginAtrStep {

  @Page AutenticacionAtrInteraction autenticacionAtrInteraction;

  @Steps Credencial credencial;

  @Step
  public void abrirClaims() {
    AmbientesUtil ambientesUtil = new AmbientesUtil();
    autenticacionAtrInteraction.open(ambientesUtil.getAmbiente(), withParameters(""));
  }

  @Step
  public void iniciarSesionUAT(List<Credencial> datosCredencial) {
    datosCredencial.forEach(
        dato ->
            autenticacionAtrInteraction.iniciarSesionUAT(dato.getUsuario(), dato.getContrasena()));
  }

  @Step
  public void obtenerCredenciales(String analista) throws IOException {
    credencial = new Credencial(UtilidadesCSV.obtenerDatosPrueba(CREDENCIAL.getValor(), analista));
    abrirClaims();
    iniciarSesionUAT(credencial.getCredenciales());
  }
}
