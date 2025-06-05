package com.retocache.models;


public class ListadosObjectStoreModel {

    private String idObjectStore;
    private String nombreObjectStore;
    private String id;
    private String nombre;

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

    @Override
    public String toString() {
        return "ListadosObjectStoreModel{" +
                "idObjectStore='" + idObjectStore + '\'' +
                ", nombreObjectStore='" + nombreObjectStore + '\'' +
                ", id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
