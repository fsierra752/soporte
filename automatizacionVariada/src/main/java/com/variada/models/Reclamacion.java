package com.variada.models;

import java.util.Map;

public abstract class Reclamacion {

  private String numPoliza;
  private String fechaSiniestro;
  private String lugarSiniestro;
  private String reservaTransaccion;
  private String pais;
  private String departamento;
  private String ciudad;
  private String direccion;
  private String tipoDireccion;
  private String fechaAvisoSiniestro;
  private String causaPerdida;
  private String descripcionHechosSiniestro;
  private String identificacionAutor;
  private String valorPerdidaSiniestro;
  private String tipoMonedaPoliza;
  private String esPolizaPropiedad;
  private String tipoDocumento;
  private String numeroIdentificacion;
  private String tipoProducto;

  public Reclamacion() {}

  public Reclamacion(Map<String, String> datosReclamacion) {
    this.fechaSiniestro = datosReclamacion.get("fechaSiniestro");
    this.numPoliza = datosReclamacion.get("numPoliza");
    this.lugarSiniestro = datosReclamacion.get("lugar");
    this.reservaTransaccion = datosReclamacion.get("reservaTransaccion");
    this.pais = datosReclamacion.get("pais");
    this.departamento = datosReclamacion.get("departamento");
    this.ciudad = datosReclamacion.get("ciudad");
    this.direccion = datosReclamacion.get("direccion");
    this.tipoDireccion = datosReclamacion.get("tipoDireccion");
    this.fechaAvisoSiniestro = datosReclamacion.get("fechaAviso");
    this.causaPerdida = datosReclamacion.get("causaPerdida");
    this.descripcionHechosSiniestro = datosReclamacion.get("descripcionHechos");
    this.identificacionAutor = datosReclamacion.get("idAutor");
    this.valorPerdidaSiniestro = datosReclamacion.get("valorPerdida");
    this.tipoMonedaPoliza = datosReclamacion.get("tipoMoneda");
    this.esPolizaPropiedad = datosReclamacion.get("esPolizaPropiedad");
    this.tipoDocumento = datosReclamacion.get("tipoDocumento");
    this.numeroIdentificacion = datosReclamacion.get("numeroIdentificacion");
    this.tipoProducto = datosReclamacion.get("tipoProducto");
  }

  public String getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  public void setNumeroIdentificacion(String numeroIdentificacion) {
    this.numeroIdentificacion = numeroIdentificacion;
  }

  public String getTipoProducto() {
    return tipoProducto;
  }

  public void setTipoProducto(String tipoProducto) {
    this.tipoProducto = tipoProducto;
  }

  public String getNumPoliza() {
    return numPoliza;
  }

  public String getFechaSiniestro() {
    return fechaSiniestro;
  }

  public String getLugarSiniestro() {
    return lugarSiniestro;
  }

  public String getReservaTransaccion() {
    return reservaTransaccion;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getDepartamento() {
    return departamento;
  }

  public String getCiudad() {
    return ciudad;
  }

  public String getDireccion() {
    return direccion;
  }

  public String getTipoDireccion() {
    return tipoDireccion;
  }

  public String getFechaAvisoSiniestro() {
    return fechaAvisoSiniestro;
  }

  public String getCausaPerdida() {
    return causaPerdida;
  }

  public String getDescripcionHechosSiniestro() {
    return descripcionHechosSiniestro;
  }

  public String getIdentificacionAutor() {
    return identificacionAutor;
  }

  public Integer getValorPerdidaSiniestro() {
    return Integer.parseInt(valorPerdidaSiniestro);
  }

  public String getTipoMonedaPoliza() {
    return tipoMonedaPoliza;
  }

  public boolean getEsPolizaPropiedad() {
    return Boolean.parseBoolean(esPolizaPropiedad);
  }
}
