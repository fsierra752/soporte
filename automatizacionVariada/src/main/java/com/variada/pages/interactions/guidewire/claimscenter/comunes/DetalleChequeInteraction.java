package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.ITERACIONES_ANULACION;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.general.GeneralPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetalleChequeInteraction extends GeneralInteraction {

  @Page
  GeneralPage generalPage;

  private Boolean response = true;

  public DetalleChequeInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public boolean realizarAnulacionCheque() {
    int contador = 0;
    while (generalPage.btnAnular.containsElements(
        By.xpath(
            "//span[@class='x-btn-button']//span[contains(text(),'Anular')]//ancestor::a[contains(@class,'disabled')]"))) {
      realizarEsperaCarga();
      driver.navigate().refresh();
      if (contador >= Integer.parseInt(ITERACIONES_ANULACION.getValor())) {
        response = false;
        break;
      }
      contador++;
    }
    anularTransaccion();
    return response;
  }
}