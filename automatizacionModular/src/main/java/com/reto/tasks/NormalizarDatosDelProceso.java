package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.reto.utils.Diccionario.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NormalizarDatosDelProceso implements Task {

    private static EscenarioDTO escenarioDTO;

    @Override
    public <T extends Actor> void performAs(T t) {
        normalizarNumeroIdentifcacion();
        normalizarFechas();
    }

    public static Performable paraElAsistente(EscenarioDTO escenarioDTO) {
        NormalizarDatosDelProceso.escenarioDTO = escenarioDTO;
        return instrumented(NormalizarDatosDelProceso.class);
    }

    public void normalizarNumeroIdentifcacion() {
        for (DatosBDAsesoresInsigniaDTO completados : escenarioDTO.getDatosBDAsesoresInsigniaDTOList()) {
            if(completados.getTipoIdentificacion().equals(NO_ESPECIFICADO)){
                completados.setNumeroIdentificacion(TIPO_NO_ESPECIFICADO + completados.getNumeroIdentificacion());
            }
            if(completados.getTipoIdentificacion().equals(CEDULA_CIUDADANIA)) {
                completados.setNumeroIdentificacion(TIPO_CEDULA_CIUDADANIA + completados.getNumeroIdentificacion());
            }
            if(completados.getTipoIdentificacion().equals(NIT)) {
                completados.setNumeroIdentificacion(TIPO_NIT + completados.getNumeroIdentificacion());
            }
            if(completados.getTipoIdentificacion().equals(PASAPORTE)) {
                completados.setNumeroIdentificacion(TIPO_PASAPORTE + completados.getNumeroIdentificacion());
            }
            if(completados.getTipoIdentificacion().equals(CEDULA_EXTRANJERIA)) {
                completados.setNumeroIdentificacion(TIPO_CEDULA_EXTRANJERIA + completados.getNumeroIdentificacion());
            }
        }
    }

    public void normalizarFechas() {
        for (DatosBDAsesoresInsigniaDTO completados : escenarioDTO.getDatosBDAsesoresInsigniaDTOList()) {
            completados.setFechaInicioInsignia(formatearFecha(completados.getFechaInicioInsignia()));
            completados.setFechaFinInsignia(formatearFecha(completados.getFechaFinInsignia()));
        }
    }

    public static String formatearFecha(String fechaOriginal) {
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.0");
        return LocalDate.parse(fechaOriginal, formatoEntrada).format(formatoSalida);
    }

}
