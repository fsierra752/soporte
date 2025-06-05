package com.variada.utils.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EnumCodigoCategoriaCoberturaAutos {
  DANOS_TERCEROS("Daños a Terceros", "PADanosATerceros"),
  DANOS_CARRO("Daños al Carro", "PADanosAlCarroGrp"),
  HURTO_CARRO("Hurto al Carro", "PAHurtoAlCarroGrp"),
  CARRO_REEMPLAZO("Carro de Reemplazo", "PACarroDeReemplazo"),
  ACCIDENTES("Accidentes", "PAAccidentes"),
  LLAVES("Llaves", "PALlaves"),
  ASISTENCIA("Asistencia", "PAAsistencia");

  private String categoriaCobertura;
  private String codigoCategoria;
  private static final Map<String, String> mMap = Collections.unmodifiableMap(inicializarMapeo());

  EnumCodigoCategoriaCoberturaAutos(String categoriaCobertura, String codigoCategoria) {
    this.categoriaCobertura = categoriaCobertura;
    this.codigoCategoria = codigoCategoria;
  }

  public static String obtenerCodigoCategoria(String categoriaCobertura) {
    if (mMap.containsKey(categoriaCobertura)) {
      return mMap.get(categoriaCobertura);
    }
    return null;
  }

  private static Map<String, String> inicializarMapeo() {
    Map<String, String> mMap = new HashMap<>();
    for (EnumCodigoCategoriaCoberturaAutos s : EnumCodigoCategoriaCoberturaAutos.values()) {
      mMap.put(s.categoriaCobertura, s.codigoCategoria);
    }
    return mMap;
  }
}
