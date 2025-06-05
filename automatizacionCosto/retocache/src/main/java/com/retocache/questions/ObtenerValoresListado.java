package com.retocache.questions;

import com.filenet.api.admin.Choice;
import com.filenet.api.admin.ChoiceList;
import com.filenet.api.core.ObjectStore;
import com.retocache.models.ValoresListadoObjectStoreModel;

public class ObtenerValoresListado extends ObtenerObjectStoreListadosFileNet<ValoresListadoObjectStoreModel> {

    /**
     * Constructor que indica que se desea incluir contenido de cada ObjectStore.
     */
    public ObtenerValoresListado() {
        super(true); // Indicamos que queremos incluir contenido.
    }

    @Override
    protected ValoresListadoObjectStoreModel crearModelo(ObjectStore objectStore) {
        // Crear y devolver el modelo del ObjectStore
        ValoresListadoObjectStoreModel model = new ValoresListadoObjectStoreModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        return model;
    }

    @Override
    protected ValoresListadoObjectStoreModel crearModeloDesdeContenido
            (ObjectStore objectStore, Choice choice, ChoiceList choiceList) {
        ValoresListadoObjectStoreModel model = new ValoresListadoObjectStoreModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        model.setId(choiceList.get_Id().toString());
        model.setNombre(choiceList.get_DisplayName());
        model.setTexto(choiceList.get_DisplayName());
        model.setValor(choice.get_ChoiceStringValue());
        return model;
    }

    public static ObtenerValoresListado desdeFileNet() {
        return new ObtenerValoresListado();
    }
}
