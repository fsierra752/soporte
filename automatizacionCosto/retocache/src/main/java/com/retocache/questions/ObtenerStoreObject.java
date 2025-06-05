package com.retocache.questions;

import com.filenet.api.core.ObjectStore;
import com.filenet.api.meta.ClassDescription;
import com.filenet.api.meta.PropertyDescription;
import com.retocache.models.ObjectStoreModel;


public class ObtenerStoreObject extends ObtenerObjectStoreFileNet<ObjectStoreModel> {

    /**
     * Constructor que indica que no se desea incluir contenido.
     */
    public ObtenerStoreObject() {
        super(false); // No se incluye contenido de los ObjectStores.
    }

    @Override
    protected ObjectStoreModel crearModelo(ObjectStore objectStore) {
        // Crear el modelo para un ObjectStore
        ObjectStoreModel model = new ObjectStoreModel();
        model.setId(objectStore.get_Id().toString());
        model.setNombre(objectStore.get_Name());
        return model;
    }

    @Override
    protected ObjectStoreModel crearModeloDesdeContenido
            (ObjectStore objectStore, ClassDescription classDescription,
             PropertyDescription propertyDescription) {
        // Como no se incluye contenido en esta clase, devolvemos null o una lista vacía
        return null;
    }

    /**
     * Método estático para obtener los ObjectStores desde FileNet.
     * @return Una instancia de ObtenerStoreObject.
     */
    public static ObtenerStoreObject desdeFileNet() {
        return new ObtenerStoreObject();
    }
}
