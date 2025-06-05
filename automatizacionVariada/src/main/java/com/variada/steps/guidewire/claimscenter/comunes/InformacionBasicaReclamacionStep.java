package com.variada.steps.guidewire.claimscenter.comunes;

import com.variada.models.ReclamacionEmpresarial;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionBasicaReclamacionInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.InformacionReclamacionInteraction;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class InformacionBasicaReclamacionStep {

  @Page
  InformacionBasicaReclamacionInteraction informacionBasicaReclamacionInteraction;
  @Page InformacionReclamacionInteraction informacionReclamacionInteraction;

  public void diligenciarInformacionBasica(List<ReclamacionEmpresarial> datosAutor) {
    datosAutor.forEach(
        autor -> {
          informacionBasicaReclamacionInteraction.seleccionarAutorReporte();
          informacionBasicaReclamacionInteraction.escribirDetalleHechos(autor.getDetalleHechos());
        });
  }

    public void ingresarInformacionBasicaVidaGrupo(List<ReclamacionEmpresarial> datosAutor) {
        datosAutor.forEach(
                autor -> {
                    informacionBasicaReclamacionInteraction.seleccionarAutorReporteVidaGrupo(
                            autor.getNombreAseguradoBeneficiario(),
                            autor.getAsegurado(),
                            autor.getRelacionAsegurado());
                    informacionBasicaReclamacionInteraction.escribirDetalleHechosVidaGrupo(autor.getDetalleHechos());
                    informacionBasicaReclamacionInteraction.escribirValorPretension(autor.getValorPretension());
                });
    }

    public void ingresarLesionesSiniestroRapido(String diagnostico, List<ReclamacionEmpresarial> datosAutor) {
        datosAutor.forEach(
                autor ->
                        informacionBasicaReclamacionInteraction.agregarLesionesVidaGrupo(
                                diagnostico, autor.getAsegurado(), autor.getNombreAseguradoBeneficiario()));
    }

    @Step("Se ingresan los datos para el pago del siniestro")
    public void elegirPagoPorTransferencia(
      String transferencia,
      String nombreTitular,
      String banco,
      String numeroCuenta,
      String tipoCuenta,
      String cuentaNueva) {
    informacionBasicaReclamacionInteraction.seleccionarCuentaBancaria(
            numeroCuenta, nombreTitular, banco, tipoCuenta, cuentaNueva, transferencia);
  }

  @Step("Se seleccionan las coberturas relacionadas con el siniestro")
    public void seleccionarCoberturaDisponible(String cobertura) {
    informacionBasicaReclamacionInteraction.marcarCoberturaSiniestro(cobertura);
    informacionReclamacionInteraction.finalizarSiniestro();
  }
}
