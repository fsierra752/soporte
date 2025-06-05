package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDAsesoresInsigniaDTO;
import com.reto.models.db.DatosBDGeneralesDTO;
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

public class AsignarRamosCorrespondientes implements Task {

    private static final Logger LOGGER = Logger.getLogger(AsignarRamosCorrespondientes.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();

    @Override
    public <T extends Actor> void performAs(T t) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_PATH.getValue());
        conexionPostgress = conexion.conectarse(auth);
        obtenerInformacionInsigniasPorRamo();
        agregarRamosCorrespondientes();
        conexion.desconectarse(conexionPostgress);
    }

    public static Performable delCotizador(EscenarioDTO escenarioDTO) {
        AsignarRamosCorrespondientes.escenarioDTO = escenarioDTO;
        return instrumented(AsignarRamosCorrespondientes.class);
    }

    private void obtenerInformacionInsigniasPorRamo() {
        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(QUERY_CONSULTAR_RAMOS);
            List<String> columnas = contenidoBD.obtenerColumnas(resultadoConsulta);
            JSONArray serializacion = contenidoBD.convertirResultSetaJSON(resultadoConsulta, columnas);
            List<DatosBDGeneralesDTO> datosBDGeneralesDTOList = obtenerInfoBD(serializacion);
            escenarioDTO.setDatosBDGeneralesDTOList(datosBDGeneralesDTOList);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private List<DatosBDGeneralesDTO> obtenerInfoBD(JSONArray serializacion) {
        List<DatosBDGeneralesDTO> datosRamosInsignias = new ArrayList<>();
        for (int i = 0; i < serializacion.length(); i++) {
            String dataBD = serializacion.get(i).toString();
            datosRamosInsignias.add(new Gson().fromJson(dataBD, DatosBDGeneralesDTO.class));
        }
        return datosRamosInsignias;
    }

    private void agregarRamosCorrespondientes() {
        for (DatosBDAsesoresInsigniaDTO completado : escenarioDTO.getDatosBDAsesoresInsigniaDTOList()) {
            String cdinsigniaCompletado = completado.getCodigoInsignia();
            for (DatosBDGeneralesDTO ramos : escenarioDTO.getDatosBDGeneralesDTOList()) {
                if (cdinsigniaCompletado.equals(ramos.getCodigoInsignia())) {
                    completado.setRamoDelCotizador(ramos.getRamosCotizador());
                }
            }
        }
    }
}
