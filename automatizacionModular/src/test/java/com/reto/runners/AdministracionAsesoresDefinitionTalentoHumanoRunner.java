package com.reto.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/administracion_asesores_talento_humano.feature",
        glue = "com.sura.idoneidadasesores.definitions.administracionasesores",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@REGRESION"
)
public class AdministracionAsesoresDefinitionTalentoHumanoRunner {
}
