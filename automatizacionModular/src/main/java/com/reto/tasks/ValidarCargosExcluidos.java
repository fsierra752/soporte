package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDMaestrasYNovedadesParaFiltrosDTO;
import com.reto.models.db.DatosBDGeneralesDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Performable;
import org.apache.log4j.Logger;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarCargosExcluidos implements Task {

    private static final Logger LOGGER = Logger.getLogger(ValidarCargosExcluidos.class);
    private final EscenarioDTO escenarioDTO;

    public ValidarCargosExcluidos(EscenarioDTO escenarioDTO) {
        this.escenarioDTO = escenarioDTO;
    }

    public static Performable enNovedades(EscenarioDTO escenarioDTO) {
        return instrumented(ValidarCargosExcluidos.class, escenarioDTO);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        excluirCargos(escenarioDTO.getDatosBDGeneralesDTOList(), escenarioDTO.getDatosBDMaestrasYNovedadesParaFiltrosDTOList());
    }

    public void excluirCargos(List<DatosBDGeneralesDTO> datosCargos, List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosNovedades) {
        if (datosNovedades.isEmpty()) {
            LOGGER.warn("La consulta de novedades viene vacía.");
            return; // Salir del método si no hay novedades
        }
        for (DatosBDGeneralesDTO cargo : datosCargos) {
            boolean existe = datosNovedades.stream()
                    .anyMatch(novedad -> novedad.getCodigoCargo().equals(cargo.getNumeroCargo()));
            if (existe) {
                LOGGER.warn("El número de cargo " + cargo.getNumeroCargo() + " no está permitido para reportar.");
            }
        }
    }


}
