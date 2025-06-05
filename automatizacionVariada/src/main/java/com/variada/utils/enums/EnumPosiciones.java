package com.variada.utils.enums;

public enum EnumPosiciones {

  POSICION_COLUMNA_MENOS_DOS("-2"),
  POSICION_FILA("1");

  private String valor;

  EnumPosiciones(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}
