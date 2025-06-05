package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumPosiciones.POSICION_FILA;
import static com.variada.utils.enums.EnumVariablesSesion.SESION_CC_NUMERO_FACTURA_PAGO_MASIVO;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.FacturaVolumenPage;
import java.util.List;
import net.serenitybdd.core.Serenity;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacturaVolumenInteraction extends GeneralInteraction {

  @Page FacturaVolumenPage facturaVolumenPage;

  public FacturaVolumenInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void obtenerUltimaPagina() {
    esperarCargaElemento();
    facturaVolumenPage.lblObtenerUltimaPagina.waitUntilPresent();
    facturaVolumenPage.lblObtenerUltimaPagina.click();
    realizarEsperaCarga();
  }

  public void buscarNumeroFacturaPagoMasivo() {
    final String NUMERO_FACTURA = "N.º de factura";
    List<WebElement> elementoEncontrado =
        obtenerElementoTablaDatoDesconocido(
            facturaVolumenPage.tblNumeroFactura,
            NUMERO_FACTURA,
            Integer.parseInt(POSICION_FILA.getValor()));
    String numeroFacturaPagoMasivo =
        (Serenity.sessionVariableCalled(SESION_CC_NUMERO_FACTURA_PAGO_MASIVO.getValor())
            .toString());
    int tamanoLista = elementoEncontrado.size();
    for (int i = 0; i <= tamanoLista - 1; i++) {
      if (numeroFacturaPagoMasivo.equals(elementoEncontrado.get(i).getText())) {
        elementoEncontrado
            .get(i)
            .findElement(By.id("BulkPay:BulkPayScreen:BulkInvoicesLV:" + i + ":InvoiceNumber"))
            .click();
        break;
      }
    }
  }
}
