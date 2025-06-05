package com.retocache.questions;

import com.filenet.api.admin.PropertyTemplate;
import com.filenet.api.core.ObjectStore;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import com.retocache.models.PropertyTemplateModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObtenerPropertyTemplates implements Question<List<PropertyTemplateModel>> {

    @Override
    public List<PropertyTemplateModel> answeredBy(Actor actor) {
        ObjectStore objectStore = actor.recall("fileNetObjectStore");

        List<PropertyTemplateModel> propertyTemplateModels = new ArrayList<>();
        Iterator<PropertyTemplate> iterator = objectStore.get_PropertyTemplates().iterator();

        while (iterator.hasNext()) {
            PropertyTemplate propertyTemplate = iterator.next();

            PropertyTemplateModel model = new PropertyTemplateModel();
            model.setName(propertyTemplate.get_SymbolicName());
            model.setCardinality(propertyTemplate.get_Cardinality().toString());
            model.setDataType(propertyTemplate.get_DataType().getValue());
            propertyTemplateModels.add(model);
        }

        return propertyTemplateModels;
    }

    public static ObtenerPropertyTemplates desdeFileNet() {
        return new ObtenerPropertyTemplates();
    }
}
