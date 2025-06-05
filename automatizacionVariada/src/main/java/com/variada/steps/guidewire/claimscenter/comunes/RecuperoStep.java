package com.variada.steps.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.CANTIDAD;
import static com.variada.utils.enums.EnumConstantes.CODIGO_RETENCION;

import com.variada.models.Recupero;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.CreacionRecuperoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuRecuperoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.NuevaReclamacionGuardadaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.VerificacionRecuperoInteraction;
import java.util.List;

import com.variada.utils.Utilidades;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class RecuperoStep {

  @Page CreacionRecuperoInteraction creacionRecuperoInteraction;

  @Page MenuRecuperoInteraction menuRecuperoInteraction;

  @Page VerificacionRecuperoInteraction verificacionRecuperoInteraction;

  @Page NuevaReclamacionGuardadaInteraction nuevaReclamacionGuardadaInteraction;

  @Step
  public void seleccionarNumeroReclamacion() {
    nuevaReclamacionGuardadaInteraction.obtenerNumeroReclamacion();
  }

  @Step
  public void diligenciarCreacionRecupero(List<Recupero> lstRecupero, String codigoRetencion) {
    menuRecuperoInteraction.ingresarMenuRecupero();
    lstRecupero.forEach(
        formulario -> {
          creacionRecuperoInteraction.seleccionarPagador(formulario.getPagador());
          creacionRecuperoInteraction.seleccionarLineaReserva(formulario.getLineaRecupero());
          creacionRecuperoInteraction.seleccionarPais(formulario.getPais());
          creacionRecuperoInteraction.seleccionarDepartamento(formulario.getDepartamento());
          creacionRecuperoInteraction.seleccionarCiudad(formulario.getCiudad());
          creacionRecuperoInteraction.seleccionarCategoriaRecuperacion(
              formulario.getCategoriaRecupero());
          creacionRecuperoInteraction.diligenciarCodigoRetencion(
              codigoRetencion, CODIGO_RETENCION.getValor());
          creacionRecuperoInteraction.diligenciarCantidadRecupero(
              formulario.getValorTransaccion(), CANTIDAD.getValor());
          creacionRecuperoInteraction.actualizarRecupero();
        });
  }

  @Step
  public void verificarCreacionRecupero(List<Recupero> lstRecupero) {
    lstRecupero.forEach(
        (Recupero validador) -> {
            List<String> listaRecuperos = verificacionRecuperoInteraction.obtenerRecuperos(validador);
            String [] recuperos = listaRecuperos.toArray(new String[0]);
            MatcherAssert.assertThat(
                    "No coincide la categoria del recupero",
                    Utilidades.filtrarArreglo(recuperos,validador.getCategoriaRecupero()));
            MatcherAssert.assertThat(
                    "No llego a SAP el recupero",
                    Utilidades.filtrarArreglo(recuperos, validador.getEstadoTransaccion()));
        });
  }
}
