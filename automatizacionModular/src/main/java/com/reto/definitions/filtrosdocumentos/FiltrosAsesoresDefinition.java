package com.reto.definitions.filtrosdocumentos;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.*;
import com.sura.idoneidadasesores.tasks.*;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class FiltrosAsesoresDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Administrador");
    }

    @Dado("que se requiere saber los documentos de {string} con tipo vinculacion {string}")
    public void queSeRequiereSaberLosDocumentosDeConTipoVinculacion(String tablaConsultada, String filtro) {
        theActorInTheSpotlight().attemptsTo(ObtenerAsesoresEnNovedades.
                preInformacionDeLosDocumentos(escenarioDTO, tablaConsultada, filtro));
    }

    @Cuando("se consulten los cargos excluidos")
    public void seConsultenLosCargosExcluidos() {
        theActorInTheSpotlight().attemptsTo(ObtenerCargosExcluidos.comparacionDeDatos(escenarioDTO));
    }

    @Entonces("el tipo cargo debe ser {string}")
    public void elTipoCargoDebeSer(String tipoCargo) {
        theActorInTheSpotlight().attemptsTo(ValidarAsesoresEnNovedades.tipoDeVinculacion(escenarioDTO, tipoCargo));
    }

    @Entonces("se reportan todos los cargos menos los cargos excluidos")
    public void seReportanTodosLosCargosMenosLosCargosExcluidos() {
        theActorInTheSpotlight().attemptsTo(ValidarCargosExcluidos.enNovedades(escenarioDTO));
    }

    @Entonces("el tipo cargo {string} no se debe reportar")
    public void elTipoCargoNoSeDebeReportar(String cargoNoAutorizado) {
        theActorInTheSpotlight().attemptsTo(ValidarCargosParaNoReportar.enNovedades(escenarioDTO, cargoNoAutorizado));
    }

    @Entonces("el tipo cargo debe ser {string} con tipo de negocio {string} y {string}")
    public void elTipoCargoDebeSerConTipoDeNegocioY(String tipoCargo, String ramoVida, String ramoGeneral) {
        theActorInTheSpotlight().attemptsTo(ValidarCargosEspeciales.enNovedades(escenarioDTO, tipoCargo, ramoVida, ramoGeneral));
    }
}
