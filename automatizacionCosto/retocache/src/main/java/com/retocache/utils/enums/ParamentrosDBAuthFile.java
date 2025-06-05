package com.retocache.utils.enums;

public enum ParamentrosDBAuthFile {

    PARAMETROS_AUTH_FILE_PATH("src/test/resources/files/db/p8parametros.json");

    private final String value;

    ParamentrosDBAuthFile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
