package com.variada.steps.guidewire.claimscenter.empresariales;

import static com.variada.utils.enums.EnumConstantes.CLAVE;
import static com.variada.utils.enums.EnumConstantes.DRIVER;
import static com.variada.utils.enums.EnumConstantes.URL;
import static com.variada.utils.enums.EnumConstantes.USUARIO;

import com.variada.models.TransaccionModeloSimplificado;
import com.variada.utils.ConexionBaseDatosUtil;
import com.variada.utils.consultasbd.ConsultarTransaccionModeloSimplificado;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.hamcrest.MatcherAssert;

public class ConsultarTransaccionModeloSimplificadoStep {

  Connection conexion;
  String transaccionConsulta;
  String sql = "";
  List<Map<String, String>> resultadoConsulta;

  @Page ConexionBaseDatosUtil consultarModeloSimplificado = new ConexionBaseDatosUtil();

  ConsultarTransaccionModeloSimplificado obtenerSql = new ConsultarTransaccionModeloSimplificado();

  private Connection conectarBaseDatos() {
    conexion =
        ConexionBaseDatosUtil.conectarBaseDatos(
            USUARIO.getValor(), CLAVE.getValor(), URL.getValor(), DRIVER.getValor());
    return conexion;
  }

  public List<Map<String, String>> consultarModeloSimplificado(
      List<TransaccionModeloSimplificado> datosTransaccion, String movimientoFinanciero)
      throws SQLException {
    sql = obtenerSql.obtenerSentenciaSql(movimientoFinanciero);
    datosTransaccion.forEach(
        datoTransaccion -> transaccionConsulta = datoTransaccion.getTransaccion());
    conexion = conectarBaseDatos();
    resultadoConsulta =
        consultarModeloSimplificado.consultarBaseDatos(conexion, transaccionConsulta, sql);
    return resultadoConsulta;
  }

  @Step
  public void verficarConsultaModeloSimplificado(
      List<TransaccionModeloSimplificado> resultadoConsulta,
      List<TransaccionModeloSimplificado> datosTransaccion) {
    for (int i = 0; i < resultadoConsulta.size(); i++) {
      TransaccionModeloSimplificado resultadoBD = resultadoConsulta.get(i);
      TransaccionModeloSimplificado resultadoCalculado = datosTransaccion.get(i);
      MatcherAssert.assertThat(
          "No coincide el valor cedido a las reaseguradoras",
          resultadoBD
              .getValorCedidoReaseguradoras()
              .equals(resultadoCalculado.getValorCedidoReaseguradoras()));
      MatcherAssert.assertThat(
          "No coincide el valor neto",
          resultadoBD.getValorNeto().equals(resultadoCalculado.getValorNeto()));
      MatcherAssert.assertThat(
          "No coincide el valor Transacción",
          resultadoBD
              .getValorMovimientoFinanciero()
              .equals(resultadoCalculado.getValorMovimientoFinanciero()));
      MatcherAssert.assertThat(
          "No coincide el estado del movimiento financiero",
          resultadoBD
              .getEstadoMovimientoFinanciero()
              .equals(resultadoCalculado.getEstadoMovimientoFinanciero()));
    }
  }
}
