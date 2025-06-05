# language: es

Característica: Proceso para activar o desactivar un usuario idioneo en portal asistente talento humano
  Como admin del sistema
  quiero activar o desactivar los asesores idoneos y no idoneos del portal asistente talento humano
  para realizar la correcta marcacion de cada asesor que obtiene una insignia

  @REGRESION
  Escenario: administrar los asesores en asistente talento humano
    Dado que se consultaran los asesores de la base de datos de idoneidad
    Cuando se marquen los asesores idoneos y no idoneos
    Y se asignen los ramos correspondientes del cotizador
    Y se obtiene la base de datos de asistente talento humano
    Y se normalicen los datos del proceso idoneo a formato de asistente talento humano
    Entonces los registros de idoneida del proceso idoneo y asistente talento humano se comparan

  Escenario: validar los ramos de los asesores en asistente talento humano
    Dado que se consultaran los asesores de la base de datos de idoneidad
    Cuando se marquen los asesores idoneos y no idoneos
    Y se asignen los ramos correspondientes del cotizador
    Y se obtiene la base de datos de asistente talento humano
    Y se normalicen los datos del proceso idoneo a formato de asistente talento humano
    Entonces los ramos de los asesores del proceso idoneo y asistente talento humano son iguales
