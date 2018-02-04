package com.eugene.shvabr.data.network.exception;

import com.eugene.shvabr.domain.exception.ShvabrException;

import java.io.IOException;

/**
 * Created by Eugene on 03.02.2018.
 */
public class HttpException extends ShvabrException {
    public HttpException(IOException cause) {
        super(cause);
    }
}
