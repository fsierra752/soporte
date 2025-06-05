package com.retocache.utils.enums;

public enum TokenFile {
    JSON_FILE_TOKEN("src/test/resources/files/auth.json");

    private final String value;

    TokenFile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
