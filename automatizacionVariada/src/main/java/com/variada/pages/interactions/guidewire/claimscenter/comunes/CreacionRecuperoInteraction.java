package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.COMODIN;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_VALOR_RECUPERO;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.CreacionRecuperoPage;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreacionRecuperoInteraction extends GeneralInteraction {

  @Page CreacionRecuperoPage creacionRecuperoPage;

  public CreacionRecuperoInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarPagador(String pagador) {
    creacionRecuperoPage.txtPagador.waitUntilClickable();
    creacionRecuperoPage.txtPagador.click();
    esperarCargaElemento();
    seleccionarOpcionCombobox(pagador);
    realizarEsperaCarga();
  }

  public void seleccionarLineaReserva(String lineaReserva) {
    creacionRecuperoPage.txtLineaReserva.click();
    seleccionarOpcionCombobox(lineaReserva);
    realizarEsperaCarga();
  }

  public void seleccionarPais(String pais) {
    seleccionarElementoListado(creacionRecuperoPage.cmbPais, pais);
  }

  public void seleccionarDepartamento(String departamento) {
    seleccionarElementoListado(creacionRecuperoPage.cmbDepartamento, departamento);
  }

  public void seleccionarCiudad(String ciudad) {
    seleccionarElementoListado(creacionRecuperoPage.cmbCiudad, ciudad);
  }

  public void seleccionarCategoriaRecuperacion(String recupero) {
    String auxiliarSeleccionarOpcion;
    realizarEsperaCarga();
    creacionRecuperoPage.txtCategoriaRecuperacion.click();
    realizarEsperaCarga();
    auxiliarSeleccionarOpcion = creacionRecuperoPage.itemListado.replace(COMODIN.getValor(), recupero);
    $(auxiliarSeleccionarOpcion).click();
    realizarEsperaCarga();
  }

  public void diligenciarCodigoRetencion(String codigoRetencion, String encabezadoColumnaDevolver) {
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            creacionRecuperoPage.tblElementoLinea, encabezadoColumnaDevolver, 1);
    elementoEncontrado.forEach(
        elemento -> {
          elemento.click();
          creacionRecuperoPage.lstOpcionesCombobox.waitUntilVisible();
          seleccionarOpcionCombobox(codigoRetencion);
        });
    realizarEsperaCarga();
  }

  public void diligenciarCantidadRecupero(String montoRecupero, String encabezadoColumnaDevolver) {
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            creacionRecuperoPage.tblElementoLinea, encabezadoColumnaDevolver, 1);
    elementoEncontrado.forEach(
        elemento -> {
          elemento.click();
          evaluateJavascript(
              String.format("$('input[name|=\"Amount\"]').val('%s')", montoRecupero));
        });
    Serenity.setSessionVariable(SESION_CC_VALOR_RECUPERO.getValor()).to(montoRecupero);
  }

  public void actualizarRecupero() {
    creacionRecuperoPage.btnActualizar.waitUntilClickable();
    creacionRecuperoPage.btnActualizar.click();
    creacionRecuperoPage.lblTituloRecupero.waitUntilVisible();
    creacionRecuperoPage.lblTituloRecupero.isVisible();
  }
}
