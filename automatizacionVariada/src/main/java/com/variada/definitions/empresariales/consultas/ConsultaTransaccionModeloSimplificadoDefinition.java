package com.variada.definitions.empresariales.consultas;

import com.variada.models.TransaccionModeloSimplificado;
import com.variada.steps.guidewire.claimscenter.empresariales.ConsultarTransaccionModeloSimplificadoStep;
import com.variada.utils.UtilidadesCSV;
import io.cucumber.java.ast.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;
import java.sql.SQLException;
import net.thucydides.core.annotations.Steps;

public class ConsultaTransaccionModeloSimplificadoDefinition {

  String movimientoFinanciero;

  TransaccionModeloSimplificado transaccionModeloSimplificado;

  TransaccionModeloSimplificado transaccionModeloSimplificadoBD;

  @Steps ConsultarTransaccionModeloSimplificadoStep conexionBDStep;

  @Dado("^que se realiza un (.*)$")
  public void realizarConexionModeloSimplificado(String movimientoFinanciero) {
    this.movimientoFinanciero = movimientoFinanciero;
  }

  @Cuando("^la transaccion se ha efectuado$")
  public void ejecutarConsultaModeloSimplificado() throws IOException {
    transaccionModeloSimplificado =
        new TransaccionModeloSimplificado(
            UtilidadesCSV.obtenerDatosPrueba("reaseguro_modelo_simplificado", movimientoFinanciero));
  }

  @Entonces("^en las fuentes del tablero deben quedar correctos los valores de reaseguro$")
  public void obtenerDatosModeloSimplificado() throws SQLException {
    transaccionModeloSimplificadoBD =
        new TransaccionModeloSimplificado(
            conexionBDStep.consultarModeloSimplificado(
                transaccionModeloSimplificado.getlstModeloSimplificado(), movimientoFinanciero));
    conexionBDStep.verficarConsultaModeloSimplificado(
        transaccionModeloSimplificadoBD.getlstModeloSimplificado(),
        transaccionModeloSimplificado.getlstModeloSimplificado());
  }
}
