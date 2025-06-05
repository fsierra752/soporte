# language: es
Característica: Realizar pago de un siniestro
  Como analista de reclamación
  Quiero efectuar un pago a una reclamación
  Para cancelar al asegurado, tercero y/o proveedor involucrados en el siniestro.

  Antecedentes: Crear póliza
    Dado se tiene una póliza de autos individual con plan Global de vigencia anual con 5 días de retroactividad

  Esquema del escenario: Crear pago del siniestro autos pago parcial
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de creacionAvisoMACA de autos que afecta la cobertura <Tipo y Cobertura>
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo y Cobertura> donde el responsable <¿Es pago soloSura?> es Sura, <Prima pendiente> tiene prima pendiente y se aplican las siguientes retenciones
      | Codigos_Retenciones |
      | 099                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de Reserva | Tipo de pago | Beneficiario del pago                 | Método del pago | ¿Es pago soloSura? | Tipo y Cobertura           | Prima pendiente|
      | 1ª parteVehículo | Parcial      | HECTOR ALONSO SALAZAR ESCALANTE CQLII | Caja Sura       | No                 | Perdida total Daños | No             |

  Esquema del escenario: Crear pago del siniestro autos pago final
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de creacionAvisoMACA de autos que afecta la cobertura <Cobertura>
    Cuando se genere un pago <Tipo de pago> al beneficiario <Beneficiario de pago> por el medio de pago de <Método de pago> sobre la línea de reserva <Línea de reserva> donde el responsable <Solo Sura> es Sura, <Prima pendiente> tiene prima pendiente y se aplican las siguientes retenciones
      | Codigos_Retenciones |
      | 099                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de reserva | Tipo de pago | Beneficiario de pago                | Método de pago | Solo Sura | Cobertura           | Prima pendiente |
      | 1ª parteVehículo | Final      | HECTOR ALONSO SALAZAR ESCALANTE CQLII | Caja Sura      | No        | Perdida total Daños | No              |

  Esquema del escenario: Crear cheque con múltiples pagos a diferentes líneas de reserva de un siniestro autos
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de creacionAvisoMACA de autos que afecta la cobertura <Cobertura>
    Cuando se genere un pago por siniestro de auto <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método de Pago> sobre las líneas de reserva <Línea de reserva 1> cuyo responsable <Pago Solo Sura> es Sura donde existe <Número de vehículos involucrados del tercero en el siniestro> vehículo involucrado del tercero en el siniestro, <Prima pendiente> tiene prima pendiente y se aplican las siguientes retenciones
      | Codigos_Retenciones |
      | 099                 |
    Entonces se genera una orden de pago para que le sea entregado al usuario

    Ejemplos:
      | Línea de reserva 1                       | Tipo de pago | Beneficiario del pago                  | Método de Pago | Cobertura           | Pago Solo Sura |Número de vehículos involucrados del tercero en el siniestro| Prima pendiente|
      | Perdida total Daños pago por en EFECTIVO | Final        | HECTOR ALONSO SALAZAR ESCALANTE CQLII  | Pago por banco | Perdida total Daños | No             |1                                                           | No             |

  Esquema del escenario: Crear pago a un siniestro con prima pendiente.
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de <Origen de siniestro> de autos que afecta la cobertura <Tipo de cobertura>
    Y que se declaró la reclamación como perdida total
    Y la póliza esta marcada como financiada, con prima pendiente por pagar
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario de pago> por el medio de pago de <Método de pago> sobre la línea de reserva <Línea de Reserva> con cobertura de <Tipo de cobertura> donde el responsable <¿Es pago soloSura?> es Sura, <Prima pendiente> tiene prima pendiente y se aplican las siguientes retenciones
      | Codigos_Retenciones |
      | 099     |
    Entonces se generan el registro de prima pendiente
      Y se genera el registro del valor del pago menos la prima pendiente

    Ejemplos:
      | Origen de siniestro | Línea de Reserva                                                                                                         | Tipo de pago | Beneficiario de pago                  | Método de pago | ¿Es pago soloSura? | Tipo de cobertura   | Prima pendiente|
      | Servicio de Maca    | (2) 1ª parteVehículo - AOA009  - JHON FEOR FEOR FEOR; Costo de reclamación/Perdida total Daños pago por en EFECTIVO; COP | Parcial      | HECTOR ALONSO SALAZAR ESCALANTE CQLII | Caja Sura      | No                 | Perdida total Daños | Si             |