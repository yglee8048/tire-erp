package com.minsoo.co.tireerp.core.exception;

import com.minsoo.co.tireerp.core.constant.SystemMessage;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, Long id) {
        this(entity, String.valueOf(id));
    }

    public NotFoundException(String entity, String name) {
        super(SystemMessage.NOT_FOUND + String.format(": [%s %s]", entity, name));
    }
}
