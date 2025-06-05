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
public class DatosBDMaestrasInsigniasDTO {

    @SerializedName("total_registros")
    private String totalRegistros;
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
    @SerializedName("dnirepresentante_legal")
    private String dniRepresentanteLegal;
    @SerializedName("dnipersona_juridica")
    private String dniPersonaJuridica;
    @SerializedName("dsnombre_persona_juridica")
    private String nombrePersonaJuridica;
}
