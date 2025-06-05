package com.retocache.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/actualizacion_clase_documental.feature"},
        glue = {"com.p8.definitions"},
        tags = "@REGRESION"
)
public class ActualizacionClaseDocumentalRunner {
}
