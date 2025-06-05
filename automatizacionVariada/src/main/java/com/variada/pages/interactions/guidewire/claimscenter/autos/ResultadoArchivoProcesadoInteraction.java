package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.ResultadoArchivoProcesadoPage;

import java.util.ArrayList;
import java.util.List;

import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultadoArchivoProcesadoInteraction extends GeneralInteraction {

  @Page ResultadoArchivoProcesadoPage resultadoArchivoProcesadoPage;

  public ResultadoArchivoProcesadoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public List<String> ObtenerResultadosArchivoProcesado() {
    final String RESULTADO_ARCHIVO_PROCESADO = "Valido";
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            resultadoArchivoProcesadoPage.tblResultadoArchivoProcesado,
            RESULTADO_ARCHIVO_PROCESADO,
            Integer.parseInt(POSICION_FILA.getValor()));
    List<String> resultados = new ArrayList<>();
    for (int i = 0; i <= elementoEncontrado.size() - 1; i++) {
      resultados.add(elementoEncontrado.get(i).getText());
    }
    return resultados;
  }
}
