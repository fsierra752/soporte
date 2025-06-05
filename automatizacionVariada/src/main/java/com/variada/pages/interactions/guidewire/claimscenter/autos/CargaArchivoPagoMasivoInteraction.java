package com.variada.pages.interactions.guidewire.claimscenter.autos;

import static com.variada.utils.enums.EnumConstantes.VALOR_CERO;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.autos.CargaArchivoPagoMasivoPage;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CargaArchivoPagoMasivoInteraction extends GeneralInteraction {

  @Page CargaArchivoPagoMasivoPage cargaArchivoPagoMasivoPage;

  public CargaArchivoPagoMasivoInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void generarFacturacionMasiva() {
    cargaArchivoPagoMasivoPage.btnFacturacionMasiva.waitUntilClickable();
    cargaArchivoPagoMasivoPage.btnFacturacionMasiva.click();
  }

  public void buscarArchivoPagoMasivo(String rutaArchivoPagoMasivo) {
    WebDriverWait wait = new WebDriverWait(getDriver(), Integer.parseInt(VALOR_CERO.getValor()));
    WebElement btnExaminar =
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath(".//input[@name='fileContent']")));
    btnExaminar.sendKeys(rutaArchivoPagoMasivo);
  }
}
