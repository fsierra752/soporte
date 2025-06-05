package com.retocache.task.filenet;

import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.Domain;
import com.filenet.api.util.UserContext;
import com.retocache.utils.ConfiguracionFileNet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;


import javax.security.auth.Subject;

public class ObtenerObjectStore implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {

        Domain domain = actor.recall("fileNetDomain");
        Subject subject = actor.recall("fileNetSubject");
        UserContext.get().pushSubject(subject);
        ObjectStore objectStore = Factory.ObjectStore.fetchInstance(
                domain,
                ConfiguracionFileNet.getObjectStore(),
                null
        );

        actor.remember("fileNetObjectStore", objectStore);
    }

    public static ObtenerObjectStore paraElDominio() {
        return Tasks.instrumented(ObtenerObjectStore.class);
    }
}

