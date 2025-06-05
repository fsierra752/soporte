package com.variada.utils.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EnumClaseVehiculo {
  AUTOMOVIL("Automóvil", "1"),
  CAMPEROS_Y_PICKUPS("Camperos y PickUps", "2"),
  MOTOS_DE_0_A_125_CC("motos de 0 a 125 cc", "3"),
  MOTOS_DE_125_A_250_CC("motos de 125 a 250 cc", "4"),
  MOTOS_MAYORES_A_250_CC("motos mayores a 250 cc", "5"),
  MOTOCARRO("Motocarro", "6"),
  REMOLQUES("Remolques", "7"),
  UTILITARIOS("Utilitarios", "8"),
  MEDIANOS("Medianos", "9"),
  PESADOS("Pesados", "10"),
  OTROS_PESADOS("Otros pesados", "11"),
  TAXIS("Taxis", "12"),
  BICICLETAS("Bicicletas", "13");

  private String codigoClaseVehiculo;
  private String descripcionClaseVehiculo;
  private static final Map<String, String> mMap = Collections.unmodifiableMap(inicializarMapeo());

  private EnumClaseVehiculo(String descripcionClaseVehiculo, String codigoClaseVehiculo) {
    this.descripcionClaseVehiculo = descripcionClaseVehiculo;
    this.codigoClaseVehiculo = codigoClaseVehiculo;
  }

  public static String obtenerCodigoClaseVehiculo(String descripcionClaseVehiculo) {
    if (mMap.containsKey(descripcionClaseVehiculo)) {
      return mMap.get(descripcionClaseVehiculo);
    }
    return null;
  }

  private static Map<String, String> inicializarMapeo() {
    Map<String, String> mMap = new HashMap<>();
    for (EnumClaseVehiculo enumClaseVehiculo : EnumClaseVehiculo.values()) {
      mMap.put(enumClaseVehiculo.descripcionClaseVehiculo, enumClaseVehiculo.codigoClaseVehiculo);
    }
    return mMap;
  }
}
