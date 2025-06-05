package com.retocache.questions;

import com.filenet.api.core.ObjectStore;
import com.retocache.models.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class ValidarDatosCache<T> implements Question<Boolean> {

    private static final Logger logger = Logger.getLogger(ValidarDatosCache.class);

    private final Map<ObjectStore, List<T>> mapFileNet;
    private final List<T> listRedis;
    private final BiPredicate<T, T> comparador;

    // Constructor para la clase genérica
    public ValidarDatosCache(Map<ObjectStore, List<T>> mapFileNet,
                             List<T> listRedis,
                             BiPredicate<T, T> comparador) {
        this.mapFileNet = mapFileNet;
        this.listRedis = listRedis;
        this.comparador = comparador;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        logger.info("Iniciando validación de datos entre FileNet y Redis.");

        // Convertir la lista de Redis a un mapa usando el id o algún atributo único,
        // asegurándonos de que no se pierdan datos al tener múltiples elementos por clave
        Map<String, List<T>> mapRedis = listRedis.stream()
                .collect(Collectors.groupingBy(
                        this::obtenerIdUnicoPorModelo,
                        Collectors.toList()));

        logger.info("Cantidad de entradas en mapRedis: " + mapRedis.size());

        // Iterar sobre los ObjectStores en FileNet
        for (Map.Entry<ObjectStore, List<T>> entry : mapFileNet.entrySet()) {
            ObjectStore objectStoreFileNet = entry.getKey();
            List<T> objectStoreFileNetList = entry.getValue();

            logger.info("Procesando ObjectStore: " + objectStoreFileNet.get_Name());

            // Filtrar los datos de Redis que correspondan a este ObjectStore
            List<T> redisFilteredList = listRedis.stream()
                    .filter(item -> obtenerNombreDeObjectStore(item, objectStoreFileNet))
                    .toList();

            if (redisFilteredList.isEmpty()) {
                logger.warn("No se encontraron datos en Redis para el ObjectStore: " + objectStoreFileNet.get_Name());
                return false;
            }

            // Realizar comparación entre FileNet y Redis filtrados
            for (T modelFileNet : objectStoreFileNetList) {
                boolean encontrado = redisFilteredList.stream()
                        .anyMatch(modelRedis -> comparador.test(modelFileNet, modelRedis));

                if (!encontrado) {
                    logger.warn("No se encontró un elemento equivalente en Redis para: " + modelFileNet);
                    return false;
                }
            }
        }

        logger.info("Validación completada exitosamente. Todos los datos coinciden.");
        return true;
    }

    // Método auxiliar para obtener el ID único del modelo
    private String obtenerIdUnicoPorModelo(T model) {
        if (model instanceof ObjectStoreModel objectStoreModel) {
            return objectStoreModel.getId();
        } else if (model instanceof ClasesObjectStoreModel clasesObjectStoreModel) {
            String idObjectStore = clasesObjectStoreModel.getIdObjectStore();
            String nombreObjectStore = clasesObjectStoreModel.getNombreObjectStore();
            String id = clasesObjectStoreModel.getId();
            return idObjectStore + nombreObjectStore + id;
        } else if (model instanceof CamposClaseDocumentalModel camposClaseDocumentalModel) {
            String idObjectStore = camposClaseDocumentalModel.getIdObjectStore();
            String nombreObjectStore = camposClaseDocumentalModel.getNombreObjectStore();
            String id = camposClaseDocumentalModel.getId();
            String nombreSimbolico = camposClaseDocumentalModel.getSymbolicName();
            String tipoDato = camposClaseDocumentalModel.getTipoDato();
            return idObjectStore + nombreObjectStore + id + nombreSimbolico + tipoDato;
        } else if (model instanceof ListadosObjectStoreModel listadosObjectStoreModel) {
            String idObjectStore = listadosObjectStoreModel.getIdObjectStore();
            String nombreObjectStore = listadosObjectStoreModel.getNombreObjectStore();
            String id = listadosObjectStoreModel.getId();
            String nombre = listadosObjectStoreModel.getNombre();
            return idObjectStore + nombreObjectStore + id + nombre;
        } else if (model instanceof ValoresListadoObjectStoreModel valoresListadoObjectStoreModel) {
            String idObjectStore = valoresListadoObjectStoreModel.getIdObjectStore();
            String nombreObjectStore = valoresListadoObjectStoreModel.getNombreObjectStore();
            String id = valoresListadoObjectStoreModel.getId();
            String nombre = valoresListadoObjectStoreModel.getNombre();
            String texto = valoresListadoObjectStoreModel.getTexto();
            String valor = valoresListadoObjectStoreModel.getValor();
            return idObjectStore + nombreObjectStore + id + nombre + texto + valor;
        }
        return null;
    }


    // Método auxiliar para verificar si un modelo pertenece al ObjectStore específico
    private boolean obtenerNombreDeObjectStore(T model, ObjectStore objectStore) {
        if (model instanceof ObjectStoreModel objectStoreModel) {
            return objectStoreModel.getId().equals(objectStore.get_Id().toString());
        } else if (model instanceof ClasesObjectStoreModel clasesObjectStoreModel) {
            return clasesObjectStoreModel.getIdObjectStore().equals(objectStore.get_Id().toString());
        } else if (model instanceof CamposClaseDocumentalModel camposClaseDocumentalModel) {
            return camposClaseDocumentalModel.getIdObjectStore().equals(objectStore.get_Id().toString());
        } else if (model instanceof ListadosObjectStoreModel listadosObjectStoreModel) {
            return listadosObjectStoreModel.getIdObjectStore().equals(objectStore.get_Id().toString());
        } else if (model instanceof ValoresListadoObjectStoreModel valoresListadoObjectStoreModel) {
            return valoresListadoObjectStoreModel.getIdObjectStore().equals(objectStore.get_Id().toString());
        }
        return false;
    }


    // Método estático para crear la validación de datos entre FileNet y Redis
    public static <T> ValidarDatosCache<T> entreFileNetYRedis(Map<ObjectStore, List<T>> mapFileNet,
                                                              List<T> listRedis,
                                                              BiPredicate<T, T> comparador) {
        return new ValidarDatosCache<>(mapFileNet, listRedis, comparador);
    }
}