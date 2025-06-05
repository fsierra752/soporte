package com.reto.models.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosDeArchivoFtpDTO {

    @CsvBindByName(column = "cdentidad")
    private String codigoEntidad;
    @CsvBindByName(column = "cdtipo_identificacion")
    private String tipoIdentificacion;
    @CsvBindByName(column = "cdidentificacion")
    private String numeroIdentificacion;
    @CsvBindByName(column = "dsnombre_asesor")
    private String nombreAsesor;
    @CsvBindByName(column = "cdestado_autorizacion")
    private String estadoAutorizacion;
    @CsvBindByName(column = "cdtipo_intermediario")
    private String tipoIntermediario;
    @CsvBindByName(column = "cdindicador_vinculado")
    private String indicadorVinculado;
    @CsvBindByName(column = "cdtipo_identificacion_compania")
    private String tipoDocumentoEntidadVinculada;
    @CsvBindByName(column = "dnicompania")
    private String numeroDocumentoEntidadVinculada;
    @CsvBindByName(column = "dsnombre_compania")
    private String nombreEntidadVinculada;
    @CsvBindByName(column = "fevinculacion")
    private String fechaVinculacion;
    @CsvBindByName(column = "fedesvinculacion")
    private String fechaDesvinculacion;
    @CsvBindByName(column = "dsorganismo_acreditador")
    private String organismoAcredita;
    @CsvBindByName(column = "feinicio_insignia")
    private String fechaInicioAcreditacion;
    @CsvBindByName(column = "fefin_insignia")
    private String fechaFinalAcreditacion;
    @CsvBindByName(column = "dsramos_autorizados")
    private String ramosAutorizados;
    @CsvBindByName(column = "cdtipo_novedad")
    private String tipoNovedad;
    @CsvBindByName(column = "dsnombre_insignia")
    private String nombreInsignia;
    @CsvBindByName(column = "cdinsignia")
    private String codigoInsignia;
}
