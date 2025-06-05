package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;
import static com.variada.utils.enums.EnumPosiciones.POSICION_COLUMNA_MENOS_DOS;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.BusquedaLibretaContactoPage;
import java.util.List;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BusquedaLibretaContactoInteraction extends GeneralInteraction {

  @Page BusquedaLibretaContactoPage busquedaLibretaContactoPage;

  public BusquedaLibretaContactoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void seleccionarTipoContacto(String tipoContacto) {
    busquedaLibretaContactoPage.cmbTipoContacto.click();
    busquedaLibretaContactoPage
        .lstTipoContacto
        .findElement(org.openqa.selenium.By.xpath("./li[contains(.,'" + tipoContacto + "')]"))
        .click();
    realizarEsperaCarga();
  }

  public void ingresarNombreContacto(String nombreContacto) {
    busquedaLibretaContactoPage.txtNombreContacto.waitUntilPresent();
    busquedaLibretaContactoPage.txtNombreContacto.sendKeys(nombreContacto);
    realizarEsperaCarga();
  }

  public void buscarContacto() {
    busquedaLibretaContactoPage.btnBuscarContacto.waitUntilPresent();
    busquedaLibretaContactoPage.btnBuscarContacto.waitUntilClickable();
    busquedaLibretaContactoPage.btnBuscarContacto.click();
    realizarEsperaCarga();
  }

  public void seleccionarContactoPagoMasivo() {
    final String RESULTADO_BUSQUEDA_CONTACTO = "Nombre";
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            busquedaLibretaContactoPage.tblResultadoBusquedaContacto,
            RESULTADO_BUSQUEDA_CONTACTO,
            Integer.parseInt(POSICION_COLUMNA_MENOS_DOS.getValor()));
    elementoEncontrado.get(Integer.parseInt(VALOR_CERO.getValor())).click();
    realizarEsperaCarga();
  }
}
