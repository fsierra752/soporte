package com.reto.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.log4j.Logger;

public class FileUtilidades {

    private static final Logger LOGGER = Logger.getLogger(FileUtilidades.class);
    private static Map<?,?> auth = new HashMap<>();
    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    private FileUtilidades() {}

    public static Object leerArchivo(String filePath) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            auth = gson.fromJson(br, Map.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return auth;
    }

    public static <T> List<T> leerArchivosCSVLocales(String nombreArchivo, Class<T> tipoClase) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(tipoClase)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo CSV: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static <T> List<T> leerArchivosCSVDescargados(String nombreArchivo, Class<T> tipoClase) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));
            for (int i = 0; i < lineas.size(); i++) {
                lineas.set(i, lineas.get(i).replace("\"", ""));
            }
            try (StringReader stringReader = new StringReader(String.join("\n", lineas))) {
                return new CsvToBeanBuilder<T>(stringReader)
                        .withType(tipoClase)
                        .withSeparator(',')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                        .parse();
            }
        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo CSV: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
