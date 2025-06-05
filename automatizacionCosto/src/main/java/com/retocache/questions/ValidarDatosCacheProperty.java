package com.retocache.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.function.BiPredicate;

public class ValidarDatosCacheProperty<T> implements Question<Boolean> {
    private static final Logger logger = Logger.getLogger(ValidarDatosCacheProperty.class);

    private final List<T> listaFileNet;
    private final List<T> listaRedis;
    private final BiPredicate<T, T> comparador;

    public ValidarDatosCacheProperty(List<T> listaFileNet, List<T> listaRedis, BiPredicate<T, T> comparador) {
        this.listaFileNet = listaFileNet;
        this.listaRedis = listaRedis;
        this.comparador = comparador;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        logger.info("Iniciando validación de datos entre FileNet y Redis.");

        // Verificar que las listas tengan el mismo tamaño
        if (listaFileNet.size() != listaRedis.size()) {
            logger.warn("El tamaño de las listas no coincide. FileNet: " + listaFileNet.size() + ", Redis: " + listaRedis.size());
            return false;
        }

        // Comparar los elementos de ambas listas
        for (T elementoRedis : listaRedis) {
            T elementoFileNet = listaFileNet.stream()
                    .filter(fileNetElemento -> comparador.test(fileNetElemento, elementoRedis))
                    .findFirst()
                    .orElse(null);

            if (elementoFileNet == null) {
                logger.warn("No se encontró un elemento equivalente en FileNet para: " + elementoRedis);
                return false;
            }
        }

        logger.info("Validación completada exitosamente. Todas las listas coinciden.");
        return true;
    }

    public static <T> ValidarDatosCacheProperty<T> entreFileNetYRedis(List<T> listaFileNet, List<T> listaRedis, BiPredicate<T, T> comparador) {
        return new ValidarDatosCacheProperty<>(listaFileNet, listaRedis, comparador);
    }
}
