package com.retocache.task.postgres;

import com.google.gson.Gson;
import com.retocache.exceptions.FileReadException;
import com.retocache.interactions.Esperar;
import com.retocache.models.db.ParametrosDbModel;
import com.retocache.utils.enums.FileUtilities;
import com.retocache.utils.enums.ParamentrosDBAuthFile;
import com.retocache.utils.postgres.Conexion;
import com.retocache.utils.postgres.Controlador;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.retocache.utils.Diccionario.QUERY_POSTGRES_PARAMETROS;

public class ObtenerParametrosDB implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerParametrosDB.class);
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();

    @Override
    public <T extends Actor> void performAs(T actor) {

        Map<String, String> auth = null;
        try {
            auth = FileUtilities.leerArchivo(ParamentrosDBAuthFile.PARAMETROS_AUTH_FILE_PATH.getValue());
        } catch (FileReadException e) {
            LOGGER.error("Error leyendo archivo de autenticación: " + e.getMessage(), e);
        }
        conexionPostgress = conexion.conectarse(auth);
        actor.attemptsTo(Esperar.unMomento(5));
        List<ParametrosDbModel> parametros = obtenerParametros();
        actor.remember("parametrosDB", parametros);
        conexion.desconectarse(conexionPostgress);
    }

    private List<ParametrosDbModel> obtenerParametros() {
        String query = QUERY_POSTGRES_PARAMETROS;

        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(query);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            return serializarParametros(serializacion);
        } catch (SQLException throwables) {
            LOGGER.error("Error ejecutando el query: " + throwables.getMessage(), throwables);
            return new ArrayList<>();
        }
    }

    private List<ParametrosDbModel> serializarParametros(JSONArray serializacion) {
        List<ParametrosDbModel> parametros = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String parametro = serializacion.get(i).toString();
            parametros.add(new Gson().fromJson(parametro, ParametrosDbModel.class));
        }
        return parametros;
    }

    public static Performable desdePostgres() {
        return Tasks.instrumented(ObtenerParametrosDB.class);
    }
}
