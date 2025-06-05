# language: es
Característica: Notificación de aviso de una reclamación en claims producto vida grupo

  Como Analista reclamaciones aprendiz
  quiero que se generen avisos internos de los diferentes productos que tiene la compañía en vida grupo
  para permitir la afectación de las pólizas que han adquirido los usuarios.

  @claimsEmpresarialVG
  Esquema del escenario: Notificación Aviso de Siniestro producto vida grupo
    Dado que se tiene un asegurado de reclamaciones tipo <Tipo Reclamaciones> <Numero Poliza>
    Cuando se genera un siniestro por diagnostico medico <Diagnostico Medico>
    Y se realiza pago por <Transferencia> con <Nombre Titular> <Nombre Banco> <Numero Cuenta> y <Tipo Cuenta> <Cuenta Nueva>
    Y se elija una <Cobertura>
    Entonces se obtiene una reclamación que <¿Genera exposición?> genera exposición

    Ejemplos:
      | Tipo Reclamaciones             | Diagnostico Medico | Transferencia | Numero Poliza | Nombre Titular | Nombre Banco | Numero Cuenta | Tipo Cuenta | Cuenta Nueva | Cobertura | ¿Genera exposición? |
      | Reclamacion invalidezAsegurado | I219               | no            | 900000189720  | ALAN MIGUEL    | BANCOLOMBIA  | 96569564656   | Corriente   | no           | Invalidez | si                  |
      | Reclamacion bonoAsegurado      | I219               | si            | 900000189720  | ALAN MIGUEL    | BANCOLOMBIA  | 78569564656   | Corriente   | no           | Invalidez | si                  |
      | Reclamacion beneficiarioVida   | I500               | si            | 900000189720  | Jhon guerra    | BANCOLOMBIA  | 43569854902   | Ahorros     | si           | Vida      | si                  |
      | Reclamacion beneficiarioVida   | I500               | no            | 900000189720  | Jhon guerra    | BANCOLOMBIA  | 43569854902   | Ahorros     | si           | Vida      | si                  |