# language: es

Característica: Proceso para verificar los campos requeridos para el reporte a fasecolda
  Como admin del sistema
  quiero validar si los campos requeridos para el reporte a fasecolda estan correctos
  para realizar un correcto reporte de asesores idoneos

  @REGRESION
  Escenario: Validar campos requeridos para el reporte a fasecolda
    Dado que se consultara los campos que se estan reportando a fasecolda
    Cuando se consulte el 'reporte' de 'Fasecolda'
    Entonces se debe validar que los campos sean los mismos que se estan reportando a 'fasecolda'