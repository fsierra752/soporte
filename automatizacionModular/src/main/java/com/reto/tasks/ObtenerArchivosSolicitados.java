package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static com.reto.utils.Diccionario.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerArchivosSolicitados implements Task {

    private static EscenarioDTO escenarioDTO;
    private final String archivosSolicitados;
    private final String fechaAyer;

    public ObtenerArchivosSolicitados(String archivosSolicitados, String fechaAyer) {
        this.archivosSolicitados = archivosSolicitados;
        this.fechaAyer = fechaAyer;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        organizarListaDeArchivosSolicitados();
    }

    public static Performable delProceso(EscenarioDTO escenarioDTO, String archivosSolicitados, String fechaAyer) {
        ObtenerArchivosSolicitados.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerArchivosSolicitados.class, archivosSolicitados, fechaAyer);
    }

    public void organizarListaDeArchivosSolicitados() {
        if (archivosSolicitados.contains(CLASE_ARCHIVO)){
            String nombreCSVSolicitado = fechaAyer + SEPADADOR_RUTA + archivosSolicitados + fechaAyer + EXTENCION_CSV;
            escenarioDTO.setArchivoSolicitado(nombreCSVSolicitado);
        } else if (archivosSolicitados.contains(NEGOCIO_VIDA)){
            String[] fechaVida = fechaAyer.split("-");
            String fechaformateadaVida = fechaVida[0] + fechaVida[2] + fechaVida[1];
            String nombreTxtVida = fechaAyer + SEPADADOR_RUTA + INDICATIVO_NEGOCIO
                    + fechaformateadaVida + NEGOCIO_VIDA + EXTENCION_TXT;
            escenarioDTO.setArchivoSolicitado(nombreTxtVida);
        } else {
            String[] negocioGeneral = fechaAyer.split("-");
            String formateadaGeneral = negocioGeneral[0] + negocioGeneral[2] + negocioGeneral[1];
            String nombreTxtGeneral = fechaAyer + SEPADADOR_RUTA + INDICATIVO_NEGOCIO
                    + formateadaGeneral +  NEGOCIO_GENERALES+ EXTENCION_TXT;
            escenarioDTO.setArchivoSolicitado(nombreTxtGeneral);
        }
    }
}
