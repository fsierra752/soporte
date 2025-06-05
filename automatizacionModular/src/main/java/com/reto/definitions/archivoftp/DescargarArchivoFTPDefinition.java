package com.reto.definitions.archivoftp;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.DescargadorDeBlobStorage;
import com.reto.tasks.ObtenerTablaHistorica;
import com.reto.tasks.ValidarCamposDeArchivos;
import com.reto.tasks.ValidarDatosDescargadosFTP;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DescargarArchivoFTPDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Asesor");
    }

    @Dado("que se ingresa al {string} del servidor ftp para descargar el {string}")
    public void queSeIngresaAlDelServidorFtpParaDescargarEl(String contenedor, String archivo) {
        String fechaHoy = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        theActorInTheSpotlight().attemptsTo(DescargadorDeBlobStorage.paraDescargarArchivos(escenarioDTO, contenedor, archivo, fechaHoy));
    }
    @Cuando("se descargue la informacion en base de datos de idoneidad")
    public void seDescargueLaInformacionEnBaseDeDatosDeIdoneidad() {
        theActorInTheSpotlight().attemptsTo(ObtenerTablaHistorica.todosLosAsesores(escenarioDTO));
    }

    @Entonces("los registros descargados estaran en la base de datos de historico de idoneidad")
    public void losRegistrosDescargadosEstaranEnLaBaseDeDatosDeHistoricoDeIdoneidad() {
        theActorInTheSpotlight().attemptsTo(ValidarDatosDescargadosFTP.enBaseDeDatosHistorica(escenarioDTO));
    }

    @Entonces("se validara que los campos internos sean correctos:")
    public void seValidaraQueLosCamposInternosSeanCorrectos(DataTable dataTable) {
        Map<String, String> parametros = dataTable.asMap(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(ValidarCamposDeArchivos.encontrados(parametros, escenarioDTO));
    }

}
