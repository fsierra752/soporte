package com.reto.definitions.archivoftp;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.ObtenerArchivosBlobStorage;
import com.reto.tasks.ObtenerArchivosSolicitados;
import com.reto.tasks.ValidarArchivosStorageYSolicitado;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class BuscarArchivosFTPDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    private String fechaAyer;
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Asesor");
    }

    @Dado("que se ingresa al {string} del servidor ftp para buscar los reportes del proceso")
    public void queSeIngresaAlDelServidorFtpParaBuscarLosReportesDelProceso(String contenedor) {
        this.fechaAyer = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        theActorInTheSpotlight().attemptsTo(ObtenerArchivosBlobStorage.paraBuscarArchivos(escenarioDTO, contenedor, fechaAyer));
    }

    @Entonces("los archivos encontrados en el blob storage seran iguales a los {string} solicitados")
    public void losArchivosEncontradosEnElBlobStorageSeranIgualesALosSolicitados(String archivosSolicitados) {
        fechaAyer = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        theActorInTheSpotlight().attemptsTo(ObtenerArchivosSolicitados.delProceso(escenarioDTO, archivosSolicitados, fechaAyer));
        theActorInTheSpotlight().attemptsTo(ValidarArchivosStorageYSolicitado.fueronEncontradosIguales(escenarioDTO));
    }
}
