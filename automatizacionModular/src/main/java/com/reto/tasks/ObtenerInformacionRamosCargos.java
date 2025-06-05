package com.reto.tasks;

import com.google.gson.Gson;
import com.reto.models.EscenarioDTO;
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

import static com.reto.utils.QuerysBaseDeDatosIdoneadad.QUERY_CONSULTAR_RAMOS_GENERALES;
import static com.reto.utils.QuerysBaseDeDatosIdoneadad.QUERY_CONSULTAR_RAMOS_VIDA;
import static com.reto.utils.QuerysBaseDeDatosIdoneadad.QUERY_CONSULTAR_CARGOS_EXCLUIDOS;
import static com.reto.utils.FileUtilidades.leerArchivo;
import static com.reto.utils.enums.IdoneaBDAuthFile.IDONEA_BD_DLLO_AUTH_FILE_PATH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerInformacionRamosCargos implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerInformacionRamosCargos.class);
    private static EscenarioDTO escenarioDTO;
    private Connection conexionPostgress;
    private final Conexion conexion = new Conexion();
    private final Controlador contenidoBD = new Controlador();
    private final String ramosaBuscar;
    private final String tipoConsulta;

    public ObtenerInformacionRamosCargos(String ramosaBuscar, String tipoConsulta) {
        this.ramosaBuscar = ramosaBuscar;
        this.tipoConsulta = tipoConsulta;
    }

    public static Performable deBaseDeDatos(EscenarioDTO escenarioDTO, String ramosaBuscar, String tipoConsulta) {
        ObtenerInformacionRamosCargos.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerInformacionRamosCargos.class, ramosaBuscar, tipoConsulta);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<?, ?> auth = (Map<?, ?>) leerArchivo(IDONEA_BD_DLLO_AUTH_FILE_PATH.getValue());
        conexionPostgress = conexion.conectarse(auth);
        obtenerInfoInsigniasPorRamo();
        conexion.desconectarse(conexionPostgress);
    }

    private void obtenerInfoInsigniasPorRamo() {
        String query;
        switch (tipoConsulta) {
            case "generales":
                query = QUERY_CONSULTAR_RAMOS_GENERALES;
                break;
            case "vida":
                query = QUERY_CONSULTAR_RAMOS_VIDA;
                break;
            case "excluidos":
                query = QUERY_CONSULTAR_CARGOS_EXCLUIDOS;
                break;
            default:
                LOGGER.error("Tipo de consulta no válido: " + tipoConsulta);
                return;
        }
        if(tipoConsulta.equals("excluidos")) query = String.format(query);
        else query = String.format(query, ramosaBuscar);
        try (Statement declaracion = conexionPostgress.createStatement()) {
            ResultSet resultadoConsulta = declaracion.executeQuery(query);
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

}
