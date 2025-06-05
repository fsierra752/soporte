package com.retocache.task.filenet;

import com.filenet.api.core.ObjectStore;
import com.retocache.models.*;
import com.retocache.questions.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;
import java.util.Map;

import static com.retocache.utils.Diccionario.*;

public class ElegirListaPara implements Task {

    private final String fileNetBuscado;

    public ElegirListaPara(String fileNetBuscado) {
        this.fileNetBuscado = fileNetBuscado;
    }

    public static ElegirListaPara elFileNet(String fileNetBuscado) {
        return Tasks.instrumented(ElegirListaPara.class, fileNetBuscado);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        switch (fileNetBuscado) {
            case NOMBRE_DE_FILENET_PROPERTY:
                List<PropertyTemplateModel> propertyTemplate =
                        actor.asksFor(ObtenerPropertyTemplates.desdeFileNet());
                actor.remember(NOMBRE_DE_FILENET_PROPERTY, propertyTemplate);
                break;
            case NOMBRE_DE_FILENET_OBJETOS:
                Map<ObjectStore, List<ObjectStoreModel>> objectStoreTemplate =
                        actor.asksFor(ObtenerStoreObject.desdeFileNet());
                actor.remember(NOMBRE_DE_FILENET_OBJETOS, objectStoreTemplate);
                break;
            case NOMBRE_DE_FILENET_CLASES:
                Map<ObjectStore, List<ClasesObjectStoreModel>> clasesObjectStoreTemplate =
                        actor.asksFor(ObtenerClasesObjectStore.desdeFileNet());
                actor.remember(NOMBRE_DE_FILENET_CLASES, clasesObjectStoreTemplate);
                break;
            case NOMBRE_DE_FILENET_CAMPOS_DOCUMENTALES:
                Map<ObjectStore, List<CamposClaseDocumentalModel>> camposDocumentalesTemplate =
                        actor.asksFor(ObtenerCamposDocumentales.desdeFileNet());
                actor.remember(NOMBRE_DE_FILENET_CAMPOS_DOCUMENTALES, camposDocumentalesTemplate);
                break;
            case NOMBRE_DE_FILENET_LISTADOS:
                Map<ObjectStore, List<ListadosObjectStoreModel>> listadoObjectStoreTemplate =
                        actor.asksFor(ObtenerListadosObjectStore.desdeFileNet());
                actor.remember(NOMBRE_DE_FILENET_LISTADOS, listadoObjectStoreTemplate);
                break;
            case NOMBRE_DE_FILENET_VALORES_LISTADOS:
                Map<ObjectStore, List<ValoresListadoObjectStoreModel>> valoresListadosTemplate =
                        actor.asksFor(ObtenerValoresListado.desdeFileNet());
                actor.remember(NOMBRE_DE_FILENET_VALORES_LISTADOS, valoresListadosTemplate);
                break;
            default:
                throw new IllegalArgumentException("No se reconoce el tipo de FileNet buscado: " + fileNetBuscado);
        }
    }

}
