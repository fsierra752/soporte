package com.reto.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/validar_filtros_asesores.feature",
        glue = "com.sura.idoneidadasesores.definitions.filtrosdocumentos",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@REGRESION"
)
public class ValidarFiltrosAsesoresRunner {
}
