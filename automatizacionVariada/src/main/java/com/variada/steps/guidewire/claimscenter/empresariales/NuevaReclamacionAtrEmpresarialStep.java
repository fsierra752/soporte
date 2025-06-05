package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumConstantes.EXPEDIENTE_CREADO_EXITOSAMENTE;
import static com.variada.utils.constantes.MenuConstante.DETALLES_SINIESTRO;

import com.variada.models.PersonaReclamacion;
import com.variada.models.ReclamacionEmpresarial;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.BusquedaPolizaInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.ConsultaReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionBasicaReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.empresariales.AsistenteVirtualAtrInteraction;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class NuevaReclamacionAtrEmpresarialStep {

  @Page AsistenteVirtualAtrInteraction asistenteVirtualAtrInteraction;

  @Page
  BusquedaPolizaInteraction busquedaPolizaInteraction;

  @Page
  InformacionBasicaReclamacionInteraction informacionBasicaReclamacionInteraction;

  @Page
  InformacionReclamacionInteraction informacionReclamacionInteraction;

  @Page
  ConsultaReclamacionInteraction consultaReclamacionInteraction;

  @Page MenuClaimInteraction menuClaimInteraction;

  @Step
  public void accederAvisoAtr() {
    asistenteVirtualAtrInteraction.accederAsistenteVirtual();
    asistenteVirtualAtrInteraction.accederAvisoEmpresa();
  }

  @Step
  public void diligenciarInformacionAsegurado(List<PersonaReclamacion> datosPersona) {
    asistenteVirtualAtrInteraction.seleccionarPlanListaProducto();
    busquedaPolizaInteraction.enfocarVistaAutomatizacion();
    busquedaPolizaInteraction.realizarEsperaCarga();
    datosPersona.forEach(
        asegurado ->
            busquedaPolizaInteraction.seleccionarDocumentoAseguradoAtr(asegurado.getTipoDocumento()));
    datosPersona.forEach(
        asegurado ->
            busquedaPolizaInteraction.digitarDocumentoAseguradoAtr(asegurado.getNumDocumento()));
    busquedaPolizaInteraction.consultarDocumentoAseguradoAtr();
  }

  @Step
  public void diligenciarInformacionReclamacion(
      String causaSiniestro, List<ReclamacionEmpresarial> datosSiniestro) {
    datosSiniestro.forEach(
        datos -> informacionBasicaReclamacionInteraction.seleccionarFechaAviso(datos.getFechaSiniestro()));
    informacionReclamacionInteraction.seleccionarCausaSiniestroAtr(causaSiniestro);
    datosSiniestro.forEach(
        datos ->
            informacionReclamacionInteraction.diligenciarDetalleHechosAtr(
                datos.getDetalleHechos()));
    informacionReclamacionInteraction.seleccionarCiudadSiniestro();
  }

  @Step
  public void consultarPolizaAtr() {
    busquedaPolizaInteraction.consultarPolizaAseguradoAtr();
    busquedaPolizaInteraction.seleccionarPolizaAtr();
    busquedaPolizaInteraction.seleccionarRiegoPolizaAtr();
  }

  @Step
  public void diligenciarValorPretension(String valorPretension) {
    informacionReclamacionInteraction.ingresarValorPretensionAtr(valorPretension);
    informacionReclamacionInteraction.enviarReclamacion();
  }

  @Step
  public String verificarSiniestroCreadoAtr() {
    MatcherAssert.assertThat(
        "No se generó el número de siniestro en ATR",
        asistenteVirtualAtrInteraction
            .obtenerTituloExpedienteCreado()
            .equalsIgnoreCase(EXPEDIENTE_CREADO_EXITOSAMENTE.getValor()));
    return informacionReclamacionInteraction.obtenerNumeroSiniestroAtr();
  }

  @Step
  public void consultarSiniestro(String numeroReclamacion) {
    consultaReclamacionInteraction.buscarReclamacion(numeroReclamacion);
    menuClaimInteraction.seleccionarOpcionMenuLateralPrimerNivel(DETALLES_SINIESTRO);
    MatcherAssert.assertThat(
        "No se encontró el número de siniestro generado en ATR",
        consultaReclamacionInteraction.obtenerLabelNumeroSiniestro().equalsIgnoreCase(numeroReclamacion));
  }
}
