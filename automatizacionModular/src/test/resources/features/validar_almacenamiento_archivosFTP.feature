# language: es

Característica: Proceso para verificar los archivos generados y su almacenamiento en FTP
  Como admin del sistema
  quiero validar si los archivos que se generar asesores idoneos se guardan correctamente
  para realizar una posible validacion de errores.

  @REGRESION
  Esquema del escenario: Validar almacenamiento de archivos en ruta FTP de sura
    Dado que se ingresa al '<contenedor>' del servidor ftp para buscar los reportes del proceso
    Entonces los archivos encontrados en el blob storage seran iguales a los '<archivos>' solicitados

    Ejemplos:
      | contenedor       | archivos          				|
      | ftp-reportes-050 | reporte_insignias_ 				|
      | ftp-reportes-050 | completados_          			|
      | ftp-reportes-050 | no_completados_      			|
      | ftp-reportes-050 | no_aplicables_vida        		|
      | ftp-reportes-050 | no_aplicables_generales          |
      | ftp-reportes-050 | SUCIS1318 						|
      | ftp-reportes-050 | SUCIS1411 						|