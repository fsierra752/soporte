package com.variada.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Credencial {

  private String usuario;
  private String contrasena;
  private List<Credencial> credenciales = new ArrayList<>();

  public Credencial() {}

  private Credencial(Map<String, String> datosUsuario) {
    this.usuario = datosUsuario.get("usuario");
    this.contrasena = datosUsuario.get("contrasena");
  }

  public Credencial(List<Map<String, String>> datosUsuario) {
    asignarDatos(datosUsuario);
  }

  public String getUsuario() {
    return usuario;
  }

  public String getContrasena() {
    return contrasena;
  }

  public List<Credencial> getCredenciales() {
    return credenciales;
  }

  public void asignarDatos(List<Map<String, String>> datosUsuario) {
    for (Map<String, String> dato : datosUsuario) {
      credenciales.add(new Credencial(dato));
    }
  }
}
