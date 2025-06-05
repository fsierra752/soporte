package com.retocache.models;

public class ValoresListadoObjectStoreModel {

    private String idObjectStore;
    private String nombreObjectStore;
    private String id;
    private String nombre;
    private String texto;
    private String valor;

    public String getIdObjectStore() {
        return idObjectStore;
    }

    public void setIdObjectStore(String idObjectStore) {
        this.idObjectStore = idObjectStore;
    }

    public String getNombreObjectStore() {
        return nombreObjectStore;
    }

    public void setNombreObjectStore(String nombreObjectStore) {
        this.nombreObjectStore = nombreObjectStore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ValoresListadoObjectStoreModel{" +
                "idObjectStore='" + idObjectStore + '\'' +
                ", nombreObjectStore='" + nombreObjectStore + '\'' +
                ", id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", texto='" + texto + '\'' +
                ", valor=" + valor +
                '}';
    }
}
