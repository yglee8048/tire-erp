package com.minsoo.co.tireerp.core.exception;

import com.minsoo.co.tireerp.core.constant.SystemMessage;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super(SystemMessage.FORBIDDEN);
    }
}
