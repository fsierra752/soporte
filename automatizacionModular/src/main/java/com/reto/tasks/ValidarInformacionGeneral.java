package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.csv.DatosCSVEscenarioDTO;
import com.reto.models.db.DatosBDGeneralesDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarInformacionGeneral implements Task {

    private final EscenarioDTO escenarioDTO;
    private Actor actor;
    private final String tipoConsulta;

    public ValidarInformacionGeneral(EscenarioDTO escenarioDTO, String tipoConsulta) {
        this.escenarioDTO = escenarioDTO;
        this.tipoConsulta = tipoConsulta;
    }

    public static Performable archivosCSVYBaseDeDatos(EscenarioDTO escenarioDTO, String tipoConsulta) {
        return instrumented(ValidarInformacionGeneral.class, escenarioDTO, tipoConsulta);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        List<DatosBDGeneralesDTO> listaBdNormalizada = normalizarDatosBD(escenarioDTO.getDatosBDGeneralesDTOList());
        List<DatosCSVEscenarioDTO> datosCSV = escenarioDTO.getDatosCSVEscenarioDTOList();
        compararDatos(listaBdNormalizada, datosCSV);
    }

    private void compararDatos(List<DatosBDGeneralesDTO> datosBD, List<DatosCSVEscenarioDTO> datosCSV) {
        switch (tipoConsulta) {
            case "excluidos":
                validarNumeroCargoExcluido(datosBD, datosCSV);
                break;
            case "fasecolda":
                validarCamposReporte(datosBD, datosCSV);
                break;
            default:
                validarNombreInsignia(datosBD, datosCSV);
                validarRamosGeneral(datosBD, datosCSV);
                validarNumeroInsignia(datosBD, datosCSV);
                break;
        }
    }


    private List<DatosBDGeneralesDTO> normalizarDatosBD(List<DatosBDGeneralesDTO> lista) {
        if (lista != null) {
            for (DatosBDGeneralesDTO dto : lista) {
                dto.setNombreInsignia(normalizarTexto(dto.getNombreInsignia()));
            }
        }
        return lista;
    }

    private String normalizarTexto(String texto) {
        if (texto == null) {
            return null;
        }
        texto = texto.replaceAll("[áàä]", "a")
                .replaceAll("[éèë]", "e")
                .replaceAll("[óòö]", "o")
                .replaceAll("[ÉÈË]", "E")
                .replaceAll("[^a-zA-Z0-9\\sáéíóú]", "")
                .replaceAll("\\s+", " ");
        return texto;
    }

    private void validarNombreInsignia(List<DatosBDGeneralesDTO> datosBD, List<DatosCSVEscenarioDTO> datosCSV) {
        for (int i = 0; i < datosBD.size(); i++) {
            DatosBDGeneralesDTO bd = datosBD.get(i);
            DatosCSVEscenarioDTO csv = datosCSV.get(i);
            actor.attemptsTo(Ensure.that(bd.getNombreInsignia()).isEqualTo(csv.getNombreInsignia()));
        }
    }

    private void validarRamosGeneral(List<DatosBDGeneralesDTO> datosBD, List<DatosCSVEscenarioDTO> datosCSV) {
        for (int i = 0; i < datosBD.size(); i++) {
            DatosBDGeneralesDTO bd = datosBD.get(i);
            DatosCSVEscenarioDTO csv = datosCSV.get(i);
            actor.attemptsTo(Ensure.that(bd.getRamosGeneral()).isEqualTo(csv.getRamosGeneral()));
        }
    }

    private void validarNumeroInsignia(List<DatosBDGeneralesDTO> datosBD, List<DatosCSVEscenarioDTO> datosCSV) {
        for (int i = 0; i < datosBD.size(); i++) {
            DatosBDGeneralesDTO bd = datosBD.get(i);
            DatosCSVEscenarioDTO csv = datosCSV.get(i);
            actor.attemptsTo(Ensure.that(bd.getCodigoInsignia()).isEqualTo(csv.getCodigoInsignia()));
        }
    }

    private void validarNumeroCargoExcluido(List<DatosBDGeneralesDTO> datosBD, List<DatosCSVEscenarioDTO> datosCSV) {
        for (int i = 0; i < datosBD.size(); i++) {
            DatosBDGeneralesDTO bd = datosBD.get(i);
            DatosCSVEscenarioDTO csv = datosCSV.get(i);
            actor.attemptsTo(Ensure.that(bd.getNumeroCargo()).isEqualTo(csv.getCodigoCargo()));
        }
    }

    private void validarCamposReporte(List<DatosBDGeneralesDTO> datosBD, List<DatosCSVEscenarioDTO> datosCSV) {
        for (int i = 0; i < datosBD.size(); i++) {
            DatosBDGeneralesDTO bd = datosBD.get(i);
            DatosCSVEscenarioDTO csv = datosCSV.get(i);
            actor.attemptsTo(Ensure.that(bd.getNombreDeColumnas()).isEqualTo(csv.getNombreColumnas()));
            actor.attemptsTo(Ensure.that(bd.getTotalDeColumnas()).isEqualTo(csv.getTotalColumnas()));
            actor.attemptsTo(Ensure.that(bd.getNombreDeColumnas()).isEqualTo(csv.getNombreColumnas()));
        }
    }

}
