package com.variada.pages.interactions.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumConstantes.COMODIN;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.empresariales.AutenticacionAtrPage;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;

@DefaultUrl(
    "https://arlappslab.suramericana.com/SSAutenticacion/faces/autenticacion/paginaAutenticacion.jspx?cdApp=SURACOM&cookies=false&ReturnUrl=%2fdefault.aspx")
@NamedUrls({
  @NamedUrl(
      name = "uat",
      url =
          "https://arlappslab.suramericana.com/SSAutenticacion/faces/autenticacion/paginaAutenticacion.jspx?cdApp=SURACOM&cookies=false")
})
public class AutenticacionAtrInteraction extends GeneralInteraction {

  @Page AutenticacionAtrPage autenticacionAtrPage;

  private String auxiliarBtnClave = "";

  public AutenticacionAtrInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void iniciarSesionUAT(String usuario, String contrasena) {
    autenticacionAtrPage.lstTipoDocumento.click();
    autenticacionAtrPage.lstCedula.click();
    for (int cadenaString = 0; cadenaString < 4; cadenaString++) {
      String digito = contrasena.substring(cadenaString, cadenaString + 1);
      auxiliarBtnClave = autenticacionAtrPage.btnClave.replace(COMODIN.getValor(), digito);
      $(auxiliarBtnClave).click();
      autenticacionAtrPage.txtClave.click();
    }
    autenticacionAtrPage.txtUsuario.type(usuario);
    autenticacionAtrPage.btnIngresar.click();
    autenticacionAtrPage.btnAceptarCondicionUso.waitUntilVisible().click();
  }
}
