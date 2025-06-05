#language:es
#Historia 682779

Característica: Validar actualizacion de parámetros
  Yo como analista de P8
  requiero consultar las configuraciones de parametros en P8 cache
  para validar su correcta actualización

  @REGRESION
  Escenario: Validar actualizacion de parametros
    Dado que se tienen los parametros en la bd de postgres
    Cuando se consulta el hash "Parametros" para Redis
    Entonces los valores de la consulta deben coincidir con los almacenados en postgres