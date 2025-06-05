package com.variada.utils.enums;

public enum EnumVariablesSesion {
  SESION_CC_CONDUCTOR_AFECTADO_SINIESTRO("conductor_afectado_siniestro"),
  SESION_CC_NUMERO_PLACAS_PARTES_IMPLICADAS("NumeroPlacaPartesInvolucradas"),
  SESION_CC_NUMERO_SINIESTRO("NumeroSiniestro"),
  SESION_CC_NUMERO_TRANSACCION("Número de transacción"),
  SESION_CC_PLACAS_VEHICULOS_INVOLUCRADOS("PlacaVehiculoInvolucrado"),
  SESION_CC_LINEA_RESERVA("lineaReserva"),
  SESION_CC_NUMERO_PAGO_INDIVIDUAL("NumeroPagoIndividual"),
  SESION_CC_NUMERO_FACTURA_PAGO_MASIVO("NumeroFacturaPagoMasivo"),
  SESION_CC_TIPO_COBERTURA_AFECTADA("tipoCoberturaAfectada"),
  SESION_CC_TIPO_PAGO("tipoPago"),
  SESION_CC_TIPO_PRODUCTO_EMPRESARIAL("tipoProductoEmpresarial"),
  SESION_CC_TOTAL_PAGO_RESERVAS("0"),
  SESION_CC_VALOR_PAGO("valorPago"),
  SESION_CC_VALOR_RECUPERO("ValorRecupero"),
  SESION_CC_VALOR_RESERVA("valorReserva"),
  SESION_CC_VALOR_RESERVA_CONSTITUCION("valorReservaConstitución"),
  SESION_CC_NUMERO_PLACA("numeroPlaca"),
  SESION_PC_NUMERO_POLIZA("numeroPoliza"),
  SESION_CC_NUMERO_PAGOS_INDIVIDUALES("NumeroPagosIndividuales");

  private String valor;

  EnumVariablesSesion(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}
