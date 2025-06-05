# language: es
Característica: Proceso para verificar la existencia de ramos asociados a las insignias del negocio vida
  Como admin del sistema
  quiero validar la existencia de nombres de insignias con sus respectivos ramos
  para verificar que cada asesor de venta tenga sus insignias y ramos correctamente

  @REGRESION
  Escenario: Validar ramos asociados de las insignias de ARL
    Dado que se buscan los siguientes numeros de ramos '31;36;37;33;40;39;30;35' de "vida"
    Entonces se valida que las insignias existan correctamente al negocio 'Vida'

  Escenario: Validar ramos asociados de las insignias de Exito Nuevas Alianzas Retail
    Dado que se buscan los siguientes numeros de ramos '30;35' de "vida"
    Entonces se valida que las insignias existan correctamente al negocio 'Salud'