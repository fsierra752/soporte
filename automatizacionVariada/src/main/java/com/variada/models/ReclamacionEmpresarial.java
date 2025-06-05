package com.variada.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReclamacionEmpresarial extends Reclamacion {

  private List<ReclamacionEmpresarial> lstReclamoEmp = new ArrayList<>();
  private String numeroContrato;
  private String detalleHechos;
  private String causaHechosSiniestro;
  private String valorPretension;
  private String tipoIncidente;
  private String ramoPolizaAtr;
  private String identificadorRiesgo;
  private String incidenteContenido;
  private String incidentePropiedad;
  private String tipoDocumento;
  private String numeroIdentificacion;
  private String tipoProducto;
  private String asegurado;
  private String nombreAseguradoBeneficiario;
  private String relacionAsegurado;

  public ReclamacionEmpresarial() {
    super();
  }

  private ReclamacionEmpresarial(Map<String, String> datosReclamacionEmpresarial) {
    super(datosReclamacionEmpresarial);
    this.numeroContrato = datosReclamacionEmpresarial.get("numeroContrato");
    this.detalleHechos = datosReclamacionEmpresarial.get("detalleHechos");
    this.causaHechosSiniestro = datosReclamacionEmpresarial.get("causa");
    this.valorPretension = datosReclamacionEmpresarial.get("valorPretension");
    this.tipoIncidente = datosReclamacionEmpresarial.get("tipoIncidente");
    this.identificadorRiesgo = datosReclamacionEmpresarial.get("idRiesgo");
    this.incidenteContenido = datosReclamacionEmpresarial.get("incidenteContenido");
    this.incidentePropiedad = datosReclamacionEmpresarial.get("incidentePropiedad");
    this.ramoPolizaAtr = datosReclamacionEmpresarial.get("ramoPolizaAtr");
    this.tipoDocumento = datosReclamacionEmpresarial.get("tipoDocumento");
    this.numeroIdentificacion = datosReclamacionEmpresarial.get("numeroIdentificacion");
    this.tipoProducto = datosReclamacionEmpresarial.get("tipoProducto");
    this.asegurado = datosReclamacionEmpresarial.get("asegurado");
    this.nombreAseguradoBeneficiario = datosReclamacionEmpresarial.get("nomAseguradoBeneficiario");
    this.relacionAsegurado = datosReclamacionEmpresarial.get("relacionAsegurado");
  }

  public ReclamacionEmpresarial(List<Map<String, String>> datosReclamacionesEmp) {
    asignarDatos(datosReclamacionesEmp);
  }

  public String getNumeroContrato() {
    return numeroContrato;
  }

  public String getDetalleHechos() {
    return detalleHechos;
  }

  public String getCausaHechosSiniestro() {
    return causaHechosSiniestro;
  }

  public String getValorPretension() {
    return valorPretension;
  }

  public String getTipoIncidente() {
    return tipoIncidente;
  }

  public String getRamoPolizaAtr() {
    return ramoPolizaAtr;
  }

  public String getIdentificadorRiesgo() {
    return identificadorRiesgo;
  }

  public boolean getIncidenteContenido() {
    return Boolean.parseBoolean(incidenteContenido);
  }

  public boolean getIncidentePropiedad() {
    return Boolean.parseBoolean(incidentePropiedad);
  }

  public List<ReclamacionEmpresarial> getLstReclamo() {
    return lstReclamoEmp;
  }

  @Override
  public String getTipoDocumento() {
    return tipoDocumento;
  }

  @Override
  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  @Override
  public String getTipoProducto() {
    return tipoProducto;
  }

  @Override
  public void setTipoProducto(String tipopProducto) {
    this.tipoProducto = tipopProducto;
  }

  @Override
  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  @Override
  public void setNumeroIdentificacion(String numeroIdentificacion) {
    this.numeroIdentificacion = numeroIdentificacion;
  }

  public String getAsegurado() {
    return asegurado;
  }

  public void setAsegurado(String asegurado) {
    this.asegurado = asegurado;
  }

  public String getNombreAseguradoBeneficiario() {
    return nombreAseguradoBeneficiario;
  }

  public String getRelacionAsegurado() {
    return relacionAsegurado;
  }

  public void setRelacionAsegurado(String relacionAsegurado) {
    this.relacionAsegurado = relacionAsegurado;
  }

  public void setNombreAseguradoBeneficiario(String nombreAseguradoBeneficiario) {
    this.nombreAseguradoBeneficiario = nombreAseguradoBeneficiario;
  }

  private void asignarDatos(List<Map<String, String>> datosReclamacionesEmp) {
    for (Map<String, String> dato : datosReclamacionesEmp) {
      lstReclamoEmp.add(new ReclamacionEmpresarial(dato));
    }
  }
}
