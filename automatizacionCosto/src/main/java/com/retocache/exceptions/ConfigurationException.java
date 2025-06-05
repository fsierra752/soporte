package com.retocache.exceptions;

/**
 * Excepción personalizada para errores relacionados con la configuración de FileNet.
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Constructor con mensaje y causa.
     *
     * @param message Mensaje descriptivo del error.
     * @param cause   Causa original del error.
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }


    public ConfigurationException(String message) {
        super(message);
    }

}
