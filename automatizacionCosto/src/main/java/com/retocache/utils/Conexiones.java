package com.retocache.utils;

import java.sql.Connection;
import java.util.Map;

public interface Conexiones {

    Connection conectarse(Map<?, ?> info);
    void desconectarse(Connection conexion);

}




