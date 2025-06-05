package com.variada.steps.guidewire.claimscenter.autos;

import static com.variada.utils.constantes.MenuConstante.VOLVER_CLAIMCENTER_MENU;
import static com.variada.utils.enums.EnumNombreProcesoBatch.ENVIO_FACTURA_VOLUMEN;
import static com.variada.utils.enums.EnumNombreProcesoBatch.MONITOR_FLUJO_TRABAJO_FACTURA_VOLUMEN;
import static com.variada.utils.enums.EnumNombreProcesoBatch.TRANSFERENCIA_FACTURA_VOLUMEN;
import com.variada.pages.interactions.guidewire.claimscenter.autos.ProcesoBatchInteraction;
import org.fluentlenium.core.annotation.Page;

public class ProcesoBatchStep {

  @Page ProcesoBatchInteraction procesoBatchInteraction;

  public void ejecutarProcesoBatch() {
    procesoBatchInteraction.ejecutarBatch();
    procesoBatchInteraction.ejecutarProcesoBatch(ENVIO_FACTURA_VOLUMEN.getValor());
    procesoBatchInteraction.ejecutarProcesoBatch(MONITOR_FLUJO_TRABAJO_FACTURA_VOLUMEN.getValor());
    procesoBatchInteraction.ejecutarProcesoBatch(TRANSFERENCIA_FACTURA_VOLUMEN.getValor());
    procesoBatchInteraction.seleccionarOpcionMenuAccion();
    procesoBatchInteraction.seleccionarOpcionMenuAccionesPrimerNivel(VOLVER_CLAIMCENTER_MENU);
  }
}
