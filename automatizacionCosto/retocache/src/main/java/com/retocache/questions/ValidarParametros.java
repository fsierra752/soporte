package com.retocache.questions;

import com.retocache.models.ParametrosModel;
import com.retocache.models.db.ParametrosDbModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.retocache.utils.Diccionario.*;

public class ValidarParametros implements Question<Boolean> {

    private static final Logger logger = Logger.getLogger(ValidarParametros.class);

    private final List<ParametrosDbModel> parametrosDb;
    private final Map<String, Object> parametrosRedis;

    public ValidarParametros(List<ParametrosDbModel> parametrosDb, Map<String, Object> parametrosRedis) {
        this.parametrosDb = parametrosDb;
        this.parametrosRedis = parametrosRedis;
    }

    public static ValidarParametros entre(List<ParametrosDbModel> parametrosDb, Map<String, Object> parametrosRedis) {
        return new ValidarParametros(parametrosDb, parametrosRedis);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        logger.info(INICIANDO_VALIDACION);

        if (parametrosDb.isEmpty()) {
            logger.error(LISTA_POSTGRES_VACIA);
            return false;
        }

        if (parametrosRedis.isEmpty()) {
            logger.error(MAPA_REDIS_VACIO);
            return false;
        }

        logger.info("Tamaño de parámetros Postgres: " + parametrosDb.size());
        logger.info("Tamaño de parámetros Redis: " + parametrosRedis.size());

        boolean validacionExitosa = true;

        for (ParametrosDbModel dbParametro : parametrosDb) {
            String aplicacion = dbParametro.getDsAplicacion();
            String parametro = dbParametro.getDsParametro();

            // Buscar el valor que coincida con la aplicacion y parametro en Redis
            ParametrosModel redisParametro = encontrarParametroEnRedis(aplicacion, parametro);
            if (redisParametro == null) {
                logger.error(String.format(ERROR_DUPLA_NO_EXISTE, aplicacion, parametro));
                validacionExitosa = false;
                continue;
            }

            // Comparar valores con normalización, ignorando los nulos
            if (!compararValores(dbParametro.getDsValorCorto(), redisParametro.getValorCorto(), "dsValorCorto", aplicacion, parametro) ||
                    !compararValores(dbParametro.getDsValorLargo(), redisParametro.getValorLargo(), "dsValorLargo", aplicacion, parametro)) {
                validacionExitosa = false;
            }
        }

        if (validacionExitosa) {
            logger.info(VALIDACION_EXITOSA);
        } else {
            logger.error(VALIDACION_FALLIDA);
        }

        return validacionExitosa;
    }

    private ParametrosModel encontrarParametroEnRedis(String aplicacion, String parametro) {
        for (Object value : parametrosRedis.values()) {
            ParametrosModel redisParametro = (ParametrosModel) value;
            if (redisParametro != null && redisParametro.getAplicacion().equalsIgnoreCase(aplicacion) &&
                    redisParametro.getParametro().equalsIgnoreCase(parametro)) {
                return redisParametro;
            }
        }
        return null;
    }

    private boolean compararValores(String valorPostgres, String valorRedis, String campo, String aplicacion, String parametro) {
        if (valorPostgres == null && valorRedis == null) {
            return true;
        }

        if (valorPostgres == null || valorRedis == null) {
            logger.error(String.format(ERROR_CAMPO_NULO, aplicacion, parametro, campo));
            logger.error(String.format(POSTGRES, valorPostgres));
            logger.error(String.format(REDIS, valorRedis));
            return false;
        }

        String valorPostgresNormalizado = valorPostgres.trim().toLowerCase();
        String valorRedisNormalizado = valorRedis.trim().toLowerCase();

        if (!valorPostgresNormalizado.equals(valorRedisNormalizado)) {
            logger.error(String.format(ERROR_VALORES_NO_COINCIDEN, aplicacion, parametro, campo));
            logger.error(String.format(POSTGRES, valorPostgres));
            logger.error(String.format(REDIS, valorRedis));
            return false;
        }

        return true;
    }
}
