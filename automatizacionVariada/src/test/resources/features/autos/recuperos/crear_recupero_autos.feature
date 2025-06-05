# language: es
Característica: Crear un recupero de un siniestro

  Como analista de reclamación
  Quiero crear un recupero a partir de una línea de reserva
  Para que Suramericana recupere una parte del valor pagado sobre el siniestro

  Antecedentes: Crear póliza
    Dado se tiene una póliza de autos individual con plan Global de vigencia anual con 5 días de retroactividad

  Esquema del escenario: crear recupero de subrogación
    Dado que se tiene una póliza con coberturas vigentes, se ingresa la reclamación a través de creacionAvisoMACA de autos que afecta la cobertura <Tipo de cobertura>
    Y que se declaró la reclamación como perdida total
    Cuando se realiza un pago <Tipo de pago> al beneficiario <Beneficiario del pago> por el medio de pago de <Método del pago> sobre la línea de reserva <Línea de Reserva> con cobertura de  <Tipo de cobertura> donde el responsable <¿Es pago soloSura?> es Sura, <Prima pendiente> tiene prima pendiente y se aplican las siguientes retenciones
      | Codigos_Retenciones |
      | 0099                |
    Y se crea el recupero con un código de retención <Código de retención recupero> a una cobertura <Tipo de cobertura>
    Entonces se obtiene un ingreso de dinero sobre el siniestro
    Ejemplos:
      | Línea de Reserva                | Tipo de pago | Beneficiario del pago                 | Método del pago | ¿Es pago soloSura? | Tipo de cobertura   | Código de retención recupero | Prima pendiente |
      | (1) 3ª parteLesiones corporales | Parcial      | HECTOR ALONSO SALAZAR ESCALANTE CQLII | Pago por banco  | No                 | RC Lesión a Persona | 0099                         | No              |
      | (2) 1ª parteVehículo            | Parcial      | HECTOR ALONSO SALAZAR ESCALANTE CQLII | Caja Sura       | No                 | Perdida total Daños | 0099                         | No              |