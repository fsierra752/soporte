package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
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
import static com.reto.utils.QuerysBaseDeDatosIdoneadad.QUERY_CONSULTAR_ASESORES_INSIGNIA;
import static com.reto.utils.enums.IdoneaBDAuthFile.IDONEA_BD_DLLO_AUTH_FILE_PATH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerAsesoresInsignias implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerAsesoresInsignias.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();

    @Override
    public <T extends Actor> void performAs(T t) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_PATH.getValue());
        conexionPostgress = conexion.conectarse(auth);
        obtenerAsesores();
        conexion.desconectarse(conexionPostgress);
    }

    public static Performable delProceso(EscenarioDTO escenarioDTO) {
        ObtenerAsesoresInsignias.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerAsesoresInsignias.class);
    }

    private void obtenerAsesores() {
        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(QUERY_CONSULTAR_ASESORES_INSIGNIA);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            List<DatosBDAsesoresInsigniaDTO> datosBDAsesoresInsigniaDTOS = obtenerInfoBD(serializacion);
            escenarioDTO.setDatosBDAsesoresInsigniaDTOList(datosBDAsesoresInsigniaDTOS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private List<DatosBDAsesoresInsigniaDTO> obtenerInfoBD(JSONArray serializacion) {
        List<DatosBDAsesoresInsigniaDTO> datosCodigoNomina = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String dataBD = serializacion.get(i).toString();
            datosCodigoNomina.add(new Gson().fromJson(dataBD, DatosBDAsesoresInsigniaDTO.class));
        }
        return datosCodigoNomina;
    }

}
