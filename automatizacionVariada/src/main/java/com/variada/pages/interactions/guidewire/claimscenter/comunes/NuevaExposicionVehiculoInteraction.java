package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.NuevaExposicionVehiculoPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class NuevaExposicionVehiculoInteraction extends GeneralInteraction {

  @Page NuevaExposicionVehiculoPage nuevaExposicionVehiculoPage;

  public NuevaExposicionVehiculoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void crearNuevoIncidenteVehicular() {
    nuevaExposicionVehiculoPage.btnNuevoIncidenteVehicular.waitUntilClickable().click();
    nuevaExposicionVehiculoPage.lblNuevoIncidente.waitUntilClickable().click();
    realizarEsperaCarga();
  }

  public void seleccionarReclamanteExposicion() {
    String nombreReclamante =
        (Serenity.sessionVariableCalled(SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO.getValor())
            .toString());
    nuevaExposicionVehiculoPage.txtReclamanteExposicionVehicular.clear();
    nuevaExposicionVehiculoPage.txtReclamanteExposicionVehicular.typeAndTab(nombreReclamante);
    realizarEsperaCarga();
  }

  public void seleccionarTipoReclamanteExposicion(String tipoReclamante) {
    nuevaExposicionVehiculoPage.txtTipoReclamanteExposicion.waitUntilClickable().click();
    nuevaExposicionVehiculoPage
        .txtTipoReclamanteExposicion
        .findElement(By.xpath("//li[contains(.,'" + tipoReclamante + "')]"))
        .click();
  }

  public void actualizarNuevaExposicion() {
    Actions actions = new Actions(driver);
    actions.moveToElement(nuevaExposicionVehiculoPage.btnActualizar).build().perform();
    nuevaExposicionVehiculoPage.btnActualizar.click();
    realizarTiempoEsperaCarga();
  }
}
