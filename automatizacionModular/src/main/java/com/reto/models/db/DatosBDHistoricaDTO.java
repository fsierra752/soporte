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
public class DatosBDHistoricaDTO {

    @SerializedName("cdentidad")
    private String negocio;
    @SerializedName("cdtipo_identificacion")
    private String tipoIdentificacion;
    @SerializedName("cdidentificacion")
    private String numeroIdentificacion;
    @SerializedName("dsnombre_asesor")
    private String nombreAsesor;
    @SerializedName("cdestado_autorizacion")
    private String estadoAutorizacion;
    @SerializedName("cdtipo_intermediario")
    private String tipoIntermediario;
    @SerializedName("cdindicador_vinculado")
    private String indicadorVinculado;
    @SerializedName("cdtipo_identificacion_compania")
    private String tipoIdentificacionCompania;
    @SerializedName("dnicompania")
    private String numeroCompania;
    @SerializedName("dsnombre_compania")
    private String nombreCompania;
    @SerializedName("fevinculacion")
    private String fechaVinculacion;
    @SerializedName("fedesvinculacion")
    private String fechaDesvinculacion;
    @SerializedName("dsorganismo_acreditador")
    private String organismoAcreditador;
    @SerializedName("feinicio_insignia")
    private String fechaInicioInsignia;
    @SerializedName("fefin_insignia")
    private String fechaFinInsignia;
    @SerializedName("cdtipo_novedad")
    private String tipoNovedad;
    @SerializedName("dsramos_autorizados")
    private String ramosAutorizados;
}
