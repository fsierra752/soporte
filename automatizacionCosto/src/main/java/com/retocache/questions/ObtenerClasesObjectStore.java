package com.retocache.questions;

import com.filenet.api.core.ObjectStore;
import com.filenet.api.meta.ClassDescription;
import com.filenet.api.meta.PropertyDescription;
import com.retocache.models.ClasesObjectStoreModel;



public class ObtenerClasesObjectStore extends ObtenerObjectStoreFileNet<ClasesObjectStoreModel> {

    /**
     * Constructor que indica que se desea incluir contenido de cada ObjectStore.
     */
    public ObtenerClasesObjectStore() {
        super(true); // Indicamos que queremos incluir contenido.
    }

    @Override
    protected ClasesObjectStoreModel crearModelo(ObjectStore objectStore) {
        ClasesObjectStoreModel model = new ClasesObjectStoreModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        return model;
    }

    @Override
    protected ClasesObjectStoreModel crearModeloDesdeContenido
            (ObjectStore objectStore, ClassDescription classDescription,
             PropertyDescription propertyDescription) {
        ClasesObjectStoreModel model = new ClasesObjectStoreModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        model.setId(classDescription.get_Id().toString());
        model.setNombreSimbolico(classDescription.get_SymbolicName());
        model.setNombre(classDescription.get_DisplayName());
        return model;
    }

    /**
     * Método estático para listar los ObjectStores junto con su contenido.
     * @return Una instancia de ObtenerClasesObjectStore.
     */
    public static ObtenerClasesObjectStore desdeFileNet() {
        return new ObtenerClasesObjectStore();
    }
}
