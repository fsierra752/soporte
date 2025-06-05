package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.interactions.Esperar;
import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDMaestrasYNovedadesParaFiltrosDTO;
import com.reto.utils.dbpostgress.Conexion;
import com.reto.utils.dbpostgress.Controlador;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.reto.utils.FileUtilidades.leerArchivo;
import static com.reto.utils.QuerysBaseDeDatosIdoneadad.*;
import static com.reto.utils.enums.IdoneaBDAuthFile.IDONEA_BD_DLLO_AUTH_FILE_PATH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerAsesoresEnNovedades implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerAsesoresEnNovedades.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();
    private final String tablaConsultada;
    private final String filtro;

    public ObtenerAsesoresEnNovedades(String tablaConsultada, String filtro) {
        this.tablaConsultada = tablaConsultada;
        this.filtro = filtro;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_PATH.getValue());
        conexionPostgress = conexion.conectarse(auth);
        actor.attemptsTo(Esperar.unMomento(5));
        obtenerDataEnNovedades();
        actor.attemptsTo(Esperar.unMomento(5));
        conexion.desconectarse(conexionPostgress);
    }

    public static Performable preInformacionDeLosDocumentos(EscenarioDTO escenarioDTO, String tablaConsultada, String filtro) {
        ObtenerAsesoresEnNovedades.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerAsesoresEnNovedades.class, tablaConsultada, filtro);
    }

    private void obtenerDataEnNovedades() {
        String query;
        switch (tablaConsultada) {
            case "asesores":
                query = QUERY_CONSULTAR_ASESORES_EN_NOVEDADES_ODIONEAS;
                break;
            case "empleados":
                query = QUERY_CONSULTAR_EMPLEADOS_EN_NOVEDADES_ODIONEAS;
                break;
            case "legales":
                query = QUERY_CONSULTAR_REPRESENTATES_LEGALES_EN_NOVEDADES_ODIONEAS;
                break;
            default:
                LOGGER.error("Tipo de consulta no válido: " + tablaConsultada);
                return;
        }
        query = String.format(query, filtro);
        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(query);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosBDMaestrasYNovedadesParaFiltrosList = obtenerInfoBD(serializacion);
            escenarioDTO.setDatosBDMaestrasYNovedadesParaFiltrosDTOList(datosBDMaestrasYNovedadesParaFiltrosList);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private List<DatosBDMaestrasYNovedadesParaFiltrosDTO> obtenerInfoBD(JSONArray serializacion) {
        List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosNovedades = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String dataBD = serializacion.get(i).toString();
            datosNovedades.add(new Gson().fromJson(dataBD, DatosBDMaestrasYNovedadesParaFiltrosDTO.class));
        }
        return datosNovedades;
    }

}
