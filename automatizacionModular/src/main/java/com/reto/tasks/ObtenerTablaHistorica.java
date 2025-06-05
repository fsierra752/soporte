package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.interactions.Esperar;
import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDHistoricaDTO;
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
import static com.reto.utils.QuerysBaseDeDatosIdoneadad.QUERY_CONSULTAR_TABLA_HISTORICA;
import static com.reto.utils.enums.IdoneaBDAuthFile.IDONEA_BD_DLLO_AUTH_FILE_PATH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerTablaHistorica implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerTablaHistorica.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_PATH.getValue());
        conexionPostgress = conexion.conectarse(auth);
        actor.attemptsTo(Esperar.unMomento(5));
        obtenerDatosHistoricos();
        actor.attemptsTo(Esperar.unMomento(5));
        conexion.desconectarse(conexionPostgress);
    }

    public static Performable todosLosAsesores(EscenarioDTO escenarioDTO) {
        ObtenerTablaHistorica.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerTablaHistorica.class);
    }

    private void obtenerDatosHistoricos() {
        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(QUERY_CONSULTAR_TABLA_HISTORICA);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            List<DatosBDHistoricaDTO> datosBDHistoricaList = obtenerInfoBD(serializacion);
            escenarioDTO.setDatosBDHistoricaDTOList(datosBDHistoricaList);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private List<DatosBDHistoricaDTO> obtenerInfoBD(JSONArray serializacion) {
        List<DatosBDHistoricaDTO> datosHistoricos = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String dataBD = serializacion.get(i).toString();
            datosHistoricos.add(new Gson().fromJson(dataBD, DatosBDHistoricaDTO.class));
        }
        return datosHistoricos;
    }
}
