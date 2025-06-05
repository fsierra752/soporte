package com.retocache.questions;

import com.filenet.api.admin.Choice;
import com.filenet.api.admin.ChoiceList;
import com.filenet.api.core.ObjectStore;
import com.retocache.models.ListadosObjectStoreModel;

public class ObtenerListadosObjectStore extends ObtenerObjectStoreListadosFileNet<ListadosObjectStoreModel> {

    /**
     * Constructor que indica que se desea incluir contenido de cada ObjectStore.
     */
    public ObtenerListadosObjectStore() {
        super(true);
    }

    @Override
    protected ListadosObjectStoreModel crearModelo(ObjectStore objectStore) {
        ListadosObjectStoreModel model = new ListadosObjectStoreModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        return model;
    }

    @Override
    protected ListadosObjectStoreModel crearModeloDesdeContenido
            (ObjectStore objectStore, Choice choice, ChoiceList choiceList) {
        ListadosObjectStoreModel model = new ListadosObjectStoreModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        model.setId(choiceList.get_Id().toString()); // Asignar el ID o nombre del Choice
        model.setNombre(choiceList.get_Name()); // Asignar el nombre legible del Choice
        return model;
    }

    /**
     * Método estático para listar los ObjectStores junto con su contenido.
     * @return Una instancia de ObtenerListadosObjectStore.
     */
    public static ObtenerListadosObjectStore desdeFileNet() {
        return new ObtenerListadosObjectStore();
    }
}
