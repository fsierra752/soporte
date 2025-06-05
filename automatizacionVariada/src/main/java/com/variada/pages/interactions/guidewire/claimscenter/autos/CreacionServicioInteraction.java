package com.variada.pages.interactions.guidewire.claimscenter.autos;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.CreacionServicioPage;
import static com.variada.utils.enums.EnumTablas.CABECERAS_CC;
import static com.variada.utils.enums.EnumTablas.REGISTROS_CC;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreacionServicioInteraction extends GeneralInteraction {
  @Page CreacionServicioPage creacionServicioPage;

  public CreacionServicioInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void seleccionarProveedor(String nombreProveedor) {
    WebElement btnSeleccionar =
        obtenerElementoLista(
            creacionServicioPage.tblProveedores,
            CABECERAS_CC,
            REGISTROS_CC,
            nombreProveedor,
            "");
    btnSeleccionar.findElement(By.tagName("a")).click();
    realizarEsperaCarga();
  }
}
