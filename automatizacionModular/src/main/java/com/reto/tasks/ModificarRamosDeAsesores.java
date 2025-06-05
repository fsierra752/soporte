package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ModificarRamosDeAsesores implements Task {

    private static EscenarioDTO escenarioDTO;

    @Override
    public <T extends Actor> void performAs(T actor) {
        duplicarRegistrosPorRamo();
    }

    public static Performable delProcesoYAsistenteTalentoHumano(EscenarioDTO escenarioDTO) {
        ModificarRamosDeAsesores.escenarioDTO = escenarioDTO;
        return instrumented(ModificarRamosDeAsesores.class);
    }

    public void duplicarRegistrosPorRamo() {
        List<DatosBDAsesoresInsigniaDTO> datosOriginales = new ArrayList<>(escenarioDTO.getDatosBDAsesoresInsigniaDTOList());
        escenarioDTO.getDatosBDAsesoresInsigniaDTOList().clear();
        for (DatosBDAsesoresInsigniaDTO reporte : datosOriginales) {
            List<String> codigosRamos = obtenerCodigosDelRamo(reporte.getRamoDelCotizador());
            for (String codigo : codigosRamos) {
                DatosBDAsesoresInsigniaDTO nuevoReporte = copiarReporte(reporte);
                nuevoReporte.setRamoDelCotizador(codigo);
                escenarioDTO.getDatosBDAsesoresInsigniaDTOList().add(nuevoReporte);
            }
        }
    }

    private DatosBDAsesoresInsigniaDTO copiarReporte(DatosBDAsesoresInsigniaDTO original) {
        DatosBDAsesoresInsigniaDTO copia = new DatosBDAsesoresInsigniaDTO();
        copia.setNumeroIdentificacion(original.getNumeroIdentificacion());
        copia.setRamoDelCotizador(original.getRamoDelCotizador());
        copia.setFechaFinInsignia(original.getFechaFinInsignia());
        copia.setEstadoComparacion(original.getEstadoComparacion());
        return copia;
    }

    private List<String> obtenerCodigosDelRamo(String ramoDelCotizador) {
        if (ramoDelCotizador == null || ramoDelCotizador.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(ramoDelCotizador.split(","));
    }
}
