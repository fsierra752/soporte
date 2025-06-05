package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDMaestrasInsigniasDTO;
import com.reto.models.db.DatosBDTemporalAsesoresInsigniasDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarInfoMaestrasYTemporal implements Task {

    private final EscenarioDTO escenarioDTO;
    private Actor actor;

    public ValidarInfoMaestrasYTemporal(EscenarioDTO escenarioDTO) {
        this.escenarioDTO = escenarioDTO;
    }

    public static Performable validarInfoEntroDatos(EscenarioDTO escenarioDTO) {
        return instrumented(ValidarInfoMaestrasYTemporal.class, escenarioDTO);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        sonIguales(escenarioDTO.getDatosBDMaestrasInsigniasDTOList(), escenarioDTO.getDatosBDTemporalAsesoresInsigniasDTOList());
    }

    public void sonIguales(List<DatosBDMaestrasInsigniasDTO> datosMaestras, List<DatosBDTemporalAsesoresInsigniasDTO> datosTemporal){
        for (int i = 0; i < datosMaestras.size(); i++) {
            DatosBDMaestrasInsigniasDTO resultadoUno = datosMaestras.get(i);
            DatosBDTemporalAsesoresInsigniasDTO resultadoDos = datosTemporal.get(i);
            actor.attemptsTo(Ensure.that(resultadoUno.getTotalRegistros()).isEqualTo(resultadoDos.getTotalRegistros()));
        }
    }

}
