package com.variada.steps.guidewire.claimscenter.autos;

import com.variada.models.Exposicion;
import com.variada.models.PagoSiniestro;
import com.variada.models.Reserva;
import com.variada.pages.interactions.general.GeneralInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.autos.CargaArchivoPagoMasivoInteraction;
import com.variada.pages.interactions.guidewire.claimscenter.comunes.MenuClaimInteraction;
import com.variada.utils.LlenadoArchivoXLS;
import java.io.File;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;

public class CargaArchivoPagoMasivoStep {

  LlenadoArchivoXLS llenadoArchivoXLS = new LlenadoArchivoXLS();
  String nombreArchivoPagoMasivo = "PlantillaPagosMasivos.xlsx";
  File resourcesDirectory = new File("src/test/resources/files");

  String rutaArchivoPagoMasivo =
      resourcesDirectory.getAbsolutePath() + "/" + nombreArchivoPagoMasivo;

  @Page MenuClaimInteraction menuClaimInteraction;
  @Page CargaArchivoPagoMasivoInteraction cargaArchivoPagoMasivoInteraction;
  @Page GeneralInteraction generalInteraction;

  @Step
  public void cargarArchivoXls(
      String nombreOpcion,
      String subItem,
      List<Exposicion> datosExposicionPagoMasivo,
      List<Reserva> datosReservaPagoMasivo,
      List<PagoSiniestro> datosPagoSiniestroPagoMasivo) {
    menuClaimInteraction.seleccionarOpcionMenuSegundoNivel(nombreOpcion, subItem);
    cargaArchivoPagoMasivoInteraction.generarFacturacionMasiva();
    llenadoArchivoXLS.llenarArchivoXls(
        rutaArchivoPagoMasivo,
        datosExposicionPagoMasivo,
        datosReservaPagoMasivo,
        datosPagoSiniestroPagoMasivo);
    cargaArchivoPagoMasivoInteraction.buscarArchivoPagoMasivo(rutaArchivoPagoMasivo);
    generalInteraction.continuarSiguientePantalla();
  }
}
