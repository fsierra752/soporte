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
public class DatosCSVEscenarioDTO {

    @CsvBindByName(column = "nombreInsignia")
    private String nombreInsignia;
    @CsvBindByName(column = "ramosGeneral")
    private String ramosGeneral;
    @CsvBindByName(column = "ramosVida")
    private String ramosVida;
    @CsvBindByName(column = "codigoInsignia")
    private String codigoInsignia;
    @CsvBindByName(column = "numeroCargo")
    private String codigoCargo;
    @CsvBindByName(column = "nombreCargo")
    private String nombreCargo;
    @CsvBindByName(column = "column_name")
    private String nombreColumnas;
    @CsvBindByName(column = "total_column")
    private String totalColumnas;
    @CsvBindByName(column = "nombre_personalizado")
    private String totalPersonalizado;
}
