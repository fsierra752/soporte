package com.reto.utils.dbpostgress;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controlador {

    private static final Logger LOGGER = Logger.getLogger(Controlador.class);
    public List<String> obtenerColumnas(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numeroDeColumnas = metaData.getColumnCount();
            return extraerNombreDeColumnas(metaData, numeroDeColumnas);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    public JSONArray convertirResultSetaJSON(ResultSet resultSet, List<String> nombresColumnas) {
        JSONArray result = new JSONArray();
        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                nombresColumnas.forEach(cn -> {
                    try {
                        row.put(cn, resultSet.getObject(cn));
                    } catch (JSONException | SQLException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                });
                result.put(row);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage(), throwables);
        }
        return result;
    }

    private List<String> extraerNombreDeColumnas(ResultSetMetaData metaData, int numeroDeColumnas){
        List<String> test = new ArrayList<>();
        for(int i = 1; i <= numeroDeColumnas; i++){
            try {
                test.add(metaData.getColumnName(i));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
      return test;
    }
}
