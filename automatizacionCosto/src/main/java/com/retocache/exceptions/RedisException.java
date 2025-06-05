package com.retocache.exceptions;

public class RedisException extends RuntimeException {
    public RedisException(String message) {
        super(message);
    }
}
