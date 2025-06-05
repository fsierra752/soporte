package com.retocache.exceptions;

/**
 * Excepción personalizada para errores al leer archivos.
 */
public class FileReadException extends Exception {
    /**
     * Constructor con mensaje y causa.
     *
     * @param message Mensaje descriptivo del error.
     * @param cause   Causa original del error.
     */
    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
