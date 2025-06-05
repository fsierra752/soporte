package com.reto.models;

import com.reto.models.csv.ArchivosAlmacenadosYSolicitados;
import com.reto.models.csv.DatosCSVEscenarioDTO;
import com.reto.models.csv.DatosDeArchivoFtpDTO;
import com.reto.models.db.*;
import com.sura.idoneidadasesores.models.db.*;
import com.sura.idoneidadasesores.models.csv.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("java:S1068")
public class EscenarioDTO {
    private List<DatosCSVEscenarioDTO> datosCSVEscenarioDTOList;
    private List<DatosBDMaestrasInsigniasDTO> datosBDMaestrasInsigniasDTOList;
    private List<DatosBDGeneralesDTO> datosBDGeneralesDTOList;
    private List<DatosBDTemporalAsesoresInsigniasDTO> datosBDTemporalAsesoresInsigniasDTOList;
    private List<DatosBDMaestrasYNovedadesParaFiltrosDTO> datosBDMaestrasYNovedadesParaFiltrosDTOList;
    private List<DatosDeArchivoFtpDTO> datosDeArchivoFtpDTOList;
    private List<DatosBDHistoricaDTO> datosBDHistoricaDTOList;
    private List<ArchivosAlmacenadosYSolicitados> archivosAlmacenadosYSolicitadosList;
    private List<DatosBDAsesoresInsigniaDTO> datosBDAsesoresInsigniaDTOList;
    private List<DatosBDAsesoresTalentoHumano> datosBDAsesoresTalentoHumanoList;
    private String archivoSolicitado;
}
