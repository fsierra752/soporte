package com.reto.definitions.camposreportados;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.ObtenerCamposDeFasecolda;
import com.reto.tasks.ObtenerInformacionCSV;
import com.reto.tasks.ValidarInformacionGeneral;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CamposReportadosDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Administrador");
    }

    @Dado("que se consultara los campos que se estan reportando a fasecolda")
    public void queSeConsultaraLosCamposQueSeEstanReportandoAFasecolda() {
        theActorInTheSpotlight().attemptsTo(ObtenerCamposDeFasecolda.paraElReporte(escenarioDTO));
    }
    @Cuando("se consulte el {string} de {string}")
    public void seConsulteElDe(String negocioPrincipal, String negocioSecundario) {
        theActorInTheSpotlight().attemptsTo(ObtenerInformacionCSV.leerArchivoCSV(escenarioDTO, negocioSecundario, negocioPrincipal));
    }
    @Entonces("se debe validar que los campos sean los mismos que se estan reportando a {string}")
    public void seDebeValidarQueLosCamposSeanLosMismosQueSeEstanReportandoA(String reporte) {
        theActorInTheSpotlight().attemptsTo(ValidarInformacionGeneral.archivosCSVYBaseDeDatos(escenarioDTO, reporte));
    }
}
