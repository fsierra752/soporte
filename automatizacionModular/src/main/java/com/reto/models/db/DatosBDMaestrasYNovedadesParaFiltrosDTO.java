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
public class DatosBDMaestrasYNovedadesParaFiltrosDTO {

    @SerializedName("total_registros")
    private String totalRegistros;
    @SerializedName("dsramos_autorizados")
    private String ramosAutorizados;
    @SerializedName("cdtipo_identificacion")
    private String tipoIdentificacion;
    @SerializedName("cdidentificacion")
    private String numeroIdentificacion;
    @SerializedName("dsnombre_asesor")
    private String nombreAsesor;
    @SerializedName("fevinculacion")
    private String fechaVinculacion;
    @SerializedName("cdcargo")
    private String codigoCargo;
    @SerializedName("dscargo")
    private String descripcionCargo;
    @SerializedName("dsclase_vinculacion")
    private String tipoVinculacion;
}
