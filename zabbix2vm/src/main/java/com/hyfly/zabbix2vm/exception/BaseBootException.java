package com.hyfly.zabbix2vm.exception;

import java.io.Serial;

public class BaseBootException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BaseBootException(String message) {
        super(message);
    }

    public BaseBootException(Throwable cause) {
        super(cause);
    }

    public BaseBootException(String message, Throwable cause) {
        super(message, cause);
    }
}
