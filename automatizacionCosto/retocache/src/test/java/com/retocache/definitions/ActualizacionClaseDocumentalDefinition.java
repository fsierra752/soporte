package com.retocache.definitions;

import com.filenet.api.core.ObjectStore;
import com.retocache.models.*;
import com.retocache.questions.*;
import com.retocache.services.generics.Setup;
import com.retocache.task.*;
import com.retocache.task.filenet.ConectarseAFileNet;
import com.retocache.task.filenet.ElegirListaPara;
import com.retocache.task.filenet.ObtenerObjectStore;
import com.retocache.task.filenet.ObtenerDominio;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

import static com.google.common.base.Predicates.equalTo;
import static com.retocache.services.serviciohealth.ServicioHealth.URL_BASE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ActualizacionClaseDocumentalDefinition extends Setup {

    @Before
    public void setup() {
        setupLog4j();
        Locale.setDefault(new Locale("en", "US"));
        actor.can(CallAnApi.at(URL_BASE));
        actor.attemptsTo(ConectarseAFileNet.conLasCredenciales());
        actor.attemptsTo(ObtenerDominio.usandoLaConexion());
        actor.attemptsTo(ObtenerObjectStore.paraElDominio());
    }

    @Dado("que se tienen las propiedades vigentes en filenet {string}")
    public void queSeTienenLasPropiedadesVigentesEnFilenet(String fileNetBuscado) {
        actor.attemptsTo(ElegirListaPara.elFileNet(fileNetBuscado));
    }

    @Cuando("se consulta el hash {string} en Redis")
    public void seConsultaElHashEnRedis(String hashName) {
        actor.attemptsTo(ConsultarYTransformarHashEnListas.desdeRedis(hashName));
    }

    @Entonces("los valores de la consulta deben ser los vigentes de PropertyTemplateP8")
    public void losValoresDeLaConsultaDebenSerLosVigentesDePropertyTemplateP8() {
        List<PropertyTemplateModel> fileNetTemplates = actor.recall("propertyTemplateP8");
        List<PropertyTemplateModel> redisTemplates = actor.recall("PropertyTemplateP8Model");
        BiPredicate<PropertyTemplateModel, PropertyTemplateModel> comparadorPropertyTemplate = (fileNet, redis) ->
                Objects.equals(fileNet.getName(), redis.getName())
                        && Objects.equals(fileNet.getCardinality(), redis.getCardinality())
                        && Objects.equals(fileNet.getDataType(), redis.getDataType());
        actor.should(seeThat(ValidarDatosCacheProperty.entreFileNetYRedis
                (fileNetTemplates, redisTemplates, comparadorPropertyTemplate), equalTo(true)));
    }

    @Entonces("los valores de la consulta deben ser los vigentes de ObjectStore")
    public void losValoresDeLaConsultaDebenSerLosVigentesDeObjectStore() {
        Map<ObjectStore, List<ObjectStoreModel>> fileNetObjectStores = actor.recall("objectStore");
        List<ObjectStoreModel> redisObjectStores = actor.recall("ObjectStoreModel");
        BiPredicate<ObjectStoreModel, ObjectStoreModel> comparadorObjectStore = (fileNet, redis) ->
                Objects.equals(fileNet.getId(), redis.getId())
                        && Objects.equals(fileNet.getNombre(), redis.getNombre());
        actor.should(seeThat(ValidarDatosCache.entreFileNetYRedis
                (fileNetObjectStores, redisObjectStores, comparadorObjectStore), equalTo(true)));
    }

    @Entonces("los valores de la consulta deben ser los vigentes de ClasesObjectStore")
    public void losValoresDeLaConsultaDebenSerLosVigentesDeClasesObjectStore() {
        Map<ObjectStore, List<ClasesObjectStoreModel>> fileNetObjectStores = actor.recall("clasesObjectStore");
        List<ClasesObjectStoreModel> redisObjectStores = actor.recall("ClasesObjectStoreModel");
        BiPredicate<ClasesObjectStoreModel, ClasesObjectStoreModel> comparadorObjectStore = (fileNet, redis) ->
                Objects.equals(fileNet.getId(), redis.getId())
                        &&Objects.equals(fileNet.getNombre(), redis.getNombre())
                        &&Objects.equals(fileNet.getNombreObjectStore(), redis.getNombreObjectStore())
                        &&Objects.equals(fileNet.getIdObjectStore(), redis.getIdObjectStore())
                        &&Objects.equals(fileNet.getNombreSimbolico(), redis.getNombreSimbolico());
        actor.should(seeThat(ValidarDatosCache.entreFileNetYRedis
                (fileNetObjectStores, redisObjectStores, comparadorObjectStore), equalTo(true)));
    }

    @Entonces("los valores de la consulta deben ser los vigentes de CamposClaseDocumental")
    public void losValoresDeLaConsultaDebenSerLosVigentesDeCamposClaseDocumental() {
        Map<ObjectStore, List<CamposClaseDocumentalModel>>
                fileNetCamposDocumentales = actor.recall("camposClaseDocumental");
        List<CamposClaseDocumentalModel> redisCamposDocumentales = actor.recall("CamposClaseDocumentalModel");
        BiPredicate<CamposClaseDocumentalModel, CamposClaseDocumentalModel> comparadorCamposDocumentales = (fileNet, redis) ->
                Objects.equals(fileNet.getId(), redis.getId())
                        && Objects.equals(fileNet.getNombre(), redis.getNombre())
                        && Objects.equals(fileNet.getNombreSimbolico(), redis.getNombreSimbolico())
                        && Objects.equals(fileNet.getSymbolicName(), redis.getSymbolicName())
                        && Objects.equals(fileNet.getDisplayName(), redis.getDisplayName());
        actor.should(seeThat(ValidarDatosCache.entreFileNetYRedis
                (fileNetCamposDocumentales, redisCamposDocumentales, comparadorCamposDocumentales), equalTo(true)));
    }

    @Entonces("los valores de la consulta deben ser los vigentes de ListadosObjectStore")
    public void losValoresDeLaConsultaDebenSerLosVigentesDeListadosObjectStore() {
        Map<ObjectStore, List<ListadosObjectStoreModel>> fileNetListados = actor.recall("listadosObjectStore");
        List<ListadosObjectStoreModel> redisListados = actor.recall("ListadosObjectStoreModel");
        BiPredicate<ListadosObjectStoreModel, ListadosObjectStoreModel> comparadorListados = (fileNet, redis) ->
                Objects.equals(fileNet.getNombre(), redis.getNombre())
                        && Objects.equals(fileNet.getId(), redis.getId())
                        && Objects.equals(fileNet.getNombreObjectStore(), redis.getNombreObjectStore())
                        && Objects.equals(fileNet.getIdObjectStore(), redis.getIdObjectStore());
        actor.should(seeThat(ValidarDatosCache.entreFileNetYRedis
                (fileNetListados, redisListados, comparadorListados), equalTo(true)));
    }

    @Entonces("los valores de la consulta deben ser los vigentes de ValoresListadoObjectStore")
    public void losValoresDeLaConsultaDebenSerLosVigentesDeValoresListadoObjectStore() {
        Map<ObjectStore, List<ValoresListadoObjectStoreModel>>
                fileNetValoresListados = actor.recall("valoresListadoObjectStore");
        List<ValoresListadoObjectStoreModel> redisValoresListados = actor.recall("ValoresListadoObjectStoreModel");
        BiPredicate<ValoresListadoObjectStoreModel, ValoresListadoObjectStoreModel> comparadorValoresListados = (fileNet, redis) ->
                Objects.equals(fileNet.getNombre(), redis.getNombre()) &&
                        Objects.equals(fileNet.getId(), redis.getId());
        actor.should(seeThat(ValidarDatosCache.entreFileNetYRedis
                (fileNetValoresListados, redisValoresListados, comparadorValoresListados), equalTo(true)));
    }

}
