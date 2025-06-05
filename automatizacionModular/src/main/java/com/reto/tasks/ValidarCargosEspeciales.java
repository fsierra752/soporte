package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDMaestrasYNovedadesParaFiltrosDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarCargosEspeciales implements Task {

    private static final Logger LOGGER = Logger.getLogger(ValidarCargosEspeciales.class);
    private final EscenarioDTO escenarioDTO;
    private Actor actor;
    private final String tipoCargo;
    private final String ramoVida;
    private final String ramoGeneral;

    public ValidarCargosEspeciales(EscenarioDTO escenarioDTO, String tipoCargo, String ramoVida, String ramoGeneral) {
        this.escenarioDTO = escenarioDTO;
        this.tipoCargo = tipoCargo;
        this.ramoVida = ramoVida;
        this.ramoGeneral = ramoGeneral;
    }

    public static Performable enNovedades(EscenarioDTO escenarioDTO, String tipoCargo, String ramoVida, String ramoGeneral) {
        return instrumented(ValidarCargosEspeciales.class, escenarioDTO, tipoCargo, ramoVida, ramoGeneral);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        validarCargosEspeciales(escenarioDTO.getDatosBDMaestrasYNovedadesParaFiltrosDTOList());
    }

    private void validarCargosEspeciales(List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosBDMaestrasYNovedadesParaFiltrosDTOList) {
        String[] tiposCargos = tipoCargo.split(" O ");
        if (datosBDMaestrasYNovedadesParaFiltrosDTOList.isEmpty()) {
            LOGGER.warn("La lista de novedades viene vacía.");
            return;
        }
        List<DatosBDMaestrasYNovedadesParaFiltrosDTO> filtradosPorRamoVida = datosBDMaestrasYNovedadesParaFiltrosDTOList.stream()
                .filter(dato -> dato.getRamosAutorizados().contains(ramoVida))
                .collect(Collectors.toList());
        for (DatosBDMaestrasYNovedadesParaFiltrosDTO dato : filtradosPorRamoVida) {
            boolean encontrado = Arrays.stream(tiposCargos)
                    .anyMatch(dato.getDescripcionCargo()::contains);
            if (encontrado) {
                actor.attemptsTo(Ensure.that(dato.getDescripcionCargo()).isNotEmpty());
            } else {
                LOGGER.error(" Tipo de cargo " + dato.getDescripcionCargo() + " no autorizado para reportar");
            }
        }

        List<DatosBDMaestrasYNovedadesParaFiltrosDTO> filtradosPorRamoGeneral = datosBDMaestrasYNovedadesParaFiltrosDTOList.stream()
                .filter(dato -> dato.getRamosAutorizados().contains(ramoGeneral))
                .collect(Collectors.toList());
        for (DatosBDMaestrasYNovedadesParaFiltrosDTO dato : filtradosPorRamoGeneral) {
            boolean encontrado = Arrays.stream(tiposCargos)
                    .anyMatch(dato.getDescripcionCargo()::contains);
            if (encontrado) {
                actor.attemptsTo(Ensure.that(dato.getDescripcionCargo()).isNotEmpty());
            } else {
                LOGGER.error(" Tipo de cargo " + dato.getDescripcionCargo() + " no autorizado para reportar");
            }
        }
    }


}
