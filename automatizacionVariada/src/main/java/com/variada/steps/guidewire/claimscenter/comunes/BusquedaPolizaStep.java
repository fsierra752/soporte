package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_VALOR_RESERVA_CONSTITUCION;
import static com.variada.utils.constantes.MenuConstante.RECLAMACION_MENU;
import static com.variada.utils.constantes.MenuConstante.NUEVA_RECLAMACION_MENU;

import com.variada.models.ReclamacionEmpresarial;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.BusquedaPolizaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import java.util.List;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class BusquedaPolizaStep {

  private static final String FECHA_HOY = "Hoy";

  @Page
  BusquedaPolizaInteraction busquedaPolizaInteraction;

  @Page GeneralInteraction generalInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  public void buscarPolizaEmpresarial(List<ReclamacionEmpresarial> datosPolizaEmpresarial) {
    menuClaimInteraction.seleccionarOpcionMenuSegundoNivel(
        RECLAMACION_MENU, NUEVA_RECLAMACION_MENU);
    datosPolizaEmpresarial.forEach(
        poliza -> {
          Serenity.setSessionVariable(SESION_CC_VALOR_RESERVA_CONSTITUCION.getValor())
              .to(poliza.getReservaTransaccion());
          busquedaPolizaInteraction.seleccionarOpcionBuscarPoliza();
          busquedaPolizaInteraction.escribirNumeroPoliza(poliza.getNumPoliza());
          if (FECHA_HOY.equals(poliza.getFechaSiniestro())) {
            busquedaPolizaInteraction.seleccionarFechaHoySiniestro();
          } else {
            busquedaPolizaInteraction.escribirFechaSiniestro(poliza.getFechaSiniestro());
          }
          generalInteraction.seleccionarPais(poliza.getPais());
          generalInteraction.seleccionarDepartamento(poliza.getDepartamento());
          busquedaPolizaInteraction.buscarPoliza();
          busquedaPolizaInteraction.seleccionarPoliza();
          generalInteraction.continuarSiguientePantalla();
        });
  }

  public void buscarPolizaAseguradoVidaGrupo(
      List<ReclamacionEmpresarial> datosPolizaEmpresarial, String numeroPoliza) {
    menuClaimInteraction.seleccionarOpcionMenuSegundoNivel(
        RECLAMACION_MENU, NUEVA_RECLAMACION_MENU);
    datosPolizaEmpresarial.forEach(
        poliza -> {
            busquedaPolizaInteraction.escribirNumeroDocumento(poliza.getNumeroIdentificacion());
            busquedaPolizaInteraction.seleccionarTipoDocumento(poliza.getTipoDocumento());
            if (FECHA_HOY.equals(poliza.getFechaSiniestro())) {
              busquedaPolizaInteraction.seleccionarFechaHoySiniestro();
            } else {
              busquedaPolizaInteraction.escribirFechaSiniestro(poliza.getFechaSiniestro());
            }
            generalInteraction.seleccionarPais(poliza.getPais());
            busquedaPolizaInteraction.buscarPoliza();
            busquedaPolizaInteraction.seleccionarPolizaAsegurado(numeroPoliza);
        });
  }

  @Step("Se busca la reclamacion generada para Vida Grupo")
  public void buscarPolizaPagoVidaGrupo(List<ReclamacionEmpresarial> datosPolizaEmpresarial) {
      datosPolizaEmpresarial.forEach(
              polizaVidaGrupo -> menuClaimInteraction.buscarReclamacion(RECLAMACION_MENU, polizaVidaGrupo.getNumPoliza())
      );

  }
}
