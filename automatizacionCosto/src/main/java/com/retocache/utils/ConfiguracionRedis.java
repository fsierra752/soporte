package com.retocache.utils;

import com.retocache.exceptions.FileReadException;
import com.retocache.utils.enums.FileUtilities;
import redis.clients.jedis.Jedis;

import java.util.Map;

import static com.retocache.utils.enums.TokenFile.JSON_FILE_TOKEN;

public class ConfiguracionRedis {

    private static Jedis jedis;


    private ConfiguracionRedis() {
    }

    public static Jedis obtenerConexion() throws FileReadException {
        Map<String, String> auth = FileUtilities.leerArchivo(JSON_FILE_TOKEN.getValue());
        String host = auth.get("host");
        String password = auth.get("password");
        int port = 6380;

        if (jedis == null || !jedis.isConnected()) {
            jedis = new Jedis("rediss://" + host + ":" + port);
            jedis.auth(password);
        }
        return jedis;
    }

    public static void cerrarConexion(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
