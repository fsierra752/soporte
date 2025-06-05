package com.retocache.services.generics;

import net.serenitybdd.screenplay.Actor;
import org.apache.log4j.PropertyConfigurator;

import static com.retocache.utils.Diccionario.ACTOR_NAME;
import static com.retocache.utils.enums.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class Setup {
    protected final Actor actor = Actor.named(ACTOR_NAME);
    protected void setupLog4j() {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_FILE_PATH.getValue());
    }
}
