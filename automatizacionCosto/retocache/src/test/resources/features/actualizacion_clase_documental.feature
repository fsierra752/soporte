#language:es
#Historia 678499 - 682758

Característica: Validar actualizacion de clases documentales
  Yo como analista de P8
  requiero consultar los campos de las clases documentales en P8 cache
  para validar su correcta actualización

  @REGRESION
  Escenario: Validar actualizacion de clases documentales de PropertyTemplateP8
    Dado que se tienen las propiedades vigentes en filenet "propertyTemplateP8"
    Cuando se consulta el hash "PropertyTemplateP8" en Redis
    Entonces los valores de la consulta deben ser los vigentes de PropertyTemplateP8

  @REGRESION
  Escenario: Validar actualizacion de clases documentales de ObjectStore
    Dado que se tienen las propiedades vigentes en filenet "objectStore"
    Cuando se consulta el hash "ObjectStore" en Redis
    Entonces los valores de la consulta deben ser los vigentes de ObjectStore

  @REGRESION
  Escenario: Validar actualizacion de clases documentales de CamposClaseDocumental
    Dado que se tienen las propiedades vigentes en filenet "camposClaseDocumental"
    Cuando se consulta el hash "CamposClaseDocumental" en Redis
    Entonces los valores de la consulta deben ser los vigentes de CamposClaseDocumental

  @REGRESION
  Escenario: Validar actualizacion de clases documentales de ListadosObjectStore
    Dado que se tienen las propiedades vigentes en filenet "listadosObjectStore"
    Cuando se consulta el hash "ListadosObjectStore" en Redis
    Entonces los valores de la consulta deben ser los vigentes de ListadosObjectStore

  @REGRESION
  Escenario: Validar actualizacion de clases documentales de ValoresListadoObjectStore
    Dado que se tienen las propiedades vigentes en filenet "valoresListadoObjectStore"
    Cuando se consulta el hash "ValoresListadoObjectStore" en Redis
    Entonces los valores de la consulta deben ser los vigentes de ValoresListadoObjectStore

