# language: es

Característica: Proceso para verificar las tablas maestras a usar
  Como admin del sistema
  quiero validar si las tablas maestras traen la informacion correcta
  para realizar un posible reporte de insignia

  @REGRESION
  Escenario: Validar la tabla de asesores
    Dado que se consultara las insignias de 'asesores' con insignias idoneas
    Entonces se debe mostrar los mismos registros sin filtros

  Escenario: Validar la tabla de empleados
    Dado que se consultara las insignias de 'empleados' con insignias idoneas
    Entonces se debe mostrar los mismos registros sin filtros

  Escenario: Validar la tabla de legales
    Dado que se consultara las insignias de 'legales' con insignias idoneas
    Entonces se debe mostrar los mismos registros sin filtros

  Escenario: Validar la tabla de delegados
    Dado que se consultara las insignias de 'delegados' con insignias idoneas
    Entonces se debe mostrar los mismos registros sin filtros