package com.reto.utils.enums;

import lombok.Getter;

@Getter
public enum IdoneaBDAuthFileOracle {

    IDONEA_BD_DLLO_AUTH_FILE_ORACLE_PATH("src/test/resources/files/db/idonea_bd_dllo_auth_file_oracle.json");

    private final String value;

    IdoneaBDAuthFileOracle(String value) {
        this.value = value;
    }

}
