# language: es
Característica: Realizar pago de un siniestro producto vida grupo

  Como analista de reclamación
  Quiero efectuar un pago a una reclamación del producto vida grupo
  Para cancelar al asegurado, tercero y/o proveedor involucrados en el siniestro.

  @claimsEmpresarialVGPagoSiniestro
  Esquema del escenario: Pago siniestro rapido producto vida grupo
    Dado que se tiene un <Tipo y Cobertura> y <Estado>
#    Y se valida en sarlaft el beneficiario <Beneficiario del pago>
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de <Tipo y Cobertura> donde el responsable <¿Es pago soloSura?> es Sura, <Prima pendiente> tiene prima pendiente y se aplican las siguientes retenciones
      | Codigos_Retenciones |
      | 099                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de Reserva                | Tipo de pago | Beneficiario del pago              | Método del pago | ¿Es pago soloSura? | Tipo y Cobertura          | Estado     | Prima pendiente |
      | (1) 1ª parteLesiones corporales | Final        | LUZ MARINA GARZON OSPINO CQLII     | Pago por banco  | No                 | Reclamacion beneficiarioCanasta   | Solicitado | no              |
      | (2) 1ª parteLesiones corporales | Final        | LUZ MARINA GARZON OSPINO CQLII     | Pago por banco  | No                 | Reclamacion beneficiarioFunerario | Solicitado | no              |
      | (3) 1ª parteLesiones corporales | Final        | LUZ MARINA GARZON OSPINO CQLII     | Caja Sura       | No                 | Reclamacion beneficiarioVida      | Solicitado | no              |
      | (1) 1ª parteLesiones corporales | Final        | MARTHA HELENA ORTIZ MARTINEZ CQLII | Transferencia   | No                 | Reclamacion bonoAsegurado | Solicitado | no              |
      | (2) 1ª parteLesiones corporales | Final        | MARTHA HELENA ORTIZ MARTINEZ CQLII | Caja Sura       | No                 | Reclamacion invalidezAsegurado    | Solicitado | no              |
