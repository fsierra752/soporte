package com.retocache.task;

import io.restassured.specification.RequestSpecification;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static com.retocache.services.serviciohealth.ServicioHealth.RESOURCE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarServiciosRocket implements Task {

    public static Performable sinParametros() {
        return instrumented(ConsultarServiciosRocket.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(RESOURCE)
                .with(
                        RequestSpecification::relaxedHTTPSValidation
                ));
    }
}
