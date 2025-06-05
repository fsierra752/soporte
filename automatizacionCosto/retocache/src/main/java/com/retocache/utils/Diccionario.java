package com.retocache.utils;

public class Diccionario {

    private Diccionario() {
    }
    public static final String ACTOR_NAME = "Analista";
    public static final String QUERY_POSTGRES_PARAMETROS = "SELECT * FROM esqadministracionp8lab.tap8_parametros;";
    public static final String NOMBRE_DE_FILENET_PROPERTY = "propertyTemplateP8";
    public static final String NOMBRE_DE_FILENET_OBJETOS = "objectStore";
    public static final String NOMBRE_DE_FILENET_CLASES = "clasesObjectStore";
    public static final String NOMBRE_DE_FILENET_CAMPOS_DOCUMENTALES = "camposClaseDocumental";
    public static final String NOMBRE_DE_FILENET_LISTADOS = "listadosObjectStore";
    public static final String NOMBRE_DE_FILENET_VALORES_LISTADOS = "valoresListadoObjectStore";
    public static final String TIPO_HASH_LIST = "list";
    public static final String TIPO_HASH_HASH = "hash";
    public static final String ERROR_DUPLA_NO_EXISTE = "ERROR ValidarParametros: La dupla dsAplicacion = '%s' y dsParametro = '%s' no existe en Redis.";
    public static final String ERROR_CAMPO_NULO = "ERROR ValidarParametros: La dupla dsAplicacion = '%s' y dsParametro = '%s' existe en Redis, pero el campo '%s' tiene un valor nulo.";
    public static final String ERROR_VALORES_NO_COINCIDEN = "ERROR ValidarParametros: La dupla dsAplicacion = '%s' y dsParametro = '%s' existe en Redis, pero el valor de '%s' no coincide.";
    public static final String POSTGRES = "Postgres: '%s'";
    public static final String REDIS = "Redis: '%s'";
    public static final String VALIDACION_EXITOSA = "Validación completada: todos los parámetros coinciden.";
    public static final String VALIDACION_FALLIDA = "Algunas validaciones fallaron. Revisa los mensajes de error.";
    public static final String INICIANDO_VALIDACION = "Iniciando validación de parámetros entre Postgres y Redis.";
    public static final String LISTA_POSTGRES_VACIA = "La lista de parámetros desde Postgres está vacía.";
    public static final String MAPA_REDIS_VACIO = "El mapa de parámetros desde Redis está vacío.";
}