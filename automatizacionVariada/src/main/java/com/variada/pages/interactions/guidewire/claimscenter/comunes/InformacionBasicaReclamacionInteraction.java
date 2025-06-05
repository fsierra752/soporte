package com.variada.pages.interactions.guidewire.claimscenter.comunes;

import static com.variada.pages.mapeos.guidewire.claimscenter.empresariales.CrearCuentaBancariaPage.ID_TXT_NOMBRE_BANCO;
import static com.variada.pages.mapeos.guidewire.claimscenter.empresariales.CrearCuentaBancariaPage.ID_TXT_TIPO_CUENTA;
import static com.variada.utils.enums.EnumConstantes.NIT;
import static com.variada.utils.enums.EnumConstantes.COMODIN;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.mapeos.guidewire.claimscenter.comunes.InformacionBasicaReclamacionPage;
import com.variada.pages.mapeos.guidewire.claimscenter.empresariales.CrearCuentaBancariaPage;
import com.variada.pages.mapeos.guidewire.claimscenter.empresariales.IncidenteLesionPage;
import com.variada.utils.Utilidades;
import com.variada.utils.enums.EnumConstantes;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class InformacionBasicaReclamacionInteraction extends GeneralInteraction {

  @Page
  InformacionBasicaReclamacionPage informacionBasicaReclamacionPage;
  @Page IncidenteLesionPage incidenteLesionPage;
  @Page CrearCuentaBancariaPage crearCuentaBancariaPage;
  private String auxiliarReemplazo = "";

  public InformacionBasicaReclamacionInteraction(WebDriver driver) {
    super(driver);
  }

  public void seleccionarAutorReporte() {
    informacionBasicaReclamacionPage.txtNombreAutor.waitUntilVisible();
    informacionBasicaReclamacionPage.txtNombreAutor.click();
    if (informacionBasicaReclamacionPage.lstAutorReporteCliente.isVisible()) {
      informacionBasicaReclamacionPage.lstAutorReporteCliente.waitUntilVisible();
      informacionBasicaReclamacionPage.lstAutorReporteCliente.click();
      realizarEsperaCarga();
    } else {
      informacionBasicaReclamacionPage.btnContactManager.waitUntilClickable();
      informacionBasicaReclamacionPage.btnContactManager.click();
      informacionBasicaReclamacionPage.btnBuscarContactoExistente.waitUntilClickable();
      informacionBasicaReclamacionPage.btnBuscarContactoExistente.click();
      informacionBasicaReclamacionPage.txtNit.waitUntilVisible();
      informacionBasicaReclamacionPage.txtNit.sendKeys(NIT.getValor());
      informacionBasicaReclamacionPage.btnBuscarNit.waitUntilClickable();
      informacionBasicaReclamacionPage.btnBuscarNit.click();
      informacionBasicaReclamacionPage.btnSeleccionarContacto.waitUntilClickable();
      informacionBasicaReclamacionPage.btnSeleccionarContacto.click();
      realizarEsperaCarga();
      informacionBasicaReclamacionPage.btnRelacionAsegurado.waitUntilClickable();
      informacionBasicaReclamacionPage.btnRelacionAsegurado.click();
      informacionBasicaReclamacionPage.lstAmigo.waitUntilVisible();
      informacionBasicaReclamacionPage.lstAmigo.click();
    }
  }

  public void seleccionarAutorReporteVidaGrupo(
          String nombreAseguradoBeneficiario, String asegurado, String relacionAsegurado) {
    informacionBasicaReclamacionPage.txtNombreAutorSiniestroRapido.waitUntilVisible();
    informacionBasicaReclamacionPage.txtNombreAutorSiniestroRapido.click();
    if (informacionBasicaReclamacionPage.lstAutorReporteCliente.isVisible()) {
      informacionBasicaReclamacionPage.lstAutorReporteCliente.waitUntilVisible();
      informacionBasicaReclamacionPage.elegirNombreRelacionAutor(nombreAseguradoBeneficiario);
      if (asegurado.equals("no")) {
        informacionBasicaReclamacionPage.lstRelacionAsegurado.click();
        informacionBasicaReclamacionPage.elegirNombreRelacionAutor(relacionAsegurado);
      }
    }
  }

  public void escribirDetalleHechos(String detalle) {
    informacionBasicaReclamacionPage.txtDetalleHechos.waitUntilVisible();
    informacionBasicaReclamacionPage.txtDetalleHechos.type(detalle);
    continuarSiguientePantalla();
  }

  public void escribirDetalleHechosVidaGrupo(String detalle) {
    informacionBasicaReclamacionPage.txtDetalleHechosSiniestroRapido.waitUntilVisible().type(detalle);
    realizarEsperaCarga();
  }

  public void agregarLesionesVidaGrupo(String diagnostico, String asegurado, String nombreAsegurado) {
    incidenteLesionPage.btnAgrearLesion.waitUntilVisible();
    incidenteLesionPage.btnAgrearLesion.click();
    incidenteLesionPage.txtPersonaLesionada.click();
    if (asegurado.equals("si")) {
      informacionBasicaReclamacionPage.elegirLesionado(nombreAsegurado);
    } else {
      informacionBasicaReclamacionPage.elegirLesionado(informacionBasicaReclamacionPage.obtenerNombreAsegurado.getText());
    }
    incidenteLesionPage.btnAgregarDiagnostico.click();
    incidenteLesionPage.celdaCodigoCie.click();
    incidenteLesionPage.txtCodigoCie.typeAndTab(diagnostico);
    realizarEsperaCarga();
    incidenteLesionPage.btnAceptar.click();
  }

  public void seleccionarCuentaBancaria(
      String numCuenta,
      String nomTitular,
      String banco,
      String tipoCuenta,
      String cuentaNueva,
      String transferencia) {
    String numCuentaModificada = numCuenta.substring(numCuenta.length() - 3);
    if (transferencia.equals(EnumConstantes.SI.getValor())){
      informacionBasicaReclamacionPage.rbtPagoTransferenciaSi.click();
      if (cuentaNueva.equals(EnumConstantes.SI.getValor())){
        informacionBasicaReclamacionPage.btnNuevaCuenta.click();
        realizarEsperaCarga();
        crearCuentaBancariaPage.txtTitularCuenta.type(nomTitular);
        seleccionarElementoListado(ID_TXT_NOMBRE_BANCO, banco);
        crearCuentaBancariaPage.txtNumeroCuenta.type(numCuenta);
        seleccionarElementoListado(ID_TXT_TIPO_CUENTA, tipoCuenta);
        crearCuentaBancariaPage.btnAceptar.click();
        realizarEsperaCarga();
        informacionBasicaReclamacionPage.elegirCuenta(numCuentaModificada);
      } else {
        informacionBasicaReclamacionPage.elegirCuenta(numCuentaModificada);
      }
    } else {
      informacionBasicaReclamacionPage.rbtPagoTransferenciaNo.click();
    }
  }

  public void marcarCoberturaSiniestro(String cobertura) {
    realizarEsperaCarga();
    informacionBasicaReclamacionPage.indicarCobertura(cobertura);
    realizarEsperaCarga();
  }

  public void seleccionarDiaCalendario(String diaUsuario) {
    navegarMenu(diaUsuario, informacionBasicaReclamacionPage.lnkDiaMes);
  }

  public void seleccionarMesAnterior(int valorMesAnterior, int valorMesActual) {
    int buscadorValor = valorMesActual - valorMesAnterior;
    int numeroClick = 0;
    auxiliarReemplazo = informacionBasicaReclamacionPage.btnCambioMesAnio.replace(COMODIN.getValor(), "2");
    while (numeroClick < buscadorValor) {
      $(auxiliarReemplazo).waitUntilVisible().click();
      numeroClick++;
    }
  }

  public void seleecionarMesPosterior(int valorMesPosterior, int valorMesActual) {
    int buscadorValor = valorMesPosterior - valorMesActual;
    int numeroClick = 0;
    auxiliarReemplazo = informacionBasicaReclamacionPage.btnCambioMesAnio.replace(COMODIN.getValor(), "4");
    while (numeroClick < buscadorValor) {
      $(auxiliarReemplazo).waitUntilVisible().click();
      numeroClick++;
    }
  }

  public void seleccionarAnioAnterior(int valorAnioAnterior, int valorAnioActual) {
    int buscadorValor = valorAnioActual - valorAnioAnterior;
    int numeroClick = 0;
    auxiliarReemplazo = informacionBasicaReclamacionPage.btnCambioMesAnio.replace(COMODIN.getValor(), "1");
    while (numeroClick < buscadorValor) {
      $(auxiliarReemplazo).waitUntilVisible().click();
      numeroClick++;
    }
  }

  public void seleccionarAnioPosterior(int valorAnioPosterior, int valorAnioActual) {
    int buscadorValor = valorAnioPosterior - valorAnioActual;
    int numeroClick = 0;
    auxiliarReemplazo = informacionBasicaReclamacionPage.btnCambioMesAnio.replace(COMODIN.getValor(), "5");
    while (numeroClick < buscadorValor) {
      $(auxiliarReemplazo).waitUntilVisible().click();
      numeroClick++;
    }
  }

  public void seleccionarFechaAviso(String fechaAviso) {
    String diaUsuario = fechaAviso.substring(9, 11);
    String mesUsuario = fechaAviso.substring(5, 8);
    String anioUsuario = fechaAviso.substring(0, 4);
    realizarEsperaCarga();
    informacionBasicaReclamacionPage.tblCalendarioFechaSiniestro.waitUntilVisible().click();
    String auxIndicadorAnioMes = informacionBasicaReclamacionPage.lblAnioMes.getText();
    String mesCalendarioAtr = auxIndicadorAnioMes.substring(5, 8);
    String anioCalendarioAtr = auxIndicadorAnioMes.substring(0, 4);
    if ("0".equalsIgnoreCase(diaUsuario.substring(0, 1))) {
      diaUsuario = diaUsuario.substring(1, 2);
    }
    int valorMesCalendarioAtr = Utilidades.valorarMes(mesCalendarioAtr);
    int valorMesUsuario = Utilidades.valorarMes(mesUsuario);
    int valorAnioCalendarioAtr = Utilidades.conversorCadenaEntero(anioCalendarioAtr);
    int valorAnioUsuario = Utilidades.conversorCadenaEntero(anioUsuario);
    if (valorAnioUsuario < valorAnioCalendarioAtr) {
      seleccionarAnioAnterior(valorAnioUsuario, valorAnioCalendarioAtr);
    } else if (valorAnioUsuario > valorAnioCalendarioAtr) {
      seleccionarAnioPosterior(valorAnioUsuario, valorAnioCalendarioAtr);
    }
    if (valorMesUsuario == valorMesCalendarioAtr) {
      seleccionarDiaCalendario(diaUsuario);
    }
    if (valorMesUsuario < valorMesCalendarioAtr) {
      seleccionarMesAnterior(valorMesUsuario, valorMesCalendarioAtr);
      seleccionarDiaCalendario(diaUsuario);
    } else {
      seleecionarMesPosterior(valorMesUsuario, valorMesCalendarioAtr);
      seleccionarDiaCalendario(diaUsuario);
    }
  }

  public void seleccionarNombre() {
    informacionBasicaReclamacionPage.cmbNombre.click();
    informacionBasicaReclamacionPage.lstNombreAutor.click();
    realizarEsperaCarga();
    continuarSiguientePantalla();
  }

  public void validarMensajeAdvertenciaRelacionAsegurado(String relacionAsegurado) {
    if (informacionBasicaReclamacionPage.msjAdvertenciaRelacionAsegurado.isPresent()) {
      informacionBasicaReclamacionPage.txtRelacionAsegurado.type(relacionAsegurado);
      informacionBasicaReclamacionPage.txtRelacionAsegurado.sendKeys(Keys.ENTER);
      realizarEsperaCarga();
      continuarSiguientePantalla();
      realizarEsperaCarga();
    }
  }

  public void escribirValorPretension(String valorPretension) {
    informacionBasicaReclamacionPage.txtValorPretension.waitUntilVisible().type(valorPretension);
    realizarEsperaCarga();
  }
}
