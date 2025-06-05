package com.retocache.task.filenet;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Factory;
import com.filenet.api.util.UserContext;
import com.retocache.utils.ConfiguracionFileNet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import javax.security.auth.Subject;

public class ConectarseAFileNet implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        Connection connection = Factory.Connection.getConnection(ConfiguracionFileNet.getUri());
        Subject subject = UserContext.createSubject(
                connection,
                ConfiguracionFileNet.getUsername(),
                ConfiguracionFileNet.getPassword(),
                ConfiguracionFileNet.getStanza()
        );

        UserContext.get().pushSubject(subject);
        actor.remember("fileNetSubject", subject);
        actor.remember("fileNetConnection", connection);

    }

    public static ConectarseAFileNet conLasCredenciales() {
        return Tasks.instrumented(ConectarseAFileNet.class);
    }
}

