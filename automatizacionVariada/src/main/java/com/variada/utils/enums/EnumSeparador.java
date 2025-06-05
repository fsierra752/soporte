package com.variada.utils.enums;

public enum EnumSeparador {
  SEPARADOR_FLECHA("->"),
  SEPARADOR_VIRGULILLA("~");

  private String valor;

  private EnumSeparador(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}
