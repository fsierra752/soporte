package com.reto.tasks;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.reto.models.EscenarioDTO;
import com.reto.models.csv.ArchivosAlmacenadosYSolicitados;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;

import java.util.ArrayList;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerArchivosBlobStorage implements Task {

    private static final Logger LOGGER = Logger.getLogger(ObtenerArchivosBlobStorage.class);
    private static EscenarioDTO escenarioDTO;
    private final String contenedor;
    private final String fechaAyer;

    public ObtenerArchivosBlobStorage(String contenedor, String fechaAyer) {
        this.contenedor = contenedor;
        this.fechaAyer = fechaAyer;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        buscarArchivoFTP();
    }

    public static Performable paraBuscarArchivos(EscenarioDTO escenarioDTO, String contenedor, String fechaAyer) {
        ObtenerArchivosBlobStorage.escenarioDTO = escenarioDTO;
        return instrumented(ObtenerArchivosBlobStorage.class, contenedor, fechaAyer);
    }

    public void buscarArchivoFTP() {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(CONNECTION_STRING_DEV).buildClient();
        escenarioDTO.setArchivosAlmacenadosYSolicitadosList(new ArrayList<>());
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(contenedor);
        boolean archivoEncontrado = false;
        for (BlobItem blobItem : containerClient.listBlobs()) {
            if (blobItem.getName().contains(fechaAyer)) {
                ArchivosAlmacenadosYSolicitados archivoBlobStorage = new ArchivosAlmacenadosYSolicitados(blobItem.getName());
                escenarioDTO.getArchivosAlmacenadosYSolicitadosList().add(archivoBlobStorage);
                archivoEncontrado = true;
            }
        }
        if (!archivoEncontrado) {
            LOGGER.error("No se encontraron archivos de la fecha anterior a hoy " + fechaAyer);
        }
    }


}
