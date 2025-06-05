package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.utils.enums.EnumConstantes.TRANSFERENCIA_ELECTRONICA;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionBeneficiarioPage;
import java.util.List;
import net.serenitybdd.core.annotations.findby.By;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InformacionBeneficiarioInteraction extends GeneralInteraction {

  @Page
  InformacionBeneficiarioPage informacionBeneficiarioPage;

  public InformacionBeneficiarioInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarNombreBeneficiario(String strNombreBeneficiario) {
    informacionBeneficiarioPage.cmbNombreBeneficiario.click();
    seleccionarOpcionCombobox(strNombreBeneficiario);
    realizarEsperaCarga();
  }

  public void seleccionarTipoBeneficiario(String strTipoBeneficiario) {
    informacionBeneficiarioPage.cmbTipoBeneficiario.waitUntilClickable().click();
    informacionBeneficiarioPage
        .cmbTipoBeneficiario
        .findElement(By.xpath("//li[contains(.,'" + strTipoBeneficiario + "')]"))
        .click();
    realizarEsperaCarga();
  }

  public void seleccionarMetodoPago(String strMetodoPago, String strCuenta, String strSeleccionar) {
    obtenerElementoPantallaPago(strMetodoPago);
    if (strMetodoPago.equals(TRANSFERENCIA_ELECTRONICA.getValor())) {
      List<WebElement> elementoEncontrado =
          obtenerElementoTablaDatoDesconocido(
              informacionBeneficiarioPage.tblCuentaElectronica, strCuenta, 1);
      elementoEncontrado
          .get(0)
          .findElement(By.xpath("//a[contains(.,'" + strSeleccionar + "')]"))
          .click();
    }
    realizarEsperaCarga();
  }

  public void obtenerElementoPantallaPago(String strElementoPantallaPago) {
    informacionBeneficiarioPage.rbtPago.waitUntilClickable();
    informacionBeneficiarioPage
        .rbtPago
        .findElement(
            By.xpath(
                "//following-sibling::label[contains( .,'"
                    + strElementoPantallaPago
                    + "')]//preceding-sibling::input"))
        .click();
  }

  public void seleccionarPagoSura(String strPagoSura) {
    informacionBeneficiarioPage.rbtPago.waitUntilClickable();
    obtenerElementoPantallaPago(strPagoSura);
  }

  @Override
  public void seleccionarPais(String strPais) {
    seleccionarElementoListado(informacionBeneficiarioPage.cmbPais, strPais);
  }

  @Override
  public void seleccionarDepartamento(String strDepartamento) {
    if(informacionBeneficiarioPage.btnBuscarDepartamento.isVisible()){
      seleccionarElementoListado(informacionBeneficiarioPage.cmbDepartamento, strDepartamento);
    }else {
      seleccionarElementoListado(informacionBeneficiarioPage.cmbDepartamento2, strDepartamento);
    }
  }

  public void seleccionarCiudad(String strCiudad) {
    if(informacionBeneficiarioPage.btnBuscarCiudad.isVisible()){
      seleccionarElementoListado(informacionBeneficiarioPage.cmbCiudad, strCiudad);
    } else{
      seleccionarElementoListado(informacionBeneficiarioPage.cmbCiudadDos, strCiudad);
    }

  }

  public void seleccionarTipoDireccion(String strTipoDireccion) {
    seleccionarElementoListado(informacionBeneficiarioPage.cmbTipoDireccion, strTipoDireccion);
  }
}
