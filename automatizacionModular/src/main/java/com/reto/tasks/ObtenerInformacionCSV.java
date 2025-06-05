package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.csv.DatosCSVEscenarioDTO;
import com.reto.utils.FileUtilidades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.List;

import static com.reto.utils.Diccionario.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerInformacionCSV implements Task {

    private static EscenarioDTO escenarioDTO;
    private final String negocio;
    private final String tipoArchivo;

    public ObtenerInformacionCSV(String negocio, String tipoArchivo) {
        this.negocio = negocio;
        this.tipoArchivo = tipoArchivo;
    }

    public static Performable leerArchivoCSV(EscenarioDTO escenarioDTO, String negocio, String tipoArchivo) {
        ObtenerInformacionCSV.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerInformacionCSV.class, negocio, tipoArchivo);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        leerYGuardarDatosDesdeCSV(negocio, tipoArchivo);
    }

    private void leerYGuardarDatosDesdeCSV(String nombreArchivo, String tipoArchivo) {
        String rutaCompleta = RUTA_CSV + tipoArchivo + SEPADADOR_RUTA + NOMBRE_PRINCIPAL + nombreArchivo + EXTENCION_CSV;
        List<DatosCSVEscenarioDTO> datos = FileUtilidades.leerArchivosCSVLocales(rutaCompleta, DatosCSVEscenarioDTO.class);
        escenarioDTO.setDatosCSVEscenarioDTOList(datos);
    }

}
