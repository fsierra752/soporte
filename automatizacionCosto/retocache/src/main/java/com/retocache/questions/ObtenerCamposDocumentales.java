package com.retocache.questions;

import com.filenet.api.core.ObjectStore;
import com.filenet.api.meta.ClassDescription;
import com.filenet.api.meta.PropertyDescription;
import com.retocache.models.CamposClaseDocumentalModel;

/**
 * Clase que implementa la lógica para obtener modelos de campos documentales de FileNet.
 */
public class ObtenerCamposDocumentales extends ObtenerObjectStoreFileNet<CamposClaseDocumentalModel> {

    /**
     * Constructor que indica que se desea incluir contenido de cada ObjectStore.
     */
    public ObtenerCamposDocumentales() {
        super(true); // Indicamos que queremos incluir contenido.
    }

    @Override
    protected CamposClaseDocumentalModel crearModelo(ObjectStore objectStore) {
        CamposClaseDocumentalModel model = new CamposClaseDocumentalModel();
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        return model;
    }

    @Override
    protected CamposClaseDocumentalModel crearModeloDesdeContenido
            (ObjectStore objectStore, ClassDescription classDescription,
             PropertyDescription propertyDescription) {
        CamposClaseDocumentalModel model = new CamposClaseDocumentalModel();
        // Información general del ObjectStore
        model.setIdObjectStore(objectStore.get_Id().toString());
        model.setNombreObjectStore(objectStore.get_Name());
        // Información de la classDescription
        model.setId(classDescription.get_Id().toString());
        model.setNombreSimbolico(classDescription.get_SymbolicName());
        model.setNombre(classDescription.get_DisplayName());
        // Información de propertyDescription
        if (propertyDescription != null) {
            model.setSymbolicName(propertyDescription.get_SymbolicName());
            model.setDisplayName(propertyDescription.get_DisplayName());
            model.setEsMutivalor(propertyDescription.get_IsValueRequired());
            model.setTipoDato(propertyDescription.get_DataType().toString());
            if (propertyDescription.get_ChoiceList() != null) {
                model.setNombreCvl(propertyDescription.get_ChoiceList().get_DisplayName());
            } else {
                model.setNombreCvl(null); // No hay ChoiceList asociado
            }
        }
        return model;
    }

    // Método estático para crear una instancia de la clase
    public static ObtenerCamposDocumentales desdeFileNet() {
        return new ObtenerCamposDocumentales();
    }
}
