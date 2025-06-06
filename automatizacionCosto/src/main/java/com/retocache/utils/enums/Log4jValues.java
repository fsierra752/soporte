package com.retocache.utils.enums;

import lombok.Getter;

@Getter
public enum Log4jValues {

    LOG4J_PROPERTIES_FILE_PATH("src/test/resources/log4j.properties");

    private final String value;

    Log4jValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
