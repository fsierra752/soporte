package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumConstantes.COLUMNA_VALIDACION_SARLAFT;
import static com.variada.utils.enums.EnumConstantes.SIN_VALIDAR_SARLAFT;
import static com.variada.utils.enums.EnumConstantes.TIPO_DOCUMENTO_CEDULA;
import static com.variada.utils.enums.EnumFormatos.FORMATO_FECHA_DDMMYYYY;
import static com.variada.utils.enums.EnumFormatos.FORMATO_FECHA_YYYYMMDD;
import static com.variada.utils.enums.EnumNombresCsv.TOMADOR;
import static com.variada.utils.enums.EnumTablas.CABECERAS_CC;
import static com.variada.utils.enums.EnumTablas.REGISTROS_CONTACTOS_CC;
import com.variada.models.Tomador;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.PartesImplicadasPage;
import com.variada.utils.Fecha;
import com.variada.utils.UtilidadesCSV;
import java.io.IOException;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PartesImplicadasInteraction extends GeneralInteraction {

  @Page PartesImplicadasPage partesImplicadasPage;

  public PartesImplicadasInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void validarSarlaft() {
    partesImplicadasPage.btnValidarSarlaft.waitUntilClickable().click();
    realizarEsperaCarga();
  }

  public boolean validarEstadoSarlaft(String beneficiarioPago) {
    String estadoSarlaft;
    partesImplicadasPage.mnuPartesImplicadas.waitUntilClickable().click();
    realizarEsperaCarga();
    WebElement columnaValidacionSarlaft =
        obtenerElementoLista(
            partesImplicadasPage.tblPartesImplicadas,
                CABECERAS_CC,
                REGISTROS_CONTACTOS_CC,
            beneficiarioPago,
                COLUMNA_VALIDACION_SARLAFT.getValor());
    estadoSarlaft = columnaValidacionSarlaft.getText();
    if (estadoSarlaft.equalsIgnoreCase(SIN_VALIDAR_SARLAFT.getValor())) {
      columnaValidacionSarlaft.click();
      realizarEsperaCarga();
      return false;
    } else {
      return true;
    }
  }

  public void completarDatosBeneficiario() throws IOException {
    final String FILTRO_TOMADOR = "tomador riesgo estándar";
    partesImplicadasPage.btnEditar.click();
    realizarEsperaCarga();
    Tomador tomador =
        new Tomador(UtilidadesCSV.obtenerPrimerDatoPrueba(TOMADOR.getValor(), FILTRO_TOMADOR));
    if (tomador.getTipoDocumento().equalsIgnoreCase(TIPO_DOCUMENTO_CEDULA.getValor())) {
      String fechaExpedicionDocumento =
          Fecha.obtenerFechaConFormato(
              tomador.getFechaExpedicionDocumento(),
                  FORMATO_FECHA_DDMMYYYY.getValor(),
                  FORMATO_FECHA_YYYYMMDD.getValor());
      partesImplicadasPage.txtFechaExpedicionDocumento.sendKeys(fechaExpedicionDocumento);
      partesImplicadasPage.cmbPaisNacimiento.click();
      seleccionarOpcionCombobox(tomador.getNacionalidad());
    }
    realizarEsperaCarga();
    partesImplicadasPage.btnActualizar.click();
    realizarEsperaCarga();
  }
}
