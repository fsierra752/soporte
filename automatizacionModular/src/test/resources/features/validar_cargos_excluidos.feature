# language: es
Característica: Proceso para verificar la existencia de cargos excluidos para un negocio
  Como admin del sistema
  quiero validar la existencia de cargos excluidos para un negocio
  para verificar que existan los cargos excluidos para un negocio y se puedan excluir

  @REGRESION
  Escenario: Validar todos los cargos excluidos para un negocio
    Dado que se buscan los numeros de cargos '30' y son "excluidos"
    Entonces se valida que existan correctamente los 'Excluidos'
