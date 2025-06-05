package com.variada.runners.uat.reclamaciones.claimcenter.e2eTest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/empresariales/pagos/pagar_siniestro.feature",
    glue = {"com.sura.reclamaciones.definitions"},
    tags = "@pagoLineaReservaEmpresariales")
public class PagoLineaReservaEmpresarialesRunner {}
