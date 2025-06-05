# language: es
Característica: Generación avisos de siniestros autos

  Yo como facilitador Maca/ abogado en sitio/ funcionario de la línea de solución
  Quiero que se puedan generar avisos de autos
  Para afectar las coberturas de una póliza, cuando un asegurado tenga un siniestro

  Antecedentes: Crear póliza
    Dado se tiene una póliza de autos individual con plan Global de vigencia anual con 5 días de retroactividad

  Escenario: generación de reclamación de tipo Responsabilidad Civil
    Dado que se tiene una póliza con las coberturas para una reclamación tipo responsabilidadCivil
    Cuando se genere un siniestro por la causa y la culpabilidad Responsabilidad civil daños persona y Responsabilidad civil daños vehículo
      |Causa                |Culpabilidad         |Responsabilidad civil daños persona|Responsabilidad civil daños vehículo|
      |Colisión con vehículo|responsabilidad Civil|peaton daños persona               |conductor daños vehículo            |
    Entonces se obtendrán exposiciones automáticas y cada una con su respectiva reserva, según la culpabilidad marcada Responsabilidad Civil

  Escenario: generación de reclamación de tipo Archivo
    Dado que se tiene una póliza con las coberturas para una reclamación tipo archivo
    Cuando se genere un siniestro
    Entonces se obtendrán exposiciones automáticas de exposición, y cada una con su respectiva reserva, según la culpabilidad marcada Archivo

  Escenario: generación de reclamación de tipo Subrogación
    Dado que se tiene una póliza con las coberturas para una reclamación tipo subrogacion
    Cuando se genere un siniestro
    Entonces se obtendrán exposiciones automáticas de exposición, y cada una con su respectiva reserva, según la culpabilidad marcada Archivo

  Escenario: generación de reclamación de tipo Solo Responsabilidad Civil
    Dado que se tiene una póliza con las coberturas para una reclamación tipo soloRC
    Cuando se genere un siniestro por la causa y la culpabilidad Responsabilidad civil daños persona y Responsabilidad civil daños vehículo
      |Causa|Culpabilidad|Responsabilidad civil daños persona|Responsabilidad civil daños vehículo|
      |Colisión con vehículo|solo RC|peaton daños persona|conductor daños vehículo|
    Entonces se obtendrán las exposiciones automáticas para cada tipo de responsabilidad, con su respectiva reserva