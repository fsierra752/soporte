package com.retocache.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retocache.exceptions.RedisException;
import com.retocache.registry.ModeloRegistry;
import com.retocache.utils.ConfiguracionRedis;
import lombok.Getter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.retocache.utils.Diccionario.TIPO_HASH_HASH;
import static com.retocache.utils.Diccionario.TIPO_HASH_LIST;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarYTransformarHashEnListas implements Task {

    private static final Logger LOGGER = Logger.getLogger(ConsultarYTransformarHashEnListas.class);

    private final String hashName;
    @Getter
    private final List<Object> resultados = new ArrayList<>();

    public ConsultarYTransformarHashEnListas(String hashName) {
        this.hashName = hashName;
    }

    public static ConsultarYTransformarHashEnListas desdeRedis(String hashName) {
        return instrumented(ConsultarYTransformarHashEnListas.class, hashName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try (Jedis jedis = ConfiguracionRedis.obtenerConexion()) {
            String tipoClave = jedis.type(hashName);

            if (TIPO_HASH_HASH.equalsIgnoreCase(tipoClave)) {
                Map<String, String> hashFields = jedis.hgetAll(hashName);
                procesarHash(hashFields, actor);
            } else if (TIPO_HASH_LIST.equalsIgnoreCase(tipoClave)) {
                List<String> listaValores = jedis.lrange(hashName, 0, -1);
                procesarLista(listaValores, actor);
            } else {
                LOGGER.error("El tipo de clave no es compatible: " + tipoClave);
            }

        } catch (RedisException e) {
            LOGGER.error("Error relacionado con Redis: " + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error desconocido durante la ejecución de la tarea", e);
        }
    }

    private void procesarHash(Map<String, String> hashFields, Actor actor) {
        Class<?> modeloClase = ModeloRegistry.obtenerModelo(hashName);
        if (modeloClase == null) {
            LOGGER.error("No se encontró un modelo registrado para el hashName: " + hashName);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        hashFields.forEach((key, value) -> {
            try {
                if (esJsonArray(value)) {
                    List<?> listaModelo = objectMapper.readValue(
                            value,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, modeloClase)
                    );
                    resultados.addAll(listaModelo);
                } else {
                    Object modelo = objectMapper.readValue(value, modeloClase);
                    resultados.add(modelo);
                }
            } catch (Exception e) {
                LOGGER.error("Error al procesar la clave: " + key + ", valor: " + value, e);
            }
        });

        actor.remember(hashName + "Model", resultados);
    }


    private void procesarLista(List<String> listaValores, Actor actor) {
        Class<?> modeloClase = ModeloRegistry.obtenerModelo(hashName);
        if (modeloClase == null) {
            LOGGER.error("No se encontró un modelo registrado para el hashName: " + hashName);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (String valor : listaValores) {
                if (esJsonArray(valor)) {
                    List<Object> listaModelo = objectMapper.readValue(
                            valor,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, modeloClase)
                    );
                    resultados.addAll(listaModelo);
                } else {
                    Object modelo = objectMapper.readValue(valor, modeloClase);
                    resultados.add(modelo);
                }
            }
            actor.remember(hashName + "Model", resultados);
        } catch (Exception e) {
            LOGGER.error("Error al procesar la lista en Redis", e);
        }
    }

    private boolean esJsonArray(String value) {
        return value.startsWith("[") && value.endsWith("]");
    }
}
