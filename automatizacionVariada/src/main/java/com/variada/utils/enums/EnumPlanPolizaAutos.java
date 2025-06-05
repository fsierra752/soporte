package com.variada.utils.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EnumPlanPolizaAutos {
  PLAN_AUTOS_BASICO("Basico", "1"),
  PLAN_AUTOS_CLASICO("Clasico", "2"),
  PLAN_AUTOS_GLOBAL("Global", "3"),
  PLAN_CONDUCE_MEJOR("Conduce Mejor", "5"),
  PLAN_MOTOS("Motos", "7"),
  PLAN_UTILITARIOS_Y_PESADOS("Utilitarios y Pesados", "8");

  private String descripcionPlan;
  private String codigoPlan;
  private static final Map<String, String> mMap = Collections.unmodifiableMap(inicializarMapeo());

  private EnumPlanPolizaAutos(String descripcionPlan, String codigoPlan) {
    this.descripcionPlan = descripcionPlan;
    this.codigoPlan = codigoPlan;
  }

  public static String obtenerCodigoPlan(String descripcionPlan) {
    if (mMap.containsKey(descripcionPlan)) {
      return mMap.get(descripcionPlan);
    }
    return null;
  }

  private static Map<String, String> inicializarMapeo() {
    Map<String, String> mMap = new HashMap<>();
    for (EnumPlanPolizaAutos s : EnumPlanPolizaAutos.values()) {
      mMap.put(s.descripcionPlan, s.codigoPlan);
    }
    return mMap;
  }
}
