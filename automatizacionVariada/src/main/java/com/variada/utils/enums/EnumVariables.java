package com.variada.utils.enums;

public enum EnumVariables {
  FORMATEAR_MONTOS("[+$.,()óéA-Za-z ]");

  private String valor;

  EnumVariables(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}
