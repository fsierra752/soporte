package com.reto.definitions.administracionasesores;

import com.reto.models.EscenarioDTO;
import com.reto.tasks.*;
import com.sura.idoneidadasesores.tasks.*;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AdministracionAsesoresDefinition {

    EscenarioDTO escenarioDTO = new EscenarioDTO();
    @Before
    public void setUp() {
        OnStage.setTheStage(Cast.ofStandardActors());
        theActorCalled("Asesor");
    }

    @Dado("que se consultaran los asesores de la base de datos de idoneidad")
    public void queSeConsultaranLosAsesoresDeLaBaseDeDatosDeIdoneidad() {
        theActorInTheSpotlight().attemptsTo(ObtenerTablaHistorica.todosLosAsesores(escenarioDTO));
        theActorInTheSpotlight().attemptsTo(ObtenerAsesoresInsignias.delProceso(escenarioDTO));
    }

    @Cuando("se marquen los asesores idoneos y no idoneos")
    public void seMarquenLosAsesoresIdoneosYNoIdoneos() {
        theActorInTheSpotlight().attemptsTo(MarcarAsesoresIdoneosYNoIdoneos.paraParaElAsistente(escenarioDTO));
    }

    @Cuando("se asignen los ramos correspondientes del cotizador")
    public void seAsignenLosRamosCorrespondientesDelCotizador() {
        theActorInTheSpotlight().attemptsTo(AsignarRamosCorrespondientes.delCotizador(escenarioDTO));
    }

    @Cuando("se obtiene la base de datos de asistente talento humano")
    public void seObtieneLaBaseDeDatosDeAsistenteTalentoHumano() {
        theActorInTheSpotlight().attemptsTo(ObtenerUsuariosDelAsistenteTalentoHumano.idoneosYNoIdoneos(escenarioDTO));
    }

    @Cuando("se normalicen los datos del proceso idoneo a formato de asistente talento humano")
    public void seNormalicenLosDatosDelProcesoIdoneoAFormatoDeAsistenteTalentoHumano() {
        theActorInTheSpotlight().attemptsTo(NormalizarDatosDelProceso.paraElAsistente(escenarioDTO));
    }

    @Entonces("los registros de idoneida del proceso idoneo y asistente talento humano se comparan")
    public void losRegistrosDeIdoneidaDelProcesoIdoneoYAsistenteTalentoHumanoSeComparan() {
        theActorInTheSpotlight().attemptsTo(CompararRegistrosDeIdoneidad.delProcesoYAsistenteTalentoHumano(escenarioDTO));
    }

    @Entonces("los ramos de los asesores del proceso idoneo y asistente talento humano son iguales")
    public void losRamosDeLosAsesoresDelProcesoIdoneoYAsistenteTalentoHumanoSonIguales() {
        theActorInTheSpotlight().attemptsTo(ModificarRamosDeAsesores.delProcesoYAsistenteTalentoHumano(escenarioDTO));
        theActorInTheSpotlight().attemptsTo(ObtenerRamosDeAsesoresCotizador.asistenteTalentoHumano(escenarioDTO));
        theActorInTheSpotlight().attemptsTo(CompararRamosDeAsesores.delProcesoYAsistenteTalentoHumano(escenarioDTO));

    }

}
