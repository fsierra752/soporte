package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresTalentoHumano;
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
import static com.reto.utils.QuerysBaseDeDatosIdoneadad.QUERY_CONSULTAR_ASESORES_RAMOS_DEL_COTIZADOR;
import static com.reto.utils.enums.IdoneaBDAuthFileOracle.IDONEA_BD_DLLO_AUTH_FILE_ORACLE_PATH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerRamosDeAsesoresCotizador implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerRamosDeAsesoresCotizador.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionOracle;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();

    @Override
    public <T extends Actor> void performAs(T t) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_ORACLE_PATH.getValue());
        conexionOracle = conexion.conectarseOracle(auth);
        obtenerInformacionTalentoHumano();
        conexion.desconectarse(conexionOracle);
    }

    public static Performable asistenteTalentoHumano(EscenarioDTO escenarioDTO) {
        ObtenerRamosDeAsesoresCotizador.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerRamosDeAsesoresCotizador.class);
    }

    private void obtenerInformacionTalentoHumano() {
        try (Statement declaracion = conexionOracle.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(QUERY_CONSULTAR_ASESORES_RAMOS_DEL_COTIZADOR);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            List<DatosBDAsesoresTalentoHumano> datosBDAsesoresTalentoHumanos = obtenerInfoBD(serializacion);
            escenarioDTO.setDatosBDAsesoresTalentoHumanoList(datosBDAsesoresTalentoHumanos);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private List<DatosBDAsesoresTalentoHumano> obtenerInfoBD(JSONArray serializacion) {
        List<DatosBDAsesoresTalentoHumano> datosTalentoHumano = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String dataBD = serializacion.get(i).toString();
            datosTalentoHumano.add(new Gson().fromJson(dataBD, DatosBDAsesoresTalentoHumano.class));
        }
        return datosTalentoHumano;
    }
}
