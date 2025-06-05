package com.reto.definitions.insigniasramos;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.ObtenerInformacionRamosCargos;
import com.reto.tasks.ObtenerInformacionCSV;
import com.reto.tasks.ValidarInformacionGeneral;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class InsigniasRamosDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    private String negocioActual;
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Administrador");
    }

    @Dado("que se buscan los siguientes numeros de ramos {string} de {string}")
    public void queSeBuscanLosSiguientesNumerosDeRamosDe(String ramosEnBusqueda, String negocioPrincipal) {
        this.negocioActual = negocioPrincipal;
        theActorInTheSpotlight().attemptsTo(ObtenerInformacionRamosCargos.deBaseDeDatos(escenarioDTO, ramosEnBusqueda, negocioPrincipal));
    }

    @Entonces("se valida que las insignias existan correctamente al negocio {string}")
    public void seValidaQueLasInsigniasExistanCorrectamenteAlNegocio(String negocioSecundario) {
        theActorInTheSpotlight().attemptsTo(ObtenerInformacionCSV.leerArchivoCSV(escenarioDTO, negocioSecundario, negocioActual));
        theActorInTheSpotlight().attemptsTo(ValidarInformacionGeneral.archivosCSVYBaseDeDatos(escenarioDTO, negocioActual));
    }


}
