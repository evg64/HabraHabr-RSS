package com.eugene.shvabr.domain.exception;

/**
 * Будем оборачивать известные ошибки в свои классы исключений, чтобы на уровне UI
 * показывать более информативные сообщения.
 */
public class ShvabrException extends Exception {
    public ShvabrException() {
    }

    public ShvabrException(String message) {
        super(message);
    }

    public ShvabrException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShvabrException(Throwable cause) {
        super(cause);
    }
}
