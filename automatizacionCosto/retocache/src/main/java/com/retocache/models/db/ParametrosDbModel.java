package com.retocache.models.db;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data

public class ParametrosDbModel {
    @SerializedName("dsaplicacion")
    private String dsAplicacion;
    @SerializedName("dsparametro")
    private String dsParametro;
    @SerializedName("dsvalor")
    private String dsValorCorto;
    @SerializedName("dsvalorlargo")
    private String dsValorLargo;


    public String getDsAplicacion() {
        return dsAplicacion;
    }

    public void setDsAplicacion(String dsAplicacion) {
        this.dsAplicacion = dsAplicacion;
    }


    public String getDsParametro() {
        return dsParametro;
    }

    public void setDsParametro(String dsParametro) {
        this.dsParametro = dsParametro;
    }


    public String getDsValorCorto() {
        return dsValorCorto;
    }

    public void setDsValorCorto(String dsValorCorto) {
        this.dsValorCorto = dsValorCorto;
    }


    public String getDsValorLargo() {
        return dsValorLargo;
    }

    public void setDsValorLargo(String dsValorLargo) {
        this.dsValorLargo = dsValorLargo;
    }
}
