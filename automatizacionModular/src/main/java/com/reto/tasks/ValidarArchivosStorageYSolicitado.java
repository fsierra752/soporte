package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.csv.ArchivosAlmacenadosYSolicitados;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.log4j.Logger;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarArchivosStorageYSolicitado implements Task {

    private static final Logger LOGGER = Logger.getLogger(ValidarArchivosStorageYSolicitado.class);
    private final EscenarioDTO escenarioDTO;
    private Actor actor;

    public ValidarArchivosStorageYSolicitado(EscenarioDTO escenarioDTO) {
        this.escenarioDTO = escenarioDTO;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        validarExistenciaDeArchivos(escenarioDTO.getArchivosAlmacenadosYSolicitadosList(), escenarioDTO.getArchivoSolicitado());
    }

    public static Performable fueronEncontradosIguales(EscenarioDTO escenarioDTO) {
        return instrumented(ValidarArchivosStorageYSolicitado.class, escenarioDTO);
    }

    public void validarExistenciaDeArchivos(List<ArchivosAlmacenadosYSolicitados> archivoStorage, String archivoSolicitado) {
        boolean archivoEncontrado = false;
        for (ArchivosAlmacenadosYSolicitados archivo : archivoStorage) {
            if (archivo.getNombreArchivoEnBlobStorage().equals(archivoSolicitado)) {
                archivoEncontrado = true;
            }
        }
        if (!archivoEncontrado) {
            LOGGER.error("No se encontró el archivo solicitado en el Blob Storage");
            actor.attemptsTo(Ensure.that(archivoEncontrado).isTrue());
        }
    }

}
