package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDMaestrasYNovedadesParaFiltrosDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.log4j.Logger;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarAsesoresEnNovedades implements Task {

    private static final Logger LOGGER = Logger.getLogger(ValidarAsesoresEnNovedades.class);
    private final EscenarioDTO escenarioDTO;
    private Actor actor;
    private final String tipoCargo;

    public ValidarAsesoresEnNovedades(EscenarioDTO escenarioDTO,  String tipoCargo) {
        this.escenarioDTO = escenarioDTO;
        this.tipoCargo = tipoCargo;
    }

    public static Performable tipoDeVinculacion(EscenarioDTO escenarioDTO, String tipoCargo) {
        return instrumented(ValidarAsesoresEnNovedades.class, escenarioDTO, tipoCargo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        cuandoSeValidaElTipoDeVinculacion(escenarioDTO.getDatosBDMaestrasYNovedadesParaFiltrosDTOList());
    }

    private void cuandoSeValidaElTipoDeVinculacion(List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosAValidar) {
        for(DatosBDMaestrasYNovedadesParaFiltrosDTO dato : datosAValidar) {
            if(dato.getTipoVinculacion().equals(tipoCargo)) {
                actor.attemptsTo(Ensure.that(dato.getTipoVinculacion()).isEqualTo(tipoCargo));
            } else {
                LOGGER.error(" Tipo de cargo " + dato.getDescripcionCargo() + " no autorizado para reportar");
                }
            }
        }
}