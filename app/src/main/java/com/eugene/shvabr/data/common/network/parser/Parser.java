package com.eugene.shvabr.data.common.network.parser;

import java.io.InputStream;

/**
 * Created by Eugene on 03.02.2018.
 */

public interface Parser<To> {
    To parse(InputStream src) throws Exception;
}
