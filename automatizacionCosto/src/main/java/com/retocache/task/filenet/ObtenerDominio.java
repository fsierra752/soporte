package com.retocache.task.filenet;

import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Connection;
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.api.util.UserContext;
import com.retocache.utils.ConfiguracionFileNet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.apache.log4j.Logger;

public class ObtenerDominio implements Task {

    private static final Logger logger = Logger.getLogger(ObtenerDominio.class);

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            logger.info("Iniciando obtención del dominio a partir de la conexión FileNet.");

            Connection connection = actor.recall("fileNetConnection");
            Domain domain = Factory.Domain.fetchInstance(
                    connection,
                    ConfiguracionFileNet.getDomain(),
                    null
            );

            logger.info("Dominio obtenido exitosamente: " + domain.get_Name());
            UserContext.get().popSubject();
            actor.remember("fileNetDomain", domain);
        } catch (EngineRuntimeException e) {
            logger.error("Error al obtener el dominio: " + e.getMessage(), e);
            throw e;
        }
    }

    public static ObtenerDominio usandoLaConexion() {
        return Tasks.instrumented(ObtenerDominio.class);
    }
}
