package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;
import com.reto.models.db.DatosBDMaestrasYNovedadesParaFiltrosDTO;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarCargosParaNoReportar implements Task {

    private static final Logger LOGGER = Logger.getLogger(ValidarCargosParaNoReportar.class);
    private final EscenarioDTO escenarioDTO;
    private final String cargoNoAutorizado;


    public ValidarCargosParaNoReportar(EscenarioDTO escenarioDTO, String cargoNoAutorizado) {
        this.escenarioDTO = escenarioDTO;
        this.cargoNoAutorizado = cargoNoAutorizado;
    }

    public static Performable enNovedades(EscenarioDTO escenarioDTO, String cargoNoAutorizado) {
        return instrumented(ValidarCargosParaNoReportar.class, escenarioDTO, cargoNoAutorizado);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        cuandoSeValidaElTipoCargoNoAutorizado(escenarioDTO.getDatosBDMaestrasYNovedadesParaFiltrosDTOList());
    }

    private void cuandoSeValidaElTipoCargoNoAutorizado(List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosAValidar) {
        if (datosAValidar.isEmpty()) {
            LOGGER.warn("La consulta de novedades viene vacía.");
            return;
        }
        boolean hayCorredor = datosAValidar.stream()
                .anyMatch(dato -> datosAValidar.get(0).getTipoVinculacion().equals(dato.getDescripcionCargo()));
        for (DatosBDMaestrasYNovedadesParaFiltrosDTO dato : datosAValidar) {
            if (dato.getTipoVinculacion().equals(cargoNoAutorizado)) {
                LOGGER.warn("El tipo de cargo " + dato.getDescripcionCargo() + " no está autorizado para reportar.");
            } else if (hayCorredor) {
                LOGGER.info("El tipo de cargo " + dato.getDescripcionCargo() + " no está permitido.");
            }
        }
    }


}
