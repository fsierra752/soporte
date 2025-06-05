# language: es
Característica: Anulación de pagos

  Como analista de reclamaciones
  Quiero generar una anulación de un pago
  Para verificar que el proceso de pago sea suspendido y no sea efectuado.

  Antecedentes: Crear póliza
    Dado se tiene una póliza de autos individual con plan Global de vigencia anual con 5 días de retroactividad

  Esquema del escenario: anulación de un pago de autos.
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de creacionAvisoMACA de autos que afecta la cobertura <Tipo de cobertura>
    Y que se tiene una reclamación que tuvo un pago <Tipo de pago> al beneficiario <Beneficiario de pago> por el medio de pago de <Método de pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo de cobertura> donde el responsable <¿Es pago soloSura?> es Sura, <Prima pendiente> tiene prima pendiente y se aplicaron las siguientes retenciones
      | Codigos Retenciones |
      | 0099                |
    Cuando se anula dicho pago con cobertura <Tipo de cobertura>
    Entonces se debe obtener la anulación del pago, quedando en estado anulado

    Ejemplos:
      | Línea de Reserva     | Tipo de pago | Beneficiario de pago                  | Método de pago | ¿Es pago soloSura? | Tipo de cobertura   |Prima pendiente|
      | (2) 1ª parteVehículo | Parcial      | HECTOR ALONSO SALAZAR ESCALANTE CQLII | Caja Sura      | No                 | Perdida total Daños |No             |