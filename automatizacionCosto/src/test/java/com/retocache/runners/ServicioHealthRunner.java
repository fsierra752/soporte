package com.retocache.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/servicio_rocket.feature"},
        glue = {"com.p8.definitions"},
        tags = "@REGRESION"
)
public class ServicioHealthRunner {
}
