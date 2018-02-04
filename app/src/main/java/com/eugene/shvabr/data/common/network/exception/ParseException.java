package com.eugene.shvabr.data.common.network.exception;

import com.eugene.shvabr.domain.exception.ShvabrException;

/**
 * Created by Eugene on 03.02.2018.
 */
public class ParseException extends ShvabrException {
    public ParseException(Exception cause) {
        super(cause);
    }
}
