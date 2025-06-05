package com.retocache.definitions;

import com.filenet.api.util.UserContext;
import com.retocache.models.db.ParametrosDbModel;
import com.retocache.questions.ValidarParametros;
import com.retocache.services.generics.Setup;
import com.retocache.task.ConsultarYTransformarHashEnMapas;
import com.retocache.task.postgres.ObtenerParametrosDB;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import static com.retocache.services.serviciohealth.ServicioHealth.URL_BASE;

public class ActualizacionParametrosDefinition extends Setup {

    @Before
    public void setup() {
        setupLog4j();
        actor.can(
                CallAnApi.at(URL_BASE)
        );
    }

    @Dado("que se tienen los parametros en la bd de postgres")
    public void queSeTienenLosParametrosEnLaBdDePostgres() {
        actor.attemptsTo(ObtenerParametrosDB.desdePostgres());
    }

    @Cuando("se consulta el hash {string} para Redis")
    public void seConsultaElHashParaRedis(String hashName) {
        actor.attemptsTo(ConsultarYTransformarHashEnMapas.desdeRedis(hashName));
    }

    @Entonces("los valores de la consulta deben coincidir con los almacenados en postgres")
    public void losValoresDeLaConsultaDebenCoincidirConLosAlmacenadosEnPostgres() {
        List<ParametrosDbModel> parametrosDb = actor.recall("parametrosDB");
        Map<String, Object> parametrosRedis = actor.recall("ParametrosModel");
        actor.should(GivenWhenThen.seeThat(ValidarParametros.entre(parametrosDb, parametrosRedis), equalTo(true)));
        UserContext.get().popSubject();
    }

}
