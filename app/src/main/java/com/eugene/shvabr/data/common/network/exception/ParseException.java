package com.eugene.shvabr.data.common.network.exception;

import com.eugene.shvabr.domain.exception.ShvabrException;

/**
 * Исключение при разборе ответа от сервера.
 */
public class ParseException extends ShvabrException {
    public ParseException(Exception cause) {
        super(cause);
    }
}
