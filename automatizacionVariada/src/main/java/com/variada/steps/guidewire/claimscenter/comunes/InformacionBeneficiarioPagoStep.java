package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.CUENTA;
import static com.variada.utils.enums.EnumConstantes.SELECCIONAR;

import com.variada.models.PagoSiniestro;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionBeneficiarioInteraction;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class InformacionBeneficiarioPagoStep {

  @Page
  InformacionBeneficiarioInteraction informacionBeneficiarioInteraction;

  @Page GeneralInteraction generalInteraction;

  @Step
  public void ingresarInformacionBeneficiarioPago(
      String strBeneficiarioPago,
      String strMetodoPago,
      String strPagoSoloSura,
      List<PagoSiniestro> lstPago) {
    for (PagoSiniestro diligenciador : lstPago) {
      informacionBeneficiarioInteraction.seleccionarNombreBeneficiario(
          strBeneficiarioPago);
      informacionBeneficiarioInteraction.seleccionarTipoBeneficiario(
          diligenciador.getTipoBeneficiario());
      informacionBeneficiarioInteraction.seleccionarMetodoPago(
          strMetodoPago, CUENTA.getValor(), SELECCIONAR.getValor());
      informacionBeneficiarioInteraction.seleccionarPagoSura(strPagoSoloSura);
      informacionBeneficiarioInteraction.seleccionarPais(diligenciador.getPais());
      informacionBeneficiarioInteraction.seleccionarDepartamento(
          diligenciador.getDepartamento());
      informacionBeneficiarioInteraction.seleccionarCiudad(diligenciador.getCiudad());
      informacionBeneficiarioInteraction.seleccionarTipoDireccion(
          diligenciador.getTipoDireccion());
      generalInteraction.continuarSiguientePantalla();
    }
  }
}
