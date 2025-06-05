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
public class DatosBDAsesoresTalentoHumano {

    @SerializedName("DNIUSUARIO")
    private String numeroDocumento;
    @SerializedName("CDTIPO_IDON")
    private String tipoIdoneidad;
    @SerializedName("CDEXCEPCION")
    private String excepcion;
    @SerializedName("DSCANAL")
    private String canal;
    @SerializedName("DSMOTIVO")
    private String motivo;
    @SerializedName("DSOBSERV")
    private String observacion;
    @SerializedName("DNIAUTORIZA")
    private String numeroDocumentoAutoriza;
    @SerializedName("FEINGRESO")
    private String fechaIngreso;
    @SerializedName("FEBAJA")
    private String fechaBaja;
    @SerializedName("CDINTERMEDIARIO")
    private String codigoIntermediario;
    @SerializedName("CDRAMO")
    private String codigoRamo;
    @SerializedName("total_registros")
    private String totalRegistros;

}
