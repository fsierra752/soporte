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
public class DatosBDAsesoresInsigniaDTO {

    @SerializedName("cdtipo_identificacion")
    private String tipoIdentificacion;
    @SerializedName("cdidentificacion")
    private String numeroIdentificacion;
    @SerializedName("dsnombre_asesor")
    private String nombreAsesor;
    @SerializedName("dnicompania")
    private String numeroCompania;
    @SerializedName("dsnombre_compania")
    private String nombreCompania;
    @SerializedName("fevinculacion")
    private String fechaVinculacion;
    @SerializedName("feinicio_insignia")
    private String fechaInicioInsignia;
    @SerializedName("fefin_insignia")
    private String fechaFinInsignia;
    @SerializedName("cdinsignia")
    private String codigoInsignia;
    @SerializedName("dsnombre_insignia")
    private String nombreInsignia;
    @SerializedName("cdcargo")
    private String codigoCargo;
    @SerializedName("dscargo")
    private String descripcionCargo;
    @SerializedName("dsclase_vinculacion")
    private String tipoVinculacion;
    @SerializedName("snrepresentante")
    private String esRepresentante;
    @SerializedName("cdnomina")
    private String codigoNomina;
    @SerializedName("idoneo_vida")
    private String idoneo;
    @SerializedName("no_idoneo_vida")
    private String noIdoneo;
    @SerializedName("estado_comparacion")
    private String estadoComparacion;
    @SerializedName("ramo_cotizador")
    private String ramoDelCotizador;
    @SerializedName("total_registros")
    private String totalRegistros;
}
