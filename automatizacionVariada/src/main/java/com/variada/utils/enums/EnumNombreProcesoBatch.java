package com.variada.utils.enums;

public enum EnumNombreProcesoBatch {
  ENVIO_FACTURA_VOLUMEN("Envío de factura por volumen"),
  MONITOR_FLUJO_TRABAJO_FACTURA_VOLUMEN("Monitor de flujos de trabajo de facturas por volumen"),
  TRANSFERENCIA_FACTURA_VOLUMEN("Transferencia de factura por volumen");

  private String valor;

  EnumNombreProcesoBatch(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}
