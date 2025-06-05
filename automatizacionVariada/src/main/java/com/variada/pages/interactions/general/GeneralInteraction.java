package com.variada.pages.interactions.general;

import com.variada.pages.mapeos.general.GeneralPage;
import static com.variada.utils.enums.EnumConstantes.NUMERO_INTENTOS_ESPERA_ELEMENTO;
import static com.variada.utils.enums.EnumConstantes.COMODIN;
import static com.variada.utils.enums.EnumTablas.CABECERAS_CC;
import static com.variada.utils.enums.EnumTablas.REGISTROS_CC;
import com.variada.utils.enums.EnumTablas;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.steps.StepInterceptor;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralInteraction extends PageObject {

  @Page GeneralPage generalPage;

  protected WebDriver driver;
  protected static final int TIEMPO_2 = 2;
  protected static final int NRO_INTENTOS_ESPERA_SPINNER = 30;

  private static final Logger LOGGER = LoggerFactory.getLogger(StepInterceptor.class);

  public GeneralInteraction(WebDriver wdriver) {
    super(wdriver);
    driver = wdriver;
  }

  public void seleccionarOpcionCombobox(String opcion) {
    generalPage.lstOpcionesCombobox.waitUntilVisible().waitUntilClickable();
    realizarEsperaCarga();
    generalPage
        .lstOpcionesCombobox
        .findElement(org.openqa.selenium.By.xpath("./li[contains(.,'" + opcion + "')]"))
        .click();
  }

  public void clickElemento(WebElementFacade elemento) {
    elemento.click();
  }

  public List<String> obtenerCabecerasTabla(
      WebElementFacade elementoTabla, EnumTablas enumCabecerasTabla) {
    return elementoTabla
        .findElements(By.xpath(enumCabecerasTabla.getXpath()))
        .stream()
        .map(cabecera -> cabecera.getText().trim())
        .collect(Collectors.toList());
  }

  public WebElement obtenerElementoColumnaTabla(
      WebElementFacade elementoTabla,
      EnumTablas enumRegistroTabla,
      String datoEnFilaABuscar,
      int posicionDatoADevolver) {
    realizarEsperaCarga();
    return elementoTabla
        .findElements(By.xpath(enumRegistroTabla.getXpath()))
        .stream()
        .filter(fila -> fila.getText().contains(datoEnFilaABuscar))
        .map(columnas -> columnas.findElement(By.xpath(".//td[" + posicionDatoADevolver + "]")))
        .findAny()
        .get();
  }

  public WebElement obtenerTextoColumnaTabla(
      WebElementFacade elementoTabla,
      EnumTablas enumRegistroTabla,
      String datoFilaBuscar,
      int posicionDatoDevolver) {
    final int aux = elementoTabla.findElements(By.xpath(enumRegistroTabla.getXpath())).size();
    final int ENCONTRAR_POSICION_ELEMENTO_TABLA;
    if (aux >= 3) {
      ENCONTRAR_POSICION_ELEMENTO_TABLA = 2;
    } else {
      ENCONTRAR_POSICION_ELEMENTO_TABLA = 3;
    }

    return elementoTabla
        .findElements(By.xpath(enumRegistroTabla.getXpath()))
        .stream()
        .filter(fila -> fila.getText().contains(datoFilaBuscar))
        .map(
            columnas ->
                columnas.findElement(
                    By.id(
                        "ClaimExposures:ClaimExposuresScreen:ExposuresLV:"
                            + (posicionDatoDevolver - ENCONTRAR_POSICION_ELEMENTO_TABLA)
                            + ":Type")))
        .distinct()
        .findFirst()
        .get();
  }

  public List<WebElement> obtenerFilasTabla(
      WebElementFacade elementoTabla, EnumTablas enumRegistroTabla) {
    return elementoTabla
        .findElements(By.xpath(enumRegistroTabla.getXpath()))
        .stream()
        .collect(Collectors.toList());
  }

  public WebElement obtenerElementoLista(
      WebElementFacade elemento,
      EnumTablas cabeceras,
      EnumTablas registros,
      String datoEnFilaABuscar,
      String columnaADevolver) {
    List<String> cabeceraFacturarCargos = obtenerCabecerasTabla(elemento, cabeceras);
    int posicionDatoADevolver = cabeceraFacturarCargos.indexOf(columnaADevolver) + 1;
    return obtenerElementoColumnaTabla(
        elemento, registros, datoEnFilaABuscar, posicionDatoADevolver);
  }

  public WebElement obtenerTextoElementoLista(
      WebElementFacade elemento,
      EnumTablas cabeceras,
      EnumTablas registros,
      String datoFilaBuscar,
      String columnaDevolver) {
    List<String> datosCabeceraTabla = obtenerCabecerasTabla(elemento, cabeceras);
    int posicionDatoADevolver = datosCabeceraTabla.indexOf(columnaDevolver) + 1;
    return obtenerTextoColumnaTabla(elemento, registros, datoFilaBuscar, posicionDatoADevolver);
  }

  public void realizarEsperaCarga() {
    setImplicitTimeout(TIEMPO_2, ChronoUnit.SECONDS);
    int nroIntentosEsperaSpinner = NRO_INTENTOS_ESPERA_SPINNER;
    while (nroIntentosEsperaSpinner > 0) {
      nroIntentosEsperaSpinner--;
      if (!generalPage.pgrBarCarga.isPresent()) {
        break;
      }
    }
  }

  public void esperarCargaElemento() {
    while (generalPage.pgrBarCarga.isVisible()) {
      generalPage.pgrBarCarga.waitUntilNotVisible();
    }
  }

  public void realizarTiempoEsperaCarga() {
    int numeroIntentos = Integer.parseInt(NUMERO_INTENTOS_ESPERA_ELEMENTO.getValor());
    while (numeroIntentos > 0) {
      if (!generalPage.pgrBarCarga.isPresent()) {
        break;
      }
      numeroIntentos--;
    }
  }

  public void aceptarOpcion() {
    generalPage.btnAceptar.waitUntilVisible();
    generalPage.btnAceptar.waitUntilClickable().click();
    realizarEsperaCarga();
  }

  public void finalizarProceso() {
    generalPage.btnFinalizar.waitUntilVisible().waitUntilClickable().click();
    realizarEsperaCarga();
    if (generalPage.btnBorrar.isVisible()) {
      generalPage.btnFinalizar.waitUntilVisible().waitUntilClickable().click();
      realizarEsperaCarga();
    }
  }

  public List<WebElement> obtenerElementoTablaDatoDesconocido(
      WebElementFacade elemento, String encabezadoColumnaDevolver, int posicionFila) {
    List<String> cabeceraTabla = obtenerCabecerasTabla(elemento, CABECERAS_CC);
    int posicionDatoDevolver = cabeceraTabla.indexOf(encabezadoColumnaDevolver) + posicionFila;
    List<WebElement> elementoEncontrado = obtenerFilasTabla(elemento, REGISTROS_CC);
    esperarCargaElemento();
    return elementoEncontrado
        .stream()
        .map(
            fila -> fila.findElement(By.xpath(String.format("./td[%d]/div", posicionDatoDevolver))))
        .collect(Collectors.toList());
  }

  public List<WebElement> obtenerElementoTablaDatoDesconocidoPagoMasivo(
      WebElementFacade elemento, int posicionFila) {
    esperarCargaElemento();
    List<WebElement> elementoEncontrado = obtenerFilasTabla(elemento, REGISTROS_CC);
    return elementoEncontrado
        .stream()
        .map(
            fila ->
                fila.findElement(
                    By.xpath(String.format("./td/div/a[contains(@id, 'InvoiceNumber')]"))))
        .collect(Collectors.toList());
  }

  public List<WebElement> obtenerElementoTablaDatoDesconocidoDatosFinanciero(
      WebElementFacade elemento) {
    esperarCargaElemento();
    List<String> cabeceraTabla = obtenerCabecerasTabla(elemento, CABECERAS_CC);
    esperarCargaElemento();
    List<WebElement> elementoEncontrado = obtenerFilasTabla(elemento, REGISTROS_CC);
    return elementoEncontrado
        .stream()
        .map(
            fila ->
                fila.findElement(
                    By.xpath(String.format("./td/div/a[contains(@id, 'CheckNumber')]"))))
        .collect(Collectors.toList());
  }

  public List<WebElement> obtenerElementoTablaDatoDesconocidoDatosFinancieroPagoMasivo(
      WebElementFacade elemento) {
    esperarCargaElemento();
    List<String> cabeceraTabla = obtenerCabecerasTabla(elemento, CABECERAS_CC);
    esperarCargaElemento();
    List<WebElement> elementoEncontrado = obtenerFilasTabla(elemento, REGISTROS_CC);
    return elementoEncontrado
        .stream()
        .map(
            fila ->
                fila.findElement(
                    By.xpath(String.format("./td/div/a[contains(@id, 'BulkInvoice')]"))))
        .collect(Collectors.toList());
  }

  public void seleccionarTipoTransaccion(String tipoTransaccion) {
    generalPage.txtTransacciones.waitUntilClickable().click();
    seleccionarOpcionCombobox(tipoTransaccion);
    realizarEsperaCarga();
  }

  public void seleccionarElementoListado(String elementoEtiqueta, String ubicacion) {
    String auxiliarReemplazo;
    realizarEsperaCarga();
    generalPage
        .mnuDinamico
        .findElement(By.xpath(String.format("//input[contains(@id,'%s')]", elementoEtiqueta)))
        .click();
    auxiliarReemplazo = generalPage.itemListado.replace(COMODIN.getValor(), ubicacion);
    $(auxiliarReemplazo).click();
  }

  public void irUltimaPagina() {
    if (generalPage.btnUltimaPagina.isVisible()) {
      generalPage.btnUltimaPagina.click();
      realizarEsperaCarga();
    }
  }

  public void irSiguientePagina() {
    if (generalPage.btnCambioPagina.isVisible()) {
      generalPage.btnCambioPagina.click();
      realizarEsperaCarga();
    }
  }

  public void continuarSiguientePantalla() {
    generalPage.btnSiguiente.waitUntilClickable();
    realizarEsperaCarga();
    generalPage.btnSiguiente.click();
    realizarEsperaCarga();
  }

  public String obtenerDatoTablaCabecera(String strDatoCabecera, int posicionElemento) {
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(generalPage.tblVerificacion, strDatoCabecera, 1);
    int longitudTabla = elementoEncontrado.size();
    return elementoEncontrado.get(longitudTabla - posicionElemento).getText();
  }

  public void ordenarTablaAscendente(){
    realizarEsperaCarga();
    if (generalPage.btnOrganizarFacturasAscendente.isVisible()){
      generalPage.btnOrganizarFacturasAscendente.click();
    }
  }

  public List<WebElement> obtenerFilaTabla(
      String strIdentificadorFila, String strXpathElementoTabla) {
    generalPage.tblVerificacion.waitUntilVisible();
    List<WebElement> lstFila;
    lstFila =
        generalPage.tblVerificacion.findElements(
            By.xpath(String.format(strXpathElementoTabla, strIdentificadorFila)));
    return lstFila;
  }

  public void enfocarVistaAutomatizacion() {
    for (String ventana : driver.getWindowHandles()) {
      driver.switchTo().window(ventana);
    }
  }

  public void navegarMenu(String opcionMenu, String mnuNavegar) {
    String auxiliarMnuNavegar;
    auxiliarMnuNavegar = mnuNavegar.replace(COMODIN.getValor(), opcionMenu);
    $(auxiliarMnuNavegar).waitUntilPresent().waitUntilVisible().click();
  }

  public void seleccionarOpcionTabla(WebElementFacade tblOpcion, String opcionSeleccionar) {
    tblOpcion.findElement(By.xpath("//td[.='" + opcionSeleccionar + "']")).click();
  }

  public void seleccionarOpcionLista(WebElementFacade lista, String opcionListaSeleccionar) {
    realizarEsperaCarga();
    lista.findElement(By.xpath("//li[contains(text(),'" + opcionListaSeleccionar + "')]")).click();
  }

  public void cerrarAlerta() {
    try {
      if (verificarPresenciaAlerta()) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
      }
    } catch (NoAlertPresentException e) {
      LOGGER.info("No se encontró alerta", e);
    }
  }

  private boolean verificarPresenciaAlerta() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (Exception e) {
      LOGGER.info(String.valueOf(e));
      return false;
    }
  }

  public void cerrarNavegador() {
    Set<String> ventana;
    do {
      enfocarVistaAutomatizacion();
      ventana = driver.getWindowHandles();
      driver.close();
    } while (ventana.size() != 1);
  }

  public boolean actualizarPantalla(String datoValidar, WebElement valorElementoPantalla) {
    String strDatoPantalla = valorElementoPantalla.getText();
    if (!strDatoPantalla.equals(datoValidar)) {
      driver.navigate().refresh();
      cerrarAlerta();
      return false;
    }
    return true;
  }

  public void anularTransaccion() {
    realizarEsperaCarga();
    generalPage.btnAnular.waitUntilEnabled().waitUntilClickable();
    generalPage.btnAnular.click();

    realizarEsperaCarga();
    generalPage.btnAnular.waitUntilEnabled().waitUntilClickable();
    generalPage.btnAnular.click();

    realizarEsperaCarga();
    generalPage.btnAceptar.waitUntilEnabled().waitUntilClickable();
    generalPage.btnAceptar.click();
    realizarEsperaCarga();
  }

  public void seleccionarPais(String pais) {
    seleccionarElementoListado(generalPage.cmbPais, pais);
  }

  public void seleccionarDepartamento(String departamento) {
    seleccionarElementoListado(generalPage.cmbDepartamento, departamento);
  }
}
