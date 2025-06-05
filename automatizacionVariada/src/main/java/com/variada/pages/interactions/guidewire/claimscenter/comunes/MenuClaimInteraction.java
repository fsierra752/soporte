package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.MenuClaimPage;
import static com.variada.utils.constantes.MenuConstante.ESCRITORIO_MENU;
import static com.variada.utils.constantes.MenuConstante.RECLAMACION_MENU;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MenuClaimInteraction extends GeneralInteraction {

  @Page MenuClaimPage menuClaimPage;

  public MenuClaimInteraction(WebDriver wDriver) {
    super(wDriver);
  }

  public void seleccionarOpcionMenuPrimerNivel(String nombreOpcion) {
    menuClaimPage
        .mnuPrimerNivel
        .findElement(By.xpath(String.format(".//a[contains(.,'%s')]", nombreOpcion)))
        .sendKeys(Keys.ARROW_DOWN);
  }

  public void seleccionarOpcionMenuSegundoNivel(String nombreOpcion, String subItem) {
    final String OPCION_MENU = ".//a[contains(.,'";
    menuClaimPage
        .mnuPrimerNivel
        .findElement(By.xpath(OPCION_MENU + nombreOpcion + "')]"))
        .sendKeys(Keys.ARROW_DOWN);
    if (nombreOpcion.equals(ESCRITORIO_MENU)) {
      menuClaimPage
          .mnuSegundoNivelEscritorio
          .findElement(By.xpath(OPCION_MENU + subItem + "')]"))
          .click();
    } else if (nombreOpcion.equals(RECLAMACION_MENU)) {
      menuClaimPage.mnuSegundoNivel.findElement(By.xpath(OPCION_MENU + subItem + "')]")).click();
    }
  }

  public void seleccionarOpcionMenuLateralPrimerNivel(String nombreOpcion) {
    realizarTiempoEsperaCarga();
    esperarCargaElemento();
    menuClaimPage
        .mnuLateralPrimerNivel
        .findElement(
            By.xpath(
                String.format(
                    "//span[contains(@class,'x-tree-node-text')][contains(text(),'%s')]",
                    nombreOpcion)))
        .click();
    esperarCargaElemento();
    realizarEsperaCarga();
  }

  public void seleccionarOpcionMenuLateralSegundoNivel(String nombreOpcion, String subItem) {
    realizarEsperaCarga();
    seleccionarOpcionMenuLateralPrimerNivel(nombreOpcion);
    realizarEsperaCarga();
    seleccionarOpcionMenuLateralPrimerNivel(subItem);
  }

  public void buscarReclamacion(String strOpcionMenu, String strReclamacion) {
    seleccionarOpcionMenuPrimerNivel(strOpcionMenu);
    menuClaimPage.mnuBuscar.click();
    menuClaimPage.mnuBuscar.typeAndEnter(strReclamacion);
    realizarEsperaCarga();
  }

  public void seleccionarOpcionMenuAccionesPrimerNivel(String nombreOpcion) {
    realizarEsperaCarga();
    menuClaimPage
        .mnuPanelOpcionesPrimerNivel
        .iterator()
        .next()
        .findBy(
            By.xpath(
                "//span[contains(@class,'x-menu-item-text')][contains(text(),'"
                    + nombreOpcion
                    + "')]"))
        .waitUntilEnabled()
        .click();
    realizarEsperaCarga();
  }

  public void seleccionarBotonAcciones() {
    esperarCargaElemento();
    menuClaimPage.btnAcciones.waitUntilVisible().waitUntilClickable().click();
  }
}
