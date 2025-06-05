package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_PLACAS_VEHICULOS_INVOLUCRADOS;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.DetalleSiniestroPage;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetalleSiniestroInteraction extends GeneralInteraction {

  @Page DetalleSiniestroPage detalleSiniestroPage;

  public DetalleSiniestroInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void obtenerNumeroPlacaPartesImplicadas() {
    List<String> placaVehiculosInvolucrados = new ArrayList();
    final String PLACA = "Placa";
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            detalleSiniestroPage.tblPlacasVehiculosInvolucrados,
            PLACA,
            Integer.parseInt(POSICION_FILA.getValor()));
    int tamanoLista = elementoEncontrado.size();
    Serenity.setSessionVariable(SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS.getValor())
        .to(tamanoLista);
    for (int i = 0; i <= tamanoLista - 1; i++) {
      if (elementoEncontrado.get(i).getText().equals(obtenerPlacaAsegurado())) {
        placaVehiculosInvolucrados.add(0, elementoEncontrado.get(i).getText());
      } else {
        placaVehiculosInvolucrados.add(i, elementoEncontrado.get(i).getText());
      }
    }
    Serenity.setSessionVariable(SESION_CC_PLACAS_VEHICULOS_INVOLUCRADOS.getValor())
        .to(placaVehiculosInvolucrados);
  }

  public String obtenerPlacaAsegurado() {
    return detalleSiniestroPage.lblPlacaAsegurado.getText().substring(7, 13);
  }
}
