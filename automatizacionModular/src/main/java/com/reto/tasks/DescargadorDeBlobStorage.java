package com.reto.tasks;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.reto.models.EscenarioDTO;
import com.reto.models.csv.DatosDeArchivoFtpDTO;
import com.reto.utils.FileUtilidades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.reto.utils.Diccionario.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DescargadorDeBlobStorage implements Task {

    private static final Logger LOGGER = Logger.getLogger(DescargadorDeBlobStorage.class);
    private static EscenarioDTO escenarioDTO;
    private final String contenedor;
    private final String archivo;
    private final String fechaHoy;

    public DescargadorDeBlobStorage(String contenedor, String archivo, String fechaHoy) {
        this.contenedor = contenedor;
        this.archivo = archivo;
        this.fechaHoy = fechaHoy;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        obtenerArchivoFTP();
    }

    public static Performable paraDescargarArchivos(EscenarioDTO escenarioDTO, String contenedor, String archivo, String fechaHoy) {
        DescargadorDeBlobStorage.escenarioDTO = escenarioDTO;
        return instrumented(DescargadorDeBlobStorage.class, contenedor, archivo, fechaHoy);
    }

    public void obtenerArchivoFTP() {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(CONNECTION_STRING_DEV).buildClient();
        String nombreArchivoBuscado = fechaHoy + SEPADADOR_RUTA + archivo + fechaHoy + EXTENCION_CSV;
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(contenedor);
        for (BlobItem blobItem : containerClient.listBlobs()) {
            if (blobItem.getName().equals(nombreArchivoBuscado)) {
                decargarArchivoLocal(containerClient.getBlobClient(blobItem.getName()));
            }
        }
    }

    private void decargarArchivoLocal(BlobClient blobClient) {
        String rutaCompleta = RUTA_DESCARGAS + archivo + fechaHoy + EXTENCION_CSV;
        try (InputStream inputStream = blobClient.openInputStream();
             FileOutputStream fos = new FileOutputStream(rutaCompleta)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            List<DatosDeArchivoFtpDTO> datos = FileUtilidades.leerArchivosCSVDescargados(rutaCompleta, DatosDeArchivoFtpDTO.class);
            escenarioDTO.setDatosDeArchivoFtpDTOList(datos);
        } catch (IOException e) {
            LOGGER.error("Error downloading file: " + blobClient.getBlobName(), e);
        }
    }

}