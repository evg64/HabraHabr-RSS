package com.eugene.shvabr.data.common.network.parser;

import java.io.InputStream;

/**
 * Интерфейс парсера.
 */
public interface Parser<To> {
    To parse(InputStream src) throws Exception;
}
