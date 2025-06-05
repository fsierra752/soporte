package com.retocache.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retocache.registry.ModeloRegistry;
import com.retocache.utils.ConfiguracionRedis;
import lombok.Getter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarYTransformarHashEnMapas implements Task {

    private static final Logger LOGGER = Logger.getLogger(ConsultarYTransformarHashEnMapas.class);
    private final String hashName;
    @Getter
    private final Map<String, Object> resultados = new HashMap<>();

    public ConsultarYTransformarHashEnMapas(String hashName) {
        this.hashName = hashName;
    }

    public static ConsultarYTransformarHashEnMapas desdeRedis(String hashName) {
        return instrumented(ConsultarYTransformarHashEnMapas.class, hashName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try (Jedis jedis = ConfiguracionRedis.obtenerConexion()) {
            Map<String, String> hashFields = jedis.hgetAll(hashName);

            Class<?> modeloClase = ModeloRegistry.obtenerModelo(hashName);
            if (modeloClase == null) {
                LOGGER.error("No se encontró un modelo registrado para el hashName: " + hashName);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            for (Map.Entry<String, String> entry : hashFields.entrySet()) {
                Object modelo = objectMapper.readValue(entry.getValue(), modeloClase);
                resultados.put(entry.getKey(), modelo);
            }

            actor.remember(hashName + "Model", resultados);

        } catch (Exception e) {
            LOGGER.error("Error al consultar y transformar el hash en Redis", e);
        }
    }
}
