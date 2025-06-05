package com.reto.definitions.tablasmaestras;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.ObtenerTablaTemporalAsesorInsignia;
import com.reto.tasks.ObtenerTablasMaestras;
import com.reto.tasks.ValidarInfoMaestrasYTemporal;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;


import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class TablasMaestrasDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Administrador");
    }

    @Dado("que se consultara las insignias de {string} con insignias idoneas")
    public void queSeConsultaraLasInsigniasDeConInsigniasIdoneas(String tabLaMaestra) {
        theActorInTheSpotlight().attemptsTo(ObtenerTablasMaestras.enInsignias(escenarioDTO, tabLaMaestra));
        theActorInTheSpotlight().attemptsTo(ObtenerTablaTemporalAsesorInsignia.enAsesoresInsignias(escenarioDTO, tabLaMaestra));
    }

    @Entonces("se debe mostrar los mismos registros sin filtros")
    public void seDebeMostrarLosMismosRegistrosSinFiltros() {
        theActorInTheSpotlight().attemptsTo(ValidarInfoMaestrasYTemporal.validarInfoEntroDatos(escenarioDTO));
    }
}
