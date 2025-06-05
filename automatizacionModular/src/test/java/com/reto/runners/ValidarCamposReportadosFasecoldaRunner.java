package com.reto.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/validar_campos_reporte_fasecolda.feature",
        glue = "com.sura.idoneidadasesores.definitions.camposreportados",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@REGRESION"
)
public class ValidarCamposReportadosFasecoldaRunner {
}
