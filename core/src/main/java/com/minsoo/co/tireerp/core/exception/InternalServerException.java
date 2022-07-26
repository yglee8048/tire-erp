package com.minsoo.co.tireerp.core.exception;

import com.minsoo.co.tireerp.core.constant.SystemMessage;

public class InternalServerException extends RuntimeException {

    public InternalServerException() {
        super(SystemMessage.INTERNAL_SERVER_ERROR);
    }
}
