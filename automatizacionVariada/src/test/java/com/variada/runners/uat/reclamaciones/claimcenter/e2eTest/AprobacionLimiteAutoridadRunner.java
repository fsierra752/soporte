package com.variada.runners.uat.reclamaciones.claimcenter.e2eTest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features =
        "src/test/resources/features/empresariales/aprobaciones/aprobar_limite_autoridad.feature",
    glue = {"com.sura.reclamaciones.definitions"})
public class AprobacionLimiteAutoridadRunner {}
