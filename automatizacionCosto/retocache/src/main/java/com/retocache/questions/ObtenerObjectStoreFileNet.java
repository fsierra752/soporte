package com.retocache.questions;

import com.filenet.api.collection.ClassDescriptionSet;
import com.filenet.api.collection.PropertyDescriptionList;
import com.filenet.api.core.Domain;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.meta.ClassDescription;
import com.filenet.api.meta.PropertyDescription;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.*;

/**
 * Clase base para obtener elementos de FileNet, adaptable para diferentes casos de uso.
 * @param <T> Tipo genérico que define el modelo a retornar.
 */
public abstract class ObtenerObjectStoreFileNet<T> implements Question<Map<ObjectStore, List<T>>> {

    private final boolean incluirContenido;

    /**
     * Constructor que permite configurar si se incluye contenido.
     * @param incluirContenido Si es true, se incluye el contenido de cada ObjectStore.
     */
    protected ObtenerObjectStoreFileNet(boolean incluirContenido) {
        this.incluirContenido = incluirContenido;
    }

    /**
     * Método abstracto para crear un modelo a partir de un ObjectStore.
     * @param objectStore El ObjectStore de FileNet.
     * @return Modelo del tipo genérico T.
     */
    protected abstract T crearModelo(ObjectStore objectStore);

    /**
     * Método abstracto para crear un modelo a partir del contenido del ObjectStore.
     * @param objectStore El ObjectStore de FileNet.
     * @param classDescription Definición de clase del contenido.
     * @param propertyDescription Definición de propiedad específica.
     * @return Modelo del tipo genérico T.
     */
    protected abstract T crearModeloDesdeContenido(ObjectStore objectStore, ClassDescription classDescription,
                                                   PropertyDescription propertyDescription);

    @Override
    public Map<ObjectStore, List<T>> answeredBy(Actor actor) {
        // Recuperar el Domain desde el actor
        Domain domain = actor.recall("fileNetDomain");
        // Obtener la lista de ObjectStores disponibles en el Domain
        Iterator<ObjectStore> objectStores = domain.get_ObjectStores().iterator();
        Map<ObjectStore, List<T>> resultado = new HashMap<>();
        // Iterar sobre los ObjectStores
        while (objectStores.hasNext()) {
            ObjectStore objectStore = objectStores.next();
            // Crear modelo del ObjectStore
            List<T> models = new ArrayList<>();
            if (incluirContenido) {
                // Si se incluye contenido, obtener las definiciones de clase
                ClassDescriptionSet classDescriptionSet = objectStore.get_ClassDescriptions();
                Iterator<ClassDescription> classIterator = classDescriptionSet.iterator();
                // Iterar sobre las definiciones de clase y procesar sus propiedades
                while (classIterator.hasNext()) {
                    ClassDescription classDescription = classIterator.next();
                    // Obtener la lista de definiciones de propiedades de la clase
                    PropertyDescriptionList propertyDescriptions = classDescription.get_PropertyDescriptions();
                    Iterator<PropertyDescription> propertyIterator = propertyDescriptions.iterator();
                    while (propertyIterator.hasNext()) {
                        PropertyDescription propertyDefinition = propertyIterator.next();
                        // Crear el modelo usando el método abstracto
                        T contentModel = crearModeloDesdeContenido(objectStore, classDescription, propertyDefinition);
                        models.add(contentModel);
                    }
                }
            } else {
                // Si no se incluye contenido, agregar solo el modelo del ObjectStore
                T objectStoreModel = crearModelo(objectStore);
                models.add(objectStoreModel);
            }
            // Asociar el ObjectStore con sus modelos
            resultado.put(objectStore, models);
        }
        return resultado;
    }
}
