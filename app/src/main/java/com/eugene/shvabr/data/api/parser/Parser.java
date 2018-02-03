package com.eugene.shvabr.data.api.parser;

import java.io.InputStream;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface Parser<To> {
    To parse(InputStream src) throws Exception;
}
