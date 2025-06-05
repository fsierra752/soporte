# language: es
  #historia 243579 y 243407

Característica: Descargar archivo CSV del servidor FTP
  Yo como admin del sistema
  Requiero poder descargar reportes en formato csv del sericio ftp
  para que se puedan validar los registros en la bd de idoneidad y asi validar el flujo completo

  @REGRESION
  Esquema del escenario: Descargar archivo ftp
    Dado que se ingresa al '<contenedor>' del servidor ftp para descargar el '<archivo>'
    Cuando se descargue la informacion en base de datos de idoneidad
    Entonces los registros descargados estaran en la base de datos de historico de idoneidad

    Ejemplos:
      | contenedor       | archivo    |
      | ftp-reportes-050 | vida_      |
      | ftp-reportes-050 | generales_ |

  @REGRESION
  Esquema del escenario: Descargar archivo ftp
    Dado que se ingresa al '<contenedor>' del servidor ftp para descargar el '<archivo>'
    Entonces se validara que los campos internos sean correctos:
      | campoUno    | cdidentificacion  |
      | campoDos    | dsnombre_asesor   |
      | campoTres   | feinicio_insignia |
      | campoCuatro | fefin_insignia    |
      | campoQuinto | dsnombre_insignia |
      | campoSexto  | cdinsignia        |

    Ejemplos:
      | contenedor       | archivo            |
      | ftp-reportes-050 | reporte_insignias_ |