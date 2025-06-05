package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.models.Recupero;
import com.variada.pages.interactions.general.GeneralInteraction;

import java.util.ArrayList;
import java.util.List;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.VerificacionRecuperoPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.variada.utils.enums.EnumConstantes.NUMERO_TRANSACCION;
import static com.variada.utils.enums.EnumConstantes.ITERACIONES_RECUPERO;
import static com.variada.utils.enums.EnumConstantes.UBICACION_ESTADO_RECUPERO;

public class VerificacionRecuperoInteraction extends GeneralInteraction {

  @Page
  VerificacionRecuperoPage verificacionRecuperoPage;

  public VerificacionRecuperoInteraction(WebDriver driver) {
    super(driver);
  }

  private List<WebElement> obtenerListaRecupero() {
    String strNumeroRecupero = obtenerDatoTablaCabecera(NUMERO_TRANSACCION.getValor(), 1);
    List<WebElement> lstFilaRecupero;
    lstFilaRecupero = obtenerFilaTabla(strNumeroRecupero, verificacionRecuperoPage.tblRecupero);
    return lstFilaRecupero;
  }

  public List<String> obtenerRecuperos(Recupero validador){
    List<WebElement> lstFilaRecupero = new ArrayList<>();
    List<String> recuperos = new ArrayList<>();
    for (int i = 0; i <= Integer.parseInt(ITERACIONES_RECUPERO.getValor()); i++) {
      realizarEsperaCarga();
      lstFilaRecupero = obtenerListaRecupero();
      WebElement elementoXpath =
              lstFilaRecupero.get(Integer.parseInt(UBICACION_ESTADO_RECUPERO.getValor()));
      boolean estadoTransaccionPantalla =
              actualizarPantalla(
                      validador.getEstadoTransaccion(), elementoXpath);
      if (estadoTransaccionPantalla) break;
    }

    for (WebElement element : lstFilaRecupero ) {
      recuperos.add(element.getText());
    }
    return recuperos;
  }
}
