package com.reto.models.db;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosBDGeneralesDTO {

    @SerializedName("dsnombre_insignia")
    private String nombreInsignia;
    @SerializedName("dsramos_general")
    private String ramosGeneral;
    @SerializedName("dsramos_vida")
    private String ramosVida;
    @SerializedName("cdinsignia")
    private String codigoInsignia;
    @SerializedName("cdcargo_excluido")
    private String numeroCargo;
    @SerializedName("dsnombre_cargo")
    private String nombreCargo;
    @SerializedName("column_name")
    private String nombreDeColumnas;
    @SerializedName("total_columns")
    private String totalDeColumnas;
    @SerializedName("nombre_personalizado")
    private String nombreDelReporte;
    @SerializedName("dsramos_cotizador")
    private String ramosCotizador;
}
