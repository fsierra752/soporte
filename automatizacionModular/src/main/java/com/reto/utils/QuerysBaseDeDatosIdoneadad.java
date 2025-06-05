package com.reto.utils;

public class QuerysBaseDeDatosIdoneadad {

    private QuerysBaseDeDatosIdoneadad() {
    }

    public static final String QUERY_CONSULTAR_RAMOS_GENERALES =
            "SELECT * FROM esqidoneidadasesordll.tido_ramos_insignias where dsramos_general = '%s';";

    public static final String QUERY_CONSULTAR_RAMOS_VIDA =
            "SELECT * FROM esqidoneidadasesordll.tido_ramos_insignias where dsramos_vida = '%s';";

    public static final String QUERY_CONSULTAR_CARGOS_EXCLUIDOS =
            "SELECT cdcargo_excluido, dsnombre_cargo FROM esqidoneidadasesordll.tido_cargos_excluidos;";

    public static final String QUERY_CONSULTAR_INSIGNIAS_EN_ASESORES = """
        SELECT
            COUNT(*) OVER () AS total_registros,
            asesores.cdtipo_identificacion,
            asesores.cdidentificacion,
            insignias.dsnombre_asesor,
            asesores.dsclase_vinculacion,
            asesores.cdcargo,
            asesores.dscargo,
            asesores.fevinculacion,
            insignias.cdinsignia,
            insignias.dsnombre_insignia,
            insignias.feinicio_insignia,
            insignias.fefin_insignia
        FROM
            esqidoneidadasesordll.tido_insignias insignias
        INNER JOIN
            esqidoneidadasesordll.tido_asesores asesores
        ON
            insignias.cdidentificacion = asesores.cdidentificacion
        WHERE
            asesores.cdidentificacion IS NOT NULL;
        """;

    public static final String QUERY_CONSULTAR_INSIGNIAS_EN_EMPLEADOS = """
        SELECT 
            COUNT(*) OVER () AS total_registros,
            empleados.cdtipo_identificacion,
            empleados.cdidentificacion,
            insignias.dsnombre_asesor,
            empleados.dsclase_vinculacion,
            empleados.cdcargo,
            empleados.dscargo,
            empleados.fevinculacion,
            insignias.cdinsignia,
            insignias.dsnombre_insignia,
            insignias.feinicio_insignia,
            insignias.fefin_insignia
        FROM 
            esqidoneidadasesordll.tido_insignias insignias
        INNER JOIN 
            esqidoneidadasesordll.tido_empleados empleados
        ON 
            insignias.cdidentificacion = empleados.cdidentificacion
        WHERE 
            empleados.cdidentificacion IS NOT NULL;
        """;

    public static final String QUERY_CONSULTAR_INSIGNIAS_EN_LEGALES = """
        SELECT 
            COUNT(*) OVER () AS total_registros,
            legales.cdtipo_identificacion,
            legales.dnirepresentante_legal,
            insignias.dsnombre_asesor,
            legales.dsclase_vinculacion,
            legales.dnicompania,
            legales.dsnombre_compania,
            legales.fevinculacion,
            insignias.cdinsignia,
            insignias.dsnombre_insignia,
            insignias.feinicio_insignia,
            insignias.fefin_insignia
        FROM 
            esqidoneidadasesordll.tido_insignias insignias
        INNER JOIN 
            esqidoneidadasesordll.tido_representantes_legales legales
        ON 
            insignias.cdidentificacion = legales.dnirepresentante_legal
        WHERE 
            legales.dnirepresentante_legal IS NOT NULL;
        """;

    public static final String QUERY_CONSULTAR_ASESORES_INSIGNIAS_EN_LEGALES = """
        SELECT 
            COUNT(*) OVER () AS total_registros,
            trl.cdtipo_identificacion,
            trl.dnirepresentante_legal,
            insignias.dsnombre_asesor,
            trl.dsclase_vinculacion,
            insignias.cdcargo,
            insignias.dscargo,
            trl.fevinculacion,
            insignias.cdinsignia,
            insignias.dsnombre_insignia,
            insignias.feinicio_insignia,
            insignias.fefin_insignia
        FROM 
            esqidoneidadasesordll.tido_asesores_insignia insignias
        INNER JOIN 
            esqidoneidadasesordll.tido_representantes_legales trl 
        ON 
            insignias.cdidentificacion = trl.dnirepresentante_legal 
        WHERE 
            trl.dnirepresentante_legal IS NOT NULL;
        """;

    public static final String QUERY_CONSULTAR_INSIGNIAS_EN_DELEGADOS = """
        SELECT 
            i.*, 
            td.*, 
            trl.*, 
            COUNT(*) OVER () AS total_registros
        FROM 
            esqidoneidadasesordll.tido_insignias i 
        INNER JOIN 
            esqidoneidadasesordll.tido_delegados td ON i.cdidentificacion = td.cdidentificacion
        INNER JOIN 
            esqidoneidadasesordll.tido_representantes_legales trl ON td.dnicompania = trl.dnicompania
        WHERE 
            trl.dsclase_vinculacion = 'Agencia';
        """;

    public static final String QUERY_CONSULTAR_ASESORES_INSIGNIAS_EN_DELEGADOS = """
        SELECT 
            tai.*, 
            td.*, 
            trl.*, 
            COUNT(*) OVER () AS total_registros
        FROM 
            esqidoneidadasesordll.tido_asesores_insignia tai 
        INNER JOIN 
            esqidoneidadasesordll.tido_delegados td ON tai.cdidentificacion = td.cdidentificacion
        INNER JOIN 
            esqidoneidadasesordll.tido_representantes_legales trl ON td.dnicompania = trl.dnicompania
        WHERE 
            trl.dsclase_vinculacion = 'Agencia';
        """;

    public static final String QUERY_CONSULTAR_ASESORES_INSIGNIAS_EN_ASESORES = """
        SELECT 
            COUNT(*) OVER () AS total_registros,
            asesores.cdtipo_identificacion,
            asesores.cdidentificacion,
            insignias.dsnombre_asesor,
            asesores.dsclase_vinculacion,
            asesores.cdcargo,
            asesores.dscargo,
            asesores.fevinculacion,
            insignias.cdinsignia,
            insignias.dsnombre_insignia,
            insignias.feinicio_insignia,
            insignias.fefin_insignia
        FROM 
            esqidoneidadasesordll.tido_asesores_insignia insignias
        INNER JOIN 
            esqidoneidadasesordll.tido_asesores asesores ON insignias.cdidentificacion = asesores.cdidentificacion
        WHERE 
            asesores.cdidentificacion IS NOT NULL;
        """;

    public static final String QUERY_CONSULTAR_ASESORES_INSIGNIAS_EN_EMPLEADOS = """
        SELECT 
            COUNT(*) OVER () AS total_registros,
            te.cdtipo_identificacion,
            te.cdidentificacion,
            insignias.dsnombre_asesor,
            te.dsclase_vinculacion,
            te.cdcargo,
            te.dscargo,
            te.fevinculacion,
            insignias.cdinsignia,
            insignias.dsnombre_insignia,
            insignias.feinicio_insignia,
            insignias.fefin_insignia
        FROM 
            esqidoneidadasesordll.tido_asesores_insignia insignias
        INNER JOIN 
            esqidoneidadasesordll.tido_empleados te ON insignias.cdidentificacion = te.cdidentificacion
        WHERE 
            te.cdidentificacion IS NOT NULL;
        """;

    public static final String QUERY_CONSULTAR_ASESORES_EN_NOVEDADES_ODIONEAS = """
        SELECT 
            a.cdidentificacion, 
            a.dsramos_autorizados,
            b.*,
            COUNT(*) OVER () AS total_registros
        FROM 
            esqidoneidadasesordll.tido_novedades_eliminacion a 
        INNER JOIN 
            esqidoneidadasesordll.tido_asesores b ON a.cdidentificacion = b.cdidentificacion
        WHERE 
            b.dsclase_vinculacion = '%s';
        """;

    public static final String QUERY_CONSULTAR_EMPLEADOS_EN_NOVEDADES_ODIONEAS = """
        SELECT 
            a.cdidentificacion, 
            a.dsramos_autorizados,
            b.*,
            COUNT(*) OVER () AS total_registros
        FROM 
            esqidoneidadasesordll.tido_novedades_eliminacion a 
        INNER JOIN 
            esqidoneidadasesordll.tido_empleados b ON a.cdidentificacion = b.cdidentificacion
        WHERE 
            b.dsclase_vinculacion = '%s';
        """;

    public static final String QUERY_CONSULTAR_REPRESENTATES_LEGALES_EN_NOVEDADES_ODIONEAS = """
        SELECT 
            a.cdidentificacion,
            b.*,
            COUNT(*) OVER () AS total_registros
        FROM 
            esqidoneidadasesordll.tido_novedades_eliminacion a 
        INNER JOIN 
            esqidoneidadasesordll.tido_representantes_legales b 
        ON 
            a.cdidentificacion = b.dnirepresentante_legal;
        """;

    public static final String QUERY_CONSULTAR_NOMBRE_DE_COLUMNAS_REPORTE_FASECOLDA = """
        SELECT
            column_name,
            (SELECT COUNT(*)
             FROM (
                 SELECT column_name
                 FROM information_schema.columns
                 WHERE table_schema = 'esqidoneidadasesordll'
                   AND table_name = 'tido_asesores_idoneidad'
                   AND column_name IN ('cdentidad',
                                       'cdtipo_identificacion',
                                       'cdidentificacion',
                                       'dsnombre_asesor',
                                       'cdestado_autorizacion',
                                       'cdtipo_intermediario',
                                       'cdindicador_vinculado',
                                       'cdtipo_identificacion_compania',
                                       'dnicompania',
                                       'dsnombre_compania',
                                       'fevinculacion',
                                       'fedesvinculacion',
                                       'dsorganismo_acreditador',
                                       'feinicio_insignia',
                                       'fefin_insignia',
                                       'cdtipo_novedad',
                                       'dsramos_autorizados')
             ) AS subquery) AS total_columns,
            CASE column_name
                WHEN 'cdentidad' THEN 'CODIGO_ENTIDAD'
                WHEN 'cdtipo_identificacion' THEN 'TIPO_IDENTIFICACION'
                WHEN 'cdidentificacion' THEN 'NUMERO_IDENTIFICACION'
                WHEN 'dsnombre_asesor' THEN 'NOMBRE'
                WHEN 'cdestado_autorizacion' THEN 'ESTADO_AUTORIZACION'
                WHEN 'cdtipo_intermediario' THEN 'TIPO_INTERMEDIARIO'
                WHEN 'cdindicador_vinculado' THEN 'INDICADOR_VINCULADO'
                WHEN 'cdtipo_identificacion_compania' THEN 'TIP_DOC_ENTIDAD_VINCULADA'
                WHEN 'dnicompania' THEN 'NUM_DOC_ENTIDAD_VINCULADA'
                WHEN 'dsnombre_compania' THEN 'NOMBRE_ENTIDAD_VINCULADA'
                WHEN 'fevinculacion' THEN 'FECHA_VINCULACION'
                WHEN 'fedesvinculacion' THEN 'FECHA_DESVINCULACION'
                WHEN 'dsorganismo_acreditador' THEN 'ORGANISMO_ACREDITA'
                WHEN 'feinicio_insignia' THEN 'FECHA_INICIO_ACREDITACION'
                WHEN 'fefin_insignia' THEN 'FECHA_FINAL_ACREDITACION'
                WHEN 'cdtipo_novedad' THEN 'TIPO_NOVEDAD'
                WHEN 'dsramos_autorizados' THEN 'RAMOS_AUTORIZADOS'
            END AS nombre_personalizado
        FROM information_schema.columns
        WHERE table_schema = 'esqidoneidadasesordll'
          AND table_name = 'tido_asesores_idoneidad'
          AND column_name IN ('cdentidad',
                              'cdtipo_identificacion',
                              'cdidentificacion',
                              'dsnombre_asesor',
                              'cdestado_autorizacion',
                              'cdtipo_intermediario',
                              'cdindicador_vinculado',
                              'cdtipo_identificacion_compania',
                              'dnicompania',
                              'dsnombre_compania',
                              'fevinculacion',
                              'fedesvinculacion',
                              'dsorganismo_acreditador',
                              'feinicio_insignia',
                              'fefin_insignia',
                              'cdtipo_novedad',
                              'dsramos_autorizados');
        """;

    public static final String QUERY_CONSULTAR_TABLA_HISTORICA = """
            select cdentidad, cdtipo_identificacion, cdidentificacion, dsnombre_asesor,
                   cdestado_autorizacion, cdtipo_intermediario, cdindicador_vinculado,
                   cdtipo_identificacion_compania, dnicompania, dsnombre_compania, fevinculacion,
                   fedesvinculacion, dsorganismo_acreditador, feinicio_insignia, fefin_insignia,
                   cdtipo_novedad, dsramos_autorizados
                   FROM esqidoneidadasesordll.tido_asesores_idoneidad;
            """;

    public static final String QUERY_CONSULTAR_RAMOS = """
                    SELECT * FROM esqidoneidadasesordll.tido_ramos_insignias;
                    """;

    public static final String QUERY_CONSULTAR_ASESORES_INSIGNIA = """
            SELECT *,
                   COUNT(*) OVER () AS total_registros
                   FROM esqidoneidadasesordll.tido_asesores_insignia;
            """;

    public static final String QUERY_CONSULTAR_ASESORES_IDONEOS_ORACLE = """
     SELECT DNIUSUARIO, CDTIPO_IDON, CDEXCEPCION, DSCANAL, DSMOTIVO, DSOBSERV, DNIAUTORIZA,
     FEINGRESO, FEBAJA, CDINTERMEDIARIO,
     COUNT(*) OVER () AS total_registros
     FROM IDONEIDAD.TIDO_USUARIOSIDON
     """;

    public static final String QUERY_CONSULTAR_ASESORES_RAMOS_DEL_COTIZADOR = """
            SELECT
            usuarios.DNIUSUARIO,
            ramos.CDRAMO,
            USUARIOS.CDTIPO_IDON,
            COUNT(*) OVER () AS total_registros
            FROM IDONEIDAD.TIDO_USUARIOSIDON usuarios
            INNER JOIN
            IDONEIDAD.TIDO_USUARIOXRAMO ramos
            ON usuarios.DNIUSUARIO = ramos.DNIUSUARIO
     """;

}
