package com.variada.pages.interactions.guidewire.claimscenter.empresariales;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.empresariales.AuditoriaPage;
import static com.variada.utils.constantes.MenuConstante.DETALLES_SINIESTRO;
import static com.variada.utils.enums.EnumConstantes.SI;
import static com.variada.utils.enums.EnumConstantes.NO;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class AuditoriaInteraction extends GeneralInteraction {

  @Page AuditoriaPage auditoriaPage;

  @Page MenuClaimInteraction menuClaimInteraction;

  private static final String COMENTARIO_AUDITORIA = "Requiere marcacion de auditoria";
  private static final String DETALLES_INVESTIGACION_AUDITORIA =
      "Detalles de investigación de auditoría";

  public AuditoriaInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarDetalleInvestigacionAuditoria() {
    menuClaimInteraction.seleccionarOpcionMenuLateralSegundoNivel(
        DETALLES_SINIESTRO, DETALLES_INVESTIGACION_AUDITORIA);
  }

  public void editarMarcacionAuditoria() {
    auditoriaPage
        .btnEditarProcesoAuditoria
        .waitUntilPresent()
        .waitUntilVisible()
        .waitUntilClickable()
        .click();
  }

  public void seleccionarMarcacionAuditoria(String strAuditoria) {
    auditoriaPage
        .cmbRequiereAuditoria
        .waitUntilClickable()
        .waitUntilPresent()
        .waitUntilVisible()
        .click();
    seleccionarOpcionCombobox(strAuditoria);
    realizarEsperaCarga();
    if (strAuditoria.equalsIgnoreCase(SI.getValor())) {
      agregarComentarioAuditoria();
    }
  }

  public void agregarComentarioAuditoria() {
    auditoriaPage.txtAreaComentario.type(COMENTARIO_AUDITORIA);
  }

  public void actualizarMarcacionAuditoria() {
    auditoriaPage.btnActualizar.waitUntilPresent().waitUntilVisible().waitUntilClickable().click();
  }

  public void verificarMarcacion(String strAuditoria) {
    if (strAuditoria.equalsIgnoreCase(NO.getValor()) && verificarImagenAuditoria()) {
      editarMarcacionAuditoria();
      seleccionarMarcacionAuditoria(strAuditoria);
      actualizarMarcacionAuditoria();
    } else {
      if (strAuditoria.equalsIgnoreCase(SI.getValor()) && !verificarImagenAuditoria()) {
        editarMarcacionAuditoria();
        seleccionarMarcacionAuditoria(strAuditoria);
        actualizarMarcacionAuditoria();
      }
    }
  }

  public boolean verificarImagenAuditoria() {
    boolean estado = false;
    if (auditoriaPage.imgAuditoria.isVisible()) {
      estado = true;
    }
    return estado;
  }

  public boolean verificarMensajeRechazo() {
    boolean estado = false;
    if (auditoriaPage.msgRechazoPago.isVisible()) {
      estado = true;
    }
    return estado;
  }

  public String capturarMensajeRechazo() {
    auditoriaPage.msgRechazoPago.waitUntilPresent().waitUntilVisible();
    return auditoriaPage.msgRechazoPago.getText();
  }
}
