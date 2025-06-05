package com.retocache.utils.postgres;

import com.retocache.utils.Conexiones;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Conexion implements Conexiones {

    private static final Logger LOGGER = Logger.getLogger(Conexion.class);
    @Override
    public Connection conectarse(Map<?, ?> info) {
        Connection conexionBaseDeDatos = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexionBaseDeDatos = DriverManager.getConnection((String) info.get("url"), (String) info.get("username"), (String) info.get("password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return conexionBaseDeDatos;
    }

    @Override
    public void desconectarse(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
