package com.reto.models.csv;

import lombok.Getter;

@Getter
@SuppressWarnings("java:S1068")
public class ArchivosAlmacenadosYSolicitados {
    private String nombreArchivoEnBlobStorage;

    public ArchivosAlmacenadosYSolicitados(String nombreArchivoEnBlobStorage) {
        this.nombreArchivoEnBlobStorage = nombreArchivoEnBlobStorage;
    }
}
