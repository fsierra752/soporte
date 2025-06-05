package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ITERACIONES_ANULACION;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.general.GeneralPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

public class DatoRecuperacionInteraction extends GeneralInteraction {

  @Page GeneralPage generalPage;

  public DatoRecuperacionInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public boolean realizarAnulacionRecupero() {
    for (int i = 0; i <= Integer.parseInt(ITERACIONES_ANULACION.getValor()); i++)
      if (generalPage.btnAnular.isVisible()) {
        anularTransaccion();
        return true;
      } else {
        driver.navigate().refresh();
      }
    return false;
  }
}
