# language: es
Característica: Anulación de recuperos

  Como analista de reclamaciones
  Quiero generar una anulación de un recupero
  Para verificar que el proceso de recuperación sea suspendido y no sea efectuado.

  Antecedentes: Crear póliza
    Dado se tiene una póliza de autos individual con plan Global de vigencia anual con 5 días de retroactividad

  Esquema del escenario: anulación de un recupero de autos.
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de creacionAvisoMACA de autos que afecta la cobertura <Tipo de cobertura>
    Y que se tiene una reclamación que tuvo un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo de cobertura> donde el responsable <¿Es pago soloSura?> es Sura, <Prima pendiente> tiene prima pendiente y se aplicaron las siguientes retenciones
      |Codigos_Retenciones|
      |0099               |
    Y que se creó el recupero con un código de retención <Código de retención recupero> a una cobertura <Tipo de cobertura>
    Cuando se anula el ingreso con cobertura <Tipo de cobertura>
    Entonces se debe obtener la anulación del recupero, quedando en estado anulado

    Ejemplos:
      | Línea de Reserva     | Tipo de pago | Beneficiario del pago                  | Método del pago | ¿Es pago soloSura? | Tipo de cobertura   | Código de retención recupero | Prima pendiente|
      | (2) 1ª parteVehículo | Parcial      |  HECTOR ALONSO SALAZAR ESCALANTE CQLII | Caja Sura       | No                 | Perdida total Daños | 0099                         | No             |