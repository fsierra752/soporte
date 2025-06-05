package com.retocache.utils;

import com.retocache.exceptions.ConfigurationException;
import com.retocache.exceptions.FileReadException;
import com.retocache.utils.enums.FileUtilities;
import com.retocache.utils.enums.TokenFile;

import java.util.Map;

public class ConfiguracionFileNet {

    private static final Map<String, String> auth;

    static {
        try {
            auth = FileUtilities.leerArchivo(TokenFile.JSON_FILE_TOKEN.getValue());
        } catch (FileReadException e) {
            throw new ConfigurationException("Error al cargar la configuración de FileNet desde el archivo JSON.", e);
        }
    }

    private static final String URI = obtenerValor("uri");
    private static final String USERNAME = obtenerValor("FilenetUser");
    private static final String PASSWORD = obtenerValor("FilenetPassword");
    private static final String STANZA = obtenerValor("stanza");
    private static final String DOMAIN = obtenerValor("domain");
    private static final String OBJECT_STORE = obtenerValor("object-store");

    private ConfiguracionFileNet() {
    }

    /**
     * Obtiene un valor de la configuración, lanzando una excepción si no está presente.
     *
     * @param clave Clave del valor en el mapa de configuración.
     * @return Valor correspondiente a la clave.
     */
    private static String obtenerValor(String clave) {
        String valor = auth.get(clave);
        if (valor == null) {
            throw new ConfigurationException("El valor de configuración para la clave '" + clave + "' no está presente.");
        }
        return valor;
    }

    public static String getUri() {
        return URI;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getStanza() {
        return STANZA;
    }

    public static String getDomain() {
        return DOMAIN;
    }

    public static String getObjectStore() {
        return OBJECT_STORE;
    }
}
