package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.ProcesoBatchPage;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProcesoBatchInteraction extends GeneralInteraction {

  @Page ProcesoBatchPage procesoBatchPage;

  public ProcesoBatchInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void ejecutarBatch() {
    String letraT = "T";
    String comando = Keys.chord(Keys.ALT, Keys.SHIFT, letraT);
    procesoBatchPage.txtBuscar.sendKeys(comando);
    realizarEsperaCarga();
  }

  public void ejecutarProcesoBatch(String nombreProcesoBatch) {
    List<String> nombresProcesoBatch = new ArrayList<String>();
    final String NOMBRE_BATCH = "Proceso por lotes";
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            procesoBatchPage.tblNombreProcesoBatch,
            NOMBRE_BATCH,
            Integer.parseInt(POSICION_FILA.getValor()));
    int tamanoLista = elementoEncontrado.size();
    for (int i = 0; i <= tamanoLista - 1; i++) {
      nombresProcesoBatch.add(i, elementoEncontrado.get(i).getText());
      if (nombresProcesoBatch.get(i).equals(nombreProcesoBatch)) {
        procesoBatchPage.tblNombreProcesoBatch.waitUntilPresent();
        esperarCargaElemento();
        procesoBatchPage
            .tblNombreProcesoBatch
            .findBy("//tr[@data-recordindex='" + i + "']//a")
            .click();
        esperarCargaElemento();
        realizarEsperaCarga();
        break;
      }
    }
  }

  public void seleccionarOpcionMenuAccion() {
    esperarCargaElemento();
    procesoBatchPage.mnuAcciones.waitUntilPresent().waitUntilEnabled();
    esperarCargaElemento();
    procesoBatchPage.mnuAcciones.click();
  }

  public void seleccionarOpcionMenuAccionesPrimerNivel(String nombreOpcion) {
    procesoBatchPage
        .mnuPanelOpcionesPrimerNivel
        .iterator()
        .next()
        .findBy(
            By.xpath(
                "//span[contains(@class,'x-menu-item-text')][contains(text(),'"
                    + nombreOpcion
                    + "')]"))
        .click();
    realizarEsperaCarga();
  }
}
