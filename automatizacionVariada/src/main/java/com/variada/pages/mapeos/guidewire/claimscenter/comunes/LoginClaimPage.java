package com.variada.pages.mapeos.guidewire.claimscenter.comunes;

import com.variada.pages.mapeos.general.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;

@DefaultUrl("https://labcoreseguros.suramericana.com/cc/ClaimCenter.do")
@NamedUrls({
  // @NamedUrl(name = "local", url = "http://todomvc.com/examples/angularjs/#"),
  @NamedUrl(name = "dllo", url = "http://dllocoreseguros.suramericana.com:7005/cc/ClaimCenter.do"),
  @NamedUrl(name = "uat", url = "https://labcoreseguros.suramericana.com/cc/ClaimCenter.do"),
  @NamedUrl(name = "pdn", url = "https://coreseguros.suramericana.com/cc/ClaimCenter.do"),
})
public class LoginClaimPage extends GeneralPage {

  @FindBy(id = "ctl00_ContentMain_txtUser1")
  public WebElementFacade txtUsuario;

  @FindBy(id = "ctl00_ContentMain_txtPassword1")
  public WebElementFacade txtContrasena;

  @FindBy(id = "Login:LoginScreen:LoginDV:username-inputEl")
  public WebElementFacade txtNombreUsuario;

  @FindBy(id = "Login:LoginScreen:LoginDV:password-inputEl")
  public WebElementFacade txtContrasenaDllo;

  @FindBy(id = "Login:LoginScreen:LoginDV:submit-btnInnerEl")
  public WebElementFacade btnIniciarSesionDllo;
}
