package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.MenuRecuperoPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class MenuRecuperoInteraction extends GeneralInteraction {

  @Page MenuRecuperoPage menuRecuperoPage;

  public MenuRecuperoInteraction(WebDriver driver) {
    super(driver);
  }

  public void ingresarMenuRecupero() {
    menuRecuperoPage.btnAcciones.waitUntilClickable();
    menuRecuperoPage.btnAcciones.click();
    menuRecuperoPage.mnuOtros.waitUntilClickable();
    menuRecuperoPage.mnuOtros.click();
    menuRecuperoPage.mnuRecuperos.waitUntilClickable();
    menuRecuperoPage.mnuRecuperos.click();
  }
}
