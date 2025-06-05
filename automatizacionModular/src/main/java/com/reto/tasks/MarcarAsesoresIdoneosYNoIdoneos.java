package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
import com.reto.models.db.DatosBDHistoricaDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static com.reto.utils.Diccionario.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MarcarAsesoresIdoneosYNoIdoneos implements Task {

    private static EscenarioDTO escenarioDTO;

    @Override
    public <T extends Actor> void performAs(T t) {
        realizarMarcacion();
    }

    public static Performable paraParaElAsistente(EscenarioDTO escenarioDTO) {
        MarcarAsesoresIdoneosYNoIdoneos.escenarioDTO = escenarioDTO;
        return instrumented(MarcarAsesoresIdoneosYNoIdoneos.class);
    }

    private void realizarMarcacion() {
        for (DatosBDAsesoresInsigniaDTO completado : escenarioDTO.getDatosBDAsesoresInsigniaDTOList()) {
            boolean coincidenciaVida = false;
            for (DatosBDHistoricaDTO vida : escenarioDTO.getDatosBDHistoricaDTOList()) {
                if (completado.getNumeroIdentificacion().equals(vida.getNumeroIdentificacion())) {
                    completado.setIdoneo(ES_IDONEO);
                    completado.setNoIdoneo(NO_IDONEO);
                    coincidenciaVida = true;
                }
            }
            if (!coincidenciaVida) {
                completado.setNoIdoneo(NO_ES_IDONEO);
                completado.setIdoneo(NO_IDONEO);
            }
        }
    }
}
