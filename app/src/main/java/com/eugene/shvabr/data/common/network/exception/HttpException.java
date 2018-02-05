package com.eugene.shvabr.data.common.network.exception;

import com.eugene.shvabr.domain.exception.ShvabrException;

import java.io.IOException;

/**
 * Исключение при выполнении http-запроса.
 */
public class HttpException extends ShvabrException {
    public HttpException(IOException cause) {
        super(cause);
    }
}
