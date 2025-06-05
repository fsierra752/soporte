package com.reto.tasks;

import com.opencsv.bean.CsvBindByName;
import com.reto.models.EscenarioDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarCamposDeArchivos implements Task {

    private final Map<String, String> parametros;
    private final EscenarioDTO escenarioDTO;

    public ValidarCamposDeArchivos(Map<String, String> parametros, EscenarioDTO escenarioDTO) {
        this.parametros = parametros;
        this.escenarioDTO = escenarioDTO;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        compararCampos(parametros, escenarioDTO);
    }

    public static Performable encontrados(Map<String, String> parametros,  EscenarioDTO escenarioDTO) {
        return instrumented(ValidarCamposDeArchivos.class, parametros, escenarioDTO);
    }

    private void compararCampos(Map<String, String> parametros, EscenarioDTO escenarioDTO) {
        List<String> nombresDeEncabezados = escenarioDTO.getDatosDeArchivoFtpDTOList()
                .stream()
                .flatMap(dto -> Arrays.stream(dto.getClass().getDeclaredFields()))
                .filter(field -> field.isAnnotationPresent(CsvBindByName.class))
                .map(field -> field.getAnnotation(CsvBindByName.class).column())
                .collect(Collectors.toList());

        parametros.forEach((clave, nombreEsperado) -> {
            if (!nombresDeEncabezados.contains(nombreEsperado)) {
                throw new AssertionError(String.format("El campo '%s' con el nombre '%s' no está presente en los encabezados.",
                        clave, nombreEsperado));
            }
        });
    }


}
