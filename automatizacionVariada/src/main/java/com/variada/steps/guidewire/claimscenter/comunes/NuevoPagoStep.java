package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.EXPOSICIONES;
import static com.variada.utils.enums.EnumConstantes.PAGOS;
import static com.variada.utils.enums.EnumConstantes.RESUMEN;
import static com.variada.utils.enums.EnumConstantes.ESTADO;

import com.variada.pages.interactions.guidewire.claimscenter.comunes.DetalleExposicionAutomaticaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ExposicionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ResumenReclamacionInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class NuevoPagoStep {

  @Page MenuClaimInteraction menuClaimInteraction;

  @Page
  ResumenReclamacionInteraction resumenReclamacionInteraction;

  @Page DetalleExposicionAutomaticaInteraction detalleExposicionAutomaticaInteraction;

  @Page ExposicionInteraction exposicionInteraction;

  @Step
  public void crearNuevoPago() {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
            RESUMEN.getValor(), ESTADO.getValor());
    resumenReclamacionInteraction.desmarcarCoberturaEnDuda();
    menuClaimInteraction.seleccionarBotonAcciones();
    menuClaimInteraction.seleccionarOpcionMenuAccionesPrimerNivel(PAGOS.getValor());
  }

  @Step
  public void declararReclamacionPerdidaTotal() {
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(EXPOSICIONES.getValor());
    exposicionInteraction.seleccionarExposicion();
    detalleExposicionAutomaticaInteraction.seleccionarCalculadoraPerdidaTotal();
    detalleExposicionAutomaticaInteraction.editarCalculadoraPerdidaTotal();
    detalleExposicionAutomaticaInteraction.seleccionarIncineracionTotalVehiculo();
    detalleExposicionAutomaticaInteraction.seleccionarMotorDestruidoFuego();
    detalleExposicionAutomaticaInteraction.seleccionarHabitaculoPasajerosIncinerado();
    detalleExposicionAutomaticaInteraction.actualizarCalculadoraPerdidaTotal();
  }

  @Step
  public void ingresarEstadoLegalReclamacion() {
    detalleExposicionAutomaticaInteraction.seleccionarDetalleExposicion();
    detalleExposicionAutomaticaInteraction.editarDetalleExposicion();
    detalleExposicionAutomaticaInteraction.ingresarEstadoLegalReclamacion();
    detalleExposicionAutomaticaInteraction.actualizarDetalleExposicion();
  }

  public void marcarReclamacionAutosPerdidaTotal() {
    declararReclamacionPerdidaTotal();
    ingresarEstadoLegalReclamacion();
  }

}
