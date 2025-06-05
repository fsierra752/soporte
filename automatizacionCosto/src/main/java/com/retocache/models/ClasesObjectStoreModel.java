package com.retocache.models;

public class ClasesObjectStoreModel {
    private String idObjectStore;
    private String nombreObjectStore;
    private String id;
    private String nombreSimbolico;
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

    public String getNombreSimbolico() {
        return nombreSimbolico;
    }

    public void setNombreSimbolico(String nombreSimbolico) {
        this.nombreSimbolico = nombreSimbolico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ClasesObjectStoreModel{" +
                "idObjectStore='" + idObjectStore + '\'' +
                ", nombreObjectStore='" + nombreObjectStore + '\'' +
                ", id='" + id + '\'' +
                ", nombreSimbolico='" + nombreSimbolico + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
