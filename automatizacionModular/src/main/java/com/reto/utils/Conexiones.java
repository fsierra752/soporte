package com.reto.utils;

import java.sql.Connection;
import java.util.Map;

public interface Conexiones {

    Connection conectarse(Map<?, ?> info);

    Connection conectarseOracle(Map<?, ?> info);

    void desconectarse(Connection conexion);
}
