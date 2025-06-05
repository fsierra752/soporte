package com.retocache.definitions;

import com.retocache.services.generics.Setup;
import com.retocache.task.ConsultarServiciosRocket;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;

import static com.retocache.services.serviciohealth.ServicioHealth.URL_BASE;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ServicioRocketDefinition extends Setup{

    @Before
    public void setup() {
        setupLog4j();
        actor.can(
                CallAnApi.at(URL_BASE)
        );
    }

    @Dado("que se consultan el estado de servicios asociados a P8 cache")
    public void queSeConsultanElEstadoDeServiciosAsociadosAP8Cache() {
        actor.attemptsTo(ConsultarServiciosRocket.sinParametros());
        LastResponse.received().answeredBy(actor).prettyPrint();
    }
    @Entonces("se obtiene como resultado que estan activos")
    public void seObtieneComoResultadoQueEstanActivos() {
        actor.should(
                seeThatResponse(
                        "EL código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validateResponse -> validateResponse.statusCode(HttpStatus.SC_OK)
                )
        );
    }
}
