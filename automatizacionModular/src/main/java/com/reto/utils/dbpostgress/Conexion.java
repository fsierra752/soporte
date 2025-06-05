package com.reto.utils.dbpostgress;

import com.reto.utils.Conexiones;
import java.sql.Connection;
import org.apache.log4j.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Conexion implements Conexiones {

    private static final Logger LOGGER = Logger.getLogger(Conexion.class);

    @Override
    public Connection conectarse(Map<?, ?> info) {
        Connection conexionBaseDeDatosPostgress = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String url = String.format("jdbc:postgresql://%s/%s", info.get("host"), info.get("dataBase"));
            conexionBaseDeDatosPostgress = DriverManager.getConnection(
                    url,
                    (String) info.get("username"),
                    (String) info.get("password")
            );
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return conexionBaseDeDatosPostgress;
    }

    @Override
    public Connection conectarseOracle(Map<?, ?> info) {
        Connection conexionBaseDeDatosOracle = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            String url = String.format("jdbc:oracle:thin:@%s:%s:%s",
                    info.get("host"),
                    info.get("port"),
                    info.get("dataBase")
            );
            conexionBaseDeDatosOracle = DriverManager.getConnection(
                    url,
                    (String) info.get("username"),
                    (String) info.get("password")
            );
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return conexionBaseDeDatosOracle;
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
