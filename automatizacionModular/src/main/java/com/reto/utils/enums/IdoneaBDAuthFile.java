package com.reto.utils.enums;

import lombok.Getter;

@Getter
public enum IdoneaBDAuthFile {

    IDONEA_BD_DLLO_AUTH_FILE_PATH("src/test/resources/files/db/idonea_bd_dllo_auth_file.json");

    private final String value;

    IdoneaBDAuthFile(String value) {
        this.value = value;
    }

}
