package com.retocache.models;

public class CamposClaseDocumentalModel {
    private String idObjectStore;
    private String nombreObjectStore;
    private String id;
    private String nombreSimbolico;
    private String nombre;
    private String symbolicName;
    private String displayName;
    private Boolean esMutivalor;
    private String tipoDato;
    private String nombreCvl;

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

    public String getSymbolicName() {
        return symbolicName;
    }

    public void setSymbolicName(String symbolicName) {
        this.symbolicName = symbolicName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getEsMutivalor() {
        return esMutivalor;
    }

    public void setEsMutivalor(Boolean esMutivalor) {
        this.esMutivalor = esMutivalor;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getNombreCvl() {
        return nombreCvl;
    }

    public void setNombreCvl(String nombreCvl) {
        this.nombreCvl = nombreCvl;
    }

    @Override
    public String toString() {
        return "CamposClaseDocumentalModel{" +
                "idObjectStore='" + idObjectStore + '\'' +
                ", nombreObjectStore='" + nombreObjectStore + '\'' +
                ", id='" + id + '\'' +
                ", nombreSimbolico='" + nombreSimbolico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", symbolicName='" + symbolicName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", esMutivalor=" + esMutivalor +
                ", tipoDato='" + tipoDato + '\'' +
                ", nombreCvl='" + nombreCvl + '\'' +
                '}';
    }
}
