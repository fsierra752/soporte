package com.retocache.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de utilidad para registrar y obtener modelos de datos.
 */
public final class ModeloRegistry {

    private static final Map<String, Class<?>> REGISTRO_MODELOS = new HashMap<>();

    // Bloque estático para inicializar los modelos
    static {
        REGISTRO_MODELOS.put("Parametros", com.retocache.models.ParametrosModel.class);
        REGISTRO_MODELOS.put("PropertyTemplateP8", com.retocache.models.PropertyTemplateModel.class);
        REGISTRO_MODELOS.put("ObjectStore", com.retocache.models.ObjectStoreModel.class);
        REGISTRO_MODELOS.put("ClasesObjectStore", com.retocache.models.ClasesObjectStoreModel.class);
        REGISTRO_MODELOS.put("CamposClaseDocumental", com.retocache.models.CamposClaseDocumentalModel.class);
        REGISTRO_MODELOS.put("ListadosObjectStore", com.retocache.models.ListadosObjectStoreModel.class);
        REGISTRO_MODELOS.put("ValoresListadoObjectStore", com.retocache.models.ValoresListadoObjectStoreModel.class);
    }

    private ModeloRegistry() {
        throw new UnsupportedOperationException("ModeloRegistry es una clase de utilidad y no debe ser instanciada.");
    }

    /**
     * Registra un modelo en el registro.
     *
     * @param hashName   Nombre del hash.
     * @param modeloClase Clase del modelo.
     */
    public static void registrarModelo(String hashName, Class<?> modeloClase) {
        REGISTRO_MODELOS.put(hashName, modeloClase);
    }

    /**
     * Obtiene un modelo del registro por su nombre de hash.
     *
     * @param hashName Nombre del hash.
     * @return Clase del modelo asociado al hash, o {@code null} si no existe.
     */
    public static Class<?> obtenerModelo(String hashName) {
        return REGISTRO_MODELOS.get(hashName);
    }
}
