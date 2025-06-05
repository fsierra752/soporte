package com.reto.tasks;

import com.reto.models.EscenarioDTO;
import com.reto.models.db.DatosBDHistoricaDTO;
import com.reto.models.csv.DatosDeArchivoFtpDTO;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.log4j.Logger;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarDatosDescargadosFTP implements Task {

    private static final Logger LOGGER = Logger.getLogger(ValidarDatosDescargadosFTP.class);
    private final EscenarioDTO escenarioDTO;
    private Actor actor;

    public ValidarDatosDescargadosFTP(EscenarioDTO escenarioDTO) {
        this.escenarioDTO = escenarioDTO;
    }

    public static Performable enBaseDeDatosHistorica(EscenarioDTO escenarioDTO) {
        return instrumented(ValidarDatosDescargadosFTP.class, escenarioDTO);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        this.actor = actor;
        losDatosDelReporteEstanEnHistorica(escenarioDTO.getDatosDeArchivoFtpDTOList(), escenarioDTO.getDatosBDHistoricaDTOList());
    }

    public void losDatosDelReporteEstanEnHistorica(List<DatosDeArchivoFtpDTO> datosArchivoFTP, List<DatosBDHistoricaDTO> datosHistorica) {
        if (datosArchivoFTP == null || datosHistorica == null) {
            LOGGER.error("Una de las listas viene null, puede que no hay archivos en el FTP , valide la informacion.");
            return;
        }

        if (datosArchivoFTP.isEmpty() || datosHistorica.isEmpty()) {
            LOGGER.error("Una de las listas viene vacia, puede que no hay datos para comparar, valide la informacion");
            return;
        }

        for (DatosDeArchivoFtpDTO datoArchivoFTP : datosArchivoFTP) {
            String numeroIdentificacion = datoArchivoFTP.getNumeroIdentificacion();
            boolean encontrado = false;
            for (DatosBDHistoricaDTO datoHistorico : datosHistorica) {
                if (datoHistorico.getNumeroIdentificacion().equals(numeroIdentificacion)) {
                    encontrado = true;
                    actor.attemptsTo(Ensure.that(datoHistorico.getNumeroIdentificacion()).isEqualTo(numeroIdentificacion));
                    break;
                }
            }
            if (!encontrado) {
                LOGGER.error("No se encontró el dato en la tabla histórica: " + numeroIdentificacion);
            }
        }
    }
}
