package com.retocache.models;

public class ObjectStoreModel {

    private String id;
    private String nombre;

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
        return "ObjectStoreModel{" +
                ", id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
