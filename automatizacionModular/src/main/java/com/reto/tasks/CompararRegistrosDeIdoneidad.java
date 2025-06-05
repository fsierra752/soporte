package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
import com.reto.models.db.DatosBDAsesoresTalentoHumano;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;

import static com.reto.utils.Diccionario.ESTADO_CORRECTO;
import static com.reto.utils.Diccionario.ESTADO_INCORRECTO_FECHA;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompararRegistrosDeIdoneidad implements Task {

    private static final Logger LOGGER = Logger.getLogger(CompararRegistrosDeIdoneidad.class);
    private static EscenarioDTO escenarioDTO;

    @Override
    public <T extends Actor> void performAs(T t) {
        realizarComparacionDelProcesoConTalentoHumano();
    }

    public static Performable delProcesoYAsistenteTalentoHumano(EscenarioDTO escenarioDTO) {
        CompararRegistrosDeIdoneidad.escenarioDTO = escenarioDTO;
        return instrumented(CompararRegistrosDeIdoneidad.class);
    }

    public void realizarComparacionDelProcesoConTalentoHumano() {
        for (DatosBDAsesoresInsigniaDTO completados : escenarioDTO.getDatosBDAsesoresInsigniaDTOList()) {
            procesarCompletado(completados);
        }
    }

    private void procesarCompletado(DatosBDAsesoresInsigniaDTO completados) {
        DatosBDAsesoresTalentoHumano asesor = buscarAsesorPorIdentificacion(completados.getNumeroIdentificacion());
        if (asesor == null) {
            LOGGER.warn("No se encontró el número de identificación del proceso "
                    + completados.getNumeroIdentificacion()
                    + " en el cotizador de talento humano, revisar el proceso!");
            return;
        }
        if (compararIdoneidad(completados, asesor)) {
            validarFechas(completados, asesor);
        } else {
            LOGGER.error("El registro existe en base hisotrica "
                    + completados.getNumeroIdentificacion() +
                    " pero su idoneidad del cotizador "
                    + asesor.getTipoIdoneidad() +
                    " no coincide con la idoneidad del proceso "
                    + completados.getIdoneo() + " o " + completados.getNoIdoneo());
        }
    }

    private DatosBDAsesoresTalentoHumano buscarAsesorPorIdentificacion(String numeroIdentificacion) {
        return escenarioDTO.getDatosBDAsesoresTalentoHumanoList().stream()
                .filter(asesor -> asesor.getNumeroDocumento().equals(numeroIdentificacion))
                .findFirst()
                .orElse(null);
    }

    private void validarFechas(DatosBDAsesoresInsigniaDTO completados, DatosBDAsesoresTalentoHumano asesor) {
        if (completados.getFechaFinInsignia().equals(asesor.getFechaBaja())) {
            completados.setEstadoComparacion(ESTADO_CORRECTO);
        } else {
            completados.setEstadoComparacion(ESTADO_INCORRECTO_FECHA);
            LOGGER.error("La fecha de fin de insignia del proceso " + completados.getFechaFinInsignia() +
                    " no coincide para el cotizador " + asesor.getFechaBaja());
        }
    }

    private boolean compararIdoneidad(DatosBDAsesoresInsigniaDTO completados, DatosBDAsesoresTalentoHumano asesoresTalentoHumano) {
        return completados.getIdoneo().equals(asesoresTalentoHumano.getTipoIdoneidad())
                || completados.getNoIdoneo().equals(asesoresTalentoHumano.getTipoIdoneidad());
    }

}
