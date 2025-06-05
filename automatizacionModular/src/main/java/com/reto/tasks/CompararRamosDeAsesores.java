package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
import com.reto.models.db.DatosBDAsesoresTalentoHumano;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompararRamosDeAsesores implements Task {

    private static EscenarioDTO escenarioDTO;
    private Actor actor;

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        compararRamos(escenarioDTO.getDatosBDAsesoresInsigniaDTOList(), escenarioDTO.getDatosBDAsesoresTalentoHumanoList());
    }

    public static Performable delProcesoYAsistenteTalentoHumano(EscenarioDTO escenarioDTO) {
        CompararRamosDeAsesores.escenarioDTO = escenarioDTO;
        return instrumented(CompararRamosDeAsesores.class);
    }

    private void compararRamos(List<DatosBDAsesoresInsigniaDTO> datosBDAsesoresInsigniaDTOList,
                               List<DatosBDAsesoresTalentoHumano> datosBDAsesoresTalentoHumanoList) {
        for (DatosBDAsesoresInsigniaDTO asesorInsignia : datosBDAsesoresInsigniaDTOList) {
            for (DatosBDAsesoresTalentoHumano asesorTalentoHumano : datosBDAsesoresTalentoHumanoList) {
                if (asesorInsignia.getNumeroIdentificacion().equals(asesorTalentoHumano.getNumeroDocumento()) && asesorInsignia.getRamoDelCotizador().equals(asesorTalentoHumano.getCodigoRamo())) {
                        indicarCorrecto(asesorInsignia, asesorTalentoHumano);
                }
            }
        }
    }

    private void indicarCorrecto(DatosBDAsesoresInsigniaDTO asesorInsignia,
                                 DatosBDAsesoresTalentoHumano asesorTalentoHumano) {
        actor.attemptsTo(Ensure.that(asesorInsignia.getNumeroIdentificacion()).isEqualTo(asesorTalentoHumano.getNumeroDocumento()));
    }

}
