package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ENGLISH;
import static com.variada.utils.enums.EnumConstantes.COMODIN;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.CambioIdiomaPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class CambioIdiomaInteraction extends GeneralInteraction {

  @Page
  CambioIdiomaPage cambioIdiomaPage;

  public CambioIdiomaInteraction(WebDriver driver) {
    super(driver);
  }

  public void ingresarConfiguraciones() {
    cambioIdiomaPage.btnAjuste.click();
  }

  public void elegirOpcionInternacional() {
    cambioIdiomaPage.lnkInternacional.click();
  }

  public void elegirIdioma() {
    cambioIdiomaPage.lnkIdioma.click();
  }

  public String seleccionarIdioma() {
    if (cambioIdiomaPage.lnkSeleccionarLenguaje.getText().equals(ENGLISH.getValor())) {
      cambioIdiomaPage.cmbTipoIdioma = cambioIdiomaPage.cmbTipoIdioma.replace(COMODIN.getValor(), "Spanish (CO)");
      $(cambioIdiomaPage.cmbTipoIdioma).click();
    } else {
      cambioIdiomaPage.cmbTipoIdioma = cambioIdiomaPage.cmbTipoIdioma.replace(COMODIN.getValor(), "(US)");
      $(cambioIdiomaPage.cmbTipoIdioma).click();
    }
    return cambioIdiomaPage.lblLetraComprobante.waitUntilVisible().getText();
  }
}
