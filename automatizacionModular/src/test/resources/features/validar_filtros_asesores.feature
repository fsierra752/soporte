# language: es

Característica: Proceso para verificar los filtros necesario de un documento
  Como admin del sistema
  quiero validar si un documento cumple con los filtros necesarios
  para realizar un posible reporte

  Esquema del escenario: Validar filtros regla 1: asesores independientes y dependientes para asesores y empleados
    Dado que se requiere saber los documentos de '<tabla>' con tipo vinculacion '<tipoVinculacion>'
    Entonces el tipo cargo debe ser '<tipoCargo>'

    Ejemplos:
      | tabla     | tipoVinculacion      | tipoCargo            |
      | asesores  | ASESOR INDEPENDIENTE | ASESOR INDEPENDIENTE |
      | asesores  | ASESOR DEPENDIENTE   | ASESOR DEPENDIENTE   |
      | empleados | ASESOR INDEPENDIENTE | ASESOR INDEPENDIENTE |
      | empleados | ASESOR DEPENDIENTE   | ASESOR DEPENDIENTE   |

  Esquema del escenario: Validar filtros regla 2: tipo vinculacion 'EMPLEADO' no reportar con cargos excluidos
    Dado que se requiere saber los documentos de '<tabla>' con tipo vinculacion '<tipoVinculacion>'
    Cuando se consulten los cargos excluidos
    Entonces se reportan todos los cargos menos los cargos excluidos

    Ejemplos:
      | tabla     | tipoVinculacion      |
      | asesores  | EMPLEADO             |
      | asesores  | EMPLEADO (CO)        |
      | empleados | EMPLEADO             |
      | empleados | EMPLEADO (CO)        |

  @REGRESION
  Esquema del escenario: Validar filtros regla 3: tipos de cargos aprendiz y corredor no se deben reportar
    Dado que se requiere saber los documentos de '<tabla>' con tipo vinculacion '<tipoVinculacion>'
    Entonces el tipo cargo '<tipoCargo>' no se debe reportar

    Ejemplos:
      | tabla     | tipoVinculacion | tipoCargo |
      | asesores  | APRENDIZ        | APRENDIZ  |
      | legales   | AGENCIA         | CORREDOR  |
      | empleados | APRENDIZ        | APRENDIZ  |

  Esquema del escenario: Validar filtros regla 4: rutas Canal corporativo y gran empresa solo reportar con cargos 'DIRECTOR O GERENTE'
    Dado que se requiere saber los documentos de '<tabla>' con tipo vinculacion '<tipoVinculacion>'
    Entonces el tipo cargo debe ser '<tipoCargo>' con tipo de negocio '<vida>' y '<general>'

    Ejemplos:
      | tabla     | tipoVinculacion | tipoCargo          | vida                    | general                          |
      | asesores  | EMPLEADO        | DIRECTOR O GERENTE | 30;35;31;36;37;32;34;39 | 3;25;8;6;7;9;14;5;10;17;12;15;11 |
      | asesores  | EMPLEADO (CO)   | DIRECTOR O GERENTE | 30;35;31;36;37;32;34;39 | 3;25;8;6;7;9;14;5;10;17;12;15;11 |
      | empleados | EMPLEADO        | DIRECTOR O GERENTE | 30;35;31;36;37;32;34;39 | 3;25;8;6;7;9;14;5;10;17;12;15;11 |
      | empleados | EMPLEADO (CO)   | DIRECTOR O GERENTE | 30;35;31;36;37;32;34;39 | 3;25;8;6;7;9;14;5;10;17;12;15;11 |