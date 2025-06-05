package com.retocache.questions;

import com.filenet.api.admin.Choice;
import com.filenet.api.admin.ChoiceList;
import com.filenet.api.collection.ChoiceListSet;
import com.filenet.api.core.Domain;
import com.filenet.api.core.ObjectStore;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.*;

/**
 * Clase base para obtener elementos de FileNet, adaptable para diferentes casos de uso.
 * @param <T> Tipo genérico que define el modelo a retornar.
 */
public abstract class ObtenerObjectStoreListadosFileNet<T> implements Question<Map<ObjectStore, List<T>>> {

    private final boolean incluirContenido;

    /**
     * Constructor que permite configurar si se incluye contenido.
     * @param incluirContenido Si es true, se incluye el contenido de cada ObjectStore.
     */
    protected ObtenerObjectStoreListadosFileNet(boolean incluirContenido) {
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
     * @param choice El elemento Choice.
     * @param choiceList La lista de Choices.
     * @return Modelo del tipo genérico T.
     */
    protected abstract T crearModeloDesdeContenido(ObjectStore objectStore, Choice choice, ChoiceList choiceList);

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
                // Obtener los ChoiceLists disponibles en el ObjectStore
                ChoiceListSet choiceListSet = objectStore.get_ChoiceLists();
                Iterator<ChoiceList> choiceListIterator = choiceListSet.iterator();
                // Iterar sobre los ChoiceLists
                while (choiceListIterator.hasNext()) {
                    ChoiceList choiceList = choiceListIterator.next();
                    // Obtener los Choices dentro de cada ChoiceList
                    @SuppressWarnings("unchecked")
                    List<Choice> choices = choiceList.get_ChoiceValues();
                    for (Choice choice : choices) {
                        // Crear el modelo usando el método abstracto
                        T contentModel = crearModeloDesdeContenido(objectStore, choice, choiceList);
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
