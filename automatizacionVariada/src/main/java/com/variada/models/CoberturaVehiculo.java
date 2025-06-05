package com.variada.models;

import com.variada.utils.Utilidades;
import com.variada.utils.enums.EnumCodigoCategoriaCoberturaAutos;
import com.variada.utils.enums.EnumCodigoNombreCoberturaAutos;
import com.variada.utils.enums.EnumCodigoTerminoCoberturaAutos;
import com.variada.utils.enums.EnumSeparador;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CoberturaVehiculo {

  private String categoria;
  private String codigoCategoria;
  private String nombre;
  private String codigoNombre;
  private String termino;
  private String codigoTermino;
  private String opcion;
  private String codigoOpcion;

  private CoberturaVehiculo(
      Map<String, String> datosCobertura, List<Map<String, String>> diccionarioCoberturas) {
    categoria = datosCobertura.get("categoria");
    setCodigoCategoria();
    nombre = datosCobertura.get("nombre");
    setCodigoNombre();
    termino = datosCobertura.get("termino");
    setCodigoTermino();
    opcion = datosCobertura.get("opcion");
    setCodigoOpcion(diccionarioCoberturas);
  }

  public static List<CoberturaVehiculo> obtenerListaCoberturas(
      List<Map<String, String>> datosCoberturaAutos,
      List<Map<String, String>> diccionarioCoberturas) {
    List<CoberturaVehiculo> lstCoberturasVehiculo = new ArrayList<>();
    for (Map<String, String> cobertura : datosCoberturaAutos) {
      lstCoberturasVehiculo.add(new CoberturaVehiculo(cobertura, diccionarioCoberturas));
    }
    return lstCoberturasVehiculo;
  }

  public String getCategoria() {
    return categoria;
  }

  public String getCodigoCategoria() {
    return codigoCategoria;
  }

  private void setCodigoCategoria() {
    this.codigoCategoria = EnumCodigoCategoriaCoberturaAutos.obtenerCodigoCategoria(categoria);
  }

  public String getCodigoNombre() {
    return codigoNombre;
  }

  private void setCodigoNombre() {
    this.codigoNombre = EnumCodigoNombreCoberturaAutos.obtenerCodigoNombre(nombre);
  }

  public String getCodigoTermino() {
    return codigoTermino;
  }

  private void setCodigoTermino() {
    String filtroTermino = nombre + EnumSeparador.SEPARADOR_FLECHA.getValor() + termino;
    this.codigoTermino = EnumCodigoTerminoCoberturaAutos.obtenerCodigoTermino(filtroTermino);
  }

  public String getCodigoOpcion() {
    return codigoOpcion;
  }

  private void setCodigoOpcion(List<Map<String, String>> diccionarioCoberturas) {
    String idFiltroCobertura =
        nombre
            + EnumSeparador.SEPARADOR_FLECHA.getValor()
            + termino
            + EnumSeparador.SEPARADOR_FLECHA.getValor()
            + opcion;
    this.codigoOpcion =
        Utilidades.obtenerDatosDiccionario(
            diccionarioCoberturas, idFiltroCobertura, "codigoOpcionCobertura");
  }
}
