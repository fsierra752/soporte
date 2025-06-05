# language: es
Característica: Proceso para verificar la existencia de ramos asociados a las insignias del negocio generales
  Como admin del sistema
  quiero validar la existencia de nombres de insignias con sus respectivos ramos
  para verificar que cada asesor de venta tenga sus insignias y ramos correctamente

  @REGRESION
  Escenario: Validar ramos asociados de las insignias de ARL
    Dado que se buscan los siguientes numeros de ramos '6' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'ARL'

  Escenario: Validar ramos asociados de las insignias de Exito Nuevas Alianzas Retail
    Dado que se buscan los siguientes numeros de ramos '4;3;6' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'ExitoNuevasAlianzasRetail'

  Escenario: Validar ramos asociados de las insignias de Corbeta Retail
    Dado que se buscan los siguientes numeros de ramos '4;6;8' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'CorbetaRetail'

  Escenario: Validar ramos asociados de las insignias de Corbeta Retail Dos
    Dado que se buscan los siguientes numeros de ramos '8;4;6' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'CorbetaRetailDos'

  Escenario: Validar ramos asociados de las insignias de Asesor Profesional
    Dado que se buscan los siguientes numeros de ramos '3;8;4;6' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'AsesorProfesional'

  Escenario: Validar ramos asociados de las insignias de Cliente Directo Televentas BST
    Dado que se buscan los siguientes numeros de ramos '3;25' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'ClienteDirectoTeleventasBST'

  Escenario: Validar ramos asociados de las insignias de Banca Seguros Bancos
    Dado que se buscan los siguientes numeros de ramos '3;25;6;5;10;17;12;15;11;7;9;14' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'BancaSegurosBancos'

  Escenario: Validar ramos asociados de las insignias de Ruta Integral
    Dado que se buscan los siguientes numeros de ramos '3;25;8;13;6;7;9;14;5;4;10;17' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'RutaIntegral'

  Escenario: Validar ramos asociados de las insignias de Corporativo Gran Empresa
    Dado que se buscan los siguientes numeros de ramos '3;25;8;6;7;9;14;5;10;17;12;15;11' de "generales"
    Entonces se valida que las insignias existan correctamente al negocio 'CorporativoGranEmpresa'