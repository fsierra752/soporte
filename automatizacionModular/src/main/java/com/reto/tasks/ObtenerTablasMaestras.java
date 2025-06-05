package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.interactions.Esperar;
import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDMaestrasInsigniasDTO;
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

import static com.reto.utils.QuerysBaseDeDatosIdoneadad.*;
import static com.reto.utils.FileUtilidades.leerArchivo;
import static com.reto.utils.enums.IdoneaBDAuthFile.IDONEA_BD_DLLO_AUTH_FILE_PATH;

public class ObtenerTablasMaestras implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerTablasMaestras.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();
    private final String tablaMaestra;

    private ObtenerTablasMaestras(String tablaMaestra) {
        this.tablaMaestra = tablaMaestra;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_PATH.getValue());
        conexionPostgress = conexion.conectarse(auth);
        actor.attemptsTo(Esperar.unMomento(5));
        obtenerConsultasVarias();
        actor.attemptsTo(Esperar.unMomento(5));
        conexion.desconectarse(conexionPostgress);
    }

    public static Performable enInsignias(EscenarioDTO escenarioDTO, String tablaMaestra) {
        ObtenerTablasMaestras.escenarioDTO = escenarioDTO;
        return new ObtenerTablasMaestras(tablaMaestra);
    }

    private void obtenerConsultasVarias() {
        String query;
        switch (tablaMaestra) {
            case "asesores":
                query = QUERY_CONSULTAR_INSIGNIAS_EN_ASESORES;
                break;
            case "empleados":
                query = QUERY_CONSULTAR_INSIGNIAS_EN_EMPLEADOS;
                break;
            case "legales":
                query = QUERY_CONSULTAR_INSIGNIAS_EN_LEGALES;
                break;
            case "delegados":
                query = QUERY_CONSULTAR_INSIGNIAS_EN_DELEGADOS;
                break;
            default:
                LOGGER.error("Tipo de consulta no válido: " + tablaMaestra);
                return;
        }
        query = String.format(query);
        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(query);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            List<DatosBDMaestrasInsigniasDTO> datosBDMaestrasInsigniasDTOList = obtenerInfoBD(serializacion);
            escenarioDTO.setDatosBDMaestrasInsigniasDTOList(datosBDMaestrasInsigniasDTOList);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private List<DatosBDMaestrasInsigniasDTO> obtenerInfoBD(JSONArray serializacion) {
        List<DatosBDMaestrasInsigniasDTO> datosMaestrasInsignias = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String dataBD = serializacion.get(i).toString();
            datosMaestrasInsignias.add(new Gson().fromJson(dataBD, DatosBDMaestrasInsigniasDTO.class));
        }
        return datosMaestrasInsignias;
    }
}
