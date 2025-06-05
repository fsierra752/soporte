# language: es
Característica: Realizar pago de un siniestro

  Como analista de reclamación
  Quiero efectuar un pago a una reclamación
  Para cancelar al asegurado, tercero y/o proveedor involucrados en el siniestro.

  @pagoLineaReservaEmpresariales
  @claimsEmpresarial
  Esquema del escenario: Pago siniestro empresarial
    Dado que se genera un siniestro del producto <Tipo y Cobertura> con causa <Causa>, valor de pretensión <Valor de Pretensión> y tipo incidente de <Tipo de incidente>
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo y Cobertura> donde el responsable <¿Es pago soloSura?> es Sura
    Y se apliquen las siguientes retenciones
      | Codigos_Retenciones |
      | 099                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de Reserva      | Tipo de pago | Beneficiario del pago         | Método del pago | ¿Es pago soloSura? | Tipo y Cobertura                                     | Causa          | Valor de Pretensión | Tipo de incidente |
      | (1) 1ª partePropiedad | Parcial      | AURA JUDITH LOPEZ JULIO CQLII | Caja Sura       | No                 | Hogar con cobertura básica                           | Incendio       | 2000000             | Propiedad         |
      | (1) 1ª parteGeneral   | Final        | TODO EN VERDE CQLII           | Caja Sura       | No                 | Cumplimiento con cobertura Cumplimiento del contrato | Incumplimiento | 3000000             | General           |

  @pagoMultipleRetencionEmpresariales
  @claimsEmpresarial
  Esquema del escenario: Pago siniestro empresarial
    Dado que se genera un siniestro del producto <Tipo y Cobertura> con causa <Causa>, valor de pretensión <Valor de Pretensión> y tipo incidente de <Tipo de incidente>
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo y Cobertura> donde el responsable <¿Es pago soloSura?> es Sura
    Y se apliquen las siguientes retenciones
      | Codigos_Retenciones |
      | 028                 |
      | 023                 |
      | 010                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de Reserva      | Tipo de pago | Beneficiario del pago         | Método del pago | ¿Es pago soloSura? | Tipo y Cobertura                                     | Causa             | Valor de Pretensión | Tipo de incidente |
      | (1) 1ª partePropiedad | Parcial      | AURA JUDITH LOPEZ JULIO CQLII | Caja Sura       | No                 | Hogar con cobertura básica                           | Incendio          | 5000000             | Propiedad         |
      | (1) 1ª parteGeneral   | Parcial      | TODO EN VERDE CQLII           | Caja Sura       | No                 | Cumplimiento con cobertura Cumplimiento del contrato | Incumplimiento    | 3000000             | General           |
      | (1) 1ª partePropiedad | Final        | MATERIALES LTDA. CQLII        | Caja Sura       | No                 | Multiriesgo corporativo con cobertura básica         | Rotura de vidrios | 4000000             | Propiedad         |

  @riesgoConsultableEmpresarial
  @claimsEmpresarial
  Esquema del escenario: Pago siniestro empresarial a un Riesgo Consultable
    Dado que se genera un siniestro del producto <Tipo y Cobertura> con causa <Causa>, valor de pretensión <Valor de Pretensión> y tipo incidente de <Tipo de incidente>
    #Y el asegurado o algún tercero de la póliza tiene marca de riesgo consultable
    Cuando se crea una nueva línea de reserva por la Exposición de <Exposición> por <Categoría> con un tipo de costo <Tipo costo> por un valor de <Valor de Pretensión>
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo y Cobertura> donde el responsable <¿Es pago soloSura?> es Sura
    Y se apliquen las siguientes retenciones
      | Codigos_Retenciones |
      | 028                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de Reserva                                                                                   | Tipo de pago | Beneficiario del pago             | Método del pago | ¿Es pago soloSura? | Tipo y Cobertura                                     | Causa                      | Valor de Pretensión | Tipo de incidente | Exposición                                                | Categoría           | Tipo costo                   | ¿Genera orden de pago? |
      | (1) 1ª parteGeneral - TODO EN VERDE CQLII; Gasto - A&O (ajuste y otros)/Gastos de siniestro; COP   | Parcial      | TODO EN VERDE CQLII               | Pago por banco  | No                 | Cumplimiento con cobertura Cumplimiento del contrato | Incumplimiento             | 3000000             | General           | (1) 1ª parteGeneral - TODO EN VERDE CQLII                 | Gastos de siniestro | Gasto - A&O (ajuste y otros) | Si                     |
      | (1) 1ª parteContenido - JUAN CARLOS PALACIO RAMIREZ CQLII; Costo de reclamación/Avería gruesa; COP | Parcial      | JUAN CARLOS PALACIO RAMIREZ CQLII | Pago por banco  | No                 | Transportes automáticos de mercancía Incendio        | Acc vehiculo transportador | 4000000             | Contenido         | (1) 1ª parteContenido - JUAN CARLOS PALACIO RAMIREZ CQLII | Avería gruesa       | Costo de reclamación         | Si                     |

  @Ignore
  @PruebaAuditoria
  Esquema del escenario: Crear Pago a con proceso de auditoría
    Dado que se tiene una póliza de <Tipo y Cobertura>
    Y se genere un siniestro por causal <Causa> con un valor de pretensión de <Valor de Pretensión>
    Y un incidente de tipo <Tipo de incidente>
    Cuando <¿Requiere auditoría?> se notifique el proceso al área de auditoría
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo y Cobertura> donde el responsable <¿Es pago soloSura?> es Sura
    Y se apliquen las siguientes retenciones
      | Codigos_Retenciones |
      | 0099                |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de Reserva      | Tipo de pago | Beneficiario del pago           | Método del pago | ¿Es pago soloSura? | Tipo y Cobertura                             | Causa          | Valor de Pretensión | Tipo de incidente | ¿Requiere auditoría? |
      | (1) 1ª partePropiedad | Final        | CLUB LOS TRIUNFADORES CQLII     | Pago por banco  | No                 | Multiriesgo corporativo con cobertura básica | Incendio       | 2000000             | Propiedad         | No                   |
      | (1) 1ª partePropiedad | Final        | CLUB LOS TRIUNFADORES CQLII     | Pago por banco  | No                 | Multiriesgo corporativo con cobertura básica | Incendio       | 2000000             | Propiedad         | Si                   |
      | (1) 1ª partePropiedad | Final        | MARTHA ENID ROJAS MARIACA CQLII | Pago por banco  | No                 | Incendio con cobertura Daños materiales      | Daños por agua | 5000000             | Propiedad         | No                   |
      | (1) 1ª partePropiedad | Final        | MARTHA ENID ROJAS MARIACA CQLII | Pago por banco  | No                 | Incendio con cobertura Daños materiales      | Daños por agua | 5000000             | Propiedad         | Si                   |