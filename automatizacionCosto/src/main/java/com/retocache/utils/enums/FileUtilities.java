package com.retocache.utils.enums;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retocache.exceptions.FileReadException;

import java.io.File;
import java.util.Map;

public class FileUtilities {

    private FileUtilities() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Lee un archivo JSON y lo convierte en un mapa.
     *
     * @param rutaArchivo Ruta del archivo JSON.
     * @return Un mapa con las claves y valores del archivo.
     * @throws FileReadException Si ocurre un error al leer el archivo.
     */
    public static Map<String, String> leerArchivo(String rutaArchivo) throws FileReadException {
        try {
            return OBJECT_MAPPER.readValue(new File(rutaArchivo), Map.class);
        } catch (Exception e) {
            throw new FileReadException("Error al leer el archivo JSON: " + rutaArchivo, e);
        }
    }
}
