package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.LoginClaimPage;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

@DefaultUrl("https://labcoreseguros.suramericana.com/cc/ClaimCenter.do")
@NamedUrls({
  // @NamedUrl(name = "local", url = "http://todomvc.com/examples/angularjs/#"),
  @NamedUrl(name = "dllo", url = "http://dllocoreseguros.suramericana.com:7005/cc/ClaimCenter.do"),
  @NamedUrl(name = "uat", url = "https://labcoreseguros.suramericana.com/cc/ClaimCenter.do"),
  @NamedUrl(name = "pdn", url = "https://coreseguros.suramericana.com/cc/ClaimCenter.do"),
})
public class LoginClaimInteraction extends GeneralInteraction {

  @Page LoginClaimPage loginClaimPage;

  public LoginClaimInteraction(WebDriver wdriver) {
    super(wdriver);
  }

  public void iniciarSesionDLLO(String usuario, String contrasena) {
    loginClaimPage.txtNombreUsuario.type(usuario);
    loginClaimPage.txtContrasenaDllo.type(contrasena);
    loginClaimPage.btnIniciarSesionDllo.click();
  }

  public void iniciarSesionLAB(String usuario, String contrasena) {
    loginClaimPage.txtUsuario.click();
    loginClaimPage.txtUsuario.type(usuario);
    realizarEsperaCarga();
    loginClaimPage.txtContrasena.click();
    loginClaimPage.txtContrasena.type(contrasena).sendKeys(Keys.ENTER);
  }
  public boolean mensajeLoginCredenciales(){
    realizarEsperaCarga();
    return loginClaimPage.btnIniciarSesionDllo.isVisible();
  }
  public void evadirMensajeLoginCredenciales(){
    realizarEsperaCarga();
    loginClaimPage.btnIniciarSesionDllo.click();
  }
}
